package net.jotred.firmasupps.common.entities.compartment;

import com.alekiponi.alekiships.common.entity.vehiclehelper.CompartmentType;
import com.alekiponi.alekiships.common.entity.vehiclehelper.compartment.AbstractCompartmentEntity;
import com.alekiponi.alekiships.common.entity.vehiclehelper.compartment.BlockCompartment;
import com.alekiponi.alekiships.common.entity.vehiclehelper.compartment.CompartmentCloneable;
import com.alekiponi.alekiships.common.entity.vehiclehelper.compartment.SimpleBlockMenuCompartment;
import net.jotred.firmasupps.common.blockentities.FSSackBlockEntity;
import net.jotred.firmasupps.common.blocks.FSSackBlock;
import net.jotred.firmasupps.common.container.FSSackCompartmentContainer;
import net.mehvahdjukaar.supplementaries.reg.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.dries007.tfc.common.capabilities.size.ItemSizeManager;
import net.dries007.tfc.common.capabilities.size.Size;
import net.dries007.tfc.util.Helpers;


public class FSSackCompartmentEntity extends AbstractCompartmentEntity implements BlockCompartment, CompartmentCloneable, SimpleBlockMenuCompartment, MenuConstructor, Container
{
    public static final int SLOTS = FSSackBlockEntity.SLOTS;
    private static final EntityDataAccessor<BlockState> DATA_ID_DISPLAY_BLOCK = SynchedEntityData.defineId(FSSackCompartmentEntity.class, EntityDataSerializers.BLOCK_STATE);

    private final ContainerOpenersCounter openersCounter = new FSSackCompartmentEntity.ContainerCounter();
    private final SackCompartmentInventory inventory;
    private final NonNullList<ItemStack> itemStacks = NonNullList.withSize(SLOTS, ItemStack.EMPTY);
    private final LazyOptional<IItemHandler> inventoryHandler;

    public FSSackCompartmentEntity(CompartmentType<? extends FSSackCompartmentEntity> compartmentType, Level level)
    {
        super(compartmentType, level);
        inventory = new SackCompartmentInventory(this);
        inventoryHandler = LazyOptional.of(() -> inventory);
    }

    public FSSackCompartmentEntity(CompartmentType<? extends FSSackCompartmentEntity> compartmentType, Level level, ItemStack itemStack)
    {
        this(compartmentType, level);

        if (itemStack.getItem() instanceof BlockItem blockItem)
        {
            this.setDisplayBlockState(blockItem.getBlock().defaultBlockState());

            if (itemStack.hasCustomHoverName())
            {
                this.setCustomName(itemStack.getHoverName());
            }

            CompoundTag beTag = BlockItem.getBlockEntityData(itemStack);
            if (beTag != null)
            {
                this.readContents(beTag);
            }
        }
    }

    @Override
    protected void addAdditionalSaveData(final CompoundTag compoundTag)
    {
        super.addAdditionalSaveData(compoundTag);
        this.saveContents(compoundTag);
        BlockCompartment.saveBlockstate(this, compoundTag);
    }

    protected void saveContents(final CompoundTag compoundTag)
    {
        compoundTag.put("inventory", this.inventory.serializeNBT());
    }

    @Override
    protected void readAdditionalSaveData(final CompoundTag compoundTag)
    {
        super.readAdditionalSaveData(compoundTag);
        this.readContents(compoundTag);
        BlockCompartment.readBlockstate(this, compoundTag);
    }

