package com.example;

import com.example.NpcInfo.NpcDefinition;
import com.example.Dialog.FakeDialogManager;
import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.gameval.NpcID;
import net.runelite.api.gameval.AnimationID;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldArea;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.*;
import net.runelite.api.geometry.SimplePolygon;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.callback.RenderCallback;
import net.runelite.client.callback.RenderCallbackManager;
import net.runelite.client.chat.ChatCommandManager;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.game.chatbox.ChatboxPanelManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.ColorUtil;
import net.runelite.client.util.ImageUtil;

import javax.inject.Inject;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

import static com.example.PetData.pets;
import static com.example.PetObjectModel.radToJau;
import static com.example.Utilities.ModelUtility.*;
import static com.example.Utilities.RenderUtility.*;
import static com.example.Utilities.InputStreamUtility.*;

@Slf4j
@PluginDescriptor(
	name = "Companion Pet Plugin",
	description = "Spawn any pet as a Follower or to act as a Thrall.",
	tags = {"pet","companion pet","fake","pvm","fake follower","Thrall"}
)
public class CompanionPetPlugin extends Plugin
{
	@Inject private Client client;
	@Inject private EventBus eventBus;
	@Inject private CompanionPetConfig config;
	@Inject private OverlayManager overlayManager;
	@Inject private CompanionPetOverlay overlayPet;
	@Inject private FakeDialogManager fakeDialogManager;
	@Inject private ChatboxPanelManager chatboxPanelManager;
	@Inject private ClientThread clientThread;
	@Inject private ConfigManager configManager;
	@Inject private ClientToolbar clientToolbar;
	@Inject private ChatMessageManager chatMessageManager;
	@Inject private ChatCommandManager chatCommandManager;
	@Inject private RenderCallbackManager renderCallbackManager;

	private final RenderCallback drawListener = createDrawListener(this::shouldDraw);

