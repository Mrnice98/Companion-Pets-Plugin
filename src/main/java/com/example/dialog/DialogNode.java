package com.example.dialog;

import java.util.List;
import java.util.function.Supplier;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Singular;
import lombok.Value;
import lombok.With;

@Value
@Builder
@With
public class DialogNode
{

	@RequiredArgsConstructor(staticName = "of")
	@Value
	public static class DialogChoice
	{
		String option;
		Supplier<DialogNode> onSelected;
	}

	DialogType type;

	boolean player;
	int npcId;
	int animationId;

	@Builder.Default
	String title = null;
	String body;

	@Singular
	List<DialogChoice> choices;

	public DialogNode getNext(int selectedIx)
	{
		if (selectedIx >= choices.size())
		{
			return null;
		}

		return this.choices.get(selectedIx).getOnSelected().get();
	}

	public int getLineHeight()
	{
		switch (this.body.split("<br>").length)
		{
			case 2:
				return 28;
			case 3:
				return 20;
			default:
				return 16;
		}
	}

	public static class DialogNodeBuilder
	{
		public DialogNodeBuilder player()
		{
			this.player = true;
			this.type(DialogType.DIALOG_HEAD_RIGHT);
			return this;
		}

		public DialogNodeBuilder npc(int npcId)
		{
			this.player = false;
			this.npcId = npcId;
			this.type(DialogType.DIALOG_HEAD_LEFT);
			return this;
		}

		public DialogNodeBuilder next(DialogNode next)
		{
			this.onContinue(() -> next);
			return this;
		}

		public DialogNodeBuilder onContinue(Supplier<DialogNode> onContinue)
		{
			this.clearChoices()
				.choice(DialogChoice.of("Click here to continue", onContinue));
			return this;
		}

		public DialogNodeBuilder option(String option, DialogNode next)
		{
			this.option(option, () -> next);
			return this;
		}

		public DialogNodeBuilder option(String option, Supplier<DialogNode> onSelect)
		{
			if (!this.title$set)
			{
				this.title("Select an Option");
			}

			this.type(DialogType.CHOICE)
				.choice(DialogChoice.of(option, onSelect));
			return this;
		}
	}

}
