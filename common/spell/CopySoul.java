package riseautomatons.common.spell;

import java.util.List;

import riseautomatons.common.Ids;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;

public class CopySoul extends Spell {

	@Override
	public boolean cast(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		if (world.isRemote) {
			return false;
		}
		List L = world.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(i - 2, j - 2, k - 2, i + 2, j + 2, k + 2));
		EntityItem targetItem = null;

		if (L.size() > 1)
		{
			for (int n = 0; n < L.size(); n++)
			{
				EntityItem e = (EntityItem)L.get(n);

				if (e.item.itemID == Ids.soulCore && e.item.getItemDamage() >= 5)
				{
					targetItem = e;
					break;
				}
			}
		}
		else
		{
			return false;
		}

		L = world.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(i - 2, j - 2, k - 2, i + 2, j + 2, k + 2));
		boolean success = false;

		for (int n = 0; n < L.size(); n++)
		{
			EntityItem e = (EntityItem)L.get(n);

			if (e.item.itemID == Ids.soulCore && e.item.getItemDamage() == 4)
			{
				e.item.setItemDamage(0);
				success = true;
			}
		}

		return success;
	}
}
