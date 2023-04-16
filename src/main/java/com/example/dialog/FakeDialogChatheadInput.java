package com.example.dialog;

import com.google.common.base.Strings;
import java.util.function.IntConsumer;
import lombok.RequiredArgsConstructor;
import net.runelite.api.Client;
import net.runelite.api.FontID;
import net.runelite.api.widgets.JavaScriptCallback;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetModelType;
import net.runelite.api.widgets.WidgetPositionMode;
import net.runelite.api.widgets.WidgetSizeMode;
import net.runelite.api.widgets.WidgetTextAlignment;
import net.runelite.api.widgets.WidgetType;
import net.runelite.client.game.chatbox.ChatboxInput;
import net.runelite.client.game.chatbox.ChatboxPanelManager;

@RequiredArgsConstructor
public class FakeDialogChatheadInput extends ChatboxInput
{

	private static final int COLOUR_TITLE = 0x800000;
	private static final int COLOUR_CONTINUE = 0x0000FF;
	private static final int COLOUR_CONTINUE_HOVER = 0xFFFFFF;

	private static final int CHATHEAD_MODEL_ZOOM = 796;
	private static final int CHATHEAD_WIDTH = 32;
	private static final int CHATHEAD_HEIGHT = 32;

	private static final int TEXT_WIDTH = 380;
	private static final int TEXT_LINE_HEIGHT = 17;
	private static final int TEXT_BLOCK_HEIGHT = 67;

	private final Client client;
	private final ChatboxPanelManager chatboxPanelManager;
	private final DialogNode currentNode;
	private final IntConsumer onSelected;

	@Override
	protected void open()
	{
		Widget container = chatboxPanelManager.getContainerWidget();
		container.deleteAllChildren();

		buildChatHead(container);
		buildTitle(container);
		buildBody(container);
		buildContinue(container);

		container.revalidate();
	}

	private void buildChatHead(Widget container)
	{
		container.createChild(-1, WidgetType.MODEL)
			.setModelType(currentNode.isPlayer() ? WidgetModelType.LOCAL_PLAYER_CHATHEAD : WidgetModelType.NPC_CHATHEAD)
			.setModelId(currentNode.isPlayer() ? -1 : currentNode.getNpcId())
			.setAnimationId(currentNode.getAnimationId())
			.setModelZoom(CHATHEAD_MODEL_ZOOM)
			.setRotationX(currentNode.getType().getHeadRotX())
			.setRotationY(currentNode.getType().getHeadRotY())
			.setRotationZ(currentNode.getType().getHeadRotZ())
			.setOriginalX(currentNode.getType().getHeadX())
			.setOriginalY(currentNode.getType().getHeadY())
			.setOriginalWidth(CHATHEAD_WIDTH)
			.setOriginalHeight(CHATHEAD_HEIGHT)
			.setXPositionMode(WidgetPositionMode.ABSOLUTE_LEFT)
			.setYPositionMode(WidgetPositionMode.ABSOLUTE_TOP)
			.setWidthMode(WidgetSizeMode.ABSOLUTE)
			.setHeightMode(WidgetSizeMode.ABSOLUTE)
			.revalidate();
	}

	private void buildTitle(Widget container)
	{
		String title = currentNode.getTitle();
		if (Strings.isNullOrEmpty(title))
		{
			if (currentNode.isPlayer())
			{
				title = client.getLocalPlayer().getName();
			}
		}

		container.createChild(-1, WidgetType.TEXT)
			.setText(title)
			.setTextColor(COLOUR_TITLE)
			.setFontId(FontID.QUILL_8)
			.setXTextAlignment(WidgetTextAlignment.CENTER)
			.setYTextAlignment(WidgetTextAlignment.TOP)
			.setOriginalX(currentNode.getType().getTextX())
			.setOriginalY(currentNode.getType().getNameY())
			.setOriginalWidth(TEXT_WIDTH)
			.setOriginalHeight(TEXT_LINE_HEIGHT)
			.setXPositionMode(WidgetPositionMode.ABSOLUTE_LEFT)
			.setYPositionMode(WidgetPositionMode.ABSOLUTE_TOP)
			.setWidthMode(WidgetSizeMode.ABSOLUTE)
			.setHeightMode(WidgetSizeMode.ABSOLUTE)
			.revalidate();
	}

	private void buildBody(Widget container)
	{
		container.createChild(-1, WidgetType.TEXT)
			.setText(currentNode.getBody())
			.setFontId(FontID.QUILL_8)
			.setXTextAlignment(WidgetTextAlignment.CENTER)
			.setYTextAlignment(WidgetTextAlignment.CENTER)
			.setOriginalX(currentNode.getType().getTextX())
			.setOriginalY(currentNode.getType().getTextY())
			.setOriginalWidth(TEXT_WIDTH)
			.setOriginalHeight(TEXT_BLOCK_HEIGHT)
			.setLineHeight(currentNode.getLineHeight())
			.setXPositionMode(WidgetPositionMode.ABSOLUTE_LEFT)
			.setYPositionMode(WidgetPositionMode.ABSOLUTE_TOP)
			.setWidthMode(WidgetSizeMode.ABSOLUTE)
			.setHeightMode(WidgetSizeMode.ABSOLUTE)
			.revalidate();
	}

	private void buildContinue(Widget container)
	{
		Widget continueWidget = container.createChild(-1, WidgetType.TEXT)
			.setText("Click here to continue")
			.setTextColor(COLOUR_CONTINUE)
			.setFontId(FontID.QUILL_8)
			.setXTextAlignment(WidgetTextAlignment.CENTER)
			.setYTextAlignment(WidgetTextAlignment.TOP)
			.setOriginalX(currentNode.getType().getTextX())
			.setOriginalY(currentNode.getType().getContinueY())
			.setOriginalWidth(TEXT_WIDTH)
			.setOriginalHeight(TEXT_LINE_HEIGHT)
			.setXPositionMode(WidgetPositionMode.ABSOLUTE_LEFT)
			.setYPositionMode(WidgetPositionMode.ABSOLUTE_TOP)
			.setWidthMode(WidgetSizeMode.ABSOLUTE)
			.setHeightMode(WidgetSizeMode.ABSOLUTE);

		Runnable queueAdvance = () ->
		{
			continueWidget.setText("Please wait...");
			continueWidget.setTextColor(COLOUR_CONTINUE);
			onSelected.accept(0);
		};

		continueWidget.setAction(0, "Continue");
		continueWidget.setOnOpListener((JavaScriptCallback) _e -> queueAdvance.run());
		continueWidget.setOnMouseOverListener((JavaScriptCallback) _e -> continueWidget.setTextColor(COLOUR_CONTINUE_HOVER));
		continueWidget.setOnMouseLeaveListener((JavaScriptCallback) _e -> continueWidget.setTextColor(COLOUR_CONTINUE));
		continueWidget.setOnKeyListener((JavaScriptCallback) e ->
		{
			if (e.getTypedKeyCode() == 83)
			{
				queueAdvance.run();
			}
		});
		continueWidget.setHasListener(true);

		continueWidget.revalidate();
	}
}
