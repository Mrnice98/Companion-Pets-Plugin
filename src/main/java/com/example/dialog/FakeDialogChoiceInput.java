package com.example.dialog;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntConsumer;
import javax.inject.Inject;
import net.runelite.api.FontID;
import net.runelite.api.SpriteID;
import net.runelite.api.widgets.JavaScriptCallback;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetPositionMode;
import net.runelite.api.widgets.WidgetSizeMode;
import net.runelite.api.widgets.WidgetTextAlignment;
import net.runelite.api.widgets.WidgetType;
import net.runelite.client.game.chatbox.ChatboxInput;
import net.runelite.client.game.chatbox.ChatboxPanelManager;
import net.runelite.client.input.KeyListener;

public class FakeDialogChoiceInput extends ChatboxInput implements KeyListener
{

	private static final int COLOUR_TITLE = 0x800000;
	private static final int COLOUR_OPTION = 0x000000;
	private static final int COLOUR_OPTION_HOVER = 0xFFFFFF;

	private final ChatboxPanelManager chatboxPanelManager;
	private final DialogNode dialogNode;
	private final IntConsumer onSelected;

	private final List<Widget> optionWidgets = new ArrayList<>(5);

	@Inject
	public FakeDialogChoiceInput(ChatboxPanelManager chatboxPanelManager, DialogNode dialogNode, IntConsumer onSelected)
	{
		this.chatboxPanelManager = chatboxPanelManager;
		this.dialogNode = dialogNode;
		this.onSelected = onSelected;
	}

	@Override
	protected void open()
	{
		Widget container = chatboxPanelManager.getContainerWidget();

		Widget prompt = container.createChild(-1, WidgetType.TEXT);
		prompt.setText(dialogNode.getTitle());
		prompt.setTextColor(COLOUR_TITLE);
		prompt.setFontId(FontID.QUILL_8);
		prompt.setXPositionMode(WidgetPositionMode.ABSOLUTE_CENTER);
		prompt.setOriginalX(0);
		prompt.setYPositionMode(WidgetPositionMode.ABSOLUTE_TOP);
		prompt.setOriginalY(8);
		prompt.setOriginalHeight(24);
		prompt.setXTextAlignment(WidgetTextAlignment.CENTER);
		prompt.setYTextAlignment(WidgetTextAlignment.CENTER);
		prompt.setWidthMode(WidgetSizeMode.MINUS);
		prompt.revalidate();


		int y = prompt.getRelativeX() + prompt.getHeight() + 6;
		int height = container.getHeight() - y - 8;
		int step = height / dialogNode.getChoices().size();
		int maxStep = dialogNode.getChoices().size() >= 3 ? 25 : 30;
		if (step > maxStep)
		{
			int ds = step - maxStep;
			step = maxStep;
			y += (ds * dialogNode.getChoices().size()) / 2;
		}

		for (int i = 0; i < dialogNode.getChoices().size(); i++)
		{
			DialogNode.DialogChoice option = dialogNode.getChoices().get(i);

			Widget optWidget = container.createChild(-1, WidgetType.TEXT);
			optWidget.setText(option.getOption());
			optWidget.setTextColor(COLOUR_OPTION);
			optWidget.setFontId(FontID.QUILL_8);
			optWidget.setXPositionMode(WidgetPositionMode.ABSOLUTE_CENTER);
			optWidget.setOriginalX(0);
			optWidget.setYPositionMode(WidgetPositionMode.ABSOLUTE_TOP);
			optWidget.setOriginalY(y);
			optWidget.setOriginalHeight(24);
			optWidget.setXTextAlignment(WidgetTextAlignment.CENTER);
			optWidget.setYTextAlignment(WidgetTextAlignment.CENTER);
			optWidget.setWidthMode(WidgetSizeMode.MINUS);
			optWidget.setAction(0, "Continue");

			final int selectIx = i;
			optWidget.setOnOpListener((JavaScriptCallback) _e -> queueAdvance(selectIx));
			optWidget.setOnMouseOverListener((JavaScriptCallback) _e -> optWidget.setTextColor(COLOUR_OPTION_HOVER));
			optWidget.setOnMouseLeaveListener((JavaScriptCallback) _e -> optWidget.setTextColor(COLOUR_OPTION));
			optWidget.setHasListener(true);

			optionWidgets.add(optWidget);
			optWidget.revalidate();
			y += step;
		}


		// left sword
		container.createChild(-1, WidgetType.GRAPHIC)
			.setSpriteId(SpriteID.RS2_SWORD_POINTED_RIGHT)
			.setOriginalX(71)
			.setOriginalY(12)
			.setOriginalWidth(57)
			.setOriginalHeight(13)
			.revalidate();


		// right sword
		container.createChild(-1, WidgetType.GRAPHIC)
			.setSpriteId(SpriteID.RS2_SWORD_POINTED_LEFT)
			.setOriginalX(351)
			.setOriginalY(12)
			.setOriginalWidth(57)
			.setOriginalHeight(13)
			.revalidate();

		container.revalidate();
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		int ix = e.getKeyCode() - '1';
		if (ix >= 0 && ix < optionWidgets.size())
		{
			queueAdvance(ix);
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{

	}

	private void queueAdvance(int ix)
	{
		Widget optWidget = optionWidgets.get(ix);
		optWidget.setText("Please wait...");
		optWidget.setTextColor(COLOUR_OPTION);
		onSelected.accept(ix);
	}
}
