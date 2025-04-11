package com.example;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.api.ItemID;
import net.runelite.api.NpcID;

import java.util.*;

@AllArgsConstructor
@Getter
public enum PetData {

    //RAIDS
    //SLAYER
    //GWD
    //OTHER BOSS PETS
    //SKILLING PETS

    //Recolor ids first color to find, second half color to replace

    //COX----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    OLMLET("Olmlet","Olmlet",true,7398,14,NpcID.OLMLET,ItemID.OLMLET,Lists.newArrayList(32697),1,7396,7395,7395,64,-1,-1,null,true,"The most cuddly Spawn of the Guardian in the Deep.","Purples:R2xMVP:223+:July 17,2022",588),//need to get all these
    PUPPADILE("Puppadile","Puppadile",true,7422,22,NpcID.PUPPADILE,ItemID.PUPPADILE,Lists.newArrayList(32681),1,7417,7982,7982,45,-1,-1,null,true,"A puppy of a mutated guardian of Xeric.","Killcount:RoyalRain:1063:Unknown",588),
    TEKTINY("Tektiny","Tektiny",true,7483,29,NpcID.TEKTINY,ItemID.TEKTINY,Lists.newArrayList(32682),1,7476,7983,7983,25,10,5,null,true,"Xeric's former artisan's former helper.\n","Killcount:RoyalRain:1063:Unknown",588),
    ENRAGED_TEKTINY("Enraged Tektiny","Enraged Tektiny",true,7493,29,NpcID.ENRAGED_TEKTINY,ItemID.ENRAGED_TEKTINY,Lists.newArrayList(32682),1,7485,8637,8637,25,10,5,null,true,"Xeric's former artisan's former helper.\n","Killcount:RoyalRain:1063:Unknown",588),
    VANGUARD("Vanguard","Vanguard",false,7433,34,NpcID.VANGUARD,ItemID.VANGUARD,Lists.newArrayList(32684),1,7430,7984,7984,40,-1,-1,null,true,"A very small member of Xeric's elite tactical unit.\n","Killcount:RoyalRain:1063:Unknown",588),
    VASA_MINIRIO("Vasa Minirio","Vasa Minirio",false,7409,39,NpcID.VASA_MINIRIO,ItemID.VASA_MINIRIO,Lists.newArrayList(32680),1,7416,7985,7985,20,-1,-1,null,true,"The son of a former High Priest, fused with the rock and bound to the dark crystals.\n","Killcount:RoyalRain:1063:Unknown",588),
    VESPINA("Vespina","Vespina",false,7450,14,NpcID.VESPINA,ItemID.VESPINA,Lists.newArrayList(32689),1,7449,7986,7986,20,-1,-1,null,true,"Princess of the Abyssal Vespine.\n","Killcount:RoyalRain:1063:Unknown",588),
    FLYING_VESPINA("Flying Vespina","Flying Vespina",true,7454,19,NpcID.FLYING_VESPINA,ItemID.FLYING_VESPINA,Lists.newArrayList(32689),1,8639,8639,8639,20,-1,-1,null,true,"Princess of the Abyssal Vespine.\n","Killcount:RoyalRain:1063:Unknown",588),

    //TOB----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    LIL_ZIK("Lil' Zik","Lil' Zik",false,8123,22,NpcID.LIL_ZIK,ItemID.LIL_ZIK,Lists.newArrayList(35381),1,8120,8122,8122,20,-1,-1,null,true,"What has eight legs and runs a Vampyric Theatre?\n","Killcount:D I R T Y Ko:3553:February 19, 2021",611),
    LIL_MAIDEN("Lil' Maiden","Lil' Maiden",false,8092,31,NpcID.LIL_MAIDEN,ItemID.LIL_MAIDEN,Lists.newArrayList(42280),1,8090,8090,8090,30,-1,-1,null,true,"Freed from her torturous constraints.\n","Killcount:Rayne Drop:1582:Unknown",610),
    LIL_BLOAT("Lil' Bloat","Lil' Bloat",false,-1,-1,NpcID.LIL_BLOAT,ItemID.LIL_BLOAT,Lists.newArrayList(35404),1,8080,9031,9031,25,-1,-1,null,true,"Smaller size, same smell.\n","Killcount:Rayne Drop:1582:Unknown",588),
    LIL_NYLO("Lil' Nylo","Lil' Nylo",true,8004,17,NpcID.LIL_NYLO,ItemID.LIL_NYLO,Lists.newArrayList(35183),1,8002,8003,8003,35,-1,-1,null,true,"Eight legs of unparalleled loyalty.\n","Killcount:Rayne Drop:1582:Unknown",544),
    LIL_SOT("Lil' Sot","Lil' Sot",false,8138,13,NpcID.LIL_SOT,ItemID.LIL_SOT,Lists.newArrayList(35403),1,8137,9032,9032,30,-1,-1,null,true,"Has a lot of anger for such a small monster.\n","Killcount:Rayne Drop:1582:Unknown",614),
    LIL_XARP("Lil' Xarp","Lil' Xarp",false,8059,20,NpcID.LIL_XARP,ItemID.LIL_XARP,Lists.newArrayList(35383),1,9033,9033,9033,20,-1,-1,null,true,"The prince of Yarasa.\n","Killcount:Rayne Drop:1582:Unknown",554),

    //TOA----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    TUMEKENS_GUARDIAN("Tumeken's Guardian","Tumeken's Guardian",false,9660,119,NpcID.TUMEKENS_GUARDIAN,ItemID.TUMEKENS_GUARDIAN,Lists.newArrayList(46337),1,9655,9651,9651,65,-1,-1,null,true,"A tiny automaton imbued with a trace of Tumeken's power.\n","Killcount:Karibola:1472:January 9, 2023",591),
    //look at below attk anim
    TUMEKENS_DAMAGED_GUARDIAN("Tumeken's Damaged Guardian","Tumeken's Damaged Guardian",false,9660,119,NpcID.TUMEKENS_DAMAGED_GUARDIAN,ItemID.TUMEKENS_DAMAGED_GUARDIAN,Lists.newArrayList(46333),1,9420,9420,9420,65,-1,-1,null,true,"A tiny automaton imbued with a trace of Tumeken's power.\n","Killcount:Karibola:1472:January 9, 2023",588),
    ELIDINIS_GUARDIAN("Elidinis' Guardian","Elidinis' Guardian",false,9660,119,NpcID.ELIDINIS_GUARDIAN,ItemID.ELIDINIS_GUARDIAN,Lists.newArrayList(46332),1,9656,9652,9652,65,-1,-1,null,true,"A tiny automaton imbued with a trace of Elidinis' power.\n","Killcount:Karibola:1472:January 9, 2023",591),
    ELIDINIS_DAMAGED_GUARDIAN("Elidinis' Damaged Guardian","Elidinis' Damaged Guardian",false,9660,119,NpcID.ELIDINIS_DAMAGED_GUARDIAN,ItemID.ELIDINIS_DAMAGED_GUARDIAN,Lists.newArrayList(46332),1,9420,9420,9420,65,-1,-1,null,true,"A tiny automaton imbued with a trace of Elidinis' power.\n","Killcount:Karibola:1472:January 9, 2023",588),
    //check for 180 rotate for below
    AKKHITO("Akkhito","Akkhito",false,9770,59,NpcID.AKKHITO,ItemID.AKKHITO,Lists.newArrayList(46360,46356,46357),1,9760,9421,9421,58,-1,-1,null,true,"Small, but still strong.\n","Killcount:Karibola:1472:January 9, 2023",588),
    BABI("Babi","Babi",false,9744,89,NpcID.BABI,ItemID.BABI,Lists.newArrayList(46352,46350),1,9741,9739,9739,36,-1,-1,null,true,"Her mother's daughter. Fond of bananas.\n","Killcount:Karibola:1472:January 9, 2023",555),
    KEPHRITI("Kephriti","Kephriti",false,9578,89,NpcID.KEPHRITI,ItemID.KEPHRITI,Lists.newArrayList(46417),1,9572,9419,9419,38,-1,40,null,true,"The tiniest bug can still make worlds fall over.\n","Killcount:Karibola:1472:January 9, 2023",579),
    ZEBO("Zebo","Zebo",true,2039,11,NpcID.ZEBO,ItemID.ZEBO,Lists.newArrayList(46509),2,2037,2036,2036,112,-1,-1,null,true,"Chomp.\n","Killcount:Karibola:1472:January 9, 2023",588),


