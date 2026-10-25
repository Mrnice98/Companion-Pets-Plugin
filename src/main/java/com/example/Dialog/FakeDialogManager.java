package com.example.Dialog;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.example.PetData;
import lombok.NonNull;
import net.runelite.api.Client;
import net.runelite.api.events.GameTick;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.chatbox.ChatboxPanelManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class FakeDialogManager
{

	@Inject
	private Client client;

	@Inject
	private ChatboxPanelManager chatboxPanelManager;

	private int selectedIx = -1;
	private DialogNode currentNode;

	public void open(@NonNull DialogNode startNode)
	{
		this.selectedIx = -1;
		this.currentNode = startNode;

		chatboxPanelManager.openInput(
			startNode.getType() == DialogType.CHOICE
				? new FakeDialogChoiceInput(chatboxPanelManager, startNode, this::setSelectedIx)
				: new FakeDialogChatheadInput(client, chatboxPanelManager, startNode, this::setSelectedIx)
		);
	}

	@Subscribe(priority = 1) // needs to run before main plugin subscriber
	public void onGameTick(GameTick e)
	{
		if (selectedIx == -1 || currentNode == null)
		{
			return;
		}

		currentNode = currentNode.getNext(selectedIx);
		if (currentNode == null)
		{
			chatboxPanelManager.close();
		}
		else
		{
			this.open(currentNode);
		}
	}

	public void setSelectedIx(int selectedIx)
	{
		// Dialog choices always take the first one selected, even if multiple are hit
		if (this.selectedIx == -1)
		{
			this.selectedIx = selectedIx;
		}
	}

	public DialogNode provideDialog(PetData petData)
	{
		List<String> data = Arrays.stream(petData.getDriestPerson().split(":")).collect(Collectors.toList());

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
						.body("It took " + name +" "+ kc + " " + kcIdentifer +" but<br>" + "They finally got me on " + date)
						.animationId(petData.getChatHeadAnimID())
						.build()
				)
				.build();
	}

}
