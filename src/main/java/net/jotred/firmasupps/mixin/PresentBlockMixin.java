package net.jotred.firmasupps.mixin;

import net.jotred.firmasupps.common.component.FSComponents;
import net.jotred.firmasupps.common.component.block.SackComponent;
import net.mehvahdjukaar.supplementaries.common.block.blocks.PresentBlock;
import net.mehvahdjukaar.supplementaries.common.items.components.PresentAddress;
import net.mehvahdjukaar.supplementaries.reg.ModComponents;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

import net.dries007.tfc.common.component.size.IItemSize;
import net.dries007.tfc.common.component.size.Size;
import net.dries007.tfc.common.component.size.Weight;

@Mixin(PresentBlock.class)
public abstract class PresentBlockMixin implements IItemSize
{
    @Override
    public Size getSize(ItemStack stack)
    {
        return Size.HUGE;
    }

    @Override
    public Weight getWeight(ItemStack stack)
    {
        return stack.get(ModComponents.ADDRESS) == null ? Weight.HEAVY : Weight.VERY_HEAVY;
    }
}
