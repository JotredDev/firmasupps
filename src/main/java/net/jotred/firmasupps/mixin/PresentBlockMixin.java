package net.jotred.firmasupps.mixin;

import net.mehvahdjukaar.supplementaries.common.block.blocks.PresentBlock;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

import net.dries007.tfc.common.capabilities.size.IItemSize;
import net.dries007.tfc.common.capabilities.size.Size;
import net.dries007.tfc.common.capabilities.size.Weight;

@Mixin(PresentBlock.class)
public abstract class PresentBlockMixin implements IItemSize
{
    @Override
    public Size getSize(ItemStack item)
    {
        return Size.HUGE;
    }

    @Override
    public Weight getWeight(ItemStack item)
    {
        return item.getTag() == null ? Weight.HEAVY : Weight.VERY_HEAVY;
    }
}
