package riseautomatons.common.item;

import riseautomatons.common.Ids;
import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.Item;
import net.minecraft.src.ItemPickaxe;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class ItemAPickaxe extends ItemPickaxe {
	  private static Block[] blocksEffectiveAgainst = {
		    Block.cobblestone, Block.stone, Block.sandStone, Block.cobblestoneMossy, Block.oreIron, Block.blockSteel, Block.oreCoal, Block.blockGold,
		    Block.oreGold, Block.oreDiamond, Block.blockDiamond, Block.ice, Block.netherrack, Block.oreLapis, Block.blockLapis, Block.blocksList[Ids.blockTech], Block.blocksList[Ids.blockFrass] };

	public ItemAPickaxe(int i, int j) {
		super(i, EnumToolMaterial.EMERALD);
		this.efficiencyOnProperMaterial = 4.0F;
		setMaxDamage(4);
		this.efficiencyOnProperMaterial = 100.0F;
		this.damageVsEntity = (j + 2);
	}

	public ItemAPickaxe(int i) {
		this(i, 2);
	}

	@Override
	public float getStrVsBlock(ItemStack par1ItemStack, Block block) {
		return block.getBlockHardness(null, 0,0,0) > 3.0F ? 1.0F : 100.0F;
	}

	@Override
	public boolean hitEntity(ItemStack itemstack,
			EntityLiving par2EntityLiving, EntityLiving entityliving1) {
		itemstack.damageItem(2, entityliving1);
	    return true;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack itemstack, World par2World,
			int par3, int par4, int par5, int par6,
			EntityLiving entityliving) {
		itemstack.damageItem(1, entityliving);
	    return true;
	}

	@Override
	public int getDamageVsEntity(Entity par1Entity) {
		return this.damageVsEntity;
	}

	@Override
	public boolean isFull3D() {
		return true;
	}

	@Override
	public boolean canHarvestBlock(Block block) {
		return block.getBlockHardness(null, 0,0,0) <= 3.0F;
	}

}
