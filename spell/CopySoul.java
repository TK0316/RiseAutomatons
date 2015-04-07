package riseautomatons.spell;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import riseautomatons.Ids;

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

				if (e.getEntityItem().getItem() == Ids.soulCore && e.getEntityItem().getItemDamage() >= 5)
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

			if (e.getEntityItem().getItem() == Ids.soulCore && e.getEntityItem().getItemDamage() == 4)
			{
				e.getEntityItem().setItemDamage(0);
				success = true;
			}
		}

		return success;
	}
}
