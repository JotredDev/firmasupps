package net.jotred.firmasupps.compat.jade.common;

import java.util.function.Consumer;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.jotred.firmasupps.common.blocks.FSCandleHolderBlock;
import net.jotred.firmasupps.common.blocks.FSFirePitBlock;
import net.jotred.firmasupps.common.blocks.FSSconceBlock;
import net.jotred.firmasupps.common.blocks.FSSconceLeverBlock;
import net.jotred.firmasupps.common.blocks.FSSconceWallBlock;
import net.jotred.firmasupps.config.FSConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.dries007.tfc.common.blockentities.TickCounterBlockEntity;
import net.dries007.tfc.util.calendar.Calendars;

public class BlockEntityTooltips
{
    public static void register(RegisterCallback<BlockEntityTooltip, Block> callback)
    {
        callback.register("candle_holder", CANDLE_HOLDER, FSCandleHolderBlock.class);
        callback.register("sconce", SCONCE, FSSconceBlock.class);
        callback.register("sconce_wall", SCONCE, FSSconceWallBlock.class);
        callback.register("sconce_lever", SCONCE, FSSconceLeverBlock.class);
        callback.register("fire_pit", FIRE_PIT, FSFirePitBlock.class);
    }

    public static final BlockEntityTooltip CANDLE_HOLDER = tickCounter(FSConfig.SERVER.candleHolderTicks);
    public static final BlockEntityTooltip SCONCE = tickCounter(FSConfig.SERVER.sconceTicks);
    public static final BlockEntityTooltip FIRE_PIT = tickCounter(FSConfig.SERVER.firePitTicks);


    public static void timeLeft(Level level, Consumer<Component> tooltip, long ticks)
    {
        timeLeft(level, tooltip, ticks, null);
    }

    public static void timeLeft(Level level, Consumer<Component> tooltip, long ticks, @Nullable Component ifNegative)
    {
        if (ticks > 0)
        {
            tooltip.accept(Component.translatable("tfc.jade.time_left", Calendars.get(level).getTimeDelta(ticks)));
        }
        else if (ifNegative != null)
        {
            tooltip.accept(ifNegative);
        }
    }

    public static BlockEntityTooltip tickCounter(Supplier<Integer> totalTicks)
    {
        return (level, state, pos, entity, tooltip) -> {
            if (entity instanceof TickCounterBlockEntity counter)
            {
                timeLeft(level, tooltip, totalTicks.get() - counter.getTicksSinceUpdate());
            }
        };
    }

}
