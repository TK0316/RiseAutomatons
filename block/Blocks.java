package riseautomatons.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import riseautomatons.RiseAutomatons;
import riseautomatons.item.ItemArch;
import riseautomatons.item.ItemComplex;
import riseautomatons.item.ItemLumo;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Blocks extends net.minecraft.init.Blocks {
	public static final ResourceLocation BLOCK_PNG = new ResourceLocation("riseautomatons", "block.png");
	public static final ResourceLocation PATTERNS_PNG = new ResourceLocation("riseautomatons", "patterns.png");

	public static void init() {
		GameRegistry.registerBlock(worker, "worker");
		//LanguageRegistry.addName(worker, "Worker");
		GameRegistry.registerBlock(sentry, "sentry");
		//LanguageRegistry.addName(sentry, "Sentry");
		GameRegistry.registerBlock(tote, "tote");
		//LanguageRegistry.addName(tote, "Tote");
		GameRegistry.registerBlock(beacon, "beacon");
		//LanguageRegistry.addName(beacon, "Beacon");

		GameRegistry.registerBlock(gearbox, "gearbox");
		//LanguageRegistry.addName(gearbox, "Gearbox");
		GameRegistry.registerBlock(chalk, "chalk");
		//LanguageRegistry.addName(chalk, "Chalk");
		BlockChalk.rendererId = RenderingRegistry.getNextAvailableRenderId();
		GameRegistry.registerBlock(turnBlock, "turnBlock");
		//LanguageRegistry.addName(turnBlock, "Shaft");
		GameRegistry.registerBlock(windmill, "windmill");
		//LanguageRegistry.addName(windmill, "Windmill");
		GameRegistry.registerBlock(slabBlock, "slabBlock");
		//LanguageRegistry.addName(slabBlock, "Slab");
		GameRegistry.registerBlock(saltOre, "saltOre");
		//LanguageRegistry.addName(saltOre, "Salt Ore");
		OreDictionary.registerOre("oreSalt", new ItemStack(saltOre, 1, 1));
		GameRegistry.registerBlock(sulfOre, "sulfOre");
		//LanguageRegistry.addName(sulfOre, "Sulfide");
		OreDictionary.registerOre("oreSulfur", new ItemStack(sulfOre, 1, 0));
		GameRegistry.registerBlock(tech, ItemComplex.class, "tech");
		//LanguageRegistry.addName(new ItemStack(tech, 1, 0), "Ancient Construct");
		//LanguageRegistry.addName(new ItemStack(tech, 1, 1), "Bionic Mass");
		//LanguageRegistry.addName(new ItemStack(tech, 1, 2), "Domestic Tile");
		//LanguageRegistry.addName(new ItemStack(tech, 1, 3), "Domestic Tile");
		GameRegistry.registerBlock(sky, ItemLumo.class, "sky");
		//LanguageRegistry.addName(new ItemStack(sky, 1, 0), "Atmospheric simulator");
		//LanguageRegistry.addName(new ItemStack(sky, 1, 1), "Walkway Tile");
		GameRegistry.registerBlock(arch, ItemArch.class, "arch");
		//LanguageRegistry.addName(arch, "Architectural Shell");
		GameRegistry.registerBlock(architect, "architect");
		//LanguageRegistry.addName(architect, "Architect");
		GameRegistry.registerBlock(archBend, "archBend");
		//LanguageRegistry.addName(archBend, "Architectural Connector");
		GameRegistry.registerBlock(crink, ItemCrink.class, "crink");
		//LanguageRegistry.addName(crink, "Foliage Array");
		GameRegistry.registerBlock(frass, "frass");
		//LanguageRegistry.addName(frass, "Frass");
		GameRegistry.registerBlock(plantMass, "plantMass");
		//LanguageRegistry.addName(plantMass, "Plant Mass");
		GameRegistry.registerBlock(grower, "grower");
		//LanguageRegistry.addName(grower, "Bionic Stalk");
		GameRegistry.registerBlock(glowy, "glowy");
		//LanguageRegistry.addName(glowy, "Illuminator");
		GameRegistry.registerBlock(fakeCrystal, "fakeCrystal");
		//LanguageRegistry.addName(fakeCrystal, "Power Shard");
		GameRegistry.registerBlock(crystal, "crystal");
		//LanguageRegistry.addName(crystal, "Power Shard");
		GameRegistry.registerBlock(boing, "boing");
		//LanguageRegistry.addName(boing, "Slider Shell");
		GameRegistry.registerBlock(heal, "heal");
		//LanguageRegistry.addName(heal, "Biomatter Regenerator");
		GameRegistry.registerBlock(dapling, "dapling");
		//LanguageRegistry.addName(dapling, "Dapling");
		GameRegistry.registerBlock(duplex, "duplex");
		//LanguageRegistry.addName(duplex, "Duplex");
		GameRegistry.registerBlock(latch, "latch");
		//LanguageRegistry.addName(latch, "Latch");

		GameRegistry.registerTileEntity(TileEntityBeacon.class, "BotBeacon");
		GameRegistry.registerTileEntity(TileEntityLatch.class, "Latch");
		GameRegistry.registerTileEntity(TileEntityTurn.class, "Turn");
		GameRegistry.registerTileEntity(TileEntityWindmill.class, "Windmill");
		GameRegistry.registerTileEntity(TileEntityArchitect.class, "Architect");
	}

	@SideOnly(Side.CLIENT)
	public static void registerTileEntities() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTurn.class, new TileEntityTurnRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWindmill.class, new TileEntityWindmillRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityArchitect.class, new TileEntityArchitectRenderer());
	}

	@SideOnly(Side.CLIENT)
	public static void registerRenderer() {
		RenderingRegistry.registerBlockHandler(new RenderWorkerBlock());
		RenderingRegistry.registerBlockHandler(new RenderSentryBlock());
		RenderingRegistry.registerBlockHandler(new RenderChalkBlock());
		RenderingRegistry.registerBlockHandler(new RenderToteBlock());
	}

	public static Block worker = (new BlockWorker())
			.setHardness(0.5F).setResistance(10F).setBlockName("riseautomatons:Worker");
    public static Block sentry = (new BlockSentry())
    		.setHardness(0.5F).setResistance(10F).setBlockName("riseautomatons:Sentry");
	public static Block tote = (new BlockTote())
			.setHardness(1F).setResistance(20F)
			.setStepSound(Block.soundTypeMetal).setBlockName("riseautomatons:Tote");
	public static Block beacon = (new BlockBeacon())
    		.setHardness(0.5F).setResistance(10F)
    		.setBlockName("riseautomatons:beacon")
    		.setBlockTextureName("riseautomatons:beacon");

	public static Block gearbox = (new BlockGearBox( 10, Material.iron))
			.setHardness(1F).setResistance(5F).setStepSound(Block.soundTypeMetal)
			.setBlockName("riseautomatons:gearbox")
			.setBlockTextureName("riseautomatons:gearbox")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block chalk = (new BlockChalk( 164))
			.setHardness(0f).setStepSound(Block.soundTypeStone)
			.setBlockName("riseautomatons:Chalk");
	public static Block turnBlock = (new BlockTurn())
			.setHardness(0.05f).setResistance(0)
			.setStepSound(Block.soundTypeStone).setBlockName("riseautomatons:TurnBlock")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block windmill = (new BlockWindmill( 4,
			Material.wood)).setHardness(0.5F).setResistance(1F)
			.setStepSound(Block.soundTypeWood).setBlockName("riseautomatons:Windmill")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block slabBlock = (new BlockSlab())
			.setHardness(1.5F).setResistance(10F).setBlockName("riseautomatons:SlabBlock")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block sulfOre = (new BlockOre( 0))
			.setHardness(3F).setResistance(5F)
			.setStepSound(Block.soundTypeStone).setBlockName("riseautomatons:SulfOre")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block saltOre = (new BlockOre( 1))
			.setHardness(3F).setResistance(5F)
			.setStepSound(Block.soundTypeStone).setBlockName("riseautomatons:SaltOre")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block tech = (new BlockComplex())
			.setHardness(1.0F).setResistance(5F)
			.setStepSound(Block.soundTypeStone).setBlockName("riseautomatons:tech")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block arch = (new BlockArch())
			.setHardness(0.25F).setStepSound(Block.soundTypeGlass)
			.setBlockName("riseautomatons:arch").setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block sky = (new BlockLumo()).setHardness(1F)
			.setResistance(5F).setLightLevel(1F)
			.setStepSound(Block.soundTypeGlass).setBlockName("riseautomatons:sky")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block archBend = (new BlockArchBend())
			.setHardness(0.15F).setStepSound(Block.soundTypeGlass)
			.setBlockName("riseautomatons:archbend").setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block architect = (new BlockArchitect())
			.setBlockUnbreakable().setStepSound(Block.soundTypeStone)
			.setBlockName("riseautomatons:coreMachine")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block crink = (new BlockCrink( 234))
			.setHardness(0.1F).setLightOpacity(1).setLightLevel(0.5F)
			.setStepSound(Block.soundTypeGlass).setBlockName("riseautomatons:crink")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
    public static Block frass = (new BlockFrass())
    		.setHardness(0.25F).setStepSound(Block.soundTypeGlass).setBlockName("riseautomatons:frass")
    		.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block plantMass = (new BlockPlant())
			.setHardness(0.5F).setStepSound(Block.soundTypeGrass)
			.setBlockName("riseautomatons:plantMass")
			.setBlockTextureName("riseautomatons:plantMass")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block crystal = new BlockGlow()
			.setHardness(0.4F).setResistance(5.0F)
			.setLightLevel(0.625F).setStepSound(Block.soundTypeGlass).setBlockName("riseautomatons:crystal")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block fakeCrystal = new BlockBad()
			.setHardness(0.4F).setResistance(5.0F)
			.setLightLevel(0.625F).setStepSound(Block.soundTypeGlass)
			.setBlockName("riseautomatons:crystal")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block glowy = (new BlockGlow())
			.loadSprites(32,31)
			.setHardness(0.4F).setResistance(5.0F).setLightLevel(0.95F)
			.setStepSound(Block.soundTypeGlass).setBlockName("riseautomatons:glowy")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
    public static Block boing = (new BlockBoing())
			.setHardness(0.3F).setStepSound(Block.soundTypeGlass).setBlockName("riseautomatons:boing")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
    public static Block grower = new BlockGrower()
    		.setHardness(0.5F).setStepSound(Block.soundTypeGlass).setBlockName("riseautomatons:grower")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block heal = new BlockHeal()
			.setHardness(0.3F).setStepSound(Block.soundTypeGlass).setBlockName("riseautomatons:heal")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block dapling = new BlockDapling()
			.setHardness(0.0F).setStepSound(Block.soundTypeGrass)
			.setBlockName("riseautomatons:dapling")
			.setBlockTextureName("riseautomatons:dapling")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block duplex = new BlockDuplex()
			.setHardness(0.1F).setLightOpacity(1).setLightLevel(0.5F)
			.setStepSound(Block.soundTypeGlass)
			.setBlockName("riseautomatons:duplex")
			.setBlockTextureName("riseautomatons:duplex")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block latch = (new BlockLatch())
			.setHardness(1F).setResistance(20F)
			.setStepSound(Block.soundTypeMetal).setBlockName("riseautomatons:latch")
			.setCreativeTab(RiseAutomatons.tabAutomatons);

	public static void setFrassSpread(boolean enableFrassSpread) {
		BlockFrass.spread = enableFrassSpread;
	}

}
