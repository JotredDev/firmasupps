package net.jotred.firmasupps.common.blocks;

import java.util.function.Supplier;
import net.mehvahdjukaar.supplementaries.common.block.blocks.LightUpWaterBlock;
import net.mehvahdjukaar.supplementaries.common.block.blocks.SconceWallBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import net.dries007.tfc.common.blockentities.TickCounterBlockEntity;
import net.dries007.tfc.common.blocks.EntityBlockExtension;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.IForgeBlockExtension;

public class FSSconceWallBlock extends SconceWallBlock implements IForgeBlockExtension, EntityBlockExtension
{
    private final ExtendedProperties properties;

    public <T extends ParticleType<?>> FSSconceWallBlock(Supplier<T> particleData, ExtendedProperties properties)
    {
        super(properties.properties(), particleData);
        this.properties = properties;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand)
    {
        FSSconceBlock.onRandomTick(state, level, pos);
    }

    @Override
    public ExtendedProperties getExtendedProperties()
    {
        return properties;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack)
    {
        FSBlocks.SCONCE.get().setPlacedBy(level, pos, state, placer, stack);
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
}
