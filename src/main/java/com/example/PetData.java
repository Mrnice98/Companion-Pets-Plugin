package com.example;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.runelite.api.ItemID;
import net.runelite.api.NpcID;

import java.util.*;

@Getter
@RequiredArgsConstructor
public enum PetData {


    //RAIDS
    //SLAYER
    //GWD
    //OTHER BOSS PETS
    //SKILLING PETS

    //Recolor ids first color to find, second half color to replace
    //COX----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//COX----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    OLMLET("Olmlet",7398,NpcID.OLMLET,ItemID.OLMLET,true,"The most cuddly Spawn of the Guardian in the Deep.","Purples:R2xMVP:262:July 17, 2022",588),//need to get all these
    PUPPADILE("Puppadile",7422,NpcID.PUPPADILE,ItemID.PUPPADILE,true,"A puppy of a mutated guardian of Xeric.","Purples:R2xMVP:262:July 17, 2022",588),
    TEKTINY("Tektiny",7483,NpcID.TEKTINY,ItemID.TEKTINY,true,"Xeric's former artisan's former helper.\n","Purples:R2xMVP:262:July 17, 2022",588),
    ENRAGED_TEKTINY("Enraged Tektiny",7493,NpcID.ENRAGED_TEKTINY,ItemID.ENRAGED_TEKTINY,true,"Xeric's former artisan's former helper.\n","Purples:R2xMVP:262:July 17, 2022",588),
    VANGUARD("Vanguard",7433,NpcID.VANGUARD_8203,ItemID.VANGUARD,true,"A very small member of Xeric's elite tactical unit.\n","Purples:R2xMVP:262:July 17, 2022",588),
    VASA_MINIRIO("Vasa Minirio",7409,NpcID.VASA_MINIRIO,ItemID.VASA_MINIRIO,true,"The son of a former High Priest, fused with the rock and bound to the dark crystals.\n","Purples:R2xMVP:262:July 17, 2022",588),
    VESPINA("Vespina",7450,NpcID.VESPINA,ItemID.VESPINA,true,"Princess of the Abyssal Vespine.\n","Purples:R2xMVP:262:July 17, 2022",588),
    FLYING_VESPINA("Flying Vespina",7454,NpcID.FLYING_VESPINA,ItemID.FLYING_VESPINA,true,"Princess of the Abyssal Vespine.\n","Purples:R2xMVP:262:July 17, 2022",588),

    //TOB----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    LIL_ZIK("Lil' Zik",8123,NpcID.LIL_ZIK,ItemID.LIL_ZIK,true,"What has eight legs and runs a Vampyric Theatre?\n","Killcount:Obesetoad:3450:February 4, 2026",611),
    LIL_MAIDEN("Lil' Maiden",8092,NpcID.LIL_MAIDEN,ItemID.LIL_MAIDEN,true,"Freed from her torturous constraints.\n","Killcount:Obesetoad:3450:February 4, 2026",610),
    LIL_BLOAT("Lil' Bloat",-1,NpcID.LIL_BLOAT,ItemID.LIL_BLOAT,true,"Smaller size, same smell.\n","Killcount:Obesetoad:3450:February 4, 2026",588),
    LIL_NYLO("Lil' Nylo",8004,NpcID.LIL_NYLO_10872,ItemID.LIL_NYLO,true,"Eight legs of unparalleled loyalty.\n","Killcount:Obesetoad:3450:February 4, 2026",544),
    LIL_SOT("Lil' Sot",8138,NpcID.LIL_SOT_10873,ItemID.LIL_SOT,true,"Has a lot of anger for such a small monster.\n","Killcount:Obesetoad:3450:February 4, 2026",614),
    LIL_XARP("Lil' Xarp",8059,NpcID.LIL_XARP_10874,ItemID.LIL_XARP,true,"The prince of Yarasa.\n","Killcount:Obesetoad:3450:February 4, 2026",554),

    //TOA----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    TUMEKENS_GUARDIAN("Tumeken's Guardian",9660,NpcID.TUMEKENS_GUARDIAN,ItemID.TUMEKENS_GUARDIAN,true,"A tiny automaton imbued with a trace of Tumeken's power.\n","Killcount:saddest cat:2471:July 4, 2026",591),
    //look at below attk anim
    TUMEKENS_DAMAGED_GUARDIAN("Tumeken's Damaged Guardian",9660,NpcID.TUMEKENS_DAMAGED_GUARDIAN,ItemID.TUMEKENS_DAMAGED_GUARDIAN,true,"A tiny automaton imbued with a trace of Tumeken's power.\n","Killcount:saddest cat:2471:July 4, 2026",588),
    ELIDINIS_GUARDIAN("Elidinis' Guardian",9660,NpcID.ELIDINIS_GUARDIAN,ItemID.ELIDINIS_GUARDIAN,true,"A tiny automaton imbued with a trace of Elidinis' power.\n","Killcount:saddest cat:2471:July 4, 2026",591),
    ELIDINIS_DAMAGED_GUARDIAN("Elidinis' Damaged Guardian",9660,NpcID.ELIDINIS_DAMAGED_GUARDIAN,ItemID.ELIDINIS_DAMAGED_GUARDIAN,true,"A tiny automaton imbued with a trace of Elidinis' power.\n","Killcount:saddest cat:2471:July 4, 2026",588),
    //check for 180 rotate for below
    AKKHITO("Akkhito",9770,NpcID.AKKHITO,ItemID.AKKHITO,true,"Small, but still strong.\n","Killcount:saddest cat:2471:July 4, 2026",588),
    BABI("Babi",9744,NpcID.BABI,ItemID.BABI,true,"Her mother's daughter. Fond of bananas.\n","Killcount:saddest cat:2471:July 4, 2026",555),
    KEPHRITI("Kephriti",9578,NpcID.KEPHRITI,ItemID.KEPHRITI,true,"The tiniest bug can still make worlds fall over.\n","Killcount:saddest cat:2471:July 4, 2026",579),
    ZEBO("Zebo",2039,NpcID.ZEBO,ItemID.ZEBO,true,"Chomp.\n","Killcount:saddest cat:2471:July 4, 2026",588),


    //14810
    //14811
    //-----
    //14861
    //14846 - Orion
    //TFA----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    ORION("Orion",14842,NpcID.ORION,ItemID.ORION,true,"Reality trembles with their mere presence.\n","Killcount:MrNice:67:April 1, 2027",591),
    ORION_ROBED("Orion",14810,NpcID.ORION_16632,ItemID.ORION_34648,true,"Reality trembles with their mere presence.\n","Killcount:MrNice:67:April 1, 2027",591),


    //SLAYER----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    ABYSSAL_ORPHAN("Abyssal Orphan",7126,NpcID.ABYSSAL_ORPHAN,ItemID.ABYSSAL_ORPHAN,false,"Born in the death throes of an Abyssal Sire.\n","Killcount:Maximumist:15728:Unknown",7130),