    //SLAYER----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    ABYSSAL_ORPHAN("Abyssal Orphan","Abyssal Orphan",true,7126,18,NpcID.ABYSSAL_ORPHAN,ItemID.ABYSSAL_ORPHAN,Lists.newArrayList(29631),1,7125,7124,7124,-1,-1,-1,null,false,"Born in the death throes of an Abyssal Sire.\n","Killcount:Maximumist:15728:Unknown",7130),

    HELLPUPPY("Hellpuppy","Hellpuppy",true,6562,9,NpcID.HELLPUPPY,ItemID.HELLPUPPY,Lists.newArrayList(29240),1,6561,6560,6560,-1,-1,-1,null,false,"A fiery little pup!\n","Killcount:Killed Elmo:15237:May 29, 2020",3411),

    NOON("Noon","Noon",false,7770,44,NpcID.NOON,ItemID.NOON,Lists.newArrayList(34183),1,7768,7768,7768,34,-1,-1,null,true,"The mini Guardian of Dawn!\n","Killcount:VB TINNS:17766:February 21, 2022",7805),//need to check -----
    MIDNIGHT("Midnight","Midnight",false,7808,21,NpcID.MIDNIGHT,ItemID.MIDNIGHT,Lists.newArrayList(34187),1,7807,7806,7806,34,-1,-1,null,true,"The mini Guardian of Dusk!\n","Killcount:VB TINNS:17766:February 21, 2022",7804),

    IKKLE_HYDRA("Ikkle Hydra","Ikkle Hydra",false,8234,16,NpcID.IKKLE_HYDRA,ItemID.IKKLE_HYDRA,Lists.newArrayList(36185),1,8233,8296,8296,20,-1,-1,null,true,"How does it not fall over?\n","Killcount:Mepn:20191:September 29, 2022",8265),
    IKKLE_HYDRA_22748("Ikkle Hydra 22748","Ikkle Hydra",false,8241,16,NpcID.IKKLE_HYDRA_8493,ItemID.IKKLE_HYDRA_22748,Lists.newArrayList(36192),1,8298,8297,8297,20,-1,-1,null,true,"How does it not fall over?\n","Killcount:Mepn:20191:September 29, 2022",8265),
    IKKLE_HYDRA_22750("Ikkle Hydra 22750","Ikkle Hydra",false,8248,16,NpcID.IKKLE_HYDRA_8494,ItemID.IKKLE_HYDRA_22750,Lists.newArrayList(36188),1,8247,8299,8299,20,-1,-1,null,true,"How does it not fall over?\n","Killcount:Mepn:20191:September 29, 2022",8265),
    IKKLE_HYDRA_22752("Ikkle Hydra 22752","Ikkle Hydra",false,8256,16,NpcID.IKKLE_HYDRA_8495,ItemID.IKKLE_HYDRA_22752,Lists.newArrayList(36191),1,8254,8300,8300,20,-1,-1,null,true,"How does it not fall over?\n","Killcount:Mepn:20191:September 29, 2022",8265),

    PET_SMOKE_DEVIL_6655("Pet Smoke Devil 6655","Pet Smoke Devil",true,3847,10,NpcID.SMOKE_DEVIL_6639,ItemID.PET_SMOKE_DEVIL,Lists.newArrayList(36012),1,1829,1828,1828,35,25,25,null,true,"Woah.","Killcount:Suede:15380:April 19, 2021",588),
    PET_SMOKE_DEVIL("Pet Smoke Devil","Pet Smoke Devil",true,3847,10,NpcID.SMOKE_DEVIL,ItemID.PET_SMOKE_DEVIL_22663,Lists.newArrayList(28442),1,1829,1828,1828,60,25,25,null,true,"Woah.","Killcount:Suede:15380:April 19, 2021",588),

    KRAKEN("Kraken","Kraken",true,3992,17,NpcID.KRAKEN,ItemID.PET_KRAKEN,Lists.newArrayList(28231),1,3989,3989,3989,15,30,30,null,false,"We'll fight, until world's end.\n","Killcount:Kraken Wall:26510:July 14, 2020",588),

    RAX("Rax","Rax",true,8147,18,NpcID.RAX,ItemID.RAX,Lists.newArrayList(44936),1,8340,9139,9139,64,-1,-1,null,true,"Really hope she doesn't crawl into my mouth while I sleep.\n","Killcount:Hallisa:10394:September 27, 2024",588),
    NID("Nid","Nid",true,11476,20,NpcID.NID,ItemID.NID,Lists.newArrayList(54289),1,11473,11474,11474,25,-1,-1,null,true,"Looks like a respectable gentleman.\n","Killcount:Hallisa:10394:September 27, 2024",588),

    //GWD----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    GENERAL_GRAARDOR_JR("General Graardor Jr.","General Graardor Jr.",true,7021,15,NpcID.GENERAL_GRAARDOR_JR,ItemID.PET_GENERAL_GRAARDOR,Lists.newArrayList(27660,27665),1,7017,7016,7016,30,30,30,null,false,"Totally unintelligible.\n","Killcount:Malfoy:19007:October 25, 2019",589),
    KREEARRA_JR("Kree'arra Jr.","Kree'arra Jr.",false,6981,13,NpcID.KREEARRA_JR,ItemID.PET_KREEARRA,Lists.newArrayList(28019,28021,28020),1,7166,7167,7167,30,30,30,null,false,"Is it a bird, is it... no it's a bird.\n","Killcount:Runecraft:20109:May 25, 2020",588),
    ZILYANA_JR("Zilyana Jr.","Zilyana Jr.",true,6967,16,NpcID.ZILYANA_JR,ItemID.PET_ZILYANA,Lists.newArrayList(27989,27937,27985,27968,27990),1,6966,6965,6965,60,30,30,null,false,"Somehow a junior even though she's named after her spawn mother!","Killcount:Letsjjj:26472:January 17, 2022",588),
    KRIL_TSUTSAROTH_JR("K'ril Tsutsaroth Jr.","K'ril Tsutsaroth Jr.",false,6948,18,NpcID.KRIL_TSUTSAROTH_JR,ItemID.PET_KRIL_TSUTSAROTH,Lists.newArrayList(27683,27681,27692,27682,27690),1,6935,4070,4070,20,30,30,null,false,"Where did he even come from?","Killcount:Xl3g3ndgOdX:21654:February 18, 2023",588),//check
    NEXLING("Nexling","Nexling",true,9182,35,NpcID.NEXLING,ItemID.NEXLING,Lists.newArrayList(43209),1,9177,9176,9176,48,-1,-1,null,false,"The gods don't quite fear this one.\n","Shards:Homer LT 0_o:17913:August 15, 2022",614),

    //DT2--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    BUTCH("Butch","Butch",true,10340,59,NpcID.BUTCH,ItemID.BUTCH,Lists.newArrayList(49298),1,10337,10339,10339,60,-1,-1,null,false,"A tiny headless executioner.","Killcount:nofkeysumsy:17004:August 11, 2024",588),
    BARON("Baron","Baron",true,10219,13,NpcID.BARON,ItemID.BARON,Lists.newArrayList(49195),1,10217,10218,10218,-1,-1,-1,Lists.newArrayList((short)937,(short)790, (short)33988,(short)39207),false,"Better keep an eye on this one.","Killcount:Prince Mate:15470:September 30, 2024",588),
    LILVIATHAN("Lil'viathan","Lil'viathan",true,10283,59,NpcID.LILVIATHAN,ItemID.LILVIATHAN,Lists.newArrayList(49285,49284),1,10277,10292,10292,20,20,30,null,false,"A small creature deformed by the Abyss.","Killcount:PJoshGRS:14898:July 15, 2024",588),
    WISP("Wisp","Wisp",true,10234,59,NpcID.WISP,ItemID.WISP,Lists.newArrayList(49222,49218,49221,49224),1,10230,10233,10233,40,-1,76,null,false,"Born in the shadows.","Killcount:greg btw:11753:December 11, 2024",588),


