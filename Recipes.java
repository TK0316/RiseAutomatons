package riseautomatons;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import riseautomatons.block.Blocks;
import riseautomatons.item.EnumCraftSetType;
import riseautomatons.item.EnumSoulCore;
import riseautomatons.item.Items;
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
	static ItemStack sealant = new ItemStack(Items.slime_ball, 1);
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
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 6, EnumCraftSetType.LENS.ordinal()), "g",
                "g",
                "g",
                'g', Blocks.glass);

		// Eye Piece recipe
		GameRegistry.addRecipe(eyePiece, "lcl",
                'l', lens, 'c', cylinder);

		// Small Gear recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 16, EnumCraftSetType.STONECOG.ordinal()), " s ",
                "sss",
                " s ",
                's', Blocks.stone);

		// Metal Rings recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 16, EnumCraftSetType.LOOP.ordinal()), " r ",
                "r r",
                " r ",
                'r', rod);

		// Chain recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 16, EnumCraftSetType.CHAIN.ordinal()), "ccc",
                "c c",
                "ccc",
                'c',chainLink);

		// Sprocket recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 16, EnumCraftSetType.SPROCKET.ordinal()), "rsr",
                "sss",
                "rsr",
                's', Blocks.stone, 'r', rod);

		// Joint recipe
		GameRegistry.addRecipe(joint, "grg",
                'g', smallGear,'r', rod);
		GameRegistry.addRecipe(joint, "g",
                "r",
                "g",
                'g',smallGear, 'r', rod);

		// Sharp recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 16, EnumCraftSetType.SHARP.ordinal()), " i ",
                "iii",
                'i', Items.iron_ingot);

		// Small Plate recipe

		// Chisel recipe
		GameRegistry.addRecipe(chisel, "r",
                "w",
                'w', Blocks.planks, 'r', rod);

		// Cylinder recipe
		GameRegistry.addRecipe(cylinder, " l ",
                "ppp",
                " l ",
                'p', smallPlate, 'l', chainLink);

		// Rod recipe
		GameRegistry.addShapelessRecipe(new ItemStack(Ids.craftSet, 9, EnumCraftSetType.ROD.ordinal()), Items.iron_ingot);

		GameRegistry.addRecipe(new ItemStack(Items.iron_ingot, 1), "OOO",
                "OOO",
                "OOO",
                'O', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.ROD.ordinal()));

		// Piston recipe
		GameRegistry.addRecipe(piston, "c",
                "r",
                "c",
                'c', cylinder, 'r', rod);

		// Actuator recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 4, EnumCraftSetType.ACTUATOR.ordinal()), "Drj",
                "  r",
                "  R",
                'R', Items.redstone, 'D', chalk, 'r', rod, 'j', joint);

		// Canvas recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 3, EnumCraftSetType.CANVAS.ordinal()), "sss",
                "sls",
                "sss",
                's', Items.stick, 'l', Items.leather);

		// Wing recipe
		GameRegistry.addRecipe(wing, "sss",
                "ccc",
                'c', canvas, 's', Items.stick);
		GameRegistry.addRecipe(wing, "sc",
                "sc",
                "sc",
                'c', canvas, 's', Items.stick);
		GameRegistry.addRecipe(wing, "sss",
                "ccc",
                'c', Items.painting, 's', Items.stick);
		GameRegistry.addRecipe(wing, "sc",
                "sc",
                "sc",
                'c', Items.painting, 's', Items.stick);

		// Passive recipe
		GameRegistry.addRecipe(passive, "a a",
                "pgp",
                " g ",
                'p', smallPlate, 'a', actuator, 'g', smallGear);

		// Aggressive recipe
		GameRegistry.addRecipe(aggressive, "a a",
                "rgr",
                "rgr",
                'r', Blocks.redstone_torch, 'a', actuator, 'g', smallGear);

		// Jaw recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 1, EnumCraftSetType.JAW.ordinal()), "lll",
                "sss",
                'l', sharp, 's',smallPlate);
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 1, EnumCraftSetType.JAW.ordinal()), "sss",
                "lll",
                'l', sharp, 's',smallPlate);

		// Sensor recipe
		GameRegistry.addRecipe(sensor, "tGt",
                "RtR",
                "r r",
                't', Blocks.redstone_torch, 'G', Blocks.glass, 'r', rod, 'R', Items.redstone);

		// Drill recipe
		GameRegistry.addRecipe(drill, "kcg",
                'k', Items.stone_pickaxe, 'c', cylinder, 'g', smallGear);
		GameRegistry.addRecipe(drill, "k",
                "c",
                "g",
                'k', Items.stone_pickaxe, 'c', cylinder, 'g', smallGear);

		// Roller Chain recipe
		GameRegistry.addShapelessRecipe(rollerChain, sprocket, sprocket, chain);

		// Small Head recipe
		GameRegistry.addRecipe(sHead, "ppp",
                "ePs",
                " g ",
                'p', smallPlate, 's', sensor, 'e', eyePiece, 'P', passive, 'g', smallGear);

		// Digger Body recipe
		GameRegistry.addRecipe(sBody, "prp",
                "pdp",
                "rGr",
                'p', smallPlate, 'G', gearbox, 'r', rod, 'd', drill);

		// Tote Head recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 1, EnumCraftSetType.TOTEHEAD.ordinal()), "psp",
                "lPl",
                "ppp",
                'p', smallPlate, 's', sensor, 'l', lens, 'P', passive);


		// Mid Body recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 1, EnumCraftSetType.MIDBODY.ordinal()), "gpg",
                "rGr",
                "gpg",
                'g', smallGear, 'p', smallPlate, 'G', gearbox, 'r', rod);

		// Small Leg recipe
		GameRegistry.addRecipe(sLeg, " j ",
                "ini",
                "psp",
                'n', spring, 'j', joint, 's', Blocks.stone, 'p', smallPlate, 'i', piston);

		// Sentry Head recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SENTRYHEAD.ordinal()), "sS",
                " G",
                "sa",
                's', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.JAW.ordinal()), 'G', gearbox, 'a', aggressive,'S',sensor);

		// Factotum Chunk recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 1, EnumCraftSetType.FACTOTUMCHUNK.ordinal()), "faf",
                "iri",
                "sfs",
                's', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SMALLPLATE.ordinal()), 'f', Blocks.furnace, 'r', Items.redstone, 'a', Items.flint_and_steel, 'i', Items.iron_ingot);

		// Blue Core recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 1, EnumCraftSetType.BLUECORE.ordinal()), "sss",
                "rrr",
                "rrr",
                's', Blocks.stone, 'r', Items.redstone);

		// Red Core recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 1, EnumCraftSetType.REDCORE.ordinal()), "bbb",
                'b', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.BLUECORE.ordinal()));

	}

	private static void addItemRecipes() {
		// Spring recipe
		GameRegistry.addRecipe(spring, "r",
                "r",
                "r",
                'r', chainLink);

		// Gear Box recipe
		GameRegistry.addRecipe(gearbox, " s ",
                "rgr",
                "glg",
                's', Blocks.stone, 'r', rod, 'g', smallGear, 'l', rollerChain);

		GameRegistry.addRecipe(gearbox, " s ",
                "glg",
                "rgr",
                's', Blocks.stone, 'r', rod, 'g', smallGear, 'l', rollerChain);

		GameRegistry.addRecipe(gearbox, "rgr",
                "glg",
                " s ",
                's', Blocks.stone, 'r', rod, 'g', smallGear, 'l', rollerChain);

		GameRegistry.addRecipe(gearbox, "glg",
                "rgr",
                " s ",
                's', Blocks.stone, 'r', rod, 'g', smallGear, 'l', rollerChain);

		// Chalk recipe
		GameRegistry.addRecipe(new ItemStack(Ids.itemChalk, 16, 0), "###",
                "#l#",
                "###",
                '#', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SALT.ordinal()), 'l', new ItemStack(Items.dye, 1, 4));

		GameRegistry.addRecipe(new ItemStack(Ids.itemChalk, 16, 0), "###",
                "#l#",
                "###",
                '#', Items.sugar, 'l', new ItemStack(Items.dye, 1, 4));

		// Turn recipe
		GameRegistry.addRecipe(new ItemStack(Ids.blockTurn, 1, 0), "sss",
                's', Items.stick);

		GameRegistry.addRecipe(new ItemStack(Ids.blockWindmill, 1, 0), " w ",
                "wgw",
                " w ",
                'w', wing, 'g', smallGear);

		// Craft Table recipe
		GameRegistry.addRecipe(new ItemStack(Blocks.crafting_table), "##",
                "##",
                '#', new ItemStack(Blocks.tech, 1, 1));

		// Pulse Rifle recipe
		GameRegistry.addRecipe(new ItemStack(Ids.blaster, 1, 0), "iis",
                "ccc",
                "  r",
                'i', Items.iron_ingot,
                's', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.REDCORE.ordinal()),
                'c', Blocks.crystal,
                'r', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.ROD.ordinal()));

		GameRegistry.addRecipe(new ItemStack(Ids.smack, 1, 0), " S",
                "i ",
                'S', Blocks.boing,
                'i', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.ROD.ordinal()));

		GameRegistry.addRecipe(new ItemStack(Ids.pickTech, 1, 0), "i",
                "L",
                'i', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.ROD.ordinal()),
                'L', Blocks.grower);
		GameRegistry.addRecipe(new ItemStack(Ids.mortar, 1, 0), "#-#",
                "#-#",
                "#-#",
                '#', Blocks.stone,
                '-', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.ROD.ordinal()));

		GameRegistry.addRecipe(new ItemStack(Ids.techifier, 1, 0), "OOO",
                "OfO",
                "i i",
                'O', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.BIONICCONGLOMERATE.ordinal()),
                'f', Blocks.frass,
                'i', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.ROD.ordinal()));

		GameRegistry.addRecipe(new ItemStack(Ids.naturizer, 1, 0), "i i",
                "OfO",
                "OOO",
                'O', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.BIONICCONGLOMERATE.ordinal()),
                'f', Blocks.sapling,
                'i', Items.stick);

		GameRegistry.addRecipe(new ItemStack(Ids.blockDapling, 1, 0), " O ",
                "OCO",
                " s ",
                'O', Blocks.crink,
                'C', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.BLUECORE.ordinal()),
                's', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.BIONICCONGLOMERATE.ordinal()));

		GameRegistry.addRecipe(new ItemStack(Ids.blockDuplex, 1, 0), "O",
                "D",
                'O', Blocks.crystal,
                'D', Blocks.crink);

		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 1, EnumCraftSetType.BIONICCONGLOMERATE.ordinal()), "O",
                "D",
                'O', new ItemStack(Ids.craftSet, 2, EnumCraftSetType.PLANTMATTER.ordinal()),
                'D', new ItemStack(Ids.craftSet, 2, EnumCraftSetType.PARTICULATE.ordinal()));
		GameRegistry.addShapelessRecipe(new ItemStack(Ids.craftSet, 2, EnumCraftSetType.PLANTMATTER.ordinal()), Blocks.sapling, Items.mortar);

		GameRegistry.addShapelessRecipe(new ItemStack(Ids.craftSet, 2, EnumCraftSetType.PLANTMATTER.ordinal()), Items.wheat_seeds, Items.mortar);

		GameRegistry.addShapelessRecipe(new ItemStack(Ids.craftSet, 2, EnumCraftSetType.PARTICULATE.ordinal()), Blocks.frass, Items.mortar);

		GameRegistry.addShapelessRecipe(new ItemStack(Ids.craftSet, 2, EnumCraftSetType.PARTICULATE.ordinal()), Blocks.crink, Items.mortar);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.redstone, 3), new ItemStack(Ids.craftSet, 1, EnumCraftSetType.BLUECORE.ordinal()), Items.mortar);

		GameRegistry.addShapelessRecipe(new ItemStack(Items.redstone, 6), new ItemStack(Ids.craftSet, 1, EnumCraftSetType.REDCORE.ordinal()), Items.mortar);

		GameRegistry.addShapelessRecipe(new ItemStack(Items.slime_ball, 3), new ItemStack(Blocks.sapling, 1, 0), Items.water_bucket, Items.clay_ball);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.slime_ball, 3), new ItemStack(Blocks.sapling, 1, 1), Items.water_bucket, Items.clay_ball);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.slime_ball, 3), new ItemStack(Blocks.sapling, 1, 2), Items.water_bucket, Items.clay_ball);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.slime_ball, 3), new ItemStack(Blocks.sapling, 1, 3), Items.water_bucket, Items.clay_ball);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.virus, 1, 1), new ItemStack(Items.virus, 1, 0), Blocks.crystal);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 3, 15), Items.skull);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 3, 15), Items.skullAnimal);

		for(int i = 0; i < 4; i++) {
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.tech, 2, i), new ItemStack(Blocks.tech, 1, i), Blocks.duplex);
		}

		for(int i = 0; i < 2; i++) {
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.sky, 2, i), new ItemStack(Blocks.sky, 1, i), Blocks.duplex);
		}

		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.arch, 2), new ItemStack(Blocks.arch, 1), Blocks.duplex);
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.archBend, 2), new ItemStack(Blocks.archBend, 1), Blocks.duplex);

		GameRegistry.addRecipe(new ItemStack(Ids.blockLatch, 1, 0), "##g",
                "s #",
                "#r#",
                '#', smallPlate,'r',Items.redstone,'g',smallGear,'s',spring);
	}

	private static void addAutomatonRecipes() {
		// Worker recipe
		GameRegistry.addRecipe(new ItemStack(Ids.itemWorker, 1, 0), " A ",
                "CBC",
                'A', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SMALLHEAD.ordinal()),
                'B', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SMALLBODY.ordinal()),
                'C', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SMALLLEG.ordinal()));

		// Sentry recipe
		GameRegistry.addRecipe(new ItemStack(Ids.itemSentry, 1, 0), " ll",
                "h##",
                " ll",
                '#', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.MIDBODY.ordinal()), 'l', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SMALLLEG.ordinal()), 'h', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SENTRYHEAD.ordinal()));

		// Factotum recipe
		GameRegistry.addRecipe(new ItemStack(Ids.itemFactotum, 1, 0), " ll",
                "h#C",
                " ll",
                '#', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.MIDBODY.ordinal()), 'l', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SMALLLEG.ordinal()), 'C', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.FACTOTUMCHUNK.ordinal()), 'h', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.TOTEHEAD.ordinal()));

		// Beacon recipe
		GameRegistry.addRecipe(new ItemStack(Ids.itemBeacon, 1, 0), "s",
                "S",
                "S",
                's', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SENSOR.ordinal()), 'S', Blocks.stone);

		// Guard Turret recipe
		GameRegistry.addRecipe(new ItemStack(Ids.itemGuard, 1, 0), "p",
                "b",
                'p', Items.blaster, 'b', Items.beacon);

		// Omni recipe
		GameRegistry.addRecipe(new ItemStack(Ids.itemOmni, 1, 0), "OOO",
                "OCO",
                "OOO",
                'O', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.BIONICCONGLOMERATE.ordinal()),
                'C', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.REDCORE.ordinal()));

		// Tote recipe
		GameRegistry.addRecipe(new ItemStack(Ids.itemTote, 1, 0), "ll",
                "hL",
                "ll",
                'l', sLeg, 'h', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.TOTEHEAD.ordinal()), 'L', new ItemStack(Ids.blockLatch, 1, 0));
	}

	private static void addSoulCoreRecipes() {
		GameRegistry.addShapelessRecipe(new ItemStack(Ids.soulCore, 1, EnumSoulCore.STONEROUNDED.ordinal()), chisel, Blocks.stone);


		GameRegistry.addShapelessRecipe(new ItemStack(Ids.soulCore, 1, EnumSoulCore.STONEBALL.ordinal()), chisel, new ItemStack(Ids.soulCore, 1, EnumSoulCore.STONEROUNDED.ordinal()));
		GameRegistry.addShapelessRecipe(new ItemStack(Ids.soulCore, 1, EnumSoulCore.STONEINCOMPLETE.ordinal()), chisel, new ItemStack(Ids.soulCore, 1, EnumSoulCore.STONEBALL.ordinal()));
		GameRegistry.addShapelessRecipe(new ItemStack(Ids.soulCore, 1, EnumSoulCore.STONEEMPTY.ordinal()), new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SALT.ordinal()), new ItemStack(Ids.soulCore, 1, EnumSoulCore.STONEINCOMPLETE.ordinal()));
		GameRegistry.addShapelessRecipe(new ItemStack(Ids.soulCore, 1, EnumSoulCore.STONEEMPTY.ordinal()), Items.sugar, new ItemStack(Ids.soulCore, 1, EnumSoulCore.STONEINCOMPLETE.ordinal()));

		GameRegistry.addShapelessRecipe(new ItemStack(Ids.soulCore, 1, EnumSoulCore.STONEEMPTY.ordinal()), new ItemStack(Ids.soulCore, 1, EnumSoulCore.SOULSYNTH.ordinal()));
		GameRegistry.addShapelessRecipe(new ItemStack(Ids.soulCore, 1, EnumSoulCore.STONEEMPTY.ordinal()), new ItemStack(Ids.soulCore, 1, EnumSoulCore.SOULPURE.ordinal()));
		GameRegistry.addShapelessRecipe(new ItemStack(Ids.soulCore, 1, EnumSoulCore.STONEEMPTY.ordinal()), new ItemStack(Ids.soulCore, 1, EnumSoulCore.SOULEVIL.ordinal()));
	}

	public static void initCheatRecipe() {

		// Worker recipe
		GameRegistry.addRecipe(new ItemStack(Ids.itemWorker, 1, 0), "D D",
                "  D",
                "  D",
                'D', new ItemStack(Items.diamond, 1, 0));

		// Sentry recipe
		GameRegistry.addRecipe(new ItemStack(Ids.itemSentry, 1, 0), "D D",
                "D  ",
                "D D",
                'D', new ItemStack(Items.diamond, 1, 0));

		// Factotum recipe
		GameRegistry.addRecipe(new ItemStack(Ids.itemFactotum, 1, 0), "D D",
                "D D",
                "D  ",
                'D', new ItemStack(Items.diamond, 1, 0));

		// SoulCore recipe
		GameRegistry.addRecipe(new ItemStack(Ids.soulCore, 1, EnumSoulCore.SOULSYNTH.ordinal()), "CCC",
                "C C",
                "CCC",
                'C', new ItemStack(Ids.itemChalk, 1, 0));
	}
}
