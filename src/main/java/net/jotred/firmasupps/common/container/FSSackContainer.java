package net.jotred.firmasupps.common.container;

import net.jotred.firmasupps.common.blockentities.FSSackBlockEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import net.dries007.tfc.common.container.BlockEntityContainer;
import net.dries007.tfc.common.container.slot.CallbackSlot;

public class FSSackContainer extends BlockEntityContainer<FSSackBlockEntity>
{
    public static FSSackContainer create(FSSackBlockEntity sack, Inventory playerInventory, int windowId)
    {
        return new FSSackContainer(sack, windowId).init(playerInventory);
    }

    public FSSackContainer(FSSackBlockEntity sack, int windowId)
    {
        super(FSContainerTypes.SACK.get(), windowId, sack);
    }

    @Override
    protected boolean moveStack(ItemStack stack, int slotIndex)
    {
        return switch (typeOf(slotIndex))
        {
            case MAIN_INVENTORY, HOTBAR -> !moveItemStackTo(stack, 0, FSSackBlockEntity.SLOTS, false);
            case CONTAINER -> !moveItemStackTo(stack, containerSlots, slots.size(), false);
        };
    }

    @Override
    protected void addContainerSlots()
    {
        addSlot(new CallbackSlot(blockEntity, 0, 62, 19));
        addSlot(new CallbackSlot(blockEntity, 1, 80, 19));
        addSlot(new CallbackSlot(blockEntity, 2, 98, 19));
        addSlot(new CallbackSlot(blockEntity, 3, 62, 37));
        addSlot(new CallbackSlot(blockEntity, 4, 80, 37));
        addSlot(new CallbackSlot(blockEntity, 5, 98, 37));
        addSlot(new CallbackSlot(blockEntity, 6, 62, 55));
        addSlot(new CallbackSlot(blockEntity, 7, 80, 55));
        addSlot(new CallbackSlot(blockEntity, 8, 98, 55));
    }
}