    HELLPUPPY("Hellpuppy",6562,NpcID.HELLPUPPY,ItemID.HELLPUPPY,false,"A fiery little pup!\n","Killcount:Parrot Poop:21247:June 6, 2026",3411),

    NOON("Noon",7770,NpcID.NOON,ItemID.NOON,true,"The mini Guardian of Dawn!\n","Killcount:R a z u z:19027:January 28, 2024",7805),//need to check -----
    MIDNIGHT("Midnight",7808,NpcID.MIDNIGHT,ItemID.MIDNIGHT,true,"The mini Guardian of Dusk!\n","Killcount:R a z u z:19027:January 28, 2024",7804),

    IKKLE_HYDRA("Ikkle Hydra",8234,NpcID.IKKLE_HYDRA,ItemID.IKKLE_HYDRA,true,"How does it not fall over?\n","Killcount:Vampire Bob:24095:February 7, 2026",8265),
    IKKLE_HYDRA_22748("Ikkle Hydra",8241,NpcID.IKKLE_HYDRA_8493,ItemID.IKKLE_HYDRA_22748,true,"How does it not fall over?\n","Killcount:Vampire Bob:24095:February 7, 2026",8265),
    IKKLE_HYDRA_22750("Ikkle Hydra",8248,NpcID.IKKLE_HYDRA_8494,ItemID.IKKLE_HYDRA_22750,true,"How does it not fall over?\n","Killcount:Vampire Bob:24095:February 7, 2026",8265),
    IKKLE_HYDRA_22752("Ikkle Hydra",8256,NpcID.IKKLE_HYDRA_8495,ItemID.IKKLE_HYDRA_22752,true,"How does it not fall over?\n","Killcount:Vampire Bob:24095:February 7, 2026",8265),

    PET_SMOKE_DEVIL_6655("Pet Smoke Devil",3847,NpcID.SMOKE_DEVIL_6639,ItemID.PET_SMOKE_DEVIL,true,"Woah.","Killcount:hank hill17:21123:September 18, 2022",588),
    PET_SMOKE_DEVIL("Pet Smoke Devil",3847,NpcID.SMOKE_DEVIL_8483,ItemID.PET_SMOKE_DEVIL_22663,true,"Woah.","Killcount:hank hill17:21123:September 18, 2022",588),

    KRAKEN("Kraken",3992,NpcID.KRAKEN_6656,ItemID.PET_KRAKEN,false,"We'll fight, until world's end.\n","Killcount:Bone Papi:29932:February 8, 2026",588),

    RAX("Rax",8147,NpcID.RAX,ItemID.RAX,true,"Really hope she doesn't crawl into my mouth while I sleep.\n","Killcount:Sterk GIM:10869 (10217 destroys):July 11, 2026",588),
    NID("Nid",11476,NpcID.NID,ItemID.NID,true,"Looks like a respectable gentleman.\n","Killcount:Sterk GIM:10869 (10217 destroys):July 11, 2026",588),

    //GWD----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    GENERAL_GRAARDOR_JR("General Graardor Jr.",7021,NpcID.GENERAL_GRAARDOR_JR,ItemID.PET_GENERAL_GRAARDOR,false,"Totally unintelligible.\n","Killcount:Cactusaak:32481:September 15, 2023",589),
    KREEARRA_JR("Kree'arra Jr.",6981,NpcID.KREEARRA_JR,ItemID.PET_KREEARRA,false,"Is it a bird, is it... no it's a bird.\n","Killcount:Pet Hunt:25174:September 5, 2025",588),
    ZILYANA_JR("Zilyana Jr.",6967,NpcID.ZILYANA_JR,ItemID.PET_ZILYANA,false,"Somehow a junior even though she's named after her spawn mother!","Killcount:48hour:37761:March 24, 2024",588),
    KRIL_TSUTSAROTH_JR("K'ril Tsutsaroth Jr.",6948,NpcID.KRIL_TSUTSAROTH_JR,ItemID.PET_KRIL_TSUTSAROTH,false,"Where did he even come from?","Killcount:Postie:27108:July 31, 2026",588),//check
    NEXLING("Nexling",9182,NpcID.NEXLING,ItemID.NEXLING,false,"The gods don't quite fear this one.\n","Shards:Jord xD:33682:March 24, 2024",614),

    //DT2--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    BUTCH("Butch",10340,NpcID.BUTCH,ItemID.BUTCH,false,"A tiny headless executioner.","Killcount:nofkeysumsy:17004:August 11, 2024",588),
    BARON("Baron",10219,NpcID.BARON,ItemID.BARON,false,"Better keep an eye on this one.","Killcount:pulp horn:16111:August 7, 2026",588),
    LILVIATHAN("Lil'viathan",10283,NpcID.LILVIATHAN,ItemID.LILVIATHAN,false,"A small creature deformed by the Abyss.","Killcount:Bartoast:17607:August 4, 2025",588),
    WISP("Wisp",10234,NpcID.WISP,ItemID.WISP,false,"Born in the shadows.","Killcount:Sloothy:14940:April 6, 2025",588),


    //Other Boss pets----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    SCURRY("Scurry",10693,NpcID.SCURRY,ItemID.SCURRY,false,"She works on the railroad.","Killcount:Arctic:24233:January 10, 2026",588),

    MOXI("Moxi",11013,NpcID.MOXI,ItemID.MOXI,false,"A tiny frost nagua.","Killcount:SniffYaHole:20618:May 6, 2026",588),

    HUBERTE("Huberte",11734,NpcID.HUBERTE,ItemID.HUBERTE,false,"Looks very polite.","Killcount:Maxed Whip:2454:December 20, 2024",588),

    AGGY("Aggy",4589,NpcID.AGGY_16334,ItemID.AGGY,false,"A cute angel.","Killcount:M a n z y:14221:October 3, 2026",588),

    MAGGOT_MARQUESS("Maggot marquess",13934,NpcID.MAGGOT_MARQUESS,ItemID.MAGGOT_MARQUESS,false,"Best not to think too much about the venator it inhabits.","Killcount:Barcoding:6899:August 17, 2026",588),

    BEEF("Beef",5849,NpcID.BEEF,ItemID.BEEF,false,"Commonly referred to as Sir Loin.","Killcount:Mackebrors:9611:July 5, 2026",588),

    //Gull
    GULLIVER("Gulliver",12552,NpcID.GULLIVER_15060,ItemID.GULLIVER,true,"Small, but somehow still looks down on me.","Killcount:Valor:17030:April 18, 2026",588),
    GULL("Gull",12588,NpcID.GULL_14931,ItemID.GULL,true,"Quite the scrongly little chicken.","Killcount:Valor:17030:April 18, 2026",588),

    //inferno
    JAL_NIB_REK("Jal-Nib-Rek",7574,NpcID.JALNIBREK,ItemID.JALNIBREK,true,"It loves to nibble.\n","Killcount:SubParMed:379:April 25, 2026",588),//check
    TZREK_ZUK("TzRek-Zuk",7978,NpcID.TZREKZUK,ItemID.TZREKZUK,true,"Not quite so fearsome anymore.","Killcount:SubParMed:379:April 25, 2026",588),//check

