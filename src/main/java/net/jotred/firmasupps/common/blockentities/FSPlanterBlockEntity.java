package net.jotred.firmasupps.common.blockentities;

import net.jotred.firmasupps.config.FSConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import net.dries007.tfc.common.blockentities.FarmlandBlockEntity;
import net.dries007.tfc.common.blocks.soil.FarmlandBlock;
import net.dries007.tfc.util.Fertilizer;

/**
 * Custom farmland type for the Supplementaries planter
 * <p>
 * Since hydration is defined by {@link FarmlandBlock} it can't be adjusted to fit the original Supplementaries property of the planter (always being hydrated),
 * so instead we change the permahydration into a fertilizer efficiency bonus as a compromise
 */
public class FSPlanterBlockEntity extends FarmlandBlockEntity
{
    public static final double NUTRIENT_MULTIPLIER = FSConfig.SERVER.planterNutrientMultiplier.get();

    public FSPlanterBlockEntity(BlockPos pos, BlockState state)
    {
        this(FSBlockEntities.PLANTER.get(), pos, state);
    }

    public FSPlanterBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
    }

    @Override
    public void addNutrients(Fertilizer fertilizer, float multiplier)
    {
        super.addNutrients(fertilizer, multiplier * (float) NUTRIENT_MULTIPLIER);
    }
}
