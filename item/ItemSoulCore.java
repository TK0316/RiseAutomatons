package riseautomatons.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import riseautomatons.Ids;
import riseautomatons.Universal;
import riseautomatons.block.Blocks;
import riseautomatons.block.TileEntityLatch;
import riseautomatons.entity.EntitySentry;
import riseautomatons.entity.EntityTote;
import riseautomatons.entity.EntityWorker;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSoulCore extends Item {
	int textur[];

	private IIcon icons[];

	public ItemSoulCore() {

		setHasSubtypes(true);
		setMaxDamage(0);
	}

	@Override
	public IIcon getIconFromDamage(int par1) {
		int i = MathHelper.clamp_int(par1, 0, EnumSoulCore.values().length-1);
		return icons[i];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[EnumSoulCore.values().length];
		for (int var4 = 0; var4 < EnumSoulCore.values().length; ++var4) {
			icons[var4] = par1IconRegister.registerIcon("riseautomatons:"+EnumSoulCore.values()[var4].name);
		}
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer,
			World world, int i, int j, int k, int par7, float par8, float par9,
			float par10) {

		if (ItemSoulCore.touch(entityplayer, itemstack.getItemDamage(), world,
				i, j, k)) {
			itemstack.stackSize--;
			return true;
		}
		return false;
	}

	public static boolean touch(EntityPlayer entityplayer, int itemDamage,
			World world, int i, int j, int k) {
		Block ii = world.getBlock(i, j, k);
		if (derr(ii)) {
			if (world.isAirBlock(i, j, k)) {
				return false;
			}
			if (world.isRaining() && world.canLightningStrikeAt(i, j + 1, k)) {
				return false;
			}
			if (!Universal.improperWorld(world)) {
				if (ii == Ids.blockWorker) {
					int m = world.getBlockMetadata(i, j, k);
					if (itemDamage == 0) {
						world.spawnEntityInWorld(new EntityWorker(world, i + 0.5F, j, k + 0.5F, m, entityplayer == null ? "" : entityplayer.getCommandSenderName()));
						world.setBlockToAir(i, j, k);
					}
					/*else if (itemDamage == 5) {
						world.spawnEntityInWorld(new EntityWorker(world,
								(float) i + 0.5F, (float) j, (float) k + 0.5F,
								m, ""));
						world.setBlockWithNotify(i, j, k, 0);
					}
					else if (itemDamage == 6) {
						EntityWorker wor = new EntityWorker(world,
								(float) i + 0.5F, (float) j, (float) k + 0.5F,
								m, "Tenebrae");
						wor.setMode(5);
						world.spawnEntityInWorld(wor);
						world.setBlockWithNotify(i, j, k, 0);
					}*/
					else {
						return false;
					}
				}
				else if (ii == Ids.blockSentry) {
					if (itemDamage != 0) {
						return false;
					}
					int m = world.getBlockMetadata(i, j, k);
					world.spawnEntityInWorld(new EntitySentry(world, i + 0.5F, j, k + 0.5F, m, entityplayer == null ? "" : entityplayer.getCommandSenderName()));
					world.setBlockToAir(i, j, k);
				}
				else if (ii == Ids.blockTote) {
					if (itemDamage != 0) {
						return false;
					}
					int m = world.getBlockMetadata(i, j, k);
					EntityTote tote = new EntityTote(world, i + 0.5F, j, k + 0.5F, entityplayer == null ? "" : entityplayer.getCommandSenderName());
					world.spawnEntityInWorld(tote);
					TileEntityLatch tot = (TileEntityLatch) world
							.getTileEntity(i, j, k);
					tote.cargoItems = tot.dispenserContents.clone();
					world.setBlockToAir(i, j, k);
				}
				/*else if (ii == Ids.toteBlock) {
					if (itemDamage != 0) {
						return false;
					}
					EntityTote toter = new EntityTote(world, (float) i + 0.5F,
							(float) j, (float) k + 0.5F,
							entityplayer == null ? "huh" : entityplayer.username);
					world.spawnEntityInWorld(toter);
					TileEntityeLatch tot = (TileEntityLatch) world
							.getTileEntity(i, j, k);
					toter.inv.cargoItems = tot.dispenserContents.clone();
					tot.dispenserContents = new ItemStack[9];
					world.setBlockWithNotify(i, j, k, 0);
				}
				else if (ii != 88) {
					Block bb = Block.blocksList[ii];
					int dam = world.getBlockMetadata(i, j, k);
					if (ii == Ids.arch) {
						dam = 0;
					}
					if (itemDamage == 0) {
						int he = 1 + MathHelper.floor_double(bb
								.getBlockHardness(world, i, j, k) * 3);
						world.spawnEntityInWorld(new EntityGolem(world,
								(float) i + 0.5F, (float) j, (float) k + 0.5F,
								ii, he, dam));
					} else if (itemDamage == 5) {
						int he = 1 + MathHelper.floor_double(bb
								.getBlockHardness(world, i, j, k) * 8);
						List<EntityItem> l = world.getEntitiesWithinAABB(
								EntityItem.class, AxisAlignedBB.getBoundingBox(
										i - 2, j - 2, k - 2, i + 2, j + 2,
										k + 2));
						int u = -1;
						if (!l.isEmpty()) {

							for (int n = 0; n < l.size(); n++) {
								EntityItem it = l.get(n);
								if (it.item.itemID == Ids.form) {
									u = it.item.getItemDamage();
									break;
								}
							}
							if (u != -1) {
								world.spawnEntityInWorld(new EntityGolemPure(u,
										world, (float) i + 0.5F, (float) j,
										(float) k + 0.5F, ii, he, dam));
							}
						}

						world.spawnEntityInWorld(new EntityGolemPure(world,
								(float) i + 0.5F, (float) j, (float) k + 0.5F,
								ii, he, dam));
					} else {
						return false;
					}
					world.setBlockWithNotify(i, j, k, 0);
				}
				else {
					world.spawnEntityInWorld(new EntityGolem2(world,
							(float) i + 0.5F, (float) j, (float) k + 0.5F));
					world.setBlockWithNotify(i, j, k, 0);
				}*/
			}
			return true;
		}
		return false;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return (new StringBuilder()).append(super.getUnlocalizedName()).append(".")
				.append(EnumSoulCore.values()[itemstack.getItemDamage()].name)
				.toString();
	}

	public static boolean derr(Block ii) {
        if(ii == Blocks.stone) return true;
        if(ii == Blocks.grass) return true;
        if(ii == Blocks.dirt) return true;
        if(ii == Blocks.cobblestone) return true;
        if(ii == Blocks.planks) return true;
        if(ii == Blocks.sand) return true;
        if(ii == Blocks.gravel) return true;
        if(ii == Blocks.gold_ore) return true;
        if(ii == Blocks.iron_ore) return true;
        if(ii == Blocks.coal_ore) return true;
        if(ii == Blocks.log) return true;
        if(ii == Blocks.sandstone) return true;
        if(ii == Blocks.wool) return true;
        if(ii == Blocks.gold_block) return true;
        if(ii == Blocks.iron_block) return true;
        if(ii == Blocks.double_stone_slab) return true;
        if(ii == Blocks.stone_slab) return true;
        if(ii == Blocks.brick_block) return true;
        if(ii == Blocks.obsidian) return true;
        if(ii == Blocks.furnace) return true;
        if(ii == Blocks.ice) return true;
        if(ii == Blocks.snow) return true;
        if(ii == Blocks.cactus) return true;
        if(ii == Blocks.clay) return true;
        if(ii == Blocks.pumpkin) return true;
        if(ii == Blocks.netherrack) return true;
        if(ii == Blocks.soul_sand) return true;
        if(ii == Blocks.stonebrick) return true;
        if(ii == Blocks.brown_mushroom_block) return true;
        if(ii == Blocks.red_mushroom_block) return true;

		if (ii == Ids.blockWorker || ii == Ids.blockSentry || ii == Ids.blockTote /*|| ii == Ids.sulfOre || ii == Ids.saltOre*/) {
			return true;
		}
		return false;
	}

	@Override
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		for (int var4 = 0; var4 < EnumSoulCore.values().length; ++var4) {
			par3List.add(new ItemStack(par1, 1, var4));
		}
	}

}