    //jad
    TZREK_JAD("TzRek-Jad",2652,NpcID.TZREKJAD,ItemID.TZREKJAD,true,"This is not going to hurt... but it might tickle.","Killcount:Luck Voltla:458:December 11, 2024",588),
    JALREK_JAD("JalRek-Jad",7593,NpcID.JALREKJAD,ItemID.JALREKJAD,true,"Small, troublesome, cute.","Killcount:Luck Voltla:458:December 11, 2024",8858),

    SMOL_HEREDIT("Smol Heredit",10882,NpcID.SMOL_HEREDIT,ItemID.SMOL_HEREDIT,true,"Still somewhat imposing, I suppose.","Killcount:I have egg73:692:June 23, 2024",588),

    //Devs did not give Dom an attack animation.
    DOM("Dom",-1,NpcID.DOM,ItemID.DOM,false,"Doom on a small scale.","Killcount:Magest:1816 (1508 8+):September 8, 2025",588),

    //Guantlet
    YOUNGLLEF("Youngllef",8418,NpcID.YOUNGLLEF,ItemID.YOUNGLLEF,true,"Looks like a bit of a nightmare.","Killcount:Dollynho Bro:5428:July 29, 2023",8427),
    CORRUPTED_YOUNGLLEF("Corrupted Youngllef",8418,NpcID.CORRUPTED_YOUNGLLEF,ItemID.CORRUPTED_YOUNGLLEF,true,"Looks like a bit of a nightmare.","Killcount:Dollynho Bro:5428:July 29, 2023",8427),

    //corp
    DARK_CORE("Dark core",-1,NpcID.DARK_CORE,ItemID.PET_DARK_CORE,true,"Isn't so annoying when in pet form.\n","Killcount:Flikker:36413:June 28, 2022",588),
    CORPOREAL_CRITTER("Corporeal Critter",1679,NpcID.CORPOREAL_CRITTER,ItemID.PET_CORPOREAL_CRITTER,true,"A critter from the spirit realm.\n","Killcount:Flikker:36413:June 28, 2022",588),

    //zulrah
    SNAKELING_BLUE("Snakeling",1741,NpcID.SNAKELING_2129,ItemID.PET_SNAKELING_12940,true,"A slithering serpent spawn of Zulrah.\n","Killcount:Outbox:38610:June 26, 2024",2415),
    SNAKELING_RED("Snakeling",1741,NpcID.SNAKELING_2128,ItemID.PET_SNAKELING_12939,true,"A slithering serpent spawn of Zulrah.\n","Killcount:Outbox:38610:June 26, 2024",2415),
    SNAKELING_GREEN("Snakeling",1741,NpcID.SNAKELING_2127,ItemID.PET_SNAKELING,true,"A slithering serpent spawn of Zulrah.\n","Killcount:Outbox:38610:June 26, 2024",2415),

    //nm
    LITTLE_NIGHTMARE("Little Nightmare",8596,NpcID.LITTLE_NIGHTMARE,ItemID.LITTLE_NIGHTMARE,true,"Quite the little nightmare.\n","Killcount:mintyale:5567:March 8, 2026",8636),
    LITTLE_PARASITE("Little Parasite",8554,NpcID.LITTLE_PARASITE,ItemID.LITTLE_PARASITE,true,"So cute!\n","Killcount:mintyale:5567:March 8, 2026",8636),

    //mole
    BABY_MOLE("Baby Mole",3312,NpcID.BABY_MOLE_6635,ItemID.BABY_MOLE,true,"Keep Molin', molin' molin' molin'!\n","Killcount:Ez Klaplong:21931:October 22, 2020",590),
    BABY_MOLE_RAT("Baby Mole-rat",3312,NpcID.BABY_MOLERAT,ItemID.BABY_MOLERAT,true,"Keep Molin', molin' molin' molin'!\n","Killcount:Ez Klaplong:21931:October 22, 2020",590),

    //kq
    KALPHITE_PRINCESS("Kalphite Princess",6224,NpcID.KALPHITE_PRINCESS_6638,ItemID.KALPHITE_PRINCESS,true,"Suggests there's a king nearby.\n","Killcount:Sana:15905:November 11, 2023",588),
    KALPHITE_PRINCESS_6637("Kalphite Princess",6235,NpcID.KALPHITE_PRINCESS_6653,ItemID.KALPHITE_PRINCESS_12654,true,"Suggests there's a king nearby.\n","Killcount:Sana:15905:November 11, 2023",588),//kq chat head is messed up
    
    //Muphin
    MUPHIN("Muphin",9922,NpcID.MUPHIN,ItemID.MUPHIN,true,"An oversized grub with arms.\n","Killcount:The3rdOlive:18690:July 23, 2025",589),
    MUPHIN_27592("Muphin",9922,NpcID.MUPHIN_12006,ItemID.MUPHIN_27592,true,"An oversized grub with arms.\n","Killcount:The3rdOlive:18690:July 23, 2025",589),
    MUPHIN_27593("Muphin",9922,NpcID.MUPHIN_12007,ItemID.MUPHIN_27593,true,"An oversized grub with arms.\n","Killcount:The3rdOlive:18690:July 23, 2025",589),


    //Sarachnis
    SRARACHA("Sraracha",8004,NpcID.SRARACHA,ItemID.SRARACHA,true,"Good thing there's no such thing as Sarachnophobia.","Killcount:Solomon293:28153:November 14, 2025",610),
    SRARACHA_25842("Sraracha",8004,NpcID.SRARACHA_11157,ItemID.SRARACHA_25842,true,"Good thing there's no such thing as Sarachnophobia.\n","Killcount:Solomon293:28153:November 14, 2025",610),
    SRARACHA_25843("Sraracha",8004,NpcID.SRARACHA_11158,ItemID.SRARACHA_25843,true,"Good thing there's no such thing as Sarachnophobia.\n","Killcount:Solomon293:28153:November 14, 2025",610),

    SKOTOS("Skotos",69,NpcID.SKOTOS,ItemID.SKOTOS,false,"Spawn of Darkness.\n","Killcount:Shiny Mew:520:October 21, 2024",611),

    VORKI("Vorki",7960,NpcID.VORKI,ItemID.VORKI,false,"Three legs on my dragon...\n","Killcount:Uneven Mango:20406:March 10, 2023",7958),

    //dks
    DAGANNOTH_SUPREME_JR("Dagannoth Supreme Jr.",2855,NpcID.DAGANNOTH_SUPREME_JR,ItemID.PET_DAGANNOTH_SUPREME,false,"Wouldn't want that sleeping at the end of my bed.\n","Killcount:okaythanxbye:29732:July 21, 2025",589),
    DAGANNOTH_PRIME_JR("Dagannoth Prime Jr.",2854,NpcID.DAGANNOTH_PRIME_JR,ItemID.PET_DAGANNOTH_PRIME,false,"Has the same temper as it's father.\n","Killcount:Berserker:34629:March 21, 2021",588),
    DAGANNOTH_REX_JR("Dagannoth Rex Jr.",2853,NpcID.DAGANNOTH_REX_JR,ItemID.PET_DAGANNOTH_REX,false,"They do say if you like it you should put a ring on it.\n","Killcount:Koi:33733:February 27, 2026",588),

//WILDY-----------------------------------------------------------------------------------------------------------------------------------------------------------------

