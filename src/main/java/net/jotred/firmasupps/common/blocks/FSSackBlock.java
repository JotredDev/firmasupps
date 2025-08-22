package net.jotred.firmasupps.common.blocks;

import java.util.List;
import java.util.Optional;
import net.jotred.firmasupps.common.blockentities.FSSackBlockEntity;
import net.mehvahdjukaar.moonlight.api.entity.ImprovedFallingBlockEntity;
import net.mehvahdjukaar.supplementaries.common.block.blocks.SackBlock;
import net.mehvahdjukaar.supplementaries.common.block.tiles.SackBlockTile;
import net.mehvahdjukaar.supplementaries.reg.ModEntities;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import net.dries007.tfc.common.blocks.EntityBlockExtension;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.IForgeBlockExtension;
import net.dries007.tfc.common.blocks.TooltipBlock;
import net.dries007.tfc.common.capabilities.size.IItemSize;
import net.dries007.tfc.common.capabilities.size.Size;
import net.dries007.tfc.common.capabilities.size.Weight;
import net.dries007.tfc.config.TFCConfig;
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
        super(properties.properties());
        this.properties = properties;
    }

    @Override
    public ExtendedProperties getExtendedProperties()
    {
        return this.properties;
    }

    @Override
    public Size getSize(ItemStack itemStack)
    {
        return Size.HUGE;
    }

    @Override
    public Weight getWeight(ItemStack itemStack)
    {
        CompoundTag tag = itemStack.getTag();
        return tag == null || tag.getCompound(Helpers.BLOCK_ENTITY_TAG).getCompound("inventory").getList("Items", 10).isEmpty() ? Weight.HEAVY : Weight.VERY_HEAVY;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState)
    {
        return new FSSackBlockEntity(pPos, pState);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> tooltip, TooltipFlag flag)
    {
        final CompoundTag tag = stack.getTagElement(Helpers.BLOCK_ENTITY_TAG);
        if (tag != null)
        {
            final CompoundTag inventoryTag = tag.getCompound("inventory");
            final ItemStackHandler inventory = new ItemStackHandler();

            inventory.deserializeNBT(inventoryTag);

            if (!Helpers.isEmpty(inventory) && !TFCConfig.CLIENT.displayItemContentsAsImages.get())
            {
                tooltip.add(Component.translatable("firmasupps.tooltip.contents").withStyle(ChatFormatting.DARK_GREEN));
                Helpers.addInventoryTooltipInfo(inventory, tooltip);
            }
        }
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack)
    {
        if (TFCConfig.CLIENT.displayItemContentsAsImages.get())
        {
            final CompoundTag tag = stack.getTagElement(Helpers.BLOCK_ENTITY_TAG);
            if (tag != null)
            {
                final CompoundTag inventoryTag = tag.getCompound("inventory");
                final ItemStackHandler inventory = new ItemStackHandler();

                inventory.deserializeNBT(inventoryTag);

                if (!Helpers.isEmpty(inventory))
                {
                    return Helpers.getTooltipImage(inventory, 3, 3, 0, FSSackBlockEntity.SLOTS - 1);
                }
            }
        }
        return Optional.empty();
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
                entity.blockData = sack.saveWithoutMetadata();

                float power = this.getAnalogOutputSignal(state, level, pos) / 15.0F;
                entity.setHurtsEntities(1.0F + power * 5.0F, 40);
            }
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        if (level.isClientSide)
        {
            return InteractionResult.SUCCESS;
        }
        else if (player.isSpectator())
        {
            return InteractionResult.CONSUME;
        }
        else
        {
            if (level.getBlockEntity(pos) instanceof FSSackBlockEntity sack && player instanceof ServerPlayer serverPlayer)
            {
                Helpers.openScreen(serverPlayer, sack, pos);
                PiglinAi.angerNearbyPiglins(player, true);
                sack.recheckOpen();

                return InteractionResult.CONSUME;
            }
            else
            {
                return InteractionResult.PASS;
            }
        }
    }
}
