package net.jotred.firmasupps.common.blocks;

import java.util.List;
import java.util.function.Consumer;
import net.jotred.firmasupps.common.blockentities.FSBlockEntities;
import net.mehvahdjukaar.supplementaries.common.block.blocks.PlanterBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import net.dries007.tfc.common.blockentities.TFCBlockEntities;
import net.dries007.tfc.common.blocks.EntityBlockExtension;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.IForgeBlockExtension;
import net.dries007.tfc.common.blocks.crop.CropHelpers;
import net.dries007.tfc.common.blocks.soil.HoeOverlayBlock;

public class FSPlanterBlock extends PlanterBlock implements HoeOverlayBlock, IForgeBlockExtension, EntityBlockExtension
{
    private final ExtendedProperties properties;

    public FSPlanterBlock(ExtendedProperties properties)
    {
        super(properties.properties());
        this.properties = properties;
    }

    @Override
    public ExtendedProperties getExtendedProperties()
    {
        return properties;
    }

    @Override
    public void addHoeOverlayInfo(Level level, BlockPos blockPos, BlockState blockState, Consumer<Component> consumer, boolean isDebug)
    {
        level.getBlockEntity(blockPos, FSBlockEntities.PLANTER.get()).ifPresent(farmland -> farmland.addHoeOverlayInfo(level, blockPos, consumer, true, true));
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        return CropHelpers.useFertilizer(level, player, hand, pos) ? ItemInteractionResult.SUCCESS : super.useItemOn(stack, state, level, pos, player, hand, hit);
    }
}
