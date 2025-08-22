package net.jotred.firmasupps.common.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import net.dries007.tfc.util.events.StartFireEvent;

public class FSSconceItem extends StandingAndWallBlockItem
{
    public FSSconceItem(Block floorBlock, Block wallBlock, Properties properties)
    {
        super(floorBlock, wallBlock, properties, Direction.DOWN);
    }

    @Override
    public InteractionResult useOn(UseOnContext context)
    {
        final Level level = context.getLevel();
        final BlockPos pos = context.getClickedPos();
        if (StartFireEvent.startFire(level, pos, level.getBlockState(pos), context.getClickedFace(), context.getPlayer(), context.getItemInHand(), StartFireEvent.FireStrength.WEAK))
        {
            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }
}
