package net.jotred.firmasupps;

import net.jotred.firmasupps.common.blocks.FSCandleHolderBlock;
import net.jotred.firmasupps.common.blocks.FSFirePitBlock;
import net.jotred.firmasupps.common.blocks.FSGobletBlock;
import net.jotred.firmasupps.common.blocks.FSSconceBlock;
import net.jotred.firmasupps.common.blocks.FSSconceLeverBlock;
import net.jotred.firmasupps.common.blocks.FSSconceWallBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;

import net.dries007.tfc.common.blockentities.TickCounterBlockEntity;
import net.dries007.tfc.util.events.StartFireEvent;

import static net.mehvahdjukaar.supplementaries.common.block.blocks.LightUpWaterBlock.*;

public class ForgeEventHandler
{
    public static void init()
    {
        final IEventBus bus = MinecraftForge.EVENT_BUS;

        bus.addListener(ForgeEventHandler::onFireStart);
        bus.addListener(EventPriority.LOWEST, true, ForgeEventHandler::onPlayerRightClickBlockLowestPriority);
    }

    public static void onFireStart(StartFireEvent event)
    {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = event.getState();
        Block block = state.getBlock();

        if (block instanceof FSCandleHolderBlock && !state.getValue(WATERLOGGED))
        {
            level.setBlock(pos, state.setValue(FSCandleHolderBlock.LIT, true), Block.UPDATE_ALL_IMMEDIATE);
            TickCounterBlockEntity.reset(level, pos);
            event.setCanceled(true);
        }
        else if (block instanceof FSSconceBlock && !state.getValue(WATERLOGGED))
        {
            level.setBlock(pos, state.setValue(FSSconceBlock.LIT, true), Block.UPDATE_ALL_IMMEDIATE);
            TickCounterBlockEntity.reset(level, pos);
            event.setCanceled(true);
        }
        else if (block instanceof FSSconceWallBlock && !state.getValue(WATERLOGGED))
        {
            level.setBlock(pos, state.setValue(FSSconceWallBlock.LIT, true), Block.UPDATE_ALL_IMMEDIATE);
            TickCounterBlockEntity.reset(level, pos);
            event.setCanceled(true);
        }
        else if (block instanceof FSSconceLeverBlock && !state.getValue(WATERLOGGED))
        {
            level.setBlock(pos, state.setValue(FSSconceWallBlock.LIT, true), Block.UPDATE_ALL_IMMEDIATE);
            TickCounterBlockEntity.reset(level, pos);
            event.setCanceled(true);
        }
        else if (block instanceof FSFirePitBlock && !state.getValue(WATERLOGGED))
        {
            level.setBlock(pos, state.setValue(FSFirePitBlock.LIT, true), Block.UPDATE_ALL_IMMEDIATE);
            TickCounterBlockEntity.reset(level, pos);
            event.setCanceled(true);
        }
    }

    public static void onPlayerRightClickBlockLowestPriority(PlayerInteractEvent.RightClickBlock event)
    {
        final Level level = event.getLevel();
        final BlockState state = level.getBlockState(event.getPos());

        if (state.getBlock() instanceof FSGobletBlock)
        {
            event.setUseBlock(Event.Result.ALLOW);
        }
    }
}