    //Other Boss pets----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    SCURRY("Scurry","Scurry",true,10693,59,NpcID.SCURRY,ItemID.SCURRY,Lists.newArrayList(50146),1,10687,10715,10715,28,30,30,null,false,"She works on the railroad.","Killcount:Blasphemy:15476:November 12, 2024",588),

    MOXI("Moxi","Moxi",true,11013,59,NpcID.MOXI,ItemID.MOXI,Lists.newArrayList(54438,54437),1,11528,11529,11529,32,-1,-1,null,false,"A tiny frost nagua.","Killcount:PvP:14928:December 20, 2024",588),

    HUBERTE("Huberte","Huberte",true,11734,89,NpcID.HUBERTE,ItemID.HUBERTE,Lists.newArrayList(54453),1,11732,11733,11733,100,-1,-1,null,false,"Looks very polite.","Killcount:Maxed Whip:2454:December 20, 2024",588),

    //inferno
    JAL_NIB_REK("Jal-Nib-Rek","Jal-Nib-Rek",true,7574,11,NpcID.JALNIBREK,ItemID.JALNIBREK,Lists.newArrayList(33005),1,7573,7572,7572,-1,30,30,null,true,"It loves to nibble.\n","Killcount:c h u c kles:269:March 1, 2025",588),//check
    TZREK_ZUK("TzRek-Zuk","TzRek-Zuk",true,7978,13,NpcID.TZREKZUK,ItemID.TZREKZUK,Lists.newArrayList(34586),1,7975,7977,7977,18,30,30,null,true,"Not quite so fearsome anymore.","Killcount:c h u c kles:269:March 1, 2025",588),//check

    //jad
    TZREK_JAD("TzRek-Jad","TzRek-Jad",true,2652,11,NpcID.TZREKJAD,ItemID.TZREKJAD,Lists.newArrayList(9319),1,2650,5805,5805,20,60,-1,null,true,"This is not going to hurt... but it might tickle.","Killcount:A LeBlanc:525:Unknown",588),
    JALREK_JAD("JalRek-Jad","JalRek-Jad",true,7593,17,NpcID.JALREKJAD,ItemID.JALREKJAD,Lists.newArrayList(33012),1,7589,8857,8857,20,-10,-1,null,true,"Small, troublesome, cute.","Killcount:A LeBlanc:525:Unknown",8858),

    SMOL_HEREDIT("Smol Heredit","Smol Heredit",true,10882,120,NpcID.SMOL_HEREDIT,ItemID.SMOL_HEREDIT,Lists.newArrayList(52580,52582,52578),1,10874,10880,10880,50,20,30,null,true,"Still somewhat imposing, I suppose.","Killcount:I have egg73:692:June 23, 2024",588),

    //Guantlet
    YOUNGLLEF("Youngllef","Youngllef",false,8418,22,NpcID.YOUNGLLEF,ItemID.YOUNGLLEF,Lists.newArrayList(38596),1,8417,8428,8428,25,-1,-1,null,true,"Looks like a bit of a nightmare.","Killcount:Design Sir:4246: May 19, 2022",8427),
    CORRUPTED_YOUNGLLEF("Corrupted Youngllef","Corrupted Youngllef",false,8418,22,NpcID.CORRUPTED_YOUNGLLEF,ItemID.CORRUPTED_YOUNGLLEF,Lists.newArrayList(38597),1,8417,8428,8428,25,-1,-1,null,true,"Looks like a bit of a nightmare.","Killcount:Design Sir:4246: May 19, 2022",8427),

    //corp
    DARK_CORE("Dark core","Dark core",true,7980,14,NpcID.DARK_CORE,ItemID.PET_DARK_CORE,Lists.newArrayList(11069),1,7980,2417,2417,83,30,30,null,true,"Isn't so annoying when in pet form.\n","Killcount:Flikker:36413:June 28, 2022",588),
    CORPOREAL_CRITTER("Corporeal Critter","Corporeal Critter",true,1679,6,NpcID.CORPOREAL_CRITTER,ItemID.PET_CORPOREAL_CRITTER,Lists.newArrayList(11056),1,1678,7974,7974,64,30,30,null,true,"A critter from the spirit realm.\n","Killcount:Flikker:36413:June 28, 2022",588),

    //zulrah
    SNAKELING_BLUE("Snakeling Blue","Snakeling",true,1741,9,NpcID.SNAKELING_2129,ItemID.PET_SNAKELING_12940,Lists.newArrayList(10414),1,1721,2405,2405,-1,-1,-1,null,true,"A slithering serpent spawn of Zulrah.\n","Killcount:Zeus07:21694:January 6, 2020",2415),
    SNAKELING_RED("Snakeling Red","Snakeling",true,1741,9,NpcID.SNAKELING_2128,ItemID.PET_SNAKELING_12939,Lists.newArrayList(10416),1,1721,2405,2405,-1,-1,-1,null,true,"A slithering serpent spawn of Zulrah.\n","Killcount:Zeus07:21694:January 6, 2020",2415),
    SNAKELING_GREEN("Snakeling Green","Snakeling",true,1741,9,NpcID.SNAKELING,ItemID.PET_SNAKELING,Lists.newArrayList(10413),1,1721,2405,2405,-1,-1,-1,null,true,"A slithering serpent spawn of Zulrah.\n","Killcount:Zeus07:21694:January 6, 2020",2415),

    //nm
    LITTLE_NIGHTMARE("Little Nightmare","Little Nightmare",false,8596,32,NpcID.LITTLE_NIGHTMARE,ItemID.LITTLE_NIGHTMARE,Lists.newArrayList(39196),1,8593,8634,8634,30,-1,-1,null,true,"Quite the little nightmare.\n","Killcount:Valor:14894:November 22, 2020",8636),
    LITTLE_PARASITE("Little Parasite","Little Parasite",true,8554,19,NpcID.LITTLE_PARASITE,ItemID.LITTLE_PARASITE,Lists.newArrayList(39210),1,8553,8553,8553,40,-1,-1,null,true,"So cute!\n","Killcount:Valor:14894:November 22, 2020",8636),

    //mole                                         //regular mole npcid returns the wrong thing
    BABY_MOLE("Baby Mole","Baby Mole",true,3312,8,6635,ItemID.BABY_MOLE,Lists.newArrayList(42012),1,3309,3313,3313,90,30,30,null,true,"Keep Molin', molin' molin' molin'!\n","Killcount:Ez Klaplong:21931:October 22, 2020",590),
    BABY_MOLE_RAT("Baby Mole-rat","Baby Mole-rat",true,3312,8,NpcID.BABY_MOLERAT,ItemID.BABY_MOLERAT,Lists.newArrayList(42012),1,3309,3313,3313,90,30,30,Lists.newArrayList((short)5388,(short)5392,(short)5268,(short)5272,(short)5276,(short)4558  ,(short)317,(short)322,(short)326,(short)328,(short)332,(short)63706),true,"Keep Molin', molin' molin' molin'!\n","Killcount:Ez Klaplong:21931:October 22, 2020",590),

    //kq                                                                  //kq returns the wrong chathead id (returns flying for both)
    KALPHITE_PRINCESS("Kalphite Princess","Kalphite Princess",true,6224,19,NpcID.KALPHITE_PRINCESS,ItemID.KALPHITE_PRINCESS,Lists.newArrayList(24597,24598),2,6239,6238,4635,45,30,30,null,true,"Suggests there's a king nearby.\n","Killcount:Sveitsi:14054:Unknown",588),
    KALPHITE_PRINCESS_6637("Kalphite Princess 6637","Kalphite Princess",true,6235,14,NpcID.KALPHITE_PRINCESS_6653,ItemID.KALPHITE_PRINCESS_12654,Lists.newArrayList(24602,24605,24606),1,6236,6236,6236,45,30,30,null,true,"Suggests there's a king nearby.\n", "Killcount:Sveitsi:14054:Unknown",588),//kq chat head is messed up