    PRINCE_BLACK_DRAGON("Prince Black Dragon",81,NpcID.PRINCE_BLACK_DRAGON,ItemID.PRINCE_BLACK_DRAGON,false,"Not quite the full royalty yet.\n","Killcount:LilReen:24214:June 24, 2026",588),
    CHAOS_ELEMENTAL_JR("Chaos Elemental Jr.",3146,NpcID.CHAOS_ELEMENTAL_JR,ItemID.PET_CHAOS_ELEMENTAL,false,"D'aw look at the liddle...\n","Killcount:Baelor Swift:3213:March 20, 2021",3144),

    //Venenatis
    VENENATIS_SPIDERLING("Venenatis spiderling",9989,NpcID.VENENATIS_SPIDERLING,ItemID.VENENATIS_SPIDERLING,true,"Vacuum proof.\n","Killcount:D1rtyy:19821:May 7, 2026",3009),
    VENENATIS_SPIDERLING_27648("Venenatis spiderling",5319,NpcID.VENENATIS_SPIDERLING_11981,ItemID.VENENATIS_SPIDERLING_27648,true,"Vacuum proof.\n","Killcount:D1rtyy:19821:May 7, 2026",3009),

    //callisto
    CALLISTO_CUB("Callisto cub",10012,NpcID.CALLISTO_CUB,ItemID.CALLISTO_CUB,true,"Bear-ly smaller than his father.\n","Killcount:RanjaForLife:23466:May 12, 2024",588),
    CALLISTO_CUB_27649("Callisto cub",4921,NpcID.CALLISTO_CUB_11982,ItemID.CALLISTO_CUB_27649,true,"Bear-ly smaller than his father.\n","Killcount:RanjaForLife:23466:May 12, 2024",588),

    //vetion
    VETION_JR("Vet'ion Jr.",9971,NpcID.VETION_JR,ItemID.VETION_JR,true,"Somehow much smoother in smaller form.\n","Killcount:TheGreat One:19227:April 20, 2023",588),//check all wildy pets and transmogs
    VETION_JR_13180("Vet'ion Jr.",9971,NpcID.VETION_JR_5537,ItemID.VETION_JR_13180,true,"Somehow much smoother in smaller form.\n","Killcount:TheGreat One:19227:April 20, 2023",588),
    VETION_JR_27650("Vet'ion Jr.",5499,NpcID.VETION_JR_11983,ItemID.VETION_JR_27650,true,"Somehow much smoother in smaller form.\n","Killcount:TheGreat One:19227:April 20, 2023",588),
    VETION_JR_27651("Vet'ion Jr.",5499,NpcID.VETION_JR_11984,ItemID.VETION_JR_27651,true,"Somehow much smoother in smaller form.\n","Killcount:TheGreat One:19227:April 20, 2023",588),

    SCORPIAS_OFFSPRING("Scorpia's offspring",6254,NpcID.SCORPIAS_OFFSPRING_5561,ItemID.SCORPIAS_OFFSPRING,false,"A scuttling little scorpion with an incredibly vicious tail.\n","Killcount:rcyy:17146:November 6, 2025",3008),

    //Royal Titans
    BRAN("Bran",11978,NpcID.BRAN,ItemID.BRAN,true,"Might have a short temper.\n","Killcount:Yeah Deadass:9536 (~8936 sacrifices):March 19, 2025",588),
    RIC("Ric",11977,NpcID.RIC,ItemID.RIC,true,"Looks like a chill guy.\n","Killcount:Yeah Deadass:9536 (~8936 sacrifices):March 19, 2025",588),

    //Yami
    YAMI("Yami",12146,NpcID.YAMI,ItemID.YAMI,false,"Small, but somehow still looks down on me.\n","Killcount:Null God:1817 (742 contracts):July 5, 2025",588),


    //SKILLING-----------------------------------------------------------------------------------------------------------------------------------------------------------------
    QUETZIN("Quetzin",10953,NpcID.QUETZIN,ItemID.QUETZIN,false,"Is this bird tailing me?","Rumors:Gath:8,770:July 4, 2026",588),

    MR_MCGROOT("Mr McGroot",5341,NpcID.MR_MCGROOT,ItemID.MR_MCGROOT,false,"He is McGroot.","Killcount:Ashes630:176,494:August 29, 2026",588),

    SOUP("Soup",13500,NpcID.SOUP,ItemID.SOUP,false,"A connoisseur of all liquid based foods.","Port Tasks:Armanyte:25897:February 23, 2026",588),

    //farming
    TANGLEROOT("Tangleroot",7314,NpcID.TANGLEROOT,ItemID.TANGLEROOT,true,"Don't be hasty.\n","Experiance:Yogololo:200,000,000:September 3, 2021",567),
    TANGLEROOT_9492("Tangleroot",7314,NpcID.TANGLEROOT_9492,ItemID.TANGLEROOT_24555,true,"Don't be hasty.\n","Experiance:Yogololo:200,000,000:September 3, 2021",567),
    TANGLEROOT_9493("Tangleroot",7314,NpcID.TANGLEROOT_9493,ItemID.TANGLEROOT_24557,true,"Don't be hasty.\n","Experiance:Yogololo:200,000,000:September 3, 2021",567),
    TANGLEROOT_9494("Tangleroot",7314,NpcID.TANGLEROOT_9494,ItemID.TANGLEROOT_24559,true,"Don't be hasty.\n","Experiance:Yogololo:200,000,000:September 3, 2021",567),
    TANGLEROOT_9495("Tangleroot",7314,NpcID.TANGLEROOT_9495,ItemID.TANGLEROOT_24561,true,"Don't be hasty.\n","Experiance:Yogololo:200,000,000:September 3, 2021",567),
    TANGLEROOT_9496("Tangleroot",7314,NpcID.TANGLEROOT_9496,ItemID.TANGLEROOT_24563,true,"Don't be hasty.\n","Experiance:Yogololo:200,000,000:September 3, 2021",567),

    //agility
    GIANT_SQUIRREL("Giant Squirrel",7311,NpcID.GIANT_SQUIRREL,ItemID.GIANT_SQUIRREL,true,"A giant squirrel with beautiful markings.\n","Laps:Mattus50:47145:October 4, 2026",614),
    DARK_SQUIRREL("Dark Squirrel",7311,NpcID.DARK_SQUIRREL,ItemID.DARK_SQUIRREL,true,"A giant squirrel with beautiful markings.\n","Laps:Mattus50:47145:October 4, 2026",614),
    BONE_SQUIRREL("Bone Squirrel",11664,NpcID.BONE_SQUIRREL,ItemID.BONE_SQUIRREL,true,"A giant squirrel with beautiful markings.\n","Laps:Mattus50:47145:October 4, 2026",614),