	@Override
	protected void startUp() throws Exception
	{
		buildSidePanel();
		overlayManager.add(overlayPet);
		eventBus.register(fakeDialogManager);
		renderCallbackManager.register(drawListener);
		chatCommandManager.registerCommand("!mochi",this::setPetMochi);

		populatePetData();
		updateThrallIds();
	}



	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlayPet);
		eventBus.unregister(fakeDialogManager);
		clientToolbar.removeNavigation(navButton);
		renderCallbackManager.register(drawListener);
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

	private static final Set<Integer> ALL_THRALL_IDS = new HashSet<>();

	private static final Set<Integer> IMP_MELEE_THRALLS = Set.of(NpcID.THRALL_IMP_MELEE_LESSER, NpcID.THRALL_IMP_MELEE_SUPERIOR, NpcID.THRALL_IMP_MELEE_GREATER);
	private static final Set<Integer> IMP_RANGE_THRALLS = Set.of(NpcID.THRALL_IMP_RANGED_LESSER, NpcID.THRALL_IMP_RANGED_SUPERIOR, NpcID.THRALL_IMP_RANGED_GREATER);
	private static final Set<Integer> IMP_MAGE_THRALLS = Set.of(NpcID.THRALL_IMP_MAGIC_LESSER, NpcID.THRALL_IMP_MAGIC_SUPERIOR, NpcID.THRALL_IMP_MAGIC_GREATER);

	private static final Set<Integer> MELEE_THRALLS = new HashSet<>(Set.of(NpcID.ARCEUUS_THRALL_ZOMBIE_LESSER, NpcID.ARCEUUS_THRALL_ZOMBIE_SUPERIOR, NpcID.ARCEUUS_THRALL_ZOMBIE_GREATER));
	private static final Set<Integer> RANGE_THRALLS = new HashSet<>(Set.of(NpcID.ARCEUUS_THRALL_SKELETON_LESSER, NpcID.ARCEUUS_THRALL_SKELETON_SUPERIOR, NpcID.ARCEUUS_THRALL_SKELETON_GREATER));
	private static final Set<Integer> MAGE_THRALLS = new HashSet<>(Set.of(NpcID.ARCEUUS_THRALL_GHOST_LESSER, NpcID.ARCEUUS_THRALL_GHOST_SUPERIOR, NpcID.ARCEUUS_THRALL_GHOST_GREATER));

	private static final Set<Integer> THRALL_SPAWN_ANIMS =
	 Set.of(AnimationID.ZOMBIE_UPDATE_THRAWL_SPAWN_RAISED,
			AnimationID.SKELETON_UPDATE_THRALL_SPAWN_RAISED,
			AnimationID.GHOST_UPDATE_THRALL_SPAWN_RAISED,
			AnimationID.LEAGUES_6_THRALL_IMP_MAGIC_SPAWN,
			AnimationID.LEAGUES_6_THRALL_IMP_RANGED_SPAWN,
			AnimationID.LEAGUES_6_THRALL_IMP_MELEE_SPAWN);


	public static HashMap<Integer,Integer> ANIMATION_OFFSETS = new HashMap<>();

	private static final String[] PET_DEFAULT_OPTIONS = {"Talk-to", "Pick-up", "Examine"};
	private static final String[] PET_MORPH_OPTIONS = {"Talk-to", "Pick-up", "Metamorphosis", "Examine"};



	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (event.getKey().equals("pet"))
		{
			petData = pets.get(config.pet().toString());
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
					thrallObjectModel.setAnimation(thrallObjectModel.animationPoses[0]);
					thrallObjectModel.spawn(npc.getWorldLocation(),npc.getOrientation(),thrallObjectModel.getSize());
				});
			}
		}

		if (event.getKey().equals("impThralls"))
		{
			updateThrallIds();
		}

	}


	@Subscribe
	public void onWorldViewUnloaded(WorldViewUnloaded worldViewUnloaded)
	{
		WorldView wv = worldViewUnloaded.getWorldView();

		if (petActive() && isOwnWorldView(wv))
		{
			lastPlayerWP = client.getLocalPlayer().getWorldLocation();
			lastActorWP = pet.getWorldLocation();
			lastActorOrientation = pet.getOrientation();
			pet.despawn();
		}
	}

	public boolean isOwnWorldView(WorldView worldView)
	{
		return client.getLocalPlayer().getWorldView().equals(worldView);
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{

		if (gameStateChanged.getGameState() == GameState.LOGGING_IN || gameStateChanged.getGameState() == GameState.HOPPING)
		{
			activeThralls.clear();
		}

		if (gameStateChanged.getGameState() == GameState.LOADING || gameStateChanged.getGameState() == GameState.HOPPING)
		{
			if (petActive() && client.getTopLevelWorldView() != null)
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
				clientThread.invokeLater(()->
				{
					if (client.getLocalPlayer() == null)
						return false;

					WorldPoint wp = client.getLocalPlayer().getWorldLocation();
					WorldPoint aWP = pet.getWorldLocation();

					double intx = aWP.toWorldArea().getX() - wp.toWorldArea().getX();
					double inty = aWP.toWorldArea().getY() - wp.toWorldArea().getY();


					if (lastPlayerWP.distanceTo(client.getLocalPlayer().getWorldLocation()) < 5)
					{
						pet.setAnimation(pet.animationPoses[0]);
						pet.spawn(lastActorWP,lastActorOrientation,petData.getSize());
					}
					else
					{
						pet.setAnimation(pet.animationPoses[0]);
						pet.spawn(client.getLocalPlayer().getWorldLocation(),radToJau(Math.atan2(intx,inty)),petData.getSize());
					}

					nextTravellingPoint = pet.getWorldLocation().toWorldArea();

					return true;
				});

			}


			for (Map.Entry<NPC,PetObjectModel> entry : activeThralls.entrySet())
			{
				PetObjectModel thrallObjectModel = entry.getValue();
				NPC npc = entry.getKey();

				thrallObjectModel.setAnimation(thrallObjectModel.animationPoses[0]);
				thrallObjectModel.spawn(npc.getWorldLocation(),npc.getOrientation(),petData.getSize());

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

		if (petActive())
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
				PetData petData = pets.get(getThrallTypeData(npcID).toString());
				Model petModel = provideModel(client,petData);

				thrallObjectModel.init(client,petData);
				thrallObjectModel.setPoseAnimations(petData.getIdleAnim(),petData.getWalkAnim(),petData.getRunAnim());
				thrallObjectModel.setModel(petModel);
				thrallObjectModel.getRlObject().setDrawFrontTilesFirst(true);
			}

			if (config.companionThralls())
			{
				thrallObjectModel.setAnimation(thrallObjectModel.animationPoses[0]); //0 == walk
				thrallObjectModel.spawn(event.getNpc().getWorldLocation(),event.getNpc().getOrientation(),1);
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


	//magic thrall and other thrall anims dont register bc their first attk can come out as a neg 1 anim, use projectile instead
	@Subscribe
	public void onAnimationChanged(AnimationChanged event)
	{   //3847 for thermy 7126 for sire
		if (event.getActor() instanceof NPC)
		{
			NPC npc = (NPC) event.getActor();
			if (activeThralls.containsKey(npc) && !THRALL_SPAWN_ANIMS.contains(npc.getAnimation()) && npc.getAnimation() != -1 && activeThralls.get(npc).isActive())
			{
				PetObjectModel thrallPet = activeThralls.get(npc);

				if (thrallPet.getPetAttkAnim() != -1)
				{
					thrallPet.setAnimation(client.loadAnimation(thrallPet.getPetAttkAnim()));
				}
			}


		}



	}

	public WorldView getWVfromWA(WorldArea wa)
	{
		return client.findWorldViewFromWorldPoint(wa.toWorldPoint());
	}



	@Subscribe
	public void onClientTick(ClientTick event)
	{
		//skelly attk = 21 frames == +55 for start
		//melee attk = 21 frames
		//magic attk = 18 frames
		//basic magic attack anim 711

		if (petActive() && client.getLocalPlayer() != null)
		{
			LocalPoint petLp = pet.getLocalLocation();
			LocalPoint playerLp = client.getLocalPlayer().getLocalLocation();

			petPoly = calculateAABB(client, pet.getRlObject().getModel(), pet.getOrientation(), petLp.getX(), petLp.getY(), client.getTopLevelWorldView().getPlane(), pet.getZOff());
			double intx = petLp.getX() - playerLp.getX();
			double inty = petLp.getY() - playerLp.getY();

			pet.onClientTick(event,radToJau(Math.atan2(intx,inty)));

			//for 2x2 set drawFontTilesFirst only when they are moving / test vs walls in house and rimmy
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
			PetObjectModel thrallPOM = entry.getValue();
			RuneLiteObject thrallRLO = thrallPOM.getRlObject();
			Animation anim = thrallRLO.getAnimation();
			NPC thrall = entry.getKey();

			if (anim != null && thrallPOM.getPetAttkAnim() != -1 && anim.getId() == thrallPOM.getPetAttkAnim() && thrallRLO.getAnimationFrame() == anim.getNumFrames() - 1)
			{
				if (thrallPOM.targetQueueSize == 0 && anim != thrallPOM.animationPoses[0])
				{
					thrallPOM.setAnimation(thrallPOM.animationPoses[0]);
				}
				else
				{
					thrallPOM.setAnimation(thrallPOM.animationPoses[1]);
				}
			}

			thrallPOM.onClientTick(event,thrall.getOrientation());
		}

	}

	@Subscribe
	public void onProjectileMoved(ProjectileMoved event)
	{
		processProjectile(event.getProjectile());
	}



	public void processProjectile(Projectile projectile)
	{
		if (projectile.getStartCycle() == client.getGameCycle())
		{
			//x1 and y1 represet the projectiles cords when it is first spawned
			LocalPoint point = new LocalPoint(projectile.getX1(),projectile.getY1());

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
		Point mousePoint = client.getMouseCanvasPosition();

		if (!petActive() || !petPoly.contains(mousePoint.getX(), mousePoint.getY()))
		{
			return;
		}

		Menu menu = client.getMenu();
		String[] options = petData.isMetamorph() ? PET_MORPH_OPTIONS : PET_DEFAULT_OPTIONS;
		String target = ColorUtil.prependColorTag(petData.getName(), Color.YELLOW);

		for (String option : options)
		{
			menu.createMenuEntry(1)//just above Cancel
					.setOption(option)
					.setTarget(target)
					.setType(MenuAction.RUNELITE)
					.setParam0(0)
					.setParam1(0)
					.setDeprioritized(true);
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
				petData = PetData.morphModel.get(petData);
				updatePet();
			}
		}

		if (event.getMenuEntry().getType() == MenuAction.RUNELITE && event.getMenuTarget().contains(petData.getName()) && event.getMenuOption().equals("Talk-to"))
		{

			if (pet.isActive() && pet.getWorldArea().isInMeleeDistance(client.getLocalPlayer().getWorldArea()))
			{
				dialogOpen = true;
				fakeDialogManager.open(fakeDialogManager.provideDialog(petData));
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
		if (petActive() || !activeThralls.isEmpty())
		{
			List<LocalPoint> localPoints = new ArrayList<>();
			List<NPC> npcs = client.getNpcs();
			List<Player> players = client.getPlayers();

			players.forEach(player -> localPoints.add(player.getLocalLocation()));
			npcs.stream().filter(npc -> !ALL_THRALL_IDS.contains(npc.getId())).forEach(npc -> localPoints.add(npc.getLocalLocation()));

			if (petActive())
			{
				boolean overlappingModel = localPoints.stream().anyMatch(localPoint -> localPoint.equals(pet.getLocalLocation()));
				conditionalHide(pet,overlappingModel);
			}

			if (!activeThralls.isEmpty())
			{
				if (petActive())
					localPoints.add(pet.getLocalLocation());

				for (PetObjectModel thrallOM : activeThralls.values())
				{
					boolean overlappingModel = localPoints.stream().anyMatch(localPoint -> localPoint.equals(thrallOM.getLocalLocation()));
					conditionalHide(thrallOM,overlappingModel);
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



	private boolean petActive()
	{
		return pet.getRlObject() != null && pet.isActive();
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
		if (petActive())
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

			pet.setAnimation(pet.animationPoses[0]);
			pet.spawn(client.getLocalPlayer().getWorldLocation(),radToJau(Math.atan2(intx,inty)),petData.getSize());
			nextTravellingPoint = pet.getWorldLocation().toWorldArea();
		}

	}

	public WorldPoint getPathOutWorldPoint(WorldArea worldArea)
	{

		System.out.println(client.findWorldViewFromWorldPoint(client.getLocalPlayer().getWorldLocation()).getPlane());
		System.out.println(client.getTopLevelWorldView().getPlane());

		ArrayList<WorldPoint> points = new ArrayList<>();

		for (int i = -1; i < 2; i++)
		{
			if (i != 0)
			{
				if (worldArea.canTravelInDirection(client.findWorldViewFromWorldPoint(worldArea.toWorldPoint()),i,0))
				{

					WorldPoint worldPoint = new WorldPoint(worldArea.getX() + i,worldArea.getY(),client.getPlane());

					boolean secondCheck = true;
					if (petData.getSize() == 2)
					{
						WorldArea area = new WorldArea(worldPoint,2,2);
						secondCheck = area.canTravelInDirection(client.findWorldViewFromWorldPoint(area.toWorldPoint()),i,0) ;
					}


					if (!worldPoint.equals(client.getLocalPlayer().getWorldLocation()) && secondCheck)
					{
						points.add(worldPoint);
					}

				}


				if (worldArea.canTravelInDirection(client.findWorldViewFromWorldPoint(worldArea.toWorldPoint()),0,i))
				{
					WorldPoint worldPoint = new WorldPoint(worldArea.getX(),worldArea.getY() + i,client.getPlane());

					boolean secondCheck = true;
					if (petData.getSize() == 2)
					{
						WorldArea area = new WorldArea(worldPoint,2,2);
						secondCheck	= area.canTravelInDirection(client.findWorldViewFromWorldPoint(area.toWorldPoint()),0,i);
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








	private void buildSidePanel()
	{
		panel = injector.getInstance(CompanionPetPanel.class);
		panel.sidePanelInitializer();

		if (petData == null)
		{
			petData = pets.get(config.pet().toString());
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

		petModel = provideModel(client,petData);
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
			petData = pets.get(config.pet().toString());
			petModel = provideModel(client,petData);

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

		pet.setAnimation(pet.animationPoses[0]);
		pet.spawn(getPathOutWorldPoint(new WorldArea(getAndUpdatePlayersDelayedLoc(),petData.getSize(),petData.getSize())),radToJau(Math.atan2(intx,inty)),petData.getSize());
		nextTravellingPoint = pet.getWorldLocation().toWorldArea();
	}




	private void populatePetData() throws IOException
	{
		ANIMATION_OFFSETS = loadAllOffsets(getClass().getResourceAsStream("/Data/vertical_offsets.json"));
		Map<Integer, NpcDefinition> npcData = loadById(getClass().getResourceAsStream("/Data/npc_data.json"));

		pets.values().forEach(pd ->
		{
			NpcDefinition def = npcData.get(pd.getNpcId());

			pd.setScale(def.getHeightScale());
			pd.setSize(def.getSize());
			pd.setModelIDs(def.getModels());
			pd.setColorToReplace(def.getColorToReplace());
			pd.setColorToReplaceWith(def.getColorToReplaceWith());
			pd.setFootprintSize(def.getFootprintSize());
			pd.setAmbient(def.getAmbient());
			pd.setContrast(def.getContrast());
			pd.setIdleAnim(def.getStandingAnimation());
			pd.setWalkAnim(def.getWalkingAnimation());
			pd.setRunAnim(def.getRunAnimation());
		});
	}


	private void setPetMochi(ChatMessage chatMessage, String s)
	{
		configManager.setConfiguration("CompanionPetPlugin","pet",PetData.MOCHI);
		callPet(chatMessage);
	}

	private void updateThrallIds()
	{
		if (config.impThralls())
		{
			MAGE_THRALLS.addAll(IMP_MAGE_THRALLS);
			RANGE_THRALLS.addAll(IMP_RANGE_THRALLS);
			MELEE_THRALLS.addAll(IMP_MELEE_THRALLS);
		}
		else
		{
			MAGE_THRALLS.removeAll(IMP_MAGE_THRALLS);
			RANGE_THRALLS.removeAll(IMP_RANGE_THRALLS);
			MELEE_THRALLS.removeAll(IMP_MELEE_THRALLS);
		}

		ALL_THRALL_IDS.clear();
		ALL_THRALL_IDS.addAll(MAGE_THRALLS);
		ALL_THRALL_IDS.addAll(RANGE_THRALLS);
		ALL_THRALL_IDS.addAll(MELEE_THRALLS);
	}




}
