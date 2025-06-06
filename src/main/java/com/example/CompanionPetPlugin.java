package com.example;

import com.example.dialog.DialogNode;
import com.example.dialog.FakeDialogManager;
import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldArea;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.*;
import net.runelite.api.geometry.SimplePolygon;
import net.runelite.api.model.Jarvis;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.callback.Hooks;
import net.runelite.client.chat.ChatCommandManager;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.game.ItemManager;
import net.runelite.client.game.chatbox.ChatboxPanelManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.ImageUtil;

import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.PetObjectModel.radToJau;
import static net.runelite.api.Perspective.COSINE;
import static net.runelite.api.Perspective.SINE;

@Slf4j
@PluginDescriptor(
	name = "Companion Pet Plugin",
	description = "Spawn any pet as a Follower or to act as a Thrall.",
	tags = {"pet","companion pet","fake","pvm","fake follower","Thrall"}
)
public class CompanionPetPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private EventBus eventBus;

	@Inject
	private CompanionPetConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private CompanionPetOverlay overlayPet;

	@Inject
	private FakeDialogManager fakeDialogManager;

	@Inject
	private ChatboxPanelManager chatboxPanelManager;

	@Inject
	private ClientThread clientThread;

	@Inject
	private ConfigManager configManager;

	@Inject
	private ClientToolbar clientToolbar;

	@Inject
	private ItemManager itemManager;

	@Inject
	private ChatMessageManager chatMessageManager;

	@Inject
	private Hooks hooks;

	@Inject
	private ChatCommandManager chatCommandManager;

	@Override
	protected void startUp() throws Exception
	{
		buildSidePanel();
		overlayManager.add(overlayPet);
		eventBus.register(fakeDialogManager);
		hooks.registerRenderableDrawListener(drawListener);
		chatCommandManager.registerCommand("!mochi",this::setPetMochi);

	}


	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlayPet);
		eventBus.unregister(fakeDialogManager);
		clientToolbar.removeNavigation(navButton);
		hooks.unregisterRenderableDrawListener(drawListener);
		panel.removeAll();

		clientThread.invokeLater(this::despawnAnyActivePOMs);
		chatCommandManager.unregisterCommand("!mochi");


	}



	@Provides
	CompanionPetConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(CompanionPetConfig.class);
	}

	private int lastActorOrientation;

	private boolean dialogOpen;
	private boolean petFollowing = false;
	private boolean petEnterHouse;

	public PetObjectModel pet = new PetObjectModel();

	public PetData petData;

	private WorldPoint lastPlayerWP;
	private WorldPoint lastActorWP;

	public WorldArea nextTravellingPoint;
	public WorldArea petWorldArea = null;

	private Model petModel;

	private final List<WorldPoint> prevPlayerWPs = new ArrayList<>();

	private final HashMap<NPC,PetObjectModel> activeThralls = new HashMap<>();

	public SimplePolygon petPoly;

	private CompanionPetPanel panel;

	private NavigationButton navButton;

	private final static String CONFIG_GROUP = "CompanionPetPlugin";

	private final static List<Integer> MELEE_THRALLS = Arrays.asList(10886,10885,10884);
	private final static List<Integer> RANGE_THRALLS = Arrays.asList(10883,10882,10881);
	private final static List<Integer> MAGE_THRALLS = Arrays.asList(10880,10879,10878);
	private final static List<Integer> ALL_THRALL_IDS = Arrays.asList(10886,10885,10884,10883,10882,10881,10880,10879,10878);

	private final Hooks.RenderableDrawListener drawListener = this::shouldDraw;


	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (event.getKey().equals("pet"))
		{
			petData = PetData.pets.get(config.pet().getIdentifier());
			clientThread.invokeLater(()-> updatePet());
		}

		if (event.getKey().equals("companionThralls") && !config.companionThralls())
		{
			for (PetObjectModel thrallObjectModel : activeThralls.values())
			{
				clientThread.invokeLater(thrallObjectModel::despawn);
			}
		}
		else if (event.getKey().equals("companionThralls"))
		{
			for (Map.Entry<NPC,PetObjectModel> entry : activeThralls.entrySet())
			{
				NPC npc = entry.getKey();
				PetObjectModel thrallObjectModel = entry.getValue();

				clientThread.invokeLater(()->
				{
					thrallObjectModel.spawn(npc.getWorldLocation(),npc.getOrientation(),thrallObjectModel.getSize());
					thrallObjectModel.setAnimation(thrallObjectModel.animationPoses[0]);

				});
			}
		}

		if (event.getKey().equals("allowBrokenPets") && config.allowBrokenPets() && !config.debug())
		{
			chatMessageManager.queue(QueuedMessage.builder().type(ChatMessageType.GAMEMESSAGE).runeLiteFormattedMessage("--------------------------------------------------------------------------------").build());
			chatMessageManager.queue(QueuedMessage.builder().type(ChatMessageType.GAMEMESSAGE).runeLiteFormattedMessage("<col=ff0000>[Companion Pets] Warning<col=000000>:<col=ffff00>").build());
			chatMessageManager.queue(QueuedMessage.builder().type(ChatMessageType.GAMEMESSAGE).runeLiteFormattedMessage("<col=000000>You have enabled the broken pets. These pets will not scale correctly and are awaiting a fix from RL.<col=ffff00>").build());
			chatMessageManager.queue(QueuedMessage.builder().type(ChatMessageType.GAMEMESSAGE).runeLiteFormattedMessage("--------------------------------------------------------------------------------").build());
		}




	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{

		if (gameStateChanged.getGameState() == GameState.LOGGING_IN || gameStateChanged.getGameState() == GameState.HOPPING)
		{
			activeThralls.clear();
		}

		if (gameStateChanged.getGameState() == GameState.LOADING)
		{
			if (pet.getRlObject() != null && pet.isActive())
			{
				lastPlayerWP = client.getLocalPlayer().getWorldLocation();
				lastActorWP = pet.getWorldLocation();
				lastActorOrientation = pet.getOrientation();

				pet.despawn();
			}


			for (PetObjectModel thrallObjectModel : activeThralls.values())
			{
				thrallObjectModel.despawn();
			}


		}


		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			if (pet.getRlObject() != null && petFollowing)
			{
				WorldPoint wp = client.getLocalPlayer().getWorldLocation();
				WorldPoint aWP = pet.getWorldLocation();

				double intx = aWP.toWorldArea().getX() - wp.toWorldArea().getX();
				double inty = aWP.toWorldArea().getY() - wp.toWorldArea().getY();


				if (lastPlayerWP.distanceTo(client.getLocalPlayer().getWorldLocation()) < 5)
				{
					pet.spawn(lastActorWP,lastActorOrientation,petData.getSize());
					pet.setAnimation(pet.animationPoses[0]);
				}
				else
				{
					pet.spawn(client.getLocalPlayer().getWorldLocation(),radToJau(Math.atan2(intx,inty)),petData.getSize());
					pet.setAnimation(pet.animationPoses[0]);
				}

				nextTravellingPoint = pet.getWorldLocation().toWorldArea();
			}


			for (Map.Entry<NPC,PetObjectModel> entry : activeThralls.entrySet())
			{
				PetObjectModel thrallObjectModel = entry.getValue();
				NPC npc = entry.getKey();

				thrallObjectModel.spawn(npc.getWorldLocation(),npc.getOrientation(),petData.getSize());
				thrallObjectModel.setAnimation(thrallObjectModel.animationPoses[0]);

			}


		}

	}



	@Subscribe
	public void onChatMessage(ChatMessage event)
	{

		String message = event.getMessage();

		if (message.equals("You do not have a follower.") && event.getType() == ChatMessageType.GAMEMESSAGE && !config.disableWhistle())
		{
			callPet(event);
		}

	}


	@Subscribe
	public void onVarbitChanged(VarbitChanged varbitChanged)
	{

		if (varbitChanged.getVarbitId() == 6719 && varbitChanged.getValue() == 0)
		{
			petEnterHouse = petFollowing;
		}

	}





	@Subscribe
	public void onGameTick(GameTick event)
	{

		WorldPoint playerDelayedLoc = getAndUpdatePlayersDelayedLoc();

		spawnPetInHouse();

		if (pet.getRlObject() != null && pet.isActive())
		{
			Player player = client.getLocalPlayer();
			double intx = pet.getLocalLocation().getX() - player.getLocalLocation().getX();
			double inty = pet.getLocalLocation().getY() - player.getLocalLocation().getY();

			if (nextTravellingPoint != null)
			{
				petWorldArea = nextTravellingPoint;
				petWorldArea = new WorldArea(nextTravellingPoint.toWorldPoint(),petData.getSize(),petData.getSize());
			}

			WorldArea worldArea = new WorldArea(playerDelayedLoc,1,1);

			nextTravellingPoint = PathingLogic.calculateNextTravellingPoint(client,petWorldArea,worldArea,true, this::extraBlockageCheck);

			//allow pets to run / need to update so it wont start walking when it gets close on arrival to the player
			if (petData == PetData.NEXLING && nextTravellingPoint != null)
			{
				nextTravellingPoint = PathingLogic.calculateNextTravellingPoint(client,nextTravellingPoint,worldArea,true, this::extraBlockageCheck);
			}

			if (nextTravellingPoint == null)
			{
				nextTravellingPoint = new WorldArea(getPathOutWorldPoint(petWorldArea),petData.getSize(),petData.getSize());
			}

			if (pet.isActive() && nextTravellingPoint != null)
			{
				pet.moveTo(nextTravellingPoint.toWorldPoint(), radToJau(Math.atan2(intx,inty)),petData.getSize());
			}

			if (pet.getWorldLocation().distanceTo(client.getLocalPlayer().getWorldLocation()) > 16 && petFollowing)
			{
				callPet(null);
			}

		}


		for (Map.Entry<NPC,PetObjectModel> entry : activeThralls.entrySet())
		{
			PetObjectModel pet = entry.getValue();
			NPC npc = entry.getKey();

			if (pet.getRlObject() == null || !pet.isActive())
			{
				return;
			}

			WorldArea petWorldArea = new WorldArea(pet.getWorldLocation(),1,1);

			//try useing the thralls local point
			WorldArea nextTravellingPoint = PathingLogic.calculateNextTravellingPoint(client,petWorldArea,npc.getWorldArea(),false, this::extraBlockageCheck);

			if (pet.isActive() && nextTravellingPoint != null)
			{
				pet.moveTo(nextTravellingPoint.toWorldPoint(),npc.getCurrentOrientation(),1);
			}

		}


	}

	@Subscribe
	public void onNpcSpawned(NpcSpawned event)
	{
		int npcID = event.getNpc().getId();

		if (ALL_THRALL_IDS.contains(npcID))
		{
			PetObjectModel thrallObjectModel = new PetObjectModel();

			if (thrallObjectModel.getRlObject() == null || !thrallObjectModel.isActive())
			{
				PetData petData = PetData.pets.get(getThrallTypeData(npcID).getIdentifier());
				Model petModel = provideModel(petData);

				thrallObjectModel.init(client,petData);
				thrallObjectModel.setPoseAnimations(petData.getIdleAnim(),petData.getWalkAnim(),petData.getRunAnim());
				thrallObjectModel.setModel(petModel);
				thrallObjectModel.getRlObject().setDrawFrontTilesFirst(true);
			}

			if (config.companionThralls())
			{
				thrallObjectModel.spawn(event.getNpc().getWorldLocation(),event.getNpc().getOrientation(),1);
				thrallObjectModel.setAnimation(thrallObjectModel.animationPoses[0]); //0 == walk
			}


			activeThralls.putIfAbsent(event.getNpc(),thrallObjectModel);
		}

	}

	@Subscribe
	public void onNpcDespawned(NpcDespawned event)
	{

		if (activeThralls.containsKey(event.getNpc()))
		{
			activeThralls.get(event.getNpc()).despawn();
			activeThralls.remove(event.getNpc());
		}

	}


	//magic thrall and other thrall anims dont register becuse their first attk can come out as a neg 1 anim, use projectile instead
	@Subscribe
	public void onAnimationChanged(AnimationChanged event)
	{   //3847 for thermy 7126 for sire
		if (event.getActor() instanceof NPC)
		{
			NPC npc = (NPC) event.getActor();

			if (activeThralls.containsKey(npc) && npc.getAnimation() != -1 && activeThralls.get(npc).isActive())
			{
				PetObjectModel thrallPet = activeThralls.get(npc);

				if (thrallPet.getPetAttkAnim() != -1)
				{
					thrallPet.setAnimation(client.loadAnimation(thrallPet.getPetAttkAnim()));
				}
			}


		}



	}



	@Subscribe
	public void onClientTick(ClientTick event)
	{

		//skelly attk = 21 frames == +55 for start
		//melee attk = 21 frames
		//magic attk = 18 frames
		//basic magic attack anim 711


		if (pet.getRlObject() != null && pet.animationPoses != null)
		{

			LocalPoint lp = pet.getLocalLocation();
			int zOff = Perspective.getTileHeight(client,lp,client.getPlane());

			petPoly = calculateAABB(client, pet.getRlObject().getModel(), pet.getOrientation(), pet.getLocalLocation().getX(), pet.getLocalLocation().getY(),client.getPlane(), zOff);

			double intx = pet.getRlObject().getLocation().getX() - client.getLocalPlayer().getLocalLocation().getX();
			double inty = pet.getRlObject().getLocation().getY() - client.getLocalPlayer().getLocalLocation().getY();

			pet.onClientTick(event,radToJau(Math.atan2(intx,inty)));

			//for 2x2 set drawFontTilesFirst only when they are moveing / test vs walls in house and rimmy
			if (petData.getSize() == 2 && pet.getRlObject().getAnimation() == pet.animationPoses[1])
			{
				pet.getRlObject().setDrawFrontTilesFirst(true);
			}
			else if (pet.getRlObject().getAnimation() == pet.animationPoses[0] && pet.getRlObject().isDrawFrontTilesFirst())
			{
				pet.getRlObject().setDrawFrontTilesFirst(false);
			}

		}




		for (Map.Entry<NPC,PetObjectModel> entry : activeThralls.entrySet())
		{
			PetObjectModel thrallOM = entry.getValue();
			NPC thrall = entry.getKey();

			//System.out.println(thrallOM.getRlObject().getAnimationFrame());


			//18 is the end frame of the animation for abyssal orpahn //10 for thermy //9 zulrah
			//1741 zulrah anim
			if (thrallOM.getRlObject().getAnimation() != null
				&& thrallOM.getPetAttkAnim() != -1
				&& thrallOM.getRlObject().getAnimation().getId() == thrallOM.getPetAttkAnim()
				&& thrallOM.getRlObject().getAnimationFrame() == thrallOM.getPetAttkAnimFrames())
			{
				if (thrallOM.targetQueueSize == 0 && thrallOM.getRlObject().getAnimation() != thrallOM.animationPoses[0])
				{
					thrallOM.setAnimation(thrallOM.animationPoses[0]);
				}
				else
				{
					thrallOM.setAnimation(thrallOM.animationPoses[1]);
				}
			}

			thrallOM.onClientTick(event,thrall.getOrientation());

		}


	}

	@Subscribe
	public void onProjectileMoved(ProjectileMoved event)
	{
		if (event.getProjectile().getStartCycle() == client.getGameCycle())
		{
			//x1 and y1 represet the projectiles cords when it is first spawned
			LocalPoint point = new LocalPoint(event.getProjectile().getX1(),event.getProjectile().getY1());

			for (Map.Entry<NPC,PetObjectModel> entry : activeThralls.entrySet())
			{
				NPC npc = entry.getKey();
				PetObjectModel thrall = entry.getValue();

				if (npc.getLocalLocation().equals(point) && npc.getAnimation() == -1 && thrall.getPetAttkAnim() != -1)
				{
					thrall.setAnimation(client.loadAnimation(thrall.getPetAttkAnim()));
				}


			}


		}







	}

	@Subscribe
	public void onMenuOpened(MenuOpened event)
	{

		if (pet.getRlObject() == null || !pet.isActive())
		{
			return;
		}

		int firstMenuIndex = 0;

		for (int i = 0; i < client.getMenuEntries().length; i++)
		{
			if (client.getMenuEntries()[i].getOption().equals("Cancel"))
			{
				firstMenuIndex = i + 1;
				break;
			}
		}


		List<String> options = Arrays.asList("Talk-to","Pick-up","Examine");

		if (petData.isMetamorph())
		{
			options = Arrays.asList("Talk-to","Pick-up","Metamorphosis","Examine");
		}


		for (String string : options)
		{
			if (petPoly.contains(client.getMouseCanvasPosition().getX(),client.getMouseCanvasPosition().getY()))
			{
				client.createMenuEntry(firstMenuIndex)
						.setOption(string)
						.setTarget("<col=ffff00>" + petData.getName() + "</col>")
						.setType(MenuAction.RUNELITE)
						.setParam0(0)
						.setParam1(0)
						.setDeprioritized(true);

			}
		}

	}



	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event)
	{


		if (petData == null)
		{
			return;
		}


		if (event.getMenuEntry().getType() == MenuAction.RUNELITE && event.getMenuTarget().contains(petData.getName()) && event.getMenuOption().equals("Pick-up"))
		{
			if (pet.isActive())
			{
				petFollowing = false;
				pet.despawn();
			}
		}

		if (event.getMenuEntry().getType() == MenuAction.RUNELITE && event.getMenuTarget().contains(petData.getName()) && event.getMenuOption().equals("Examine"))
		{
			if (pet.isActive())
			{
				client.addChatMessage(ChatMessageType.GAMEMESSAGE,"",petData.getExamine(),"",false);
			}
		}


		if (event.getMenuEntry().getType() == MenuAction.RUNELITE && event.getMenuTarget().contains(petData.getName()) && event.getMenuOption().equals("Metamorphosis"))
		{
			if (pet.isActive())
			{
				if (!PetData.morphModel.get(petData).isWorking() && !config.allowBrokenPets())
				{
					chatMessageManager.queue(QueuedMessage.builder().type(ChatMessageType.GAMEMESSAGE).runeLiteFormattedMessage("<col=000000>Sadly the transmog for this pet is awaiting a RL fix to work correctly<col=ffff00>").build());
					return;
				}

				petData = PetData.morphModel.get(petData);
				updatePet();
			}
		}

		if (event.getMenuEntry().getType() == MenuAction.RUNELITE && event.getMenuTarget().contains(petData.getName()) && event.getMenuOption().equals("Talk-to"))
		{

			if (pet.isActive() && pet.getWorldArea().isInMeleeDistance(client.getLocalPlayer().getWorldArea()))
			{
				dialogOpen = true;
				fakeDialogManager.open(provideDialog());
			}

		}

		if (!event.getMenuTarget().contains(petData.getName()) && !event.getMenuOption().equals("Continue") && dialogOpen)
		{
			dialogOpen = false;
			chatboxPanelManager.close();
		}



	}



	//Hides the runelite obj to give other renderables like players prio
	private boolean shouldDraw(Renderable renderable, boolean drawingUI)
	{
		if (renderable instanceof RuneLiteObject)
		{
			RuneLiteObject rlObject = (RuneLiteObject) renderable;
			if (pet.getRlObject() != null && pet.getRlObject().equals(rlObject))
			{
				List<LocalPoint> localPoints = new ArrayList<>();
				List<Player> nonLocalPlayers = client.getPlayers().stream().filter(player -> !Objects.equals(player.getName(), client.getLocalPlayer().getName())).collect(Collectors.toList());
				nonLocalPlayers.forEach(player -> localPoints.add(player.getLocalLocation()));
				client.getNpcs().forEach(npc -> localPoints.add(npc.getLocalLocation()));

				boolean overlappingModel = localPoints.stream().anyMatch(localPoint -> localPoint.equals(pet.getLocalLocation()));
				return !overlappingModel;
			}


			for (PetObjectModel thrallOM : activeThralls.values())
			{
				if (thrallOM.getRlObject().equals(rlObject))
				{
					List<LocalPoint> localPoints = new ArrayList<>();
					client.getPlayers().forEach(player -> localPoints.add(player.getLocalLocation()));

					client.getNpcs().stream().filter(npc -> !ALL_THRALL_IDS.contains(npc.getId())).forEach(npc -> localPoints.add(npc.getLocalLocation()));

					boolean overlappingModel = localPoints.stream().anyMatch(localPoint -> localPoint.equals(thrallOM.getLocalLocation()));

					return !overlappingModel;
				}


			}

		}


		if (renderable instanceof NPC && config.companionThralls())
		{
			NPC npc = (NPC) renderable;
			return !ALL_THRALL_IDS.contains(npc.getId());
		}

		return true;
	}

	private PetData getThrallTypeData(int npcID)
	{
		if (MELEE_THRALLS.contains(npcID))
		{
			return config.meleeThrall();
		}

		if (RANGE_THRALLS.contains(npcID))
		{
			return config.rangeThrall();
		}

		if (MAGE_THRALLS.contains(npcID))
		{
			return config.mageThrall();
		}

		return null;
	}

	private void despawnAnyActivePOMs()
	{
		if (pet.getRlObject() != null && pet.isActive())
		{
			pet.despawn();
		}

		for (PetObjectModel pom : activeThralls.values())
		{
			pom.despawn();
		}

		activeThralls.clear();
	}

	private WorldPoint getAndUpdatePlayersDelayedLoc()
	{

		if (client.getLocalPlayer().getWorldLocation() == null)
		{
			return null;
		}


		WorldPoint worldPoint = null;

		prevPlayerWPs.add(0,client.getLocalPlayer().getWorldLocation());

		if (prevPlayerWPs.size() >= 2)
		{
			worldPoint = prevPlayerWPs.get(1);
		}

		if (worldPoint == null)
		{
			worldPoint = client.getLocalPlayer().getWorldLocation();
		}

		if (prevPlayerWPs.size() > 10)
		{
			prevPlayerWPs.subList(10, prevPlayerWPs.size()).clear();
		}

		return worldPoint;
	}

	private void spawnPetInHouse()
	{
		if (petEnterHouse && petFollowing)
		{
			petEnterHouse = false;
			WorldPoint wp = client.getLocalPlayer().getWorldLocation();
			WorldPoint aWP = pet.getWorldLocation();

			double intx = aWP.toWorldArea().getX() - wp.toWorldArea().getX();
			double inty = aWP.toWorldArea().getY() - wp.toWorldArea().getY();

			pet.spawn(client.getLocalPlayer().getWorldLocation(),radToJau(Math.atan2(intx,inty)),petData.getSize());
			pet.setAnimation(pet.animationPoses[0]);
			nextTravellingPoint = pet.getWorldLocation().toWorldArea();
		}

	}

	public WorldPoint getPathOutWorldPoint(WorldArea worldArea)
	{

		ArrayList<WorldPoint> points = new ArrayList<>();

		for (int i = -1; i < 2; i++)
		{
			if (i != 0)
			{
				if (worldArea.canTravelInDirection(client.getTopLevelWorldView(),i,0))
				{

					WorldPoint worldPoint = new WorldPoint(worldArea.getX() + i,worldArea.getY(),client.getPlane());

					boolean secondCheck = true;
					if (petData.getSize() == 2)
					{
						WorldArea area = new WorldArea(worldPoint,2,2);
						secondCheck = area.canTravelInDirection(client.getTopLevelWorldView(),i,0) ;
					}


					if (!worldPoint.equals(client.getLocalPlayer().getWorldLocation()) && secondCheck)
					{
						points.add(worldPoint);
					}

				}


				if (worldArea.canTravelInDirection(client.getTopLevelWorldView(),0,i))
				{
					WorldPoint worldPoint = new WorldPoint(worldArea.getX(),worldArea.getY() + i,client.getPlane());

					boolean secondCheck = true;
					if (petData.getSize() == 2)
					{
						WorldArea area = new WorldArea(worldPoint,2,2);
						secondCheck	= area.canTravelInDirection(client.getTopLevelWorldView(),0,i);
					}

					if (!worldPoint.equals(client.getLocalPlayer().getWorldLocation()) && secondCheck)
					{
						points.add(worldPoint);
					}
				}
			}
		}


		if (!points.isEmpty())
		{
			return points.get(getRandomInt(points.size() - 1,0));
		}

		return null;
	}

	private int getRandomInt(int max, int min)
	{
		return min + (int)(Math.random() * ((max - min) + 1));
	}


	private DialogNode provideDialog()
	{

		List<String> data = Arrays.stream(petData.getDryestPerson().split(":")).collect(Collectors.toList());

		String kcIdentifer = data.get(0);
		String name = data.get(1);
		String kc = data.get(2);
		String date = data.get(3);

		return DialogNode.builder()
				.player()
				.animationId(567)
				.body("Tell me something to make me feel better")
				.onContinue
						(() ->
								DialogNode.builder()
										.npc(petData.getNpcId())
										.title(petData.getName())
										.body("It took " + name +" "+ kc + " " + kcIdentifer +" but<br>" +
												"They finally got me on " + date)
										.animationId(petData.getChatHeadAnimID())
										.build()


						)
				.build();
	}



	//contrast * 5 + 850
	public Model provideModel(PetData petData)
	{
		ModelData[] modelDataArray = new ModelData[petData.getModelIDs().size()];
		for (int i = 0; i < petData.getModelIDs().size(); i++)
		{
			modelDataArray[i] = client.loadModelData(petData.getModelIDs().get(i));
		}

		ModelData modelData = createModel(client,modelDataArray);
		modelData.cloneVertices();

//		if (petData.getScale() != -1)
//		{
//			modelData.cloneVertices();
//			modelData.scale(petData.getScale(),petData.getScale(),petData.getScale());
//		}


		//cut list in half fist half color to find, second half color to replace
		if (petData.getRecolorIDs() !=  null)
		{
			modelData.cloneColors();
			int mid = (petData.getRecolorIDs().size() / 2);

			for (int i = 0; i < mid; i++)
			{
				modelData.recolor(petData.getRecolorIDs().get(i),petData.getRecolorIDs().get(mid + i));
			}

		}


		int ambient = (petData.getAmbient() != -1 ? petData.getAmbient() : 0);
		int contrast = (petData.getContrast() != -1 ? petData.getContrast() : 0);

		return modelData.light(ambient + 64, contrast + 850,-30,-50,-30);
	}


	private void buildSidePanel()
	{
		panel = injector.getInstance(CompanionPetPanel.class);
		panel.sidePanelInitializer();

		if (petData == null)
		{
			petData = PetData.pets.get(config.pet().getIdentifier());
		}

		BufferedImage icon = ImageUtil.loadImageResource(getClass(), "/nav_icon.png");
		navButton = NavigationButton.builder().tooltip("Companion Pet Plugin").icon(icon).priority(5).panel(panel).build();
		clientToolbar.addNavigation(navButton);
	}




	public void updatePet()
	{
		if (petData != null)
		{
			configManager.setConfiguration(CONFIG_GROUP,"pet",petData);
		}

		if (pet.getRlObject() == null)
		{
			pet.init(client,petData);
		}

		petModel = provideModel(petData);
		pet.setPoseAnimations(petData.getIdleAnim(),petData.getWalkAnim(),petData.getRunAnim());
		pet.setPetData(petData);

		if (client.getGameState() == GameState.LOGGED_IN && pet.isActive())
		{
			//set to 0 for 1x1 and != 90 for 2x2
			if (pet.getLocalLocation().distanceTo(LocalPoint.fromWorld(client,nextTravellingPoint.toWorldPoint())) > 0 && pet.getLocalLocation().distanceTo(LocalPoint.fromWorld(client,nextTravellingPoint.toWorldPoint())) != 90)
			{
				pet.setAnimation(pet.animationPoses[1]);
			}
			else
			{
				pet.setAnimation(pet.animationPoses[0]);
			}

		}

		pet.setModel(petModel);
		panel.updateCurrentPetIcon();
	}

	public void updatePet(PetData buttonPetData)
	{
		petData = buttonPetData;
		updatePet();
	}

	public boolean extraBlockageCheck(WorldPoint worldPoint)
	{
		if (petData.getSize() != 2)
		{
			return true;
		}

		WorldArea area = new WorldArea(worldPoint, 1, 1);

		List<WorldArea> worldAreas = new ArrayList<>();
		client.getPlayers().forEach(p -> worldAreas.add(p.getWorldArea()));
		client.getNpcs().forEach(npc -> worldAreas.add(npc.getWorldArea()));

		boolean overlappingModel = worldAreas.stream().anyMatch(wa -> wa.intersectsWith(area));

		return !overlappingModel;
	}


	private void callPet(ChatMessage event)
	{

		if ((pet.getRlObject() == null || !pet.isActive()))
		{
			petData = PetData.pets.get(config.pet().getIdentifier());
			petModel = provideModel(petData);

			pet.init(client,petData);
			pet.setPoseAnimations(petData.getIdleAnim(),petData.getWalkAnim(),petData.getRunAnim());
			pet.setModel(petModel);
			pet.getRlObject().setDrawFrontTilesFirst(true);
		}

		WorldPoint wp = client.getLocalPlayer().getWorldLocation();
		WorldPoint aWP = pet.getWorldLocation();

		boolean petHasLOS = wp.toWorldArea().hasLineOfSightTo(client.getTopLevelWorldView(),aWP);

		if (event != null && wp.toWorldArea().distanceTo(aWP.toWorldArea()) < 6 && petHasLOS && pet.isActive())
		{
			event.getMessageNode().setValue("Your follower is already close enough.");
			return;
		}
		else if (pet.isActive())
		{
			pet.despawn();
		}


		double intx = aWP.toWorldArea().getX() - wp.toWorldArea().getX();
		double inty = aWP.toWorldArea().getY() - wp.toWorldArea().getY();

		if (event != null)
		{
			event.getMessageNode().setValue("");
		}

		petFollowing = true;

		pet.spawn(getPathOutWorldPoint(new WorldArea(getAndUpdatePlayersDelayedLoc(),petData.getSize(),petData.getSize())),radToJau(Math.atan2(intx,inty)),petData.getSize());
		pet.setAnimation(pet.animationPoses[0]); //0 == walk
		nextTravellingPoint = pet.getWorldLocation().toWorldArea();
	}

	private static ModelData createModel(Client client, ModelData... data)
	{
		return client.mergeModels(data);
	}

	private static ModelData createModel(Client client, int... data)
	{
		ModelData[] modelData = new ModelData[data.length];
		for (int i = 0; i < data.length; i++)
		{
			modelData[i] = client.loadModelData(data[i]);
		}
		return client.mergeModels(modelData);
	}


	private static SimplePolygon calculateAABB(Client client, Model m, int jauOrient, int x, int y, int z, int zOff)
	{
		AABB aabb = m.getAABB(jauOrient);

		int x1 = aabb.getCenterX();
		int y1 = aabb.getCenterZ();
		int z1 = aabb.getCenterY() + zOff;

		int ex = aabb.getExtremeX();
		int ey = aabb.getExtremeZ();
		int ez = aabb.getExtremeY();

		int x2 = x1 + ex;
		int y2 = y1 + ey;
		int z2 = z1 + ez;

		x1 -= ex;
		y1 -= ey;
		z1 -= ez;

		int[] xa = new int[]{
				x1, x2, x1, x2,
				x1, x2, x1, x2
		};
		int[] ya = new int[]{
				y1, y1, y2, y2,
				y1, y1, y2, y2
		};
		int[] za = new int[]{
				z1, z1, z1, z1,
				z2, z2, z2, z2
		};

		int[] x2d = new int[8];
		int[] y2d = new int[8];

		modelToCanvasCpu(client, 8, x, y, z, 0, xa, ya, za, x2d, y2d);

		return Jarvis.convexHull(x2d, y2d);
	}

	private static void modelToCanvasCpu(Client client, int end, int x3dCenter, int y3dCenter, int z3dCenter, int rotate, int[] x3d, int[] y3d, int[] z3d, int[] x2d, int[] y2d)
	{
		final int
				cameraPitch = client.getCameraPitch(),
				cameraYaw = client.getCameraYaw(),

				pitchSin = SINE[cameraPitch],
				pitchCos = COSINE[cameraPitch],
				yawSin = SINE[cameraYaw],
				yawCos = COSINE[cameraYaw],
				rotateSin = SINE[rotate],
				rotateCos = COSINE[rotate],

				cx = x3dCenter - client.getCameraX(),
				cy = y3dCenter - client.getCameraY(),
				cz = z3dCenter - client.getCameraZ(),

				viewportXMiddle = client.getViewportWidth() / 2,
				viewportYMiddle = client.getViewportHeight() / 2,
				viewportXOffset = client.getViewportXOffset(),
				viewportYOffset = client.getViewportYOffset(),

				zoom3d = client.getScale();

		for (int i = 0; i < end; i++)
		{
			int x = x3d[i];
			int y = y3d[i];
			int z = z3d[i];

			if (rotate != 0)
			{
				int x0 = x;
				x = x0 * rotateCos + y * rotateSin >> 16;
				y = y * rotateCos - x0 * rotateSin >> 16;
			}

			x += cx;
			y += cy;
			z += cz;

			final int
					x1 = x * yawCos + y * yawSin >> 16,
					y1 = y * yawCos - x * yawSin >> 16,
					y2 = z * pitchCos - y1 * pitchSin >> 16,
					z1 = y1 * pitchCos + z * pitchSin >> 16;

			int viewX, viewY;

			if (z1 < 50)
			{
				viewX = Integer.MIN_VALUE;
				viewY = Integer.MIN_VALUE;
			}
			else
			{
				viewX = (viewportXMiddle + x1 * zoom3d / z1) + viewportXOffset;
				viewY = (viewportYMiddle + y2 * zoom3d / z1) + viewportYOffset;
			}

			x2d[i] = viewX;
			y2d[i] = viewY;
		}
	}

	private void setPetMochi(ChatMessage chatMessage, String s)
	{
		configManager.setConfiguration("CompanionPetPlugin","pet",PetData.MOCHI);
		callPet(chatMessage);
	}


}
