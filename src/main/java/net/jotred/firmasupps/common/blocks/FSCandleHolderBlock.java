package net.jotred.firmasupps.common.blocks;

import java.util.function.ToIntFunction;
import net.jotred.firmasupps.common.blockentities.FSTickCounterBlockEntity;
import net.jotred.firmasupps.config.FSServerConfig;
import net.mehvahdjukaar.moonlight.api.block.ILightable;
import net.mehvahdjukaar.supplementaries.common.block.blocks.CandleHolderBlock;
import net.mehvahdjukaar.supplementaries.common.block.blocks.LightUpWaterBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import net.dries007.tfc.common.blocks.EntityBlockExtension;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.IForgeBlockExtension;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Helpers;

public class FSCandleHolderBlock extends CandleHolderBlock implements IForgeBlockExtension, EntityBlockExtension
{
    private final ExtendedProperties properties;
    public static final ToIntFunction<BlockState> LIGHTING_SCALE = (state) -> state.getValue(LIT) ? 7 + 2 * state.getValue(CANDLES) : 0;

    public FSCandleHolderBlock(DyeColor color, ExtendedProperties properties)
    {
        super(color, properties.properties(), CandleHolderBlock::getDefaultParticleOffsets);
        this.properties = properties;

    }

    public static void onRandomTick(BlockState state, ServerLevel level, BlockPos pos)
    {
        if (level.getBlockEntity(pos) instanceof FSTickCounterBlockEntity candleHolder)
        {
            final int candleHolderTicks = FSServerConfig.candleHolderTicks.get();
            if (candleHolder.getTicksSinceUpdate() > candleHolderTicks && candleHolderTicks > 0)
            {
                level.setBlockAndUpdate(pos, state.setValue(LIT, false));
            }
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult)
    {
        // Preventing candle holders from immediately extinguishing if lit with a firestarter
        if (Helpers.isItem(player.getMainHandItem(), TFCItems.FIRESTARTER.get()))
        {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand)
    {
        onRandomTick(state, level, pos);
    }

    @Override
    public ExtendedProperties getExtendedProperties()
    {
        return this.properties;
    }

    /**
     * The default interaction from using a flint and steel is set by {@link LightUpWaterBlock#tryLightUp}
     * Since this doesn't reset the {@link FSTickCounterBlockEntity}, we have to override it
     */
    @Override
    public boolean tryLightUp(@Nullable Entity player, BlockState state, BlockPos pos, LevelAccessor world, ILightable.FireSoundType fireSourceType)
    {
        FSTickCounterBlockEntity.reset((Level) world, pos);
        return super.tryLightUp(player, state, pos, world, fireSourceType);
    }
}
