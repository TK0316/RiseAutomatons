package riseautomatons.common;

import riseautomatons.common.item.EnumCraftSetType;
import riseautomatons.common.item.EnumSoulCore;
import riseautomatons.common.item.Items;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class Recipes {
	public static void init() {
		ItemStack lens = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.LENS.ordinal());
		ItemStack eyePiece = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.EYEPIECE.ordinal());
		ItemStack smallGear = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.STONECOG.ordinal());
		ItemStack chainLink = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.LOOP.ordinal());
		ItemStack chain = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.CHAIN.ordinal());
		ItemStack sprocket = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SPROCKET.ordinal());
		ItemStack joint = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.JOINT.ordinal());
		// TODO sharp
		ItemStack smallPlate = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SMALLPLATE.ordinal());
		ItemStack cylinder = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.CYLINDER.ordinal());
		ItemStack rod = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.ROD.ordinal());
		ItemStack piston = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.PISTON.ordinal());
		ItemStack actuator = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.ACTUATOR.ordinal());
		ItemStack canvas = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.CANVAS.ordinal());
		ItemStack wing = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.WING.ordinal());
		ItemStack passive = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.PASSIVE.ordinal());
		// TODO aggressive
		// TODO jaw
		ItemStack sensor = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SENSOR.ordinal());
		ItemStack drill = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.DRILL.ordinal());
		ItemStack rollerChain = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.ROLLERCHAIN.ordinal());
		ItemStack sHead = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SMALLHEAD.ordinal());
		ItemStack sBody = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SMALLBODY.ordinal());
		// TODO totehead
		// TODO midbody
		ItemStack sLeg = new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SMALLLEG.ordinal());
		// TODO shead

		ItemStack spring = new ItemStack(Ids.spring, 1, 0);
		ItemStack gearbox = new ItemStack(Ids.blockGearbox, 1, 0);
		ItemStack sealant = new ItemStack(Item.slimeBall, 1);
		ItemStack chalk = new ItemStack(Ids.itemChalk, 1, 0);
		ItemStack chisel = new ItemStack(Ids.chisel, 1, 0);

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
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 16, EnumCraftSetType.LOOP.ordinal()), new Object[] {
			" s ",
			"sss",
			" s ",
			's', Block.stone });

		// Chain Link recipe
		GameRegistry.addRecipe(new ItemStack(Ids.craftSet, 16, EnumCraftSetType.CHAIN.ordinal()), new Object[] {
			" r ",
			"r r",
			" r ",
			'r', rod });

		// Chain recipe
		GameRegistry.addRecipe(chain, new Object[] {
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

		// Small Plate recipe
		GameRegistry.addRecipe(cylinder, new Object[] {
			" l ",
			"ppp",
			" l ",
			'p', smallPlate, 'l', chainLink });

		GameRegistry.addRecipe(chisel, new Object[] {
			"r",
			"w",
			'w', Block.planks, 'r', rod});

		// Cylinder recipe
		GameRegistry.addRecipe(eyePiece, new Object[] {
			"lcl",
			'l', lens, 'c', cylinder });

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
			'c', cylinder, 's', sealant, 'r', rod });

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

		// Small Leg recipe
		GameRegistry.addRecipe(sLeg, new Object[] {
			" j ",
			"ini",
			"psp",
			'n', spring, 'j', joint, 's', Block.stone, 'p', smallPlate, 'i', piston });

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

		GameRegistry.addShapelessRecipe(new ItemStack(Item.slimeBall, 3), new Object[]
				{
			new ItemStack(Block.sapling, 1, 0), Item.bucketWater, Item.clay
				});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.slimeBall, 3), new Object[]
				{
			new ItemStack(Block.sapling, 1, 1), Item.bucketWater, Item.clay
				});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.slimeBall, 3), new Object[]
				{
			new ItemStack(Block.sapling, 1, 2), Item.bucketWater, Item.clay
				});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.slimeBall, 3), new Object[]
				{
			new ItemStack(Block.sapling, 1, 3), Item.bucketWater, Item.clay
				});

		// Worker recipe
		GameRegistry.addRecipe(new ItemStack(Ids.itemWorker, 1, 0), new Object[] {
			" A ",
			"CBC",
			'A', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SMALLHEAD.ordinal()),
			'B', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SMALLBODY.ordinal()),
			'C', new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SMALLLEG.ordinal()) });

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
}
