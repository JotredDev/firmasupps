package net.jotred.firmasupps.common.blocks;


import net.jotred.firmasupps.common.blockentities.FSBlockEntities;
import net.jotred.firmasupps.common.blockentities.FSGobletBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import net.dries007.tfc.common.blocks.EntityBlockExtension;
import net.dries007.tfc.common.blocks.ExtendedBlock;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.capabilities.Capabilities;
import net.dries007.tfc.common.fluids.FluidHelpers;
import net.dries007.tfc.util.Drinkable;
import net.dries007.tfc.util.loot.CopyFluidFunction;

public class FSGobletBlock extends ExtendedBlock implements EntityBlockExtension
{
    protected static final VoxelShape GOBLET_SHAPE = Block.box(5, 0, 5, 11, 9, 11);

    public FSGobletBlock(ExtendedProperties properties)
    {
        super(properties);
    }

    @Override
    @SuppressWarnings("deprecation")
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result)
    {
        final @Nullable FSGobletBlockEntity goblet = level.getBlockEntity(pos, FSBlockEntities.GOBLET.get()).orElse(null);

        if (goblet != null)
        {
            final ItemStack item = player.getItemInHand(hand);

            if (hand.equals(InteractionHand.MAIN_HAND) && item.isEmpty())
            {
                final IFluidHandler handler = goblet.getCapability(Capabilities.FLUID).resolve().orElse(null);

                if (handler != null)
                {
                    final FluidStack drained = handler.drain(FSGobletBlockEntity.GOBLET_CAPACITY, IFluidHandler.FluidAction.EXECUTE);
                    final Drinkable drink = Drinkable.get(drained.getFluid());
                    goblet.markForSync();

                    if (drink != null && !level.isClientSide)
                    {
                        drink.onDrink(player, drained.getAmount());
                        return InteractionResult.SUCCESS;
                    }
                }
            }
            else if (FluidHelpers.transferBetweenBlockEntityAndItem(item, goblet, player, hand))
            {
                goblet.markForSync();
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player)
    {
        ItemStack stack = super.getCloneItemStack(state, target, level, pos, player);
        CopyFluidFunction.copyToItem(stack, level.getBlockEntity(pos));
        return stack;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState)
    {
        return new FSGobletBlockEntity(pPos, pState);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
    {
        return GOBLET_SHAPE;
    }

    @Override
    @SuppressWarnings("deprecation")
    public RenderShape getRenderShape(BlockState state)
    {
        return RenderShape.MODEL;
    }

    @Override
    @SuppressWarnings("deprecation")
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos)
    {
        if (level.getBlockEntity(pos) instanceof FSGobletBlockEntity goblet)
        {
            return goblet.isEmpty() ? 0 : 15;
        }
        return 0;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
    {
        return Block.canSupportCenter(level, pos.relative(Direction.DOWN), Direction.UP);
    }
}