    //Muphin
    MUPHIN("Muphin","Muphin",false,9918,119,NpcID.MUPHIN,ItemID.MUPHIN,Lists.newArrayList(47149),1,9913,9915,9915,25,-1,-1,null,true,"An oversized grub with arms.\n","Killcount:Sabler:8233:March 15, 2023",589),
    MUPHIN_27592("Muphin 27592","Muphin",false,9918,119,NpcID.MUPHIN_12006,ItemID.MUPHIN_27592,Lists.newArrayList(47147),1,9913,9915,9915,25,-1,-1,null,true,"An oversized grub with arms.\n","Killcount:Sabler:8233:March 15, 2023",589),
    MUPHIN_27593("Muphin 27593","Muphin",false,9918,119,NpcID.MUPHIN_12007,ItemID.MUPHIN_27593,Lists.newArrayList(47156),1,9913,9915,9915,25,-1,-1,null,true,"An oversized grub with arms.\n","Killcount:Sabler:8233:March 15, 2023",589),


    //Sarachnis
    SRARACHA("Sraracha","Sraracha",false,8004,17,NpcID.SRARACHA,ItemID.SRARACHA,Lists.newArrayList(37292),1,8320,8319,8319,48,30,30,null,true,"Good thing there's no such thing as Sarachnophobia.","Killcount:Nosevesey:17000:June 6, 2022",610),
    SRARACHA_25842("Sraracha 25842","Sraracha",false,8004,17,NpcID.SRARACHA_11157,ItemID.SRARACHA_25842,Lists.newArrayList(37291),1,8320,8319,8319,48,30,30,Lists.newArrayList((short)229,(short)348,(short)412,(short)555,(short)670  ,(short)36069,(short)35041,(short)3505,(short)5945,(short)4007),true,"Good thing there's no such thing as Sarachnophobia.\n","Killcount:Nosevesey:17000:June 6, 2022",610),
    SRARACHA_25843("Sraracha 25843","Sraracha",false,8004,17,NpcID.SRARACHA_11158,ItemID.SRARACHA_25843,Lists.newArrayList(37290),1,8320,8319,8319,48,30,30,Lists.newArrayList((short)229,(short)348,(short)412,(short)555,(short)670  ,(short)36069,(short)35041,(short)39219,(short)39611,(short)39719),true,"Good thing there's no such thing as Sarachnophobia.\n","Killcount:Nosevesey:17000:June 6, 2022",610),

    SKOTOS("Skotos","Skotos",false,69,26,NpcID.SKOTOS,ItemID.SKOTOS,Lists.newArrayList(31653),1,6935,4070,4070,20,30,30,null,false,"Spawn of Darkness.\n","Killcount:Balla:435:July 4, 2022",611),

    VORKI("Vorki","Vorki",true,7960,19,NpcID.VORKI,ItemID.VORKI,Lists.newArrayList(35023),1,7948,7959,7959,16,-1,-1,null,false,"Three legs on my dragon...\n","Killcount:Uneven Mango:20406:March 10, 2023",7958),

    //dks
    DAGANNOTH_SUPREME_JR("Dagannoth Supreme Jr.","Dagannoth Supreme Jr.",true,-1,-1,NpcID.DAGANNOTH_SUPREME_JR,ItemID.PET_DAGANNOTH_SUPREME,Lists.newArrayList(9941,9943),1,2850,2849,2849,60,-1,-1,null,false,"Wouldn't want that sleeping at the end of my bed.\n","Killcount:Nikco:22561:October 1, 2019",589),
    DAGANNOTH_PRIME_JR("Dagannoth Prime Jr.","Dagannoth Prime Jr.",true,-1,-1,NpcID.DAGANNOTH_PRIME_JR,ItemID.PET_DAGANNOTH_PRIME,Lists.newArrayList(9940,9943,9942),1,2850,2849,2849,60,30,30,Lists.newArrayList((short)11930,(short)27144,(short)16536,(short)16540, (short)5931,(short)1688,(short)21530,(short)21534),false,"Has the same temper as it's father.\n","Killcount:Berserker:34629:March 21, 2021",588),
    DAGANNOTH_REX_JR("Dagannoth Rex Jr.","Dagannoth Rex Jr.",true,-1,-1,NpcID.DAGANNOTH_REX_JR,ItemID.PET_DAGANNOTH_REX,Lists.newArrayList(9941),1,2850,2849,2849,60,30,30,Lists.newArrayList((short)16536,(short)16540,(short)27144,(short)2477, (short)7322,(short)7326,(short)10403,(short)2595),false,"They do say if you like it you should put a ring on it.\n","Killcount:Custom Tk:25824:June 1, 2021",588),

    //WILDY-----------------------------------------------------------------------------------------------------------------------------------------------------------------

    PRINCE_BLACK_DRAGON("Prince Black Dragon","Prince Black Dragon",true,81,29,NpcID.PRINCE_BLACK_DRAGON,ItemID.PRINCE_BLACK_DRAGON,Lists.newArrayList(17414,17415,17429,17422,17423),1,90,4635,4635,40,30,30,null,false,"Not quite the full royalty yet.\n","Killcount:Kbd-sits:21088:September 15, 2022",588),
    CHAOS_ELEMENTAL_JR("Chaos Elemental Jr.","Chaos Elemental Jr.",true,3148,10,NpcID.CHAOS_ELEMENTAL_JR,ItemID.PET_CHAOS_ELEMENTAL,Lists.newArrayList(28256),1,3144,3145,3145,-1,-1,-1,null,false,"D'aw look at the liddle...\n","Killcount:Baelor Swift:3213:March 20, 2021",3144),

    //Venenatis
    VENENATIS_SPIDERLING("Venenatis spiderling","Venenatis spiderling",true,9989,18,NpcID.VENENATIS_SPIDERLING,ItemID.VENENATIS_SPIDERLING,Lists.newArrayList(47393),1,9986,9987,9987,58,30,30,null,true,"Vacuum proof.\n","Killcount:A 101:14470:December 3, 2022",3009),
    VENENATIS_SPIDERLING_27648("Venenatis spiderling 27648","Venenatis spiderling",true,5319,11,NpcID.VENENATIS_SPIDERLING_11981,ItemID.VENENATIS_SPIDERLING_27648,Lists.newArrayList(28294,28295),1,5326,5325,5325,60,30,30,null,true,"Vacuum proof.\n","Killcount:A 101:14470:December 3, 2022",3009),

    //callisto
    CALLISTO_CUB("Callisto cub","Callisto cub",false,10012,59,NpcID.CALLISTO_CUB,ItemID.CALLISTO_CUB,Lists.newArrayList(47396),1,10011,10010,10010,16,30,30,null,true,"Bear-ly smaller than his father.\n","Killcount:Shewdoh:14239:December 5, 2020",588),
    CALLISTO_CUB_27649("Callisto cub 27649","Callisto cub",true,4921,10,NpcID.CALLISTO_CUB_11982,ItemID.CALLISTO_CUB_27649,Lists.newArrayList(28298),1,4919,4923,4923,35,30,30,null,true,"Bear-ly smaller than his father.\n","Killcount:Shewdoh:14239:December 5, 2020",588),

