package net.jotred.firmasupps.common.blockentities;

import net.jotred.firmasupps.common.blocks.FSSackBlock;
import net.jotred.firmasupps.common.container.FSSackContainer;
import net.mehvahdjukaar.supplementaries.reg.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.dries007.tfc.common.blockentities.InventoryBlockEntity;
import net.dries007.tfc.common.capabilities.InventoryItemHandler;
import net.dries007.tfc.common.capabilities.size.ItemSizeManager;
import net.dries007.tfc.common.capabilities.size.Size;

import static net.jotred.firmasupps.FirmaSupplementaries.*;


public class FSSackBlockEntity extends InventoryBlockEntity<FSSackBlockEntity.SackInventory> implements Nameable
{
    public static final int SLOTS = 9;
    public static final Component NAME = Component.translatable("block_entity." + MOD_ID + ".sack");

    private final ContainerOpenersCounter openersCounter = new ContainerCounter();

    public FSSackBlockEntity(BlockPos pos, BlockState state)
    {
        this(FSBlockEntities.SACK.get(), pos, state);
    }

    public FSSackBlockEntity(BlockEntityType<? extends FSSackBlockEntity> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state, SackInventory::new, NAME);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int windowID, Inventory inv, Player player)
    {
        return FSSackContainer.create(this, inv, windowID);
    }

    @Override
    public Component getName()
    {
        return NAME;
    }

    @Override
    public Component getDisplayName()
    {
        return super.getDisplayName();
    }

    @Override
    public Component getCustomName()
    {
        return this.customName;
    }

    /**
     * Internal InventoryItemHandler subclass, used for handling item sizes
     */
    public static class SackInventory extends InventoryItemHandler implements INBTSerializable<CompoundTag>
    {
        private final FSSackBlockEntity sack;

        public SackInventory(InventoryBlockEntity<?> entity)
        {
            super(entity, SLOTS);
            sack = (FSSackBlockEntity) entity;
        }

        @NotNull
        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
        {
            return super.insertItem(slot, stack, simulate);
        }

        @NotNull
        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate)
        {
            return super.extractItem(slot, amount, simulate);
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack)
        {
            return ItemSizeManager.get(stack).getSize(stack).isEqualOrSmallerThan(Size.LARGE) && super.isItemValid(slot, stack);
        }
    }

    public void playOpenSound()
    {
        double d0 = this.worldPosition.getX() + 0.5;
        double d1 = this.worldPosition.getY() + 1.0;
        double d2 = this.worldPosition.getZ() + 0.5;
        this.level.playSound(null, d0, d1, d2, ModSounds.SACK_OPEN.get(), SoundSource.BLOCKS, 1.0F, this.level.random.nextFloat() * 0.1F + 0.95F);
    }

    public void playCloseSound()
    {
        double d0 = this.worldPosition.getX() + 0.5;
        double d1 = this.worldPosition.getY() + 1.0;
        double d2 = this.worldPosition.getZ() + 0.5;
        this.level.playSound(null, d0, d1, d2, ModSounds.SACK_OPEN.get(), SoundSource.BLOCKS, 1.0F, this.level.random.nextFloat() * 0.1F + 0.8F);
    }

    public void updateBlockState(BlockState state, boolean open)
    {
        this.level.setBlock(this.getBlockPos(), state.setValue(FSSackBlock.OPEN, open), 3);
    }

    public void recheckOpen()
    {
        if (!this.remove)
        {
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }

    }

    /**
     * Internal ContainerOpenersCounter subclass, used for inferring whether the sack is open or not
     * <p>
     * This can't just be done by setting the blockstate value upon using as multiple players might be accessing the same sack at the same time,
     * so this class allows counting the players that have opened this sack at the current moment
     */
    private class ContainerCounter extends ContainerOpenersCounter
    {
        private ContainerCounter()
        {
        }

        protected void onOpen(Level level, BlockPos pos, BlockState state)
        {
            FSSackBlockEntity.this.playOpenSound();
            FSSackBlockEntity.this.updateBlockState(state, true);
        }

        protected void onClose(Level level, BlockPos pos, BlockState state)
        {
            FSSackBlockEntity.this.playCloseSound();
            FSSackBlockEntity.this.updateBlockState(state, false);
        }

        protected void openerCountChanged(Level level, BlockPos pos, BlockState state, int i, int i1)
        {
        }

        protected boolean isOwnContainer(Player player)
        {
            if (player.containerMenu instanceof FSSackContainer sackMenu)
            {
                return sackMenu.getBlockEntity() == FSSackBlockEntity.this;
            }
            else
            {
                return false;
            }
        }
    }
}
