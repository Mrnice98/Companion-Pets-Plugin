package com.example;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import java.util.List;
import java.util.EnumSet;
import java.util.Set;

@ConfigGroup("CompanionPetPlugin")
public interface CompanionPetConfig extends Config
{
	@ConfigItem(
		keyName = "pet",
		name = "Pick Pet",
		description = "Pick pet",
		hidden = true
	)
	default PetData pet()
	{
		return PetData.ABYSSAL_ORPHAN;
	}


	@ConfigItem(
			keyName = "filter",
			name = "Filter",
			description = "Filter",
			hidden = true
	)
	default boolean filter()
	{
		return true;
	}


	@ConfigItem(
			keyName = "showPets",
			name = "Show Pets",
			description = "Show Pets",
			hidden = true
	)
	default boolean showPetList()
	{
		return true;
	}


	@ConfigItem(
			keyName = "favorites",
			name = "Favorites",
			description = "favorites",
			hidden = true
	)
	default String favorites()
	{
		return "ABYSSAL_ORPHAN,HELLPUPPY,TANGLEROOT,OLMLET";
	}


	@ConfigItem(
			keyName = "meleeThrall",
			name = "meleeThrall",
			description = "meleeThrall",
			hidden = true
	)
	default PetData meleeThrall()
	{
		return PetData.SNAKELING_RED;
	}


	@ConfigItem(
			keyName = "rangeThrall",
			name = "rangeThrall",
			description = "rangeThrall",
			hidden = true
	)
	default PetData rangeThrall()
	{
		return PetData.SNAKELING_GREEN;
	}


	@ConfigItem(
			keyName = "mageThrall",
			name = "mageThrall",
			description = "mageThrall",
			hidden = true
	)
	default PetData mageThrall()
	{
		return PetData.SNAKELING_BLUE;
	}


	@ConfigItem(
			keyName = "showThralls",
			name = "showThralls",
			description = "showThralls",
			hidden = true
	)
	default boolean showThralls()
	{
		return true;
	}


	@ConfigItem(
			keyName = "companionThralls",
			name = "companionThralls",
			description = "companionThralls",
			hidden = true
	)
	default boolean companionThralls()
	{
		return true;
	}


	@ConfigItem(
			keyName = "impThralls",
			name = "Imp Thralls",
			description = "Plugin overrides imp thralls if enabled.",
			hidden = false
	)
	default boolean impThralls()
	{
		return true;
	}


	@ConfigItem(
			keyName = "disableWhistle",
			name = "Disable Fake Follower",
			description = "Disables the ability to spawn a fake follower by clicking the call pet whistle.",
			hidden = false
	)
	default boolean disableWhistle()
	{
		return false;
	}


	@ConfigItem(
			keyName = "debug",
			name = "Enable Debug",
			description = "Enables Debug.",
			hidden = false
	)
	default boolean debug()
	{
		return false;
	}


}
