package riseautomatons.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.oredict.OreDictionary;
import riseautomatons.Ids;
import riseautomatons.RiseAutomatons;
import riseautomatons.item.ItemArch;
import riseautomatons.item.ItemComplex;
import riseautomatons.item.ItemLumo;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Blocks {
	public static final String BLOCK_PNG = "/riseautomatons/block.png";
	public static final String PATTERNS_PNG = "/riseautomatons/patterns.png";

	public static void init() {
		GameRegistry.registerBlock(worker);
		LanguageRegistry.addName(worker, "Worker");
		GameRegistry.registerBlock(sentry);
		LanguageRegistry.addName(sentry, "Sentry");
		GameRegistry.registerBlock(tote);
		LanguageRegistry.addName(tote, "Tote");
		GameRegistry.registerBlock(beacon);
		LanguageRegistry.addName(beacon, "Beacon");

		GameRegistry.registerBlock(gearbox);
		LanguageRegistry.addName(gearbox, "Gearbox");
		GameRegistry.registerBlock(chalk);
		LanguageRegistry.addName(chalk, "Chalk");
		BlockChalk.rendererId = RenderingRegistry.getNextAvailableRenderId();
		GameRegistry.registerBlock(turnBlock);
		LanguageRegistry.addName(turnBlock, "Shaft");
		GameRegistry.registerBlock(windmill);
		LanguageRegistry.addName(windmill, "Windmill");
		GameRegistry.registerBlock(slabBlock);
		LanguageRegistry.addName(slabBlock, "Slab");
		GameRegistry.registerBlock(saltOre);
		LanguageRegistry.addName(saltOre, "Salt Ore");
		OreDictionary.registerOre("oreSalt", new ItemStack(saltOre, 1, 1));
		GameRegistry.registerBlock(sulfOre);
		LanguageRegistry.addName(sulfOre, "Sulfide");
		OreDictionary.registerOre("oreSulfur", new ItemStack(sulfOre, 1, 0));
		GameRegistry.registerBlock(tech, ItemComplex.class);
		LanguageRegistry.addName(new ItemStack(tech, 1, 0), "Ancient Construct");
		LanguageRegistry.addName(new ItemStack(tech, 1, 1), "Bionic Mass");
		LanguageRegistry.addName(new ItemStack(tech, 1, 2), "Domestic Tile");
		LanguageRegistry.addName(new ItemStack(tech, 1, 3), "Domestic Tile");
		GameRegistry.registerBlock(sky, ItemLumo.class);
		LanguageRegistry.addName(new ItemStack(sky, 1, 0), "Atmospheric simulator");
		LanguageRegistry.addName(new ItemStack(sky, 1, 1), "Walkway Tile");
		GameRegistry.registerBlock(arch, ItemArch.class);
		LanguageRegistry.addName(arch, "Architectural Shell");
		GameRegistry.registerBlock(architect);
		LanguageRegistry.addName(architect, "Architect");
		GameRegistry.registerBlock(archBend);
		LanguageRegistry.addName(archBend, "Architectural Connector");
		GameRegistry.registerBlock(crink);
		LanguageRegistry.addName(crink, "Foliage Array");
		GameRegistry.registerBlock(frass);
		LanguageRegistry.addName(frass, "Frass");
		GameRegistry.registerBlock(plantMass);
		LanguageRegistry.addName(plantMass, "Plant Mass");
		GameRegistry.registerBlock(grower);
		LanguageRegistry.addName(grower, "Bionic Stalk");
		GameRegistry.registerBlock(glowy);
		LanguageRegistry.addName(glowy, "Illuminator");
		GameRegistry.registerBlock(fakeCrystal);
		LanguageRegistry.addName(fakeCrystal, "Power Shard");
		GameRegistry.registerBlock(crystal);
		LanguageRegistry.addName(crystal, "Power Shard");
		GameRegistry.registerBlock(boing);
		LanguageRegistry.addName(boing, "Slider Shell");
		GameRegistry.registerBlock(heal);
		LanguageRegistry.addName(heal, "Biomatter Regenerator");
		GameRegistry.registerBlock(dapling);
		LanguageRegistry.addName(dapling, "Dapling");
		GameRegistry.registerBlock(duplex);
		LanguageRegistry.addName(duplex, "Duplex");
		GameRegistry.registerBlock(latch);
		LanguageRegistry.addName(latch, "Latch");

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
	public static void registerTextures() {
		MinecraftForgeClient.preloadTexture(BLOCK_PNG);
		MinecraftForgeClient.preloadTexture(PATTERNS_PNG);
		RenderingRegistry.registerBlockHandler(new RenderWorkerBlock());
		RenderingRegistry.registerBlockHandler(new RenderSentryBlock());
		RenderingRegistry.registerBlockHandler(new RenderChalkBlock());
		RenderingRegistry.registerBlockHandler(new RenderToteBlock());
	}

	public static Block worker = (new BlockWorker(Ids.blockWorker))
			.setHardness(0.5F).setResistance(10F).setUnlocalizedName("riseautomatons:Worker");
    public static Block sentry = (new BlockSentry(Ids.blockSentry))
    		.setHardness(0.5F).setResistance(10F).setUnlocalizedName("riseautomatons:Sentry");
	public static Block tote = (new BlockTote(Ids.blockTote))
			.setHardness(1F).setResistance(20F)
			.setStepSound(Block.soundMetalFootstep).setUnlocalizedName("riseautomatons:Tote");
	public static Block beacon = (new BlockBeacon(Ids.blockBeacon))
    		.setHardness(0.5F).setResistance(10F).setUnlocalizedName("riseautomatons:beacon");

	public static Block gearbox = (new BlockGearBox(Ids.blockGearbox, 10, Material.iron))
			.setHardness(1F).setResistance(5F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("riseautomatons:gearbox")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block chalk = (new BlockChalk(Ids.blockChalk, 164))
			.setHardness(0f).setStepSound(Block.soundPowderFootstep).setUnlocalizedName("riseautomatons:Chalk");
	public static Block turnBlock = (new BlockTurn(Ids.blockTurn))
			.setHardness(0.05f).setResistance(0)
			.setStepSound(Block.soundStoneFootstep).setUnlocalizedName("riseautomatons:TurnBlock")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block windmill = (new BlockWindmill(Ids.blockWindmill, 4,
			Material.wood)).setHardness(0.5F).setResistance(1F)
			.setStepSound(Block.soundWoodFootstep).setUnlocalizedName("riseautomatons:Windmill")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block slabBlock = (new BlockSlab(Ids.blockSlab))
			.setHardness(1.5F).setResistance(10F).setUnlocalizedName("riseautomatons:SlabBlock")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block sulfOre = (new BlockOre(Ids.sulfOre, 0))
			.setHardness(3F).setResistance(5F)
			.setStepSound(Block.soundStoneFootstep).setUnlocalizedName("riseautomatons:SulfOre")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block saltOre = (new BlockOre(Ids.saltOre, 1))
			.setHardness(3F).setResistance(5F)
			.setStepSound(Block.soundStoneFootstep).setUnlocalizedName("riseautomatons:SaltOre")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block tech = (new BlockComplex(Ids.blockTech))
			.setHardness(1.0F).setResistance(5F)
			.setStepSound(Block.soundStoneFootstep).setUnlocalizedName("riseautomatons:tech")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block arch = (new BlockArch(Ids.blockArch))
			.setHardness(0.25F).setStepSound(Block.soundGlassFootstep)
			.setUnlocalizedName("riseautomatons:arch").setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block sky = (new BlockLumo(Ids.blockSky)).setHardness(1F)
			.setResistance(5F).setLightValue(1F)
			.setStepSound(Block.soundGlassFootstep).setUnlocalizedName("riseautomatons:sky")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block archBend = (new BlockArchBend(Ids.blockArchBend))
			.setHardness(0.15F).setStepSound(Block.soundGlassFootstep)
			.setUnlocalizedName("riseautomatons:archbend").setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block architect = (new BlockArchitect(Ids.blockArchitect))
			.setBlockUnbreakable().setStepSound(Block.soundStoneFootstep)
			.setUnlocalizedName("riseautomatons:coreMachine")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block crink = (new BlockCrink(Ids.blockCrink, 234))
			.setHardness(0.1F).setLightOpacity(1).setLightValue(0.5F)
			.setStepSound(Block.soundGlassFootstep).setUnlocalizedName("riseautomatons:crink")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
    public static Block frass = (new BlockFrass(Ids.blockFrass))
    		.setHardness(0.25F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("riseautomatons:frass")
    		.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block plantMass = (new BlockPlant(Ids.blockPlantMass))
			.setHardness(0.5F).setStepSound(Block.soundGrassFootstep)
			.setUnlocalizedName("riseautomatons:plantMass")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block crystal = new BlockGlow(Ids.blockCrystal)
			.setHardness(0.4F).setResistance(5.0F)
			.setLightValue(0.625F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("riseautomatons:crystal")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block fakeCrystal = new BlockBad(Ids.blockFakeCrystal)
			.setHardness(0.4F).setResistance(5.0F)
			.setLightValue(0.625F).setStepSound(Block.soundGlassFootstep)
			.setUnlocalizedName("riseautomatons:crystal")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block glowy = (new BlockGlow(Ids.blockGlowy))
			.loadSprites(32,31)
			.setHardness(0.4F).setResistance(5.0F).setLightValue(0.95F)
			.setStepSound(Block.soundGlassFootstep).setUnlocalizedName("riseautomatons:glowy")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
    public static Block boing = (new BlockBoing(Ids.blockBoing))
			.setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("riseautomatons:boing")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
    public static Block grower = new BlockGrower(Ids.blockGrower)
    		.setHardness(0.5F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("riseautomatons:grower")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block heal = new BlockHeal(Ids.blockHeal)
			.setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("riseautomatons:heal")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block dapling = new BlockDapling(Ids.blockDapling)
			.setHardness(0.0F).setStepSound(Block.soundGrassFootstep)
			.setUnlocalizedName("riseautomatons:dapling")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block duplex = new BlockDuplex(Ids.blockDuplex)
			.setHardness(0.1F).setLightOpacity(1).setLightValue(0.5F)
			.setStepSound(Block.soundGlassFootstep).setUnlocalizedName("riseautomatons:duplex")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block latch = (new BlockLatch(Ids.blockLatch))
			.setHardness(1F).setResistance(20F)
			.setStepSound(Block.soundMetalFootstep).setUnlocalizedName("riseautomatons:latch")
			.setCreativeTab(RiseAutomatons.tabAutomatons);

	public static void setFrassSpread(boolean enableFrassSpread) {
		BlockFrass.spread = enableFrassSpread;
	}

}
