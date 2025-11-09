package net.jotred.firmasupps.common.blocks;

import net.jotred.firmasupps.common.blockentities.FSBlockEntities;
import net.jotred.firmasupps.common.blockentities.FSSackBlockEntity;
import net.mehvahdjukaar.moonlight.api.entity.ImprovedFallingBlockEntity;
import net.mehvahdjukaar.supplementaries.common.block.blocks.SackBlock;
import net.mehvahdjukaar.supplementaries.common.block.tiles.SackBlockTile;
import net.mehvahdjukaar.supplementaries.reg.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import net.dries007.tfc.common.blocks.EntityBlockExtension;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.IForgeBlockExtension;
import net.dries007.tfc.common.blocks.TooltipBlock;
import net.dries007.tfc.common.component.TFCComponents;
import net.dries007.tfc.common.component.item.ItemListComponent;
import net.dries007.tfc.common.component.size.IItemSize;
import net.dries007.tfc.common.component.size.Size;
import net.dries007.tfc.common.component.size.Weight;
import net.dries007.tfc.util.Helpers;

/**
 * Adaptation of {@link SackBlock} for TFC, to allow item size restrictions
 * <p>
 * Due to not using a {@link SackBlockTile} as the block entity, most of the original functions have to be overridden to account for {@link FSSackBlockEntity} instead
 */
public class FSSackBlock extends SackBlock implements IItemSize, TooltipBlock, IForgeBlockExtension, EntityBlockExtension
{
    private final ExtendedProperties properties;

    public FSSackBlock(ExtendedProperties properties)
    {
        super(null, properties.properties());
        this.properties = properties;
    }

    @Override
    public ExtendedProperties getExtendedProperties()
    {
        return this.properties;
    }

    @Override
    public Size getSize(ItemStack stack)
    {
        return Size.HUGE;
    }

    @Override
    public Weight getWeight(ItemStack stack)
    {
        return Helpers.isEmpty(stack.getOrDefault(TFCComponents.CONTENTS, ItemListComponent.EMPTY).contents()) ? Weight.HEAVY : Weight.VERY_HEAVY;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState)
    {
        return new FSSackBlockEntity(pPos, pState);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand)
    {
        if (level.getBlockEntity(pos) instanceof FSSackBlockEntity sack)
        {
            sack.recheckOpen();

            if (canFall(pos, level))
            {
                ImprovedFallingBlockEntity entity = ImprovedFallingBlockEntity.fall(ModEntities.FALLING_SACK.get(), level, pos, state, true);
                entity.blockData = sack.saveWithFullMetadata(level.registryAccess());

                float power = this.getAnalogOutputSignal(state, level, pos) / 15.0F;
                entity.setHurtsEntities(1.0F + power * 5.0F, 40);
            }
        }
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        if (player instanceof ServerPlayer serverPlayer)
        {
            level.getBlockEntity(pos, FSBlockEntities.SACK.get()).ifPresent(sack ->
            {
                serverPlayer.openMenu(sack, sack.getBlockPos());
                PiglinAi.angerNearbyPiglins(player, true);
                sack.recheckOpen();
            });
        }

        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    }
}
