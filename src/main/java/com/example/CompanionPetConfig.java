package com.example;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

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
			keyName = "debug",
			name = "Enable Debug",
			description = "Enables Debug",
			hidden = false
	)
	default boolean debug()
	{
		return false;
	}

	@ConfigItem(
			keyName = "allowBrokenPets",
			name = "Enable Broken Pets",
			description = "These pets do not scale correctly and are awaiting a RL update to fix",
			hidden = false
	)
	default boolean allowBrokenPets()
	{
		return false;
	}

	@ConfigItem(
			keyName = "forceRun",
			name = "Force Run",
			description = "Will make the pet run without a custom animation",
			hidden = false
	)
	default boolean forceRun()
	{
		return false;
	}

}
