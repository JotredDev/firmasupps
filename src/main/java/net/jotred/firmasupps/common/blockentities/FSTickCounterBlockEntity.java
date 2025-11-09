package net.jotred.firmasupps.common.blockentities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import net.dries007.tfc.common.blockentities.TickCounterBlockEntity;

/**
 * Dummy class extending {@link TickCounterBlockEntity}, to ensure the correct blocks are seen as "valid" for the block entity type.
 * <p>
 * Just using the TFC class directly instead would lead to a crash, since it only allows the TFC-defined tick counters to use it
 */
public class FSTickCounterBlockEntity extends TickCounterBlockEntity
{
    public FSTickCounterBlockEntity(BlockPos pos, BlockState state)
    {
        super(FSBlockEntities.TICK_COUNTER.get(), pos, state);
    }

    protected FSTickCounterBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
    }

    public static void reset(Level level, BlockPos pos)
    {
        level.getBlockEntity(pos, FSBlockEntities.TICK_COUNTER.get()).ifPresent(TickCounterBlockEntity::resetCounter);
    }
}
