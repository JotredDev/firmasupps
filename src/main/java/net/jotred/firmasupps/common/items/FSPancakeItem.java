package net.jotred.firmasupps.common.items;

import net.jotred.firmasupps.common.blocks.FSBlocks;
import net.jotred.firmasupps.common.blocks.FSPancakeBlock;
import net.mehvahdjukaar.moonlight.api.item.additional_placements.AdditionalItemPlacement;
import net.mehvahdjukaar.supplementaries.common.items.PancakeItem;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;

public class FSPancakeItem extends PancakeItem
{
    public FSPancakeItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public String getDescriptionId()
    {
        return FSBlocks.PANCAKE.get().getDescriptionId();
    }

    /**
     * We need to override to ensure our pancake item places a {@link FSPancakeBlock},
     * while also handling music disc behaviour of pancakes
     */
    @Override
    public InteractionResult useOn(UseOnContext context)
    {/*
        // Handling music disc behaviour
        // Notably, we check only with a single item instead of the whole stack, to avoid putting an entire stack into a juke box
        ItemStack handItem = context.getItemInHand();
        int oldAmount = handItem.getCount();

        handItem.setCount(1);
        InteractionResult result = super.useOn(context);

        if (handItem.isEmpty())
        {
            handItem.setCount(oldAmount - 1);
        }
        else
        {
            handItem.setCount(oldAmount);
        }

        // If no pancake was consumed by the check, we try placing a pancake block
        if (!result.consumesAction())
        {
            return AdditionalItemPlacement.getBlockPlacer().mimicUseOn(context, FSBlocks.PANCAKE.get(), null);
        }
        return result;
        */
        return AdditionalItemPlacement.getBlockPlacer().mimicUseOn(context, FSBlocks.PANCAKE.get(), null);
    }
}