    //vetion
    VETION_JR("Vet'ion Jr.","Vet'ion Jr.",false,9971,89,NpcID.VETION_JR,ItemID.VETION_JR,Lists.newArrayList(47387,47384),1,9965,9967,9967,54,30,30,null,true,"Somehow much smoother in smaller form.\n","Killcount:o c c u l t:10577:May 10, 2021",588),//check all wildy pets and transmogs
    VETION_JR_13180("Vet'ion Jr. 13180","Vet'ion Jr.",false,9971,89,NpcID.VETION_JR_5537,ItemID.VETION_JR_13180,Lists.newArrayList(47383,47385,47388,47389),1,9965,9967,9967,54,30,30,Lists.newArrayList((short)55184,(short)54926,(short)54693,(short)55190,(short)54571,(short)54804  ,(short)4019,(short)4007,(short)4007,(short)3879,(short)4023,(short)2966),true,"Somehow much smoother in smaller form.\n","Killcount:o c c u l t:10577:May 10, 2021",588),
    VETION_JR_27650("Vet'ion Jr. 27650","Vet'ion Jr.",true,5499,12,NpcID.VETION_JR_11983,ItemID.VETION_JR_27650,Lists.newArrayList(28299),1,5505,5497,5497,45,30,30,null,true,"Somehow much smoother in smaller form.\n","Killcount:o c c u l t:10577:May 10, 2021",588),
    VETION_JR_27651("Vet'ion Jr. 27651","Vet'ion Jr.",true,5499,12,NpcID.VETION_JR_11984,ItemID.VETION_JR_27651,Lists.newArrayList(28299),1,5505,5497,5497,45,30,30,Lists.newArrayList((short)55184,(short)54926,(short)54693,(short)55190,(short)54571,(short)54804  ,(short)4019,(short)4007,(short)4007,(short)3879,(short)4023,(short)2966),true,"Somehow much smoother in smaller form.\n","Killcount:o c c u l t:10577:May 10, 2021",588),

    SCORPIAS_OFFSPRING("Scorpia's offspring","Scorpia's offspring",true,6254,10,NpcID.SCORPIAS_OFFSPRING,ItemID.SCORPIAS_OFFSPRING,Lists.newArrayList(29193),2,6258,6257,6257,280,30,30,Lists.newArrayList((short)142,(short)4525,(short)4636,(short)4884,(short)4645  ,(short)28,(short)16,(short)16,(short)16,(short)16),false,"A scuttling little scorpion with an incredibly vicious tail.\n","Killcount:The Gang:12736:October 25, 2022",3008),

    //SKILLING-----------------------------------------------------------------------------------------------------------------------------------------------------------------
    QUETZIN("Quetzin","Quetzin",false,10953,12,NpcID.QUETZIN,ItemID.QUETZIN,Lists.newArrayList(52601),1,10952,10952,10952,-1,40,-1,null,false,"Is this bird tailing me?","Rumors:Tech Spectre:5694:June 22, 2024",588),

    //farming
    TANGLEROOT("Tangleroot","Tangleroot",true,7314,9,NpcID.TANGLEROOT,ItemID.TANGLEROOT,Lists.newArrayList(32202),1,7312,7313,7313,-1,-1,-1,null,true,"Don't be hasty.\n","Experiance:Yogololo:200,000,000:September 03, 2021",567),
    TANGLEROOT_9492("Tangleroot 9492","Tangleroot",true,7314,9,NpcID.TANGLEROOT_9492,ItemID.TANGLEROOT_24555,Lists.newArrayList(39573),1,7312,7313,7313,-1,30,-1,null,true,"Don't be hasty.\n","Experiance:Yogololo:200,000,000:September 03, 2021",567),
    TANGLEROOT_9493("Tangleroot 9493","Tangleroot",true,7314,9,NpcID.TANGLEROOT_9493,ItemID.TANGLEROOT_24557,Lists.newArrayList(39571),1,7312,7313,7313,-1,-1,-1,null,true,"Don't be hasty.\n","Experiance:Yogololo:200,000,000:September 03, 2021",567),
    TANGLEROOT_9494("Tangleroot 9494","Tangleroot",true,7314,9,NpcID.TANGLEROOT_9494,ItemID.TANGLEROOT_24559,Lists.newArrayList(39572),1,7312,7313,7313,-1,-1,-1,null,true,"Don't be hasty.\n","Experiance:Yogololo:200,000,000:September 03, 2021",567),
    TANGLEROOT_9495("Tangleroot 9495","Tangleroot",true,7314,9,NpcID.TANGLEROOT_9495,ItemID.TANGLEROOT_24561,Lists.newArrayList(39574),1,7312,7313,7313,-1,-1,-1,null,true,"Don't be hasty.\n","Experiance:Yogololo:200,000,000:September 03, 2021",567),
    TANGLEROOT_9496("Tangleroot 9496","Tangleroot",true,7314,9,NpcID.TANGLEROOT_9496,ItemID.TANGLEROOT_24563,Lists.newArrayList(39575),1,7312,7313,7313,-1,-1,-1,null,true,"Don't be hasty.\n","Experiance:Yogololo:200,000,000:September 03, 2021",567),

    //agility
    GIANT_SQUIRREL("Giant Squirrel","Giant Squirrel",true,7311,9,NpcID.GIANT_SQUIRREL,ItemID.GIANT_SQUIRREL,Lists.newArrayList(32206),1,7309,7310,7310,110,-1,-1,null,true,"A giant squirrel with beautiful markings.\n","Laps:SiennaEhtycs:39402:January 20, 2022",614),
    DARK_SQUIRREL("Dark Squirrel","Dark Squirrel",true,7311,9,NpcID.DARK_SQUIRREL,ItemID.DARK_SQUIRREL,Lists.newArrayList(32206),1,7309,7310,7310,110,-1,-1,Lists.newArrayList((short)38160,(short)38156,(short)3633,(short)3290,(short)3623,(short)4430  ,(short)24,(short)12,(short)668,(short)24,(short)673,(short)12),true,"A giant squirrel with beautiful markings.\n","Laps:SiennaEhtycs:39402:January 20, 2022",614),