    //Rc
    RIFT_GUARDIAN("Rift guardian",7308,NpcID.RIFT_GUARDIAN,ItemID.RIFT_GUARDIAN,true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    RIFT_GUARDIAN_7338("Rift guardian",7308,NpcID.RIFT_GUARDIAN_7338,ItemID.RIFT_GUARDIAN_20667,true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),//check
    RIFT_GUARDIAN_7339("Rift guardian",7308,NpcID.RIFT_GUARDIAN_7339,ItemID.RIFT_GUARDIAN_20669,true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    RIFT_GUARDIAN_7340("Rift guardian",7308,NpcID.RIFT_GUARDIAN_7340,ItemID.RIFT_GUARDIAN_20671,true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    RIFT_GUARDIAN_7341("Rift guardian",7308,NpcID.RIFT_GUARDIAN_7341,ItemID.RIFT_GUARDIAN_20673,true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    RIFT_GUARDIAN_7342("Rift guardian",7308,NpcID.RIFT_GUARDIAN_7342,ItemID.RIFT_GUARDIAN_20675,true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    RIFT_GUARDIAN_7343("Rift guardian",7308,NpcID.RIFT_GUARDIAN_7343,ItemID.RIFT_GUARDIAN_20677,true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    RIFT_GUARDIAN_7344("Rift guardian",7308,NpcID.RIFT_GUARDIAN_7344,ItemID.RIFT_GUARDIAN_20679,true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    RIFT_GUARDIAN_7345("Rift guardian",7308,NpcID.RIFT_GUARDIAN_7345,ItemID.RIFT_GUARDIAN_20681,true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    RIFT_GUARDIAN_7346("Rift guardian",7308,NpcID.RIFT_GUARDIAN_7346,ItemID.RIFT_GUARDIAN_20683,true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    RIFT_GUARDIAN_7347("Rift guardian",7308,NpcID.RIFT_GUARDIAN_7347,ItemID.RIFT_GUARDIAN_20685,true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    RIFT_GUARDIAN_7348("Rift guardian",7308,NpcID.RIFT_GUARDIAN_7348,ItemID.RIFT_GUARDIAN_20687,true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    RIFT_GUARDIAN_7349("Rift guardian",7308,NpcID.RIFT_GUARDIAN_7349,ItemID.RIFT_GUARDIAN_20689,true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    RIFT_GUARDIAN_7350("Rift guardian",7308,NpcID.RIFT_GUARDIAN_7350,ItemID.RIFT_GUARDIAN_20691,true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    RIFT_GUARDIAN_7351("Rift guardian",7308,NpcID.RIFT_GUARDIAN_8024,ItemID.RIFT_GUARDIAN_21990,true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    GREATISH_GUARDIAN("Greatish guardian",9382,NpcID.GREATISH_GUARDIAN,ItemID.GREATISH_GUARDIAN,true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",589),

    //Mining
    ROCK_GOLEM("Rock Golem",-1,NpcID.ROCK_GOLEM,ItemID.ROCK_GOLEM,true,"Found somewhere between a rock and a hard place.\n","Experiance:Pikkupeppi:96,619,716:August 22, 2025",7179),
    ROCK_GOLEM_21187("Rock Golem",-1,NpcID.ROCK_GOLEM_7439,ItemID.ROCK_GOLEM_21187,true,"Found somewhere between a rock and a hard place.\n","Experiance:Pikkupeppi:96,619,716:August 22, 2025",7179),
    ROCK_GOLEM_21188("Rock Golem",-1,NpcID.ROCK_GOLEM_7440,ItemID.ROCK_GOLEM_21188,true,"Found somewhere between a rock and a hard place.\n","Experiance:Pikkupeppi:96,619,716:August 22, 2025",7179),
    ROCK_GOLEM_21189("Rock Golem",-1,NpcID.ROCK_GOLEM_7441,ItemID.ROCK_GOLEM_21189,true,"Found somewhere between a rock and a hard place.\n","Experiance:Pikkupeppi:96,619,716:August 22, 2025",7179),
    ROCK_GOLEM_21190("Rock Golem",-1,NpcID.ROCK_GOLEM_7442,ItemID.ROCK_GOLEM_21190,true,"Found somewhere between a rock and a hard place.\n","Experiance:Pikkupeppi:96,619,716:August 22, 2025",7179),
    ROCK_GOLEM_21191("Rock Golem",-1,NpcID.ROCK_GOLEM_7443,ItemID.ROCK_GOLEM_21191,true,"Found somewhere between a rock and a hard place.\n","Experiance:Pikkupeppi:96,619,716:August 22, 2025",7179),
    ROCK_GOLEM_21192("Rock Golem",-1,NpcID.ROCK_GOLEM_7444,ItemID.ROCK_GOLEM_21192,true,"Found somewhere between a rock and a hard place.\n","Experiance:Pikkupeppi:96,619,716:August 22, 2025",7179),
    ROCK_GOLEM_21193("Rock Golem",-1,NpcID.ROCK_GOLEM_7445,ItemID.ROCK_GOLEM_21193,true,"Found somewhere between a rock and a hard place.\n","Experiance:Pikkupeppi:96,619,716:August 22, 2025",7179),
    ROCK_GOLEM_21194("Rock Golem",-1,NpcID.ROCK_GOLEM_7446,ItemID.ROCK_GOLEM_21194,true,"Found somewhere between a rock and a hard place.\n","Experiance:Pikkupeppi:96,619,716:August 22, 2025",7179),
    ROCK_GOLEM_21195("Rock Golem",-1,NpcID.ROCK_GOLEM_7447,ItemID.ROCK_GOLEM_21195,true,"Found somewhere between a rock and a hard place.\n","Experiance:Pikkupeppi:96,619,716:August 22, 2025",7179),
    ROCK_GOLEM_21196("Rock Golem",-1,NpcID.ROCK_GOLEM_7448,ItemID.ROCK_GOLEM_21196,true,"Found somewhere between a rock and a hard place.\n","Experiance:Pikkupeppi:96,619,716:August 22, 2025",7179),
    ROCK_GOLEM_21197("Rock Golem",-1,NpcID.ROCK_GOLEM_7449,ItemID.ROCK_GOLEM_21197,true,"Found somewhere between a rock and a hard place.\n","Experiance:Pikkupeppi:96,619,716:August 22, 2025",7179),
    ROCK_GOLEM_21198("Rock Golem",-1,NpcID.ROCK_GOLEM_7450,ItemID.ROCK_GOLEM_21340,true,"Found somewhere between a rock and a hard place.\n","Experiance:Pikkupeppi:96,619,716:August 22, 2025",7179),
    ROCK_GOLEM_21199("Rock Golem",-1,NpcID.ROCK_GOLEM_7451,ItemID.ROCK_GOLEM_21358,true,"Found somewhere between a rock and a hard place.\n","Experiance:Pikkupeppi:96,619,716:August 22, 2025",7179),
    ROCK_GOLEM_21200("Rock Golem",-1,NpcID.ROCK_GOLEM_7452,ItemID.ROCK_GOLEM_21359,true,"Found somewhere between a rock and a hard place.\n","Experiance:Pikkupeppi:96,619,716:August 22, 2025",7179),
    ROCK_GOLEM_21201("Rock Golem",-1,NpcID.ROCK_GOLEM_7453,ItemID.ROCK_GOLEM_21360,true,"Found somewhere between a rock and a hard place.\n","Experiance:Pikkupeppi:96,619,716:August 22, 2025",7179),

    //fishing
    HERON("Heron",6775,NpcID.HERON,ItemID.HERON,true,"A long-legged bird that likes to fish.\n","Experiance:Thurbs:200,000,000:October 11, 2023",7),
    GREAT_BLUE_HERON("Great blue heron",6775,NpcID.GREAT_BLUE_HERON,ItemID.GREAT_BLUE_HERON,true,"A long-legged bird that likes to fish.\n","Experiance:Thurbs:200,000,000:October 11, 2023",7),

    BEAVER("Beaver",-1,NpcID.BEAVER,ItemID.BEAVER,false,"Looks like it's gotten through a lot of wood.\n","Experiance:Vinny:177,611,398:October 11, 2020",6551),

    //chin
    BABY_CHINCHOMPA("Baby Chinchompa",5185,NpcID.BABY_CHINCHOMPA_6756,ItemID.BABY_CHINCHOMPA,true,"Fluffy and cute, keep away from fire!\n","Experiance:Bloomin:200,000,000:May 2, 2024",7182),
    BABY_CHINCHOMPA_13324("Baby Chinchompa",5185,NpcID.BABY_CHINCHOMPA,ItemID.BABY_CHINCHOMPA_13324,true,"Fluffy and cute, keep away from fire!\n","Experiance:Bloomin:200,000,000:May 2, 2024",7182),
    BABY_CHINCHOMPA_13325("Baby Chinchompa",5185,NpcID.BABY_CHINCHOMPA_6758,ItemID.BABY_CHINCHOMPA_13325,true,"Fluffy and cute, keep away from fire!\n","Experiance:Bloomin:200,000,000:May 2, 2024",7182),
    BABY_CHINCHOMPA_13326("Baby Chinchompa",5185,NpcID.BABY_CHINCHOMPA_6759,ItemID.BABY_CHINCHOMPA_13326,true,"Fluffy and cute, keep away from fire!\n","Experiance:Bloomin:200,000,000:May 2, 2024",7182),

    //theiving
    ROCKY("Rocky",7318,NpcID.ROCKY,ItemID.ROCKY,true,"Raccoons, like pandas but worse.\n","Experiance:Stashes:200,000,000:December 26, 2020",588),
    RED("Red",7318,NpcID.RED,ItemID.RED,true,"Red panda, like pandas but red.\n","Experiance:Stashes:200,000,000:December 26, 2020",588),
    ZIGGY("Ziggy",7318,NpcID.ZIGGY,ItemID.ZIGGY,true,"No relation to a regular raccoon.\n","Experiance:Stashes:200,000,000:December 26, 2020",588),

    //WT
    PHOENIX("Phoenix",6811,NpcID.PHOENIX_7370,ItemID.PHOENIX,true,"The essence of fire.\n","Killcount:Uneven Mango:15188:June 26, 2022",7),
    PHOENIX_24483("Phoenix",6811,NpcID.PHOENIX_3081,ItemID.PHOENIX_24483,true,"The essence of fire.\n","Killcount:Uneven Mango:15188:June 26, 2022",7),
    PHOENIX_24484("Phoenix",6811,NpcID.PHOENIX_3078,ItemID.PHOENIX_24484,true,"The essence of fire.\n","Killcount:Uneven Mango:15188:June 26, 2022",7),
    PHOENIX_24485("Phoenix",6811,NpcID.PHOENIX_3079,ItemID.PHOENIX_24485,true,"The essence of fire.\n","Killcount:Uneven Mango:15188:June 26, 2022",7),
    PHOENIX_24486("Phoenix",6811,NpcID.PHOENIX_3080,ItemID.PHOENIX_24486,true,"The essence of fire.\n","Killcount:Uneven Mango:15188:June 26, 2022",7),

    HERBI("Herbi",7696,NpcID.HERBI,ItemID.HERBI,false,"A boar with an impressive mane of dried herbs.\n","Killcount:Must Slay:46599:April 18, 2026",590),

    SMOLCANO("Smolcano",8433,NpcID.SMOLCANO,ItemID.SMOLCANO,false,"Not so threatening at this size.\n","Killcount:saddest cat:15987:December 1, 2025",588),//need to get

    TINY_TEMPOR("Tiny Tempor",8905,NpcID.TINY_TEMPOR,ItemID.TINY_TEMPOR,false,"So smol, so anger.\n","Permits:Squiremarcus:54742:April 15, 2026",8907),

    ABYSSAL_PROTECTOR("Abyssal protector",2186,NpcID.ABYSSAL_PROTECTOR,ItemID.ABYSSAL_PROTECTOR,false,"Your own personal abyssal horror.\n","Searches:Flap Slap:24468:October 6, 2025",614),

    //MISC-----------------------------------------------------------------------------------------------------------------------------------------------------------------
    PENANCE_PET("Penance Pet",5411,NpcID.PENANCE_PET,ItemID.PET_PENANCE_QUEEN,false,"Run away! Run aw... wait, it's tiny...\n","Gambles:Lvls Up:6114:April 3, 2026",589),

    BLOODHOUND("Bloodhound",6559,NpcID.BLOODHOUND,ItemID.BLOODHOUND,false,"Tracking down clues with logic and a pipe.\n","Caskets:Kacy:6210:April 1, 2020",4479),

    CHOMPY_CHICK("Chompy chick",6761,NpcID.CHOMPY_CHICK,ItemID.CHOMPY_CHICK,false,"A small boisterous bird, a delicacy for ogres.\n","Killcount:MrNice98:18563:April 1, 2026",6764),

    LIL_CREATOR("Lil' Creator",8844,NpcID.LIL_CREATOR,ItemID.LIL_CREATOR,true,"A pint-sized bringer of existence.\n","Crates:8 PM EST:3248:June 25, 2022",8849),
    LIL_DESTRUCTOR("Lil' Destructor",8840,NpcID.LIL_DESTRUCTOR,ItemID.LIL_DESTRUCTOR,true,"Harbinger of light surface scratches and minor dents.\n","Crates:8 PM EST:3248:June 25, 2022",8848),



    //Special Requests
    MOCHI("Mochi", 6562, NpcID.ARCHIBALD, ItemID.SUNSHINE, false, "A tiny, fluffy Yorkie with a big personality.", "Treats:Anfoone:5:A Walk", 600),


    ;




    final String name;
    final int attkAnim;

    final int NpcId;
    final int iconID;

    final boolean metamorph;
    final String examine;
    final String driestPerson;
    final int chatHeadAnimID;


    private @Setter int scale;
    private @Setter int size;
    private @Setter int footprintSize;

    private @Setter int[] modelIDs;
    private @Setter short[] colorToReplace;
    private @Setter short[] colorToReplaceWith;
    private @Setter int ambient;
    private @Setter int contrast;

    private @Setter int idleAnim;
    private @Setter int walkAnim;
    private @Setter int runAnim;

    static
    {
        ImmutableMap.Builder<String,PetData> builder = new ImmutableMap.Builder<>();
        for (PetData petData:values())
        {
            builder.put(petData.toString(), petData);
        }
        pets = builder.build();
    }

    public static final Map<String, PetData>pets;
    public static final Map<PetData, PetData>morphModel = new HashMap<>();

    //RAIDS
    //SLAYER
    //GWD
    //OTHER BOSS PETS
    //SKILLING PETS
    //MISC

    public static final List<PetData> petsToShow = Arrays.asList(

            //RAIDS----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            OLMLET,
            LIL_ZIK,
            TUMEKENS_GUARDIAN,
            ORION,

            //SLAYER----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            ABYSSAL_ORPHAN,
            HELLPUPPY,
            NOON,
            IKKLE_HYDRA,
            PET_SMOKE_DEVIL_6655,
            KRAKEN,
            RAX,

            //GWD----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            GENERAL_GRAARDOR_JR,
            KREEARRA_JR,
            ZILYANA_JR,
            KRIL_TSUTSAROTH_JR,
            NEXLING,

            //Other Boss pets----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

            SMOL_HEREDIT,
            DOM,
            JAL_NIB_REK,
            TZREK_JAD,
            YOUNGLLEF,
            CORPOREAL_CRITTER,
            SNAKELING_GREEN,
            LITTLE_NIGHTMARE,
            BABY_MOLE,
            KALPHITE_PRINCESS,
            MUPHIN,
            SRARACHA,
            SKOTOS,
            VORKI,
            MOXI,
            HUBERTE,
            SCURRY,
            DAGANNOTH_SUPREME_JR,
            DAGANNOTH_PRIME_JR,
            DAGANNOTH_REX_JR,
            BRAN,
            YAMI,
            AGGY,
            MAGGOT_MARQUESS,
            BEEF,
            GULLIVER,

            //DT2--------------------------------------------------------------
            BARON,
            BUTCH,
            WISP,
            LILVIATHAN,

            //WILDY-----------------------------------------------------------------------------------------------------------------------------------------------------------------

            PRINCE_BLACK_DRAGON,
            CHAOS_ELEMENTAL_JR,
            VENENATIS_SPIDERLING,
            CALLISTO_CUB,
            VETION_JR,
            VETION_JR_27650,
            SCORPIAS_OFFSPRING,

            //SKILLING-----------------------------------------------------------------------------------------------------------------------------------------------------------------

            TANGLEROOT,
            GIANT_SQUIRREL,
            RIFT_GUARDIAN,
            GREATISH_GUARDIAN,
            ROCK_GOLEM,
            HERON,
            BEAVER,
            BABY_CHINCHOMPA,
            ROCKY,
            PHOENIX,
            HERBI,
            SMOLCANO,
            TINY_TEMPOR,
            ABYSSAL_PROTECTOR,
            QUETZIN,
            MR_MCGROOT,
            SOUP,

            //MISC-----------------------------------------------------------------------------------------------------------------------------------------------------------------
            PENANCE_PET,
            BLOODHOUND,
            CHOMPY_CHICK,
            LIL_CREATOR

            );



    static
    {
        //zulrah
        morphModel.put(SNAKELING_RED,SNAKELING_GREEN);
        morphModel.put(SNAKELING_BLUE,SNAKELING_RED);
        morphModel.put(SNAKELING_GREEN,SNAKELING_BLUE);

        //Inferno
        morphModel.put(TZREK_ZUK,JAL_NIB_REK);
        morphModel.put(JAL_NIB_REK,TZREK_ZUK);

        //Jad
        morphModel.put(TZREK_JAD,JALREK_JAD);
        morphModel.put(JALREK_JAD,TZREK_JAD);

        //Royal Titans
        morphModel.put(BRAN,RIC);
        morphModel.put(RIC,BRAN);

        //Rc
        morphModel.put(RIFT_GUARDIAN,RIFT_GUARDIAN_7338);
        morphModel.put(RIFT_GUARDIAN_7338,RIFT_GUARDIAN_7339);
        morphModel.put(RIFT_GUARDIAN_7339,RIFT_GUARDIAN_7340);
        morphModel.put(RIFT_GUARDIAN_7340,RIFT_GUARDIAN_7341);
        morphModel.put(RIFT_GUARDIAN_7341,RIFT_GUARDIAN_7342);
        morphModel.put(RIFT_GUARDIAN_7342,RIFT_GUARDIAN_7343);
        morphModel.put(RIFT_GUARDIAN_7343,RIFT_GUARDIAN_7344);
        morphModel.put(RIFT_GUARDIAN_7344,RIFT_GUARDIAN_7345);
        morphModel.put(RIFT_GUARDIAN_7345,RIFT_GUARDIAN_7346);
        morphModel.put(RIFT_GUARDIAN_7346,RIFT_GUARDIAN_7347);
        morphModel.put(RIFT_GUARDIAN_7347,RIFT_GUARDIAN_7348);
        morphModel.put(RIFT_GUARDIAN_7348,RIFT_GUARDIAN_7349);
        morphModel.put(RIFT_GUARDIAN_7349,RIFT_GUARDIAN_7350);
        morphModel.put(RIFT_GUARDIAN_7350,RIFT_GUARDIAN_7351);
        morphModel.put(RIFT_GUARDIAN_7351,GREATISH_GUARDIAN);
        morphModel.put(GREATISH_GUARDIAN,RIFT_GUARDIAN);

        //Farming
        morphModel.put(TANGLEROOT,TANGLEROOT_9492);
        morphModel.put(TANGLEROOT_9492,TANGLEROOT_9493);
        morphModel.put(TANGLEROOT_9493,TANGLEROOT_9494);
        morphModel.put(TANGLEROOT_9494,TANGLEROOT_9495);
        morphModel.put(TANGLEROOT_9495,TANGLEROOT_9496);
        morphModel.put(TANGLEROOT_9496,TANGLEROOT);

        //Agility
        morphModel.put(GIANT_SQUIRREL,DARK_SQUIRREL);
        morphModel.put(DARK_SQUIRREL,GIANT_SQUIRREL);

        //Mining
        morphModel.put(ROCK_GOLEM,ROCK_GOLEM_21187);
        morphModel.put(ROCK_GOLEM_21187,ROCK_GOLEM_21188);
        morphModel.put(ROCK_GOLEM_21188,ROCK_GOLEM_21189);
        morphModel.put(ROCK_GOLEM_21189,ROCK_GOLEM_21190);
        morphModel.put(ROCK_GOLEM_21190,ROCK_GOLEM_21191);
        morphModel.put(ROCK_GOLEM_21191,ROCK_GOLEM_21192);
        morphModel.put(ROCK_GOLEM_21192,ROCK_GOLEM_21193);
        morphModel.put(ROCK_GOLEM_21193,ROCK_GOLEM_21194);
        morphModel.put(ROCK_GOLEM_21194,ROCK_GOLEM_21195);
        morphModel.put(ROCK_GOLEM_21195,ROCK_GOLEM_21196);
        morphModel.put(ROCK_GOLEM_21196,ROCK_GOLEM_21197);
        morphModel.put(ROCK_GOLEM_21197,ROCK_GOLEM_21198);
        morphModel.put(ROCK_GOLEM_21198,ROCK_GOLEM_21199);
        morphModel.put(ROCK_GOLEM_21199,ROCK_GOLEM_21200);
        morphModel.put(ROCK_GOLEM_21200,ROCK_GOLEM_21201);
        morphModel.put(ROCK_GOLEM_21201,ROCK_GOLEM);

        //mole
        morphModel.put(BABY_MOLE,BABY_MOLE_RAT);
        morphModel.put(BABY_MOLE_RAT,BABY_MOLE);

        //muphin
        morphModel.put(MUPHIN,MUPHIN_27592);
        morphModel.put(MUPHIN_27592,MUPHIN_27593);
        morphModel.put(MUPHIN_27593,MUPHIN);

        //TOA
        morphModel.put(TUMEKENS_GUARDIAN,TUMEKENS_DAMAGED_GUARDIAN);
        morphModel.put(TUMEKENS_DAMAGED_GUARDIAN,ELIDINIS_GUARDIAN);
        morphModel.put(ELIDINIS_GUARDIAN,ELIDINIS_DAMAGED_GUARDIAN);
        morphModel.put(ELIDINIS_DAMAGED_GUARDIAN,AKKHITO);
        morphModel.put(AKKHITO,BABI);
        morphModel.put(BABI,KEPHRITI);
        morphModel.put(KEPHRITI,ZEBO);
        morphModel.put(ZEBO,TUMEKENS_GUARDIAN);

        //TFA
        morphModel.put(ORION,ORION_ROBED);
        morphModel.put(ORION_ROBED,ORION);

        //Soul Wars
        morphModel.put(LIL_CREATOR,LIL_DESTRUCTOR);
        morphModel.put(LIL_DESTRUCTOR,LIL_CREATOR);

        //Gauntlet
        morphModel.put(YOUNGLLEF,CORRUPTED_YOUNGLLEF);
        morphModel.put(CORRUPTED_YOUNGLLEF,YOUNGLLEF);

        //Sarachnis
        morphModel.put(SRARACHA,SRARACHA_25842);
        morphModel.put(SRARACHA_25842,SRARACHA_25843);
        morphModel.put(SRARACHA_25843,SRARACHA);

        //Hydra
        morphModel.put(IKKLE_HYDRA,IKKLE_HYDRA_22748);
        morphModel.put(IKKLE_HYDRA_22748,IKKLE_HYDRA_22750);
        morphModel.put(IKKLE_HYDRA_22750,IKKLE_HYDRA_22752);
        morphModel.put(IKKLE_HYDRA_22752,IKKLE_HYDRA);

        //tob
        morphModel.put(LIL_ZIK,LIL_MAIDEN);
        morphModel.put(LIL_MAIDEN,LIL_BLOAT);
        morphModel.put(LIL_BLOAT,LIL_NYLO);
        morphModel.put(LIL_NYLO,LIL_SOT);
        morphModel.put(LIL_SOT,LIL_XARP);
        morphModel.put(LIL_XARP,LIL_ZIK);

        //nm
        morphModel.put(LITTLE_NIGHTMARE,LITTLE_PARASITE);
        morphModel.put(LITTLE_PARASITE,LITTLE_NIGHTMARE);

        //GG's
        morphModel.put(NOON,MIDNIGHT);
        morphModel.put(MIDNIGHT,NOON);

        //cox
        morphModel.put(OLMLET,PUPPADILE);
        morphModel.put(PUPPADILE,TEKTINY);
        morphModel.put(TEKTINY,ENRAGED_TEKTINY);
        morphModel.put(ENRAGED_TEKTINY,VANGUARD);
        morphModel.put(VANGUARD,VASA_MINIRIO);
        morphModel.put(VASA_MINIRIO,VESPINA);
        morphModel.put(VESPINA,FLYING_VESPINA);
        morphModel.put(FLYING_VESPINA,OLMLET);

        //Phoenix
        morphModel.put(PHOENIX,PHOENIX_24483);
        morphModel.put(PHOENIX_24483,PHOENIX_24484);
        morphModel.put(PHOENIX_24484,PHOENIX_24485);
        morphModel.put(PHOENIX_24485,PHOENIX_24486);
        morphModel.put(PHOENIX_24486,PHOENIX);

        //rocky
        morphModel.put(ROCKY,RED);
        morphModel.put(RED,ZIGGY);
        morphModel.put(ZIGGY,ROCKY);

        //Chin pet
        morphModel.put(BABY_CHINCHOMPA,BABY_CHINCHOMPA_13324);
        morphModel.put(BABY_CHINCHOMPA_13324,BABY_CHINCHOMPA_13325);
        morphModel.put(BABY_CHINCHOMPA_13325,BABY_CHINCHOMPA_13326);
        morphModel.put(BABY_CHINCHOMPA_13326,BABY_CHINCHOMPA);

        //Fishing pet
        morphModel.put(HERON,GREAT_BLUE_HERON);
        morphModel.put(GREAT_BLUE_HERON,HERON);

        //vetion
        morphModel.put(VETION_JR,VETION_JR_13180);
        morphModel.put(VETION_JR_13180,VETION_JR_27650);
        morphModel.put(VETION_JR_27650,VETION_JR_27651);
        morphModel.put(VETION_JR_27651,VETION_JR);

        //callisto
        morphModel.put(CALLISTO_CUB,CALLISTO_CUB_27649);
        morphModel.put(CALLISTO_CUB_27649,CALLISTO_CUB);

        //venenatis
        morphModel.put(VENENATIS_SPIDERLING,VENENATIS_SPIDERLING_27648);
        morphModel.put(VENENATIS_SPIDERLING_27648,VENENATIS_SPIDERLING);

        //kq
        morphModel.put(KALPHITE_PRINCESS,KALPHITE_PRINCESS_6637);
        morphModel.put(KALPHITE_PRINCESS_6637,KALPHITE_PRINCESS);

        //thermy
        morphModel.put(PET_SMOKE_DEVIL,PET_SMOKE_DEVIL_6655);
        morphModel.put(PET_SMOKE_DEVIL_6655,PET_SMOKE_DEVIL);

        //arraxor
        morphModel.put(RAX,NID);
        morphModel.put(NID,RAX);

        //corp
        morphModel.put(DARK_CORE,CORPOREAL_CRITTER);
        morphModel.put(CORPOREAL_CRITTER,DARK_CORE);

        //Gull
        morphModel.put(GULL,GULLIVER);
        morphModel.put(GULLIVER,GULL);

    }


}
