package riseautomatons.item;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import riseautomatons.Ids;
import static net.minecraft.init.Blocks.*;

public class ItemAPickaxe extends ItemPickaxe {
	  private static Block[] blocksEffectiveAgainst = {
		    cobblestone, stone, sandstone, mossy_cobblestone, iron_ore, iron_block, coal_ore, coal_block, emerald_block, emerald_ore, obsidian, gold_block,
              gold_ore, diamond_ore, diamond_block, ice, netherrack, lapis_ore, lapis_block, Ids.blockTech, Ids.blockFrass };

	public ItemAPickaxe() {
		super(Item.ToolMaterial.EMERALD);
		this.efficiencyOnProperMaterial = 4.0F;
		setMaxDamage(4);
		this.efficiencyOnProperMaterial = 100.0F;
	}

	@Override
	public float func_150893_a(ItemStack par1ItemStack, Block block) {
		return block.getBlockHardness(null, 0,0,0) > 3.0F ? 1.0F : 100.0F;
	}

	@Override
	public boolean hitEntity(ItemStack itemstack,
			EntityLivingBase par2EntityLiving, EntityLivingBase entityliving1) {
		itemstack.damageItem(2, entityliving1);
	    return true;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack itemstack, World par2World,
			Block par3, int par4, int par5, int par6,
			EntityLivingBase entityliving) {
		itemstack.damageItem(1, entityliving);
	    return true;
	}

	@Override
	public boolean isFull3D() {
		return true;
	}

	@Override
	public boolean func_150897_b(Block block) {
		return block.getBlockHardness(null, 0,0,0) <= 3.0F;
	}

}
