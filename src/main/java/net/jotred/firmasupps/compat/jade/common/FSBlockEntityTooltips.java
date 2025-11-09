package net.jotred.firmasupps.compat.jade.common;

import net.jotred.firmasupps.common.blocks.FSCandleHolderBlock;
import net.jotred.firmasupps.common.blocks.FSFirePitBlock;
import net.jotred.firmasupps.common.blocks.FSSconceBlock;
import net.jotred.firmasupps.common.blocks.FSSconceLeverBlock;
import net.jotred.firmasupps.common.blocks.FSSconceWallBlock;
import net.jotred.firmasupps.config.FSServerConfig;
import net.minecraft.world.level.block.Block;

import net.dries007.tfc.util.tooltip.BlockEntityTooltip;

import static net.dries007.tfc.util.tooltip.BlockEntityTooltips.*;

public class FSBlockEntityTooltips
{
    public static void register(FSRegisterCallback<BlockEntityTooltip, Block> callback)
    {
        callback.register("candle_holder", CANDLE_HOLDER, FSCandleHolderBlock.class);
        callback.register("sconce", SCONCE, FSSconceBlock.class);
        callback.register("sconce_wall", SCONCE, FSSconceWallBlock.class);
        callback.register("sconce_lever", SCONCE, FSSconceLeverBlock.class);
        callback.register("fire_pit", FIRE_PIT, FSFirePitBlock.class);
    }

    public static final BlockEntityTooltip CANDLE_HOLDER = tickCounter(FSServerConfig.candleHolderTicks);
    public static final BlockEntityTooltip SCONCE = tickCounter(FSServerConfig.sconceTicks);
    public static final BlockEntityTooltip FIRE_PIT = tickCounter(FSServerConfig.firePitTicks);
}
