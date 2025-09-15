package net.jotred.firmasupps.common.blocks;

import java.util.function.Supplier;
import net.jotred.firmasupps.config.FSConfig;
import net.mehvahdjukaar.supplementaries.common.block.blocks.LightUpWaterBlock;
import net.mehvahdjukaar.supplementaries.common.block.blocks.SconceLeverBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import net.dries007.tfc.common.blockentities.TFCBlockEntities;
import net.dries007.tfc.common.blockentities.TickCounterBlockEntity;
import net.dries007.tfc.common.blocks.EntityBlockExtension;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.IForgeBlockExtension;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Helpers;

public class FSSconceLeverBlock extends SconceLeverBlock implements IForgeBlockExtension, EntityBlockExtension
{
    private final ExtendedProperties properties;
    public static final BooleanProperty GLUED = BooleanProperty.create("glued");

    public FSSconceLeverBlock(Supplier<SimpleParticleType> particleData, ExtendedProperties properties)
    {
        super(properties.properties(), particleData);
        this.registerDefaultState(this.stateDefinition.any()
            .setValue(GLUED, false)
            .setValue(POWERED, false)
            .setValue(FACING, Direction.NORTH)
            .setValue(WATERLOGGED, false)
            .setValue(LIT, true));
        this.properties = properties;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(GLUED);
    }

    @Override
    public int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return blockState.getValue(POWERED) ^ !(blockState.getValue(GLUED) || blockState.getValue(LIT)) ? 15 : 0;
    }

    @Override
    public int getDirectSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return blockState.getValue(POWERED) ^ !(blockState.getValue(GLUED) || blockState.getValue(LIT)) && getFacing(blockState) == side ? 15 : 0;
    }

    public static void onRandomTick(BlockState state, ServerLevel level, BlockPos pos)
    {
        if (level.getBlockEntity(pos) instanceof TickCounterBlockEntity sconce)
        {
            final int sconceTicks = FSConfig.SERVER.sconceTicks.get();
            if (sconce.getTicksSinceUpdate() > sconceTicks && sconceTicks > 0)
            {
                level.setBlockAndUpdate(pos, state.setValue(LIT, false));
            }
        }
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
        return properties;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack)
    {
        level.getBlockEntity(pos, TFCBlockEntities.TICK_COUNTER.get()).ifPresent(TickCounterBlockEntity::resetCounter);
        super.setPlacedBy(level, pos, state, placer, stack);
    }

    /**
     * The default interaction from using a flint and steel is set by {@link LightUpWaterBlock#lightUp}
     * Since this doesn't reset the {@link TickCounterBlockEntity}, we have to override it
     */
    @Override
    public boolean lightUp(@Nullable Entity player, BlockState state, BlockPos pos, LevelAccessor world, FireSourceType fireSourceType)
    {
        TickCounterBlockEntity.reset((Level) world, pos);
        return super.lightUp(player, state, pos, world, fireSourceType);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        if (!state.getValue(GLUED) && Helpers.isItem(player.getMainHandItem(), TFCItems.GLUE.get()))
        {
            if (level.isClientSide)
            {
                return InteractionResult.SUCCESS;
            }
            else
            {
                if (!player.isCreative())
                {
                    player.getMainHandItem().shrink(1);
                }
                level.setBlockAndUpdate(pos, state.setValue(GLUED, true));
                return InteractionResult.CONSUME;
            }
        }
        return super.use(state, level, pos, player, hand, hit);
    }
}