    //Rc
    RIFT_GUARDIAN("Rift guardian","Rift guardian",true,7308,7,NpcID.RIFT_GUARDIAN,ItemID.RIFT_GUARDIAN,Lists.newArrayList(32204),1,7307,7306,7306,-1,-1,-1,null,true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    RIFT_GUARDIAN_7338("Rift guardian 7338","Rift guardian",true,7308,7,NpcID.RIFT_GUARDIAN_7338,ItemID.RIFT_GUARDIAN_20667,Lists.newArrayList(32204),1,7307,7306,7306,-1,-1,-1,Lists.newArrayList((short)939,(short)960,(short)60595  ,(short)103,(short)127,(short)74),true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),//check
    RIFT_GUARDIAN_7339("Rift guardian 7339","Rift guardian",true,7308,7,NpcID.RIFT_GUARDIAN_7339,ItemID.RIFT_GUARDIAN_20669,Lists.newArrayList(32204),1,7307,7306,7306,-1,-1,-1,Lists.newArrayList((short)939,(short)960,(short)60595  ,(short)0,(short)5056,(short)5551),true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    RIFT_GUARDIAN_7340("Rift guardian 7340","Rift guardian",true,7308,7,NpcID.RIFT_GUARDIAN_7340,ItemID.RIFT_GUARDIAN_20671,Lists.newArrayList(32204),1,7307,7306,7306,-1,-1,-1,Lists.newArrayList((short)939,(short)960,(short)60595  ,(short)39849,(short)38866,(short)38086),true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    RIFT_GUARDIAN_7341("Rift guardian 7341","Rift guardian",true,7308,7,NpcID.RIFT_GUARDIAN_7341,ItemID.RIFT_GUARDIAN_20673,Lists.newArrayList(32204),1,7307,7306,7306,-1,-1,-1,Lists.newArrayList((short)939,(short)960,(short)60595  ,(short)6036,(short)6942,(short)6319),true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    RIFT_GUARDIAN_7342("Rift guardian 7342","Rift guardian",true,7308,7,NpcID.RIFT_GUARDIAN_7342,ItemID.RIFT_GUARDIAN_20675,Lists.newArrayList(32204),1,7307,7306,7306,-1,-1,-1,Lists.newArrayList((short)939,(short)960,(short)60595  ,(short)43961,(short)0,(short)43313),true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    RIFT_GUARDIAN_7343("Rift guardian 7343","Rift guardian",true,7308,7,NpcID.RIFT_GUARDIAN_7343,ItemID.RIFT_GUARDIAN_20677,Lists.newArrayList(32204),1,7307,7306,7306,-1,-1,-1,Lists.newArrayList((short)939,(short)960,(short)60595  ,(short)10469,(short)11200,(short)10425),true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    RIFT_GUARDIAN_7344("Rift guardian 7344","Rift guardian",true,7308,7,NpcID.RIFT_GUARDIAN_7344,ItemID.RIFT_GUARDIAN_20679,Lists.newArrayList(32204),1,7307,7306,7306,-1,-1,-1,Lists.newArrayList((short)939,(short)960,(short)60595  ,(short)7104,(short)127,(short)5551),true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    RIFT_GUARDIAN_7345("Rift guardian 7345","Rift guardian",true,7308,7,NpcID.RIFT_GUARDIAN_7345,ItemID.RIFT_GUARDIAN_20681,Lists.newArrayList(32204),1,7307,7306,7306,-1,-1,-1,Lists.newArrayList((short)939,(short)960,(short)60595  ,(short)22430,(short)22461,(short)21698),true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    RIFT_GUARDIAN_7346("Rift guardian 7346","Rift guardian",true,7308,7,NpcID.RIFT_GUARDIAN_7346,ItemID.RIFT_GUARDIAN_20683,Lists.newArrayList(32204),1,7307,7306,7306,-1,-1,-1,Lists.newArrayList((short)939,(short)960,(short)60595  ,(short)43945,(short)43968,(short)43313),true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    RIFT_GUARDIAN_7347("Rift guardian 7347","Rift guardian",true,7308,7,NpcID.RIFT_GUARDIAN_7347,ItemID.RIFT_GUARDIAN_20685,Lists.newArrayList(32204),1,7307,7306,7306,-1,-1,-1,Lists.newArrayList((short)939,(short)960,(short)60595  ,(short)127,(short)0,(short)74),true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    RIFT_GUARDIAN_7348("Rift guardian 7348","Rift guardian",true,7308,7,NpcID.RIFT_GUARDIAN_7348,ItemID.RIFT_GUARDIAN_20687,Lists.newArrayList(32204),1,7307,7306,7306,-1,-1,-1,Lists.newArrayList((short)939,(short)960,(short)60595  ,(short)43484,(short)46040,(short)45361),true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    RIFT_GUARDIAN_7349("Rift guardian 7349","Rift guardian",true,7308,7,NpcID.RIFT_GUARDIAN_7349,ItemID.RIFT_GUARDIAN_20689,Lists.newArrayList(32204),1,7307,7306,7306,-1,-1,-1,Lists.newArrayList((short)939,(short)960,(short)60595  ,(short)54503,(short)54742,(short)54449),true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    RIFT_GUARDIAN_7350("Rift guardian 7350","Rift guardian",true,7308,7,NpcID.RIFT_GUARDIAN_7350,ItemID.RIFT_GUARDIAN_20691,Lists.newArrayList(32204),1,7307,7306,7306,-1,-1,-1,Lists.newArrayList((short)939,(short)960,(short)60595  ,(short)920,(short)910,(short)57),true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    RIFT_GUARDIAN_7351("Rift guardian 7351","Rift guardian",true,7308,7,NpcID.RIFT_GUARDIAN_8024,ItemID.RIFT_GUARDIAN_21990,Lists.newArrayList(32204),1,7307,7306,7306,-1,-1,-1,Lists.newArrayList((short)939,(short)960,(short)60595  ,(short)4,(short)962,(short)43059),true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",588),
    GREATISH_GUARDIAN("Greatish guardian","Greatish guardian",true,9382,23,NpcID.GREATISH_GUARDIAN,ItemID.GREATISH_GUARDIAN,Lists.newArrayList(44061),1,9379,9378,9378,32,20,20,null,true,"An abyssal rift guardian.\n","Experiance:Tero:103,501,840:January 12, 2021",589),

    //Mining
    ROCK_GOLEM("Rock Golem","Rock Golem",true,-1,-1,NpcID.ROCK_GOLEM,ItemID.ROCK_GOLEM,Lists.newArrayList(29755),1,7180,7181,7181,-1,-1,10,null,true,"Found somewhere between a rock and a hard place.\n","Experiance:D 4 L:200,000,000:October 16, 2019",7179),
    ROCK_GOLEM_21187("Rock Golem 21187","Rock Golem",true,-1,-1,NpcID.ROCK_GOLEM_7439,ItemID.ROCK_GOLEM_21187,Lists.newArrayList(29755),1,7180,7181,7181,-1,-1,10,Lists.newArrayList((short)6823,(short)6697,(short)6819,(short)6814,(short)6682,(short)5656  ,(short)66,(short)61,(short)49,(short)53,(short)53,(short)49),true,"Found somewhere between a rock and a hard place.\n","Experiance:D 4 L:200,000,000:October 16, 2019",7179),
    ROCK_GOLEM_21188("Rock Golem 21188","Rock Golem",true,-1,-1,NpcID.ROCK_GOLEM_7440,ItemID.ROCK_GOLEM_21188,Lists.newArrayList(29755),1,7180,7181,7181,-1,-1,10,Lists.newArrayList((short)6823,(short)6697,(short)6819,(short)6814,(short)6682,(short)5656  ,(short)4044,(short)3912,(short)3906,(short)3901,(short)3897,(short)4021),true,"Found somewhere between a rock and a hard place.\n","Experiance:D 4 L:200,000,000:October 16, 2019",7179),
    ROCK_GOLEM_21189("Rock Golem 21189","Rock Golem",true,-1,-1,NpcID.ROCK_GOLEM_7441,ItemID.ROCK_GOLEM_21189,Lists.newArrayList(29755),1,7180,7181,7181,-1,-1,10,Lists.newArrayList((short)6823,(short)6697,(short)6819,(short)6814,(short)6682,(short)5656  ,(short)2337,(short)2332,(short)2328,(short)2452,(short)3346,(short)3470),true,"Found somewhere between a rock and a hard place.\n","Experiance:D 4 L:200,000,000:October 16, 2019",7179),
    ROCK_GOLEM_21190("Rock Golem 21190","Rock Golem",true,-1,-1,NpcID.ROCK_GOLEM_7442,ItemID.ROCK_GOLEM_21190,Lists.newArrayList(29755),1,7180,7181,7181,-1,-1,10,Lists.newArrayList((short)6823,(short)6697,(short)6819,(short)6814,(short)6682,(short)5656  ,(short)43235,(short)43233,(short)43486,(short)43482,(short)43862,(short)43730),true,"Found somewhere between a rock and a hard place.\n","Experiance:D 4 L:200,000,000:October 16, 2019",7179),
    ROCK_GOLEM_21191("Rock Golem 21191","Rock Golem",true,-1,-1,NpcID.ROCK_GOLEM_7443,ItemID.ROCK_GOLEM_21191,Lists.newArrayList(29755),1,7180,7181,7181,-1,-1,10,Lists.newArrayList((short)6823,(short)6697,(short)6819,(short)6814,(short)6682,(short)5656  ,(short)43105,(short)43100,(short)43096,(short)43092,(short)43088,(short)43084),true,"Found somewhere between a rock and a hard place.\n","Experiance:D 4 L:200,000,000:October 16, 2019",7179),
    ROCK_GOLEM_21192("Rock Golem 21192","Rock Golem",true,-1,-1,NpcID.ROCK_GOLEM_7444,ItemID.ROCK_GOLEM_21192,Lists.newArrayList(29755),1,7180,7181,7181,-1,-1,10,Lists.newArrayList((short)6823,(short)6697,(short)6819,(short)6814,(short)6682,(short)5656  ,(short)10388,(short)10512,(short)10508,(short)10378,(short)8,(short)4),true,"Found somewhere between a rock and a hard place.\n","Experiance:D 4 L:200,000,000:October 16, 2019",7179),
    ROCK_GOLEM_21193("Rock Golem 21193","Rock Golem",true,-1,-1,NpcID.ROCK_GOLEM_7445,ItemID.ROCK_GOLEM_21193,Lists.newArrayList(29755),1,7180,7181,7181,-1,-1,10,Lists.newArrayList((short)6823,(short)6697,(short)6819,(short)6814,(short)6682,(short)5656  ,(short)8134,(short)8128,(short)7104,(short)7101,(short)7099,(short)7097),true,"Found somewhere between a rock and a hard place.\n","Experiance:D 4 L:200,000,000:October 16, 2019",7179),
    ROCK_GOLEM_21194("Rock Golem 21194","Rock Golem",true,-1,-1,NpcID.ROCK_GOLEM_7446,ItemID.ROCK_GOLEM_21194,Lists.newArrayList(29755),1,7180,7181,7181,-1,-1,10,Lists.newArrayList((short)6823,(short)6697,(short)6819,(short)6814,(short)6682,(short)5656  ,(short)43181,(short)43177,(short)43173,(short)43297,(short)43292,(short)43288),true,"Found somewhere between a rock and a hard place.\n","Experiance:D 4 L:200,000,000:October 16, 2019",7179),
    ROCK_GOLEM_21195("Rock Golem 21195","Rock Golem",true,-1,-1,NpcID.ROCK_GOLEM_7447,ItemID.ROCK_GOLEM_21195,Lists.newArrayList(29755),1,7180,7181,7181,-1,-1,10,Lists.newArrayList((short)6823,(short)6697,(short)6819,(short)6814,(short)6682,(short)5656  ,(short)4550,(short)4548,(short)4544,(short)4539,(short)4535,(short)2487),true,"Found somewhere between a rock and a hard place.\n","Experiance:D 4 L:200,000,000:October 16, 2019",7179),
    ROCK_GOLEM_21196("Rock Golem 21196","Rock Golem",true,-1,-1,NpcID.ROCK_GOLEM_7448,ItemID.ROCK_GOLEM_21196,Lists.newArrayList(29755),1,7180,7181,7181,-1,-1,10,Lists.newArrayList((short)6823,(short)6697,(short)6819,(short)6814,(short)6682,(short)5656  ,(short)21675,(short)21671,(short)21667,(short)21662,(short)21658,(short)21782),true,"Found somewhere between a rock and a hard place.\n","Experiance:D 4 L:200,000,000:October 16, 2019",7179),
    ROCK_GOLEM_21197("Rock Golem 21197","Rock Golem",true,-1,-1,NpcID.ROCK_GOLEM_7449,ItemID.ROCK_GOLEM_21197,Lists.newArrayList(29755),1,7180,7181,7181,-1,-1,10,Lists.newArrayList((short)6823,(short)6697,(short)6819,(short)6814,(short)6682,(short)5656  ,(short)36145,(short)36141,(short)36137,(short)36133,(short)36257,(short)36252),true,"Found somewhere between a rock and a hard place.\n","Experiance:D 4 L:200,000,000:October 16, 2019",7179),
    ROCK_GOLEM_21198("Rock Golem 21198","Rock Golem",true,-1,-1,NpcID.ROCK_GOLEM_7450,ItemID.ROCK_GOLEM_21340,Lists.newArrayList(29755),1,7180,7181,7181,-1,-1,10,Lists.newArrayList((short)6823,(short)6697,(short)6819,(short)6814,(short)6682,(short)5656  ,(short)63830,(short)62800,(short)63692,(short)60624,(short)63675,(short)63663),true,"Found somewhere between a rock and a hard place.\n","Experiance:D 4 L:200,000,000:October 16, 2019",7179),
    ROCK_GOLEM_21199("Rock Golem 21199","Rock Golem",true,-1,-1,NpcID.ROCK_GOLEM_7451,ItemID.ROCK_GOLEM_21358,Lists.newArrayList(29755),1,7180,7181,7181,-1,-1,10,Lists.newArrayList((short)6823,(short)6697,(short)6819,(short)6814,(short)6682,(short)5656  ,(short)805,(short)929,(short)798,(short)796,(short)790,(short)912),true,"Found somewhere between a rock and a hard place.\n","Experiance:D 4 L:200,000,000:October 16, 2019",7179),
    ROCK_GOLEM_21200("Rock Golem 21200","Rock Golem",true,-1,-1,NpcID.ROCK_GOLEM_7452,ItemID.ROCK_GOLEM_21359,Lists.newArrayList(29755),1,7180,7181,7181,-1,-1,10,Lists.newArrayList((short)6823,(short)6697,(short)6819,(short)6814,(short)6682,(short)5656  ,(short)50999,(short)50995,(short)50993,(short)49967,(short)50087,(short)51098),true,"Found somewhere between a rock and a hard place.\n","Experiance:D 4 L:200,000,000:October 16, 2019",7179),
    ROCK_GOLEM_21201("Rock Golem 21201","Rock Golem",true,-1,-1,NpcID.ROCK_GOLEM_7453,ItemID.ROCK_GOLEM_21360,Lists.newArrayList(29755),1,7180,7181,7181,-1,-1,10,Lists.newArrayList((short)6823,(short)6697,(short)6819,(short)6814,(short)6682,(short)5656  ,(short)31531,(short)31655,(short)31651,(short)31518,(short)31640,(short)30612),true,"Found somewhere between a rock and a hard place.\n","Experiance:D 4 L:200,000,000:October 16, 2019",7179),

    //fishing
    HERON("Heron","Heron",true,6775,9,NpcID.HERON,ItemID.HERON,Lists.newArrayList(29756),1,6772,6774,6774,-1,-1,-1,null,true,"A long-legged bird that likes to fish.\n","Experiance:swftZ:172,714,570:January 19, 2020",7),
    GREAT_BLUE_HERON("Great blue heron","Great blue heron",true,6775,9,NpcID.GREAT_BLUE_HERON,ItemID.GREAT_BLUE_HERON,Lists.newArrayList(41628),1,6772,6774,6774,-1,-1,-1,null,true,"A long-legged bird that likes to fish.\n","Experiance:swftZ:172,714,570:January 19, 2020",7),

    BEAVER("Beaver","Beaver",true,-1,-1,NpcID.BEAVER,ItemID.BEAVER,Lists.newArrayList(29754),1,7177,7178,7178,-1,-1,-1,null,false,"Looks like it's gotten through a lot of wood.\n","Experiance:Vinny:177,611,398:October 11, 2020",6551),

    //chin
    BABY_CHINCHOMPA("Baby Chinchompa","Baby Chinchompa",true,5185,12,NpcID.BABY_CHINCHOMPA_6756,ItemID.BABY_CHINCHOMPA,Lists.newArrayList(19371),1,5182,5181,5181,-1,-1,-1,Lists.newArrayList((short)5169,(short)7343,(short)7335,(short)7339,(short)7343,(short)5165  ,(short)3988,(short)3988,(short)3982,(short)3986,(short)5014,(short)3988),true,"Fluffy and cute, keep away from fire!\n","Experiance:Runecraft:121,584,270:September 26, 2017",7182),
    BABY_CHINCHOMPA_13324("Baby Chinchompa 13324","Baby Chinchompa",true,5185,12,NpcID.BABY_CHINCHOMPA,ItemID.BABY_CHINCHOMPA_13324,Lists.newArrayList(19371),1,5182,5181,5181,-1,-1,-1,null,true,"Fluffy and cute, keep away from fire!\n","Experiance:Runecraft:121,584,270:September 26, 2017",7182),
    BABY_CHINCHOMPA_13325("Baby Chinchompa 13325","Baby Chinchompa",true,5185,12,NpcID.BABY_CHINCHOMPA_6758,ItemID.BABY_CHINCHOMPA_13325,Lists.newArrayList(19371),1,5182,5181,5181,-1,-1,-1,Lists.newArrayList((short)5169,(short)7343,(short)7335,(short)7339,(short)7343,(short)5165  ,(short)20,(short)33,(short)12,(short)37,(short)45,(short)49),true,"Fluffy and cute, keep away from fire!\n","Experiance:Runecraft:121,584,270:September 26, 2017",7182),
    BABY_CHINCHOMPA_13326("Baby Chinchompa 13326","Baby Chinchompa",true,5185,12,NpcID.BABY_CHINCHOMPA_6759,ItemID.BABY_CHINCHOMPA_13326,Lists.newArrayList(29757),1,5182,5181,5181,-1,-1,-1,null,true,"Fluffy and cute, keep away from fire!\n","Experiance:Runecraft:121,584,270:September 26, 2017",7182),

    //theiving
    ROCKY("Rocky","Rocky",true,7318,4,NpcID.ROCKY,ItemID.ROCKY,Lists.newArrayList(32203),1,7315,7316,7316,110,-1,-1,null,true,"Raccoons, like pandas but worse.\n","Experiance:Stashes:200,000,000:December 26, 2020",588),
    RED("Red","Red",true,7318,4,NpcID.RED,ItemID.RED,Lists.newArrayList(37361),1,7315,7316,7316,110,-1,-1,null,true,"Red panda, like pandas but red.\n","Experiance:Stashes:200,000,000:December 26, 2020",588),
    ZIGGY("Ziggy","Ziggy",true,7318,4,NpcID.ZIGGY,ItemID.ZIGGY,Lists.newArrayList(14390),1,7315,7316,7316,110,-1,-1,null,true,"No relation to a regular raccoon.\n","Experiance:Stashes:200,000,000:December 26, 2020",588),

    //WT
    PHOENIX("Phoenix","Phoenix",true,6811,10,NpcID.PHOENIX_7370,ItemID.PHOENIX,Lists.newArrayList(26852),1,6809,6808,6808,80,40,-1,null,true,"The essence of fire.\n","Killcount:Uneven Mango:15188:June 26, 2022",7),
    PHOENIX_24483("Phoenix 24483","Phoenix",true,6811,10,NpcID.PHOENIX_3081,ItemID.PHOENIX_24483,Lists.newArrayList(39148),1,6809,6808,6808,80,40,-1,null,true,"The essence of fire.\n","Killcount:Uneven Mango:15188:June 26, 2022",7),
    PHOENIX_24484("Phoenix 24484","Phoenix",true,6811,10,NpcID.PHOENIX_3078,ItemID.PHOENIX_24484,Lists.newArrayList(39146),1,6809,6808,6808,80,40,-1,Lists.newArrayList((short)4894,(short)4647,(short)5669,(short)6053,(short)5066,(short)5053  ,(short)43943,(short)41907,(short)39855,(short)38715,(short)39855,(short)38860),true,"The essence of fire.\n","Killcount:Uneven Mango:15188:June 26, 2022",7),
    PHOENIX_24485("Phoenix 24485","Phoenix",true,6811,10,NpcID.PHOENIX_3079,ItemID.PHOENIX_24485,Lists.newArrayList(39149),1,6809,6808,6808,80,40,-1,Lists.newArrayList((short)4894,(short)4647,(short)5669,(short)6053,(short)5066,(short)5053  ,(short)20,(short)33,(short)49,(short)20,(short)74,(short)86),true,"The essence of fire.\n","Killcount:Uneven Mango:15188:June 26, 2022",7),
    PHOENIX_24486("Phoenix 24486","Phoenix",true,6811,10,NpcID.PHOENIX_3080,ItemID.PHOENIX_24486,Lists.newArrayList(39147),1,6809,6808,6808,80,40,-1,Lists.newArrayList((short)4894,(short)4647,(short)5669,(short)6053,(short)5066,(short)5053  ,(short)48819,(short)48844,(short)48844,(short)50006,(short)48844,(short)50904),true,"The essence of fire.\n","Killcount:Uneven Mango:15188:June 26, 2022",7),

    HERBI("Herbi","Herbi",true,7696,7,NpcID.HERBI,ItemID.HERBI,Lists.newArrayList(33890),1,7694,7695,7695,110,-1,-1,Lists.newArrayList((short)19992,(short)20364,(short)19988,(short)20422  ,(short)6049,(short)6040,(short)4781,(short)4038),false,"A boar with an impressive mane of dried herbs.\n","Killcount:Ethwin:41630:October 16, 2020",590),

    SMOLCANO("Smolcano","Smolcano",false,8433,21,NpcID.SMOLCANO,ItemID.SMOLCANO,Lists.newArrayList(38592),1,8429,8447,8447,30,-1,-1,null,false,"Not so threatening at this size.\n","Killcount:OUTSlDERS:14939:May 28, 2022",588),//need to get

    TINY_TEMPOR("Tiny Tempor","Tiny Tempor",true,8905,29,NpcID.TINY_TEMPOR,ItemID.TINY_TEMPOR,Lists.newArrayList(41812),1,8895,8895,8895,24,-1,-1,null,false,"So smol, so anger.\n","Permits:Liney:44230:November 6, 2021",8907),

    ABYSSAL_PROTECTOR("Abyssal protector","Abyssal protector",true,2186,9,NpcID.ABYSSAL_PROTECTOR,ItemID.ABYSSAL_PROTECTOR,Lists.newArrayList(44070),1,2185,2184,2184,80,-1,-1,null,false,"Your own personal abyssal horror.\n","Searches:Aradof:15186:December 19, 2022",614),

    //MISC-----------------------------------------------------------------------------------------------------------------------------------------------------------------
    PENANCE_PET("Penance Pet","Penance Pet",true,5411,7,NpcID.PENANCE_PET,ItemID.PET_PENANCE_QUEEN,Lists.newArrayList(20717,20715,20714,20709,20713,20712,20711,20710,20716),1,5410,5409,5409,29,30,30,null,false,"Run away! Run aw... wait, it's tiny...\n","Gambles:AbyssWalkerr:5223:January 18, 2023",589),

    BLOODHOUND("Bloodhound","Bloodhound",true,6559,11,NpcID.BLOODHOUND,ItemID.BLOODHOUND,Lists.newArrayList(31740),1,7269,7280,7280,-1,-1,-1,null,false,"Tracking down clues with logic and a pipe.\n","Caskets:Kacy:6210:April 1, 2020",4479),

    CHOMPY_CHICK("Chompy chick","Chompy chick",true,6761,12,NpcID.CHOMPY_CHICK,ItemID.CHOMPY_CHICK,Lists.newArrayList(26861),1,6764,6765,6765,83,30,30,null,false,"A small boisterous bird, a delicacy for ogres.\n", "Killcount:MrNice98:18563:April 1, 2023",6764),

    LIL_CREATOR("Lil' Creator","Lil' Creator",false,8844,21,NpcID.LIL_CREATOR,ItemID.LIL_CREATOR,Lists.newArrayList(41240),1,8842,8846,8846,24,-1,-1,null,true,"A pint-sized bringer of existence.\n","Crates:8 PM EST:3248:June 25, 2022",8849),
    LIL_DESTRUCTOR("Lil' Destructor","Lil' Destructor",false,8840,25,NpcID.LIL_DESTRUCTOR,ItemID.LIL_DESTRUCTOR,Lists.newArrayList(41242),1,3079,8847,8847,24,-1,-1,null,true,"Harbinger of light surface scratches and minor dents.\n","Crates:8 PM EST:3248:June 25, 2022",8848),


    ;



    final String identifier;
    final String name;
    final boolean working;

    final int attkAnim;
    final int attkAnimFrames;


    final int NpcId;
    final int iconID;
    final ArrayList<Integer>modelIDs;
    final int size;
    final int idleAnim;
    final int walkAnim;
    final int runAnim;

    final int scale;
    final int ambient;
    final int contrast;
    final ArrayList<Short>recolorIDs;
    final boolean metamorph;
    final String examine;
    final String dryestPerson;
    final int chatHeadAnimID;

    static
    {
        ImmutableMap.Builder<String,PetData>builder = new ImmutableMap.Builder<>();

        for (PetData petData:values())
        {
            builder.put(petData.getIdentifier(), petData);
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

    public static final List<PetData>petsToShow = Arrays.asList(

            //RAIDS----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            OLMLET,
            LIL_ZIK,
            TUMEKENS_GUARDIAN,

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


    }


}
