package net.jotred.firmasupps.common.items;

import java.util.List;
import net.jotred.firmasupps.common.blocks.FSBlocks;
import net.jotred.firmasupps.common.blocks.FSPancakeBlock;
import net.mehvahdjukaar.moonlight.api.item.additional_placements.AdditionalItemPlacement;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class FSPancakeItem extends RecordItem
{
    public FSPancakeItem(int i, SoundEvent soundEvent, Properties properties, int seconds)
    {
        super(i, soundEvent, properties, seconds);
    }

    /**
     * Empty override to prevent any hovertext from being appended, since this is still just a pancake, not a regular music disc
     */
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced)
    {
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
    {
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
    }
}
