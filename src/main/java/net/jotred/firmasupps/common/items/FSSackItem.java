package net.jotred.firmasupps.common.items;

import java.util.Optional;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import net.mehvahdjukaar.supplementaries.common.items.SackItem;

import net.dries007.tfc.common.blocks.TooltipBlock;
import net.dries007.tfc.common.component.TFCComponents;
import net.dries007.tfc.common.component.item.ItemListComponent;
import net.dries007.tfc.config.TFCConfig;

public class FSSackItem extends SackItem
{
    public FSSackItem(Block block, Properties properties)
    {
        super(block, properties);
    }

    @Override
    public int getMaxStackSize(ItemStack stack)
    {
        return 1;
    }

    /**
     * Supplementaries uses their own system for becoming overencumbered, which gets triggered from the {@link SackItem#inventoryTick(ItemStack, Level, Entity, int, boolean)} method.
     * <p>
     * However, since TFC already has its own system for overburdening, and the implementation by Supplementaries doesn't work with the NBT naming system used by TFC block entities,
     * we simply stop our implementation from calling their method.
     * <p>
     * As it only overrides the empty {@link Item#inventoryTick(ItemStack, Level, Entity, int, boolean)}, this is completely safe to do, without risking any, still intended, actions being skipped
     */
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int itemSlot, boolean isSelected)
    {
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack)
    {
        return TFCConfig.CLIENT.displayItemContentsAsImages.get()
            ? TooltipBlock.buildInventoryTooltip(stack.getOrDefault(TFCComponents.CONTENTS, ItemListComponent.EMPTY).contents(), 3, 3)
            : Optional.empty();
    }
}
