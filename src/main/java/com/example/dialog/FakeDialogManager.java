package com.example.dialog;

import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.NonNull;
import net.runelite.api.Client;
import net.runelite.api.events.GameTick;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.chatbox.ChatboxPanelManager;

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
		// dialog choices always take the first one selected, even if multiple are hit
		if (this.selectedIx == -1)
		{
			this.selectedIx = selectedIx;
		}
	}
}
