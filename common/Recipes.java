package riseautomatons.common;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import riseautomatons.common.block.Blocks;
import riseautomatons.common.item.EnumCraftSetType;
import riseautomatons.common.item.EnumSoulCore;
import riseautomatons.common.item.Items;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {
	static ItemStack lens = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.LENS.ordinal());
	static ItemStack eyePiece = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.EYEPIECE.ordinal());
	static ItemStack smallGear = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.STONECOG.ordinal());
	static ItemStack chainLink = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.LOOP.ordinal());
	static ItemStack chain = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.CHAIN.ordinal());
	static ItemStack sprocket = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SPROCKET.ordinal());
	static ItemStack joint = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.JOINT.ordinal());
	static ItemStack sharp = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SHARP.ordinal());
	static ItemStack smallPlate = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SMALLPLATE.ordinal());
	static ItemStack cylinder = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.CYLINDER.ordinal());
	static ItemStack rod = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.ROD.ordinal());
	static ItemStack piston = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.PISTON.ordinal());
	static ItemStack actuator = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.ACTUATOR.ordinal());
	static ItemStack canvas = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.CANVAS.ordinal());
	static ItemStack wing = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.WING.ordinal());
	static ItemStack passive = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.PASSIVE.ordinal());
	static ItemStack aggressive = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.AGGRESSIVE.ordinal());
	static ItemStack jaw = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.JAW.ordinal());
	static ItemStack sensor = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SENSOR.ordinal());
	static ItemStack drill = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.DRILL.ordinal());
	static ItemStack rollerChain = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.ROLLERCHAIN.ordinal());
	static ItemStack sHead = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SMALLHEAD.ordinal());
	static ItemStack sBody = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SMALLBODY.ordinal());
	static ItemStack toteHead = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.TOTEHEAD.ordinal());
	static ItemStack midBody = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.MIDBODY.ordinal());
	static ItemStack sLeg = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SMALLLEG.ordinal());
	static ItemStack sentryHead = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SENTRYHEAD.ordinal());
	static ItemStack factotumChunk = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.FACTOTUMCHUNK.ordinal());

	static ItemStack spring = new ItemStack(Ids.spring, 1, 0);
	static ItemStack gearbox = new ItemStack(Ids.blockGearbox, 1, 0);
	static ItemStack sealant = new ItemStack(Item.slimeBall, 1);
	static ItemStack chalk = new ItemStack(Ids.itemChalk, 1, 0);
	static ItemStack chisel = new ItemStack(Ids.chisel, 1, 0);

	public static void init() {

		addCraftSetRecipes();
		addItemRecipes();
		addAutomatonRecipes();
		addSoulCoreRecipes();
	}

	private static void addCraftSetRecipes() {
		// Lens recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 6, EnumCraftSetType.LENS.ordinal()), new Object[] {
			"g",
			"g",
			"g",
			'g', Block.glass });

		// Eye Piece recipe
		GameRegistry.addRecipe(eyePiece, new Object[] {
			"lcl",
			'l', lens, 'c', cylinder });

		// Small Gear recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 16, EnumCraftSetType.STONECOG.ordinal()), new Object[] {
		" s ",
		"sss",
		" s ",
		's', Block.stone });

		// Metal Rings recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 16, EnumCraftSetType.LOOP.ordinal()), new Object[] {
			" r ",
			"r r",
			" r ",
			'r', rod });

		// Chain recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 16, EnumCraftSetType.CHAIN.ordinal()), new Object[] {
			"ccc",
			"c c",
			"ccc",
			'c',chainLink });

		// Sprocket recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 16, EnumCraftSetType.SPROCKET.ordinal()), new Object[] {
			"rsr",
			"sss",
			"rsr",
			's', Block.stone, 'r', rod });

		// Joint recipe
		GameRegistry.addRecipe(joint, new Object[] {
			"grg",
			'g', smallGear,'r', rod });
		GameRegistry.addRecipe(joint, new Object[] {
			"g",
			"r",
			"g",
			'g',smallGear, 'r', rod });

		// Sharp recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 16, EnumCraftSetType.SHARP.ordinal()), new Object[] {
			" i ",
			"iii",
			'i', Item.ingotIron});

		// Small Plate recipe

		// Chisel recipe
		GameRegistry.addRecipe(chisel, new Object[] {
			"r",
			"w",
			'w', Block.planks, 'r', rod});

		// Cylinder recipe
		GameRegistry.addRecipe(cylinder, new Object[] {
			" l ",
			"ppp",
			" l ",
			'p', smallPlate, 'l', chainLink });

		// Rod recipe
		GameRegistry.addShapelessRecipe(new ItemStack(Ids.craftSet, 9, EnumCraftSetType.ROD.ordinal()), new Object[] {
			Item.ingotIron });

		GameRegistry.addRecipe(new ItemStack(Item.ingotIron, 1), new Object[] {
			"OOO",
			"OOO",
			"OOO",
			'O', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.ROD.ordinal()) });

		// Piston recipe
		GameRegistry.addRecipe(piston, new Object[] {
			"c",
			"r",
			"c",
			'c', cylinder, 'r', rod });

		// Actuator recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 4, EnumCraftSetType.ACTUATOR.ordinal()), new Object[] {
			"Drj",
			"  r",
			"  R",
			'R', Item.redstone, 'D', chalk, 'r', rod, 'j', joint });

		// Canvas recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 3, EnumCraftSetType.CANVAS.ordinal()), new Object[] {
			"sss",
			"sls",
			"sss", 's', Item.stick, 'l', Item.leather
				});

		// Wing recipe
		GameRegistry.addRecipe(wing, new Object[] {
			"sss",
			"ccc",
			'c', canvas, 's', Item.stick});
		GameRegistry.addRecipe(wing, new Object[] {
			"sc",
			"sc",
			"sc",
			'c', canvas, 's', Item.stick});

		// Passive recipe
		GameRegistry.addRecipe(passive, new Object[] {
			"a a",
			"pgp",
			" g ",
			'p', smallPlate, 'a', actuator, 'g', smallGear });

		// Aggressive recipe
		GameRegistry.addRecipe(aggressive, new Object[] {
			"a a",
			"rgr",
			"rgr",
			'r', Block.torchRedstoneActive, 'a', actuator, 'g', smallGear});

		// Jaw recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 1, EnumCraftSetType.JAW.ordinal()), new Object[] {
			"lll",
			"sss",
			'l', sharp, 's',smallPlate});
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 1, EnumCraftSetType.JAW.ordinal()), new Object[] {
			"sss",
			"lll",
			'l', sharp, 's',smallPlate});

		// Sensor recipe
		GameRegistry.addRecipe(sensor, new Object[] {
			"tGt",
			"RtR",
			"r r",
			't', Block.torchRedstoneActive, 'G', Block.glass, 'r', rod, 'R', Item.redstone });

		// Drill recipe
		GameRegistry.addRecipe(drill, new Object[] {
			"kcg",
			'k', Item.pickaxeStone, 'c', cylinder, 'g', smallGear });
		GameRegistry.addRecipe(drill, new Object[] {
			"k",
			"c",
			"g",
			'k', Item.pickaxeStone, 'c', cylinder, 'g', smallGear });

		// Roller Chain recipe
		GameRegistry.addShapelessRecipe(rollerChain, new Object[] {
			sprocket, sprocket, chain });

		// Small Head recipe
		GameRegistry.addRecipe(sHead, new Object[] {
			"ppp",
			"ePs",
			" g ",
			'p', smallPlate, 's', sensor, 'e', eyePiece, 'P', passive, 'g', smallGear });

		// Digger Body recipe
		GameRegistry.addRecipe(sBody, new Object[] {
			"prp",
			"pdp",
			"rGr",
			'p', smallPlate, 'G', gearbox, 'r', rod, 'd', drill });

		// Tote Head recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 1, EnumCraftSetType.TOTEHEAD.ordinal()), new Object[] {
			"psp",
			"lPl",
			"ppp",
			'p', smallPlate, 's', sensor, 'l', lens, 'P', passive});


		// Mid Body recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 1, EnumCraftSetType.MIDBODY.ordinal()), new Object[] {
			"gpg",
			"rGr",
			"gpg",
			'g', smallGear , 'p', smallPlate, 'G', gearbox, 'r', rod});

		// Small Leg recipe
		GameRegistry.addRecipe(sLeg, new Object[] {
			" j ",
			"ini",
			"psp",
			'n', spring, 'j', joint, 's', Block.stone, 'p', smallPlate, 'i', piston });

		// Sentry Head recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SENTRYHEAD.ordinal()), new Object[] {
			"sS",
			" G",
			"sa",
			's', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.JAW.ordinal()) , 'G', gearbox, 'a', aggressive,'S',sensor});

		// Factotum Chunk recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 1, EnumCraftSetType.FACTOTUMCHUNK.ordinal()), new Object[] {
			"faf",
			"iri",
			"sfs",
			's', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SMALLPLATE.ordinal()) , 'f', Block.stoneOvenIdle, 'r', Item.redstone, 'a', Item.flintAndSteel, 'i', Item.ingotIron});

		// Blue Core recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 1, EnumCraftSetType.BLUECORE.ordinal()), new Object[] {
			"sss",
			"rrr",
			"rrr",
			's', Block.stone, 'r', Item.redstone});

		// Red Core recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 1, EnumCraftSetType.REDCORE.ordinal()), new Object[] {
			"bbb",
			'b', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.BLUECORE.ordinal())});

	}

	private static void addItemRecipes() {
		// Spring recipe
		GameRegistry.addRecipe(spring, new Object[] {
			"r",
			"r",
			"r",
			'r', chainLink });

		// Gear Box recipe
		GameRegistry.addRecipe(gearbox, new Object[] {
			" s ",
			"rgr",
			"glg",
			's', Block.stone, 'r', rod, 'g', smallGear, 'l', rollerChain });

		GameRegistry.addRecipe(gearbox, new Object[] {
			" s ",
			"glg",
			"rgr",
			's', Block.stone, 'r', rod, 'g', smallGear, 'l', rollerChain });

		GameRegistry.addRecipe(gearbox, new Object[] {
			"rgr",
			"glg",
			" s ",
			's', Block.stone, 'r', rod, 'g', smallGear, 'l', rollerChain });

		GameRegistry.addRecipe(gearbox, new Object[] {
			"glg",
			"rgr",
			" s ",
			's', Block.stone, 'r', rod, 'g', smallGear, 'l', rollerChain });

		// Chalk recipe
		GameRegistry.addRecipe(new ItemStack(Ids.itemChalk, 16, 0), new Object[] {
			"###",
			"#l#",
			"###",
			'#', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SALT.ordinal()), 'l', new ItemStack(Item.dyePowder, 1, 4) });

		GameRegistry.addRecipe(new ItemStack(Ids.itemChalk, 16, 0), new Object[] {
			"###",
			"#l#",
			"###",
			'#', Item.sugar, 'l', new ItemStack(Item.dyePowder, 1, 4) });

		// Turn recipe
		GameRegistry.addRecipe(new ItemStack(Ids.blockTurn, 1, 0), new Object[] {
			"sss",
			's' , Item.stick});

		GameRegistry.addRecipe(new ItemStack(Ids.blockWindmill, 1, 0), new Object[] {
			" w "
			, "wgw",
			" w ",
			'w', wing, 'g', smallGear});

		// Craft Table recipe
		GameRegistry.addRecipe(new ItemStack(Block.workbench), new Object[] {
			"##",
			"##",
			'#', new ItemStack(Blocks.tech, 1, 1)});

		// Pulse Rifle recipe
		GameRegistry.addRecipe(new ItemStack(Ids.blaster, 1, 0), new Object[] {
			"iis",
			"ccc",
			"  r",
			'i', Item.ingotIron,
			's', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.REDCORE.ordinal()),
			'c', Blocks.crystal,
			'r', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.ROD.ordinal())});

		GameRegistry.addRecipe(new ItemStack(Ids.smack, 1, 0), new Object[] {
			" S",
			"i ",
			'S', Blocks.boing,
			'i', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.ROD.ordinal()) });

		GameRegistry.addRecipe(new ItemStack(Ids.pickTech, 1, 0), new Object[] {
			"i",
			"L",
			'i', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.ROD.ordinal()),
			'L', Blocks.grower });
		GameRegistry.addRecipe(new ItemStack(Ids.mortar, 1, 0), new Object[] {
			"#-#",
			"#-#",
			"#-#",
			'#', Block.stone,
			'-',  new ItemStack(Ids.craftSet, 1, EnumCraftSetType.ROD.ordinal())});

		GameRegistry.addRecipe(new ItemStack(Ids.techifier, 1, 0), new Object[] {
			"OOO",
			"OfO",
			"i i",
			'O', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.BIONICCONGLOMERATE.ordinal()),
			'f', Blocks.frass,
			'i', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.ROD.ordinal()) });

		GameRegistry.addRecipe(new ItemStack(Ids.naturizer, 1, 0),new Object[] {
			"i i",
			"OfO",
			"OOO",
			'O', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.BIONICCONGLOMERATE.ordinal()),
			'f', Block.sapling,
			'i', Item.stick });

		GameRegistry.addRecipe(new ItemStack(Ids.blockDapling, 1, 0), new Object[] {
			" O ",
			"OCO",
			" s ",
			'O', Blocks.crink,
			'C', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.BLUECORE.ordinal()),
			's', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.BIONICCONGLOMERATE.ordinal()) });

		GameRegistry.addRecipe(new ItemStack(Ids.blockDuplex, 1, 0), new Object[] {
			"O",
			"D",
			'O', Blocks.crystal,
			'D', Blocks.crink });

		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 1, EnumCraftSetType.BIONICCONGLOMERATE.ordinal()), new Object[] {
			"O",
			"D",
			'O', new ItemStack(Ids.craftSet, 2, EnumCraftSetType.PLANTMATTER.ordinal()),
			'D', new ItemStack(Ids.craftSet, 2, EnumCraftSetType.PARTICULATE.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 2, EnumCraftSetType.PLANTMATTER.ordinal()), new Object[] {
			"O",
			"D",
			'O', Block.sapling,
			'D', Items.mortar });

		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 2, EnumCraftSetType.PLANTMATTER.ordinal()), new Object[] {
			"O",
			"D",
			'O', Item.seeds,
			'D', Items.mortar });

		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 2, EnumCraftSetType.PARTICULATE.ordinal()), new Object[] {
			"O",
			"M",
			'O', Blocks.frass,
			'M', Items.mortar });

		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 2, EnumCraftSetType.PARTICULATE.ordinal()), new Object[] {
			"O",
			"D",
			'O', Blocks.crink,
			'D', Items.mortar });
		GameRegistry.addRecipe(new ItemStack(Item.redstone, 3), new Object[] {
			"O",
			"D",
			'O', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.BLUECORE.ordinal()),
			'D', Items.mortar });

		GameRegistry.addRecipe(new ItemStack(Item.redstone, 6), new Object[] {
			"O",
			"D",
			'O', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.REDCORE.ordinal()),
			'D', Items.mortar });

		GameRegistry.addShapelessRecipe(new ItemStack(Item.slimeBall, 3), new Object[] {
			new ItemStack(Block.sapling, 1, 0), Item.bucketWater, Item.clay});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.slimeBall, 3), new Object[] {
			new ItemStack(Block.sapling, 1, 1), Item.bucketWater, Item.clay});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.slimeBall, 3), new Object[] {
			new ItemStack(Block.sapling, 1, 2), Item.bucketWater, Item.clay});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.slimeBall, 3), new Object[] {
			new ItemStack(Block.sapling, 1, 3), Item.bucketWater, Item.clay});
	}

	private static void addAutomatonRecipes() {
		// Worker recipe
		GameRegistry.addRecipe(new ItemStack(Ids.itemWorker, 1, 0), new Object[] {
			" A ",
			"CBC",
			'A', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SMALLHEAD.ordinal()),
			'B', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SMALLBODY.ordinal()),
			'C', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SMALLLEG.ordinal()) });

		// Sentry recipe
		GameRegistry.addRecipe(new ItemStack(Ids.itemSentry, 1, 0), new Object[] {
			" ll",
			"h##",
			" ll",
			'#', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.MIDBODY.ordinal()), 'l', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SMALLLEG.ordinal()), 'h', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SENTRYHEAD.ordinal())});

		// Factotum recipe
		GameRegistry.addRecipe(new ItemStack(Ids.itemFactotum, 1, 0), new Object[] {
			" ll",
			"h#C",
			" ll",
			'#', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.MIDBODY.ordinal()), 'l', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SMALLLEG.ordinal()), 'C', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.FACTOTUMCHUNK.ordinal()), 'h', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.TOTEHEAD.ordinal())});

		// Beacon recipe
		GameRegistry.addRecipe(new ItemStack(Ids.itemBeacon, 1, 0), new Object[] {
			"s",
			"S",
			"S",
			's', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SENSOR.ordinal()), 'S', Block.stone});

		// Guard Turret recipe
		GameRegistry.addRecipe(new ItemStack(Ids.itemGuard, 1, 0), new Object[] {
			"p",
			"b",
			'p', Items.blaster, 'b', Items.beacon});
	}

	private static void addSoulCoreRecipes() {
		GameRegistry.addShapelessRecipe(new ItemStack(Ids.soulCore, 1, EnumSoulCore.STONEROUNDED.ordinal()), new Object[] {
			chisel, Block.stone});


		GameRegistry.addShapelessRecipe(new ItemStack(Ids.soulCore, 1, EnumSoulCore.STONEBALL.ordinal()), new Object[] {
			chisel, new ItemStack(Ids.soulCore, 1, EnumSoulCore.STONEROUNDED.ordinal())});
		GameRegistry.addShapelessRecipe(new ItemStack(Ids.soulCore, 1, EnumSoulCore.STONEINCOMPLETE.ordinal()), new Object[] {
			chisel, new ItemStack(Ids.soulCore, 1, EnumSoulCore.STONEBALL.ordinal())});
		GameRegistry.addShapelessRecipe(new ItemStack(Ids.soulCore, 1, EnumSoulCore.STONEEMPTY.ordinal()), new Object[] {
			new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SALT.ordinal()), new ItemStack(Ids.soulCore, 1, EnumSoulCore.STONEINCOMPLETE.ordinal())});
		GameRegistry.addShapelessRecipe(new ItemStack(Ids.soulCore, 1, EnumSoulCore.STONEEMPTY.ordinal()), new Object[] {
			Item.sugar, new ItemStack(Ids.soulCore, 1, EnumSoulCore.STONEINCOMPLETE.ordinal())});

		GameRegistry.addShapelessRecipe(new ItemStack(Ids.soulCore, 1, EnumSoulCore.STONEEMPTY.ordinal()), new Object[] {
			new ItemStack(Ids.soulCore, 1, EnumSoulCore.SOULSYNTH.ordinal())});
		GameRegistry.addShapelessRecipe(new ItemStack(Ids.soulCore, 1, EnumSoulCore.STONEEMPTY.ordinal()), new Object[] {
			new ItemStack(Ids.soulCore, 1, EnumSoulCore.SOULPURE.ordinal())});
		GameRegistry.addShapelessRecipe(new ItemStack(Ids.soulCore, 1, EnumSoulCore.STONEEMPTY.ordinal()), new Object[] {
			new ItemStack(Ids.soulCore, 1, EnumSoulCore.SOULEVIL.ordinal())});
	}

	public static void initCheatRecipe() {

		// Worker recipe
		GameRegistry.addRecipe(new ItemStack(Ids.itemWorker, 1, 0), new Object[] {
			"D D",
			"  D",
			"  D",
			'D', new ItemStack(Item.diamond.shiftedIndex, 1, 0) });

		// Sentry recipe
		GameRegistry.addRecipe(new ItemStack(Ids.itemSentry, 1, 0), new Object[] {
			"D D",
			"D  ",
			"D D",
			'D', new ItemStack(Item.diamond.shiftedIndex, 1, 0) });

		// Factotum recipe
		GameRegistry.addRecipe(new ItemStack(Ids.itemFactotum, 1, 0), new Object[] {
			"D D",
			"D D",
			"D  ",
			'D', new ItemStack(Item.diamond.shiftedIndex, 1, 0) });

		// SoulCore recipe
		GameRegistry.addRecipe(new ItemStack(Ids.soulCore, 1, EnumSoulCore.SOULSYNTH.ordinal()), new Object[] {
			"CCC",
			"C C",
			"CCC",
			'C', new ItemStack(Ids.itemChalk, 1, 0) });
	}
}