    protected void readContents(CompoundTag tag)
    {
        this.inventory.deserializeNBT(tag.getCompound("inventory"));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_DISPLAY_BLOCK, Blocks.AIR.defaultBlockState());
    }

    @Override
    public InteractionResult interact(final Player player, final InteractionHand hand)
    {
        if (!this.level().isClientSide && player instanceof final ServerPlayer serverPlayer)
        {
            NetworkHooks.openScreen(serverPlayer, this.getMenuProvider(), friendlyByteBuf -> friendlyByteBuf.writeVarInt(this.getId()));
            this.recheckOpen();
            return InteractionResult.CONSUME;
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockState getDisplayBlockState()
    {
        return this.entityData.get(DATA_ID_DISPLAY_BLOCK);
    }

    @Override
    public void setDisplayBlockState(BlockState blockState)
    {
        this.entityData.set(DATA_ID_DISPLAY_BLOCK, blockState);
    }

    @Override
    public CompoundTag saveForItemStack()
    {
        CompoundTag tag = new CompoundTag();
        this.saveContents(tag);

        if (this.hasCustomName())
        {
            tag.putString("CustomName", Component.Serializer.toJson(this.getCustomName()));
        }

        return tag;
    }

    @Override
    public MenuProvider getMenuProvider() {
        return new SimpleMenuProvider(this, this.getDisplayName());
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int windowID, Inventory inv, Player player)
    {
        return FSSackCompartmentContainer.create(windowID, inv, this);
    }

    @Override
    public int getContainerSize()
    {
        return SLOTS;
    }

    @Override
    public boolean isEmpty()
    {
        return this.itemStacks.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getItem(int slot)
    {
        return this.itemStacks.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount)
    {
        return ContainerHelper.removeItem(this.itemStacks, slot, amount);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot)
    {
        return ContainerHelper.takeItem(this.itemStacks, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack)
    {
        this.itemStacks.set(slot, stack);
    }

    @Override
    public void setChanged()
    {

    }

    @Override
    public boolean stillValid(Player player)
    {
        return !this.isRemoved() && this.position().closerThan(player.position(), 8);
    }

    @Override
    public final void clearContent()
    {
        this.itemStacks.clear();
    }

    @Override
    protected ItemStack getDropStack()
    {
        ItemStack dropStack = new ItemStack(this.getDisplayBlockState().getBlock());
        CompoundTag compoundTag = new CompoundTag();

        this.saveContents(compoundTag);

        if (!compoundTag.isEmpty())
        {
            dropStack.addTagElement(Helpers.BLOCK_ENTITY_TAG, compoundTag);
        }

        return dropStack;
    }

    @Override
    public @Nullable ItemStack getPickResult()
    {
        return new ItemStack(this.getDisplayBlockState().getBlock());
    }

    @Override
    protected void onPlaced()
    {
        BlockCompartment.playPlaceSound(this);
    }

    @Override
    protected void onHurt(DamageSource damageSource)
    {
        BlockCompartment.playHitSound(this);
    }

    @Override
    protected void onBreak()
    {
        BlockCompartment.playBreakSound(this);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(final Capability<T> capability, final @Nullable Direction facing)
    {
        if (this.isAlive() && capability == ForgeCapabilities.ITEM_HANDLER)
        {
            return inventoryHandler.cast();
        }

        return super.getCapability(capability, facing);
    }


    private static class SackCompartmentInventory extends InvWrapper implements INBTSerializable<CompoundTag>
    {
        private final FSSackCompartmentEntity sackCompartment;

        public SackCompartmentInventory(FSSackCompartmentEntity sackCompartment)
        {
            super(sackCompartment);
            this.sackCompartment = sackCompartment;
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
        public boolean isItemValid(int slotIndex, ItemStack itemStack)
        {
            return ItemSizeManager.get(itemStack).getSize(itemStack).isEqualOrSmallerThan(Size.LARGE) && super.isItemValid(slotIndex, itemStack);
        }

        @Override
        public CompoundTag serializeNBT()
        {
            CompoundTag tag = new CompoundTag();
            ListTag inventoryListTag = new ListTag();

            for (int i = 0; i < SLOTS; i++)
            {
                ItemStack itemStack = sackCompartment.getItem(i);
                if (!itemStack.isEmpty())
                {
                    CompoundTag itemTag = new CompoundTag();
                    itemTag.putInt("Slot", i);
                    itemStack.save(itemTag);
                    inventoryListTag.add(itemTag);
                }
            }

            tag.put("Items", inventoryListTag);
            tag.putInt("Size", SLOTS);
            return tag;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt)
        {
            ListTag inventoryListTag = nbt.getList("Items", Tag.TAG_COMPOUND);
            for (int i = 0; i < inventoryListTag.size(); i++)
            {
                CompoundTag itemTags = inventoryListTag.getCompound(i);
                int slot = itemTags.getInt("Slot");

                if (slot >= 0 && slot < SLOTS)
                {
                    sackCompartment.setItem(slot, ItemStack.of(itemTags));
                }
            }
        }
    }


    public void playOpenSound()
    {
        double d0 = this.getX() + 0.5;
        double d1 = this.getY() + 1.0;
        double d2 = this.getZ() + 0.5;
        this.level().playSound(null, d0, d1, d2, ModSounds.SACK_OPEN.get(), SoundSource.BLOCKS, 1.0F, this.level().random.nextFloat() * 0.1F + 0.95F);
    }

    public void playCloseSound()
    {
        double d0 = this.getX() + 0.5;
        double d1 = this.getY() + 1.0;
        double d2 = this.getZ() + 0.5;
        this.level().playSound(null, d0, d1, d2, ModSounds.SACK_OPEN.get(), SoundSource.BLOCKS, 1.0F, this.level().random.nextFloat() * 0.1F + 0.8F);
    }

    public void updateBlockState(boolean open)
    {
        this.setDisplayBlockState(this.getDisplayBlockState().setValue(FSSackBlock.OPEN, open));
    }

    public void recheckOpen()
    {
        if (!this.isRemoved())
        {
            this.openersCounter.recheckOpeners(this.level(), BlockPos.containing(this.position()), this.getDisplayBlockState());
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
            FSSackCompartmentEntity.this.playOpenSound();
            FSSackCompartmentEntity.this.updateBlockState(true);
        }

        protected void onClose(Level level, BlockPos pos, BlockState state)
        {
            FSSackCompartmentEntity.this.playCloseSound();
            FSSackCompartmentEntity.this.updateBlockState(false);
        }

        protected void openerCountChanged(Level level, BlockPos pos, BlockState state, int i, int i1)
        {
        }

        protected boolean isOwnContainer(Player player)
        {
            if (player.containerMenu instanceof FSSackCompartmentContainer sackCompartmentMenu)
            {
                return sackCompartmentMenu.getSackCompartment() == FSSackCompartmentEntity.this;
            }
            else
            {
                return false;
            }
        }
    }

    @Override
    public void tick()
    {
        super.tick();
        this.recheckOpen();
    }
}
