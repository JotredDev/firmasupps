package net.jotred.firmasupps.common.blocks;

import java.util.function.ToIntFunction;
import net.jotred.firmasupps.common.blockentities.FSTickCounterBlockEntity;
import net.jotred.firmasupps.config.FSServerConfig;
import net.mehvahdjukaar.moonlight.api.block.ILightable;
import net.mehvahdjukaar.supplementaries.common.block.blocks.FirePitBlock;
import net.mehvahdjukaar.supplementaries.common.block.blocks.LightUpWaterBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import org.jetbrains.annotations.Nullable;

import net.dries007.tfc.common.blockentities.TFCBlockEntities;
import net.dries007.tfc.common.blocks.EntityBlockExtension;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.IForgeBlockExtension;

public class FSFirePitBlock extends FirePitBlock implements IForgeBlockExtension, EntityBlockExtension
{
    private final ExtendedProperties properties;
    public static final ToIntFunction<BlockState> LIGHTING_SCALE = (state) -> state.getValue(LIT) ? 15 : 0;

    public <T extends ParticleType<?>> FSFirePitBlock(float fireDamage, ExtendedProperties properties)
    {
        super(1, properties.properties());
        this.properties = properties;
    }

    public static void onRandomTick(BlockState state, ServerLevel level, BlockPos pos)
    {
        if (level.getBlockEntity(pos) instanceof FSTickCounterBlockEntity sconce)
        {
            final int sconceTicks = FSServerConfig.firePitTicks.get();
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
        FSTickCounterBlockEntity.reset(level, pos);
        super.setPlacedBy(level, pos, state, placer, stack);
    }

    @Override
    public PathType getBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob entity)
    {
        return super.getBlockPathType(state, level, pos, entity);
    }

    @Override
    public @Nullable PathType getAdjacentBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob entity, PathType originalType)
    {
        return super.getAdjacentBlockPathType(state, level, pos, entity, originalType);
    }

    /**
     * The default interaction from using a flint and steel is set by {@link LightUpWaterBlock#tryLightUp}
     * Since this doesn't reset the {@link FSTickCounterBlockEntity}, we have to override it
     */
    @Override
    public boolean tryLightUp(@Nullable Entity player, BlockState state, BlockPos pos, LevelAccessor level, ILightable.FireSoundType fireSourceType)
    {
        FSTickCounterBlockEntity.reset((Level) level, pos);
        return super.tryLightUp(player, state, pos, level, fireSourceType);
    }
}
