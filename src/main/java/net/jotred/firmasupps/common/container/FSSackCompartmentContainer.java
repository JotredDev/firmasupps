package net.jotred.firmasupps.common.container;

import net.jotred.firmasupps.common.entities.compartment.FSSackCompartmentEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

import net.dries007.tfc.common.capabilities.Capabilities;
import net.dries007.tfc.common.container.Container;

import static net.jotred.firmasupps.common.entities.compartment.FSSackCompartmentEntity.*;

public class FSSackCompartmentContainer extends Container
{
    private final FSSackCompartmentEntity sackCompartment;

    public static FSSackCompartmentContainer create(int windowID, Inventory playerInventory, FSSackCompartmentEntity sackCompartment)
    {
        return new FSSackCompartmentContainer(sackCompartment, windowID).init(playerInventory);
    }

    public FSSackCompartmentContainer(FSSackCompartmentEntity sackCompartment, int windowID)
    {
        super(FSContainerTypes.SACK_COMPARTMENT_MENU.get(), windowID);
        this.sackCompartment = sackCompartment;
    }

    public static FSSackCompartmentContainer fromNetwork(int windowID, Inventory playerInventory, FriendlyByteBuf friendlyByteBuf)
    {
        Entity entity = playerInventory.player.level().getEntity(friendlyByteBuf.readVarInt());
        if (entity instanceof  FSSackCompartmentEntity sackCompartment)
        {
            return create(windowID, playerInventory, sackCompartment);
        }

        throw new IllegalStateException(String.format("%s is not a Sack compartment", entity));
    }

    public FSSackCompartmentEntity getSackCompartment()
    {
        return this.sackCompartment;
    }

    @Override
    protected boolean moveStack(ItemStack stack, int slotIndex)
    {
        return switch (typeOf(slotIndex))
        {
            case MAIN_INVENTORY, HOTBAR -> !moveItemStackTo(stack, 0, SLOTS, false);
            case CONTAINER -> !moveItemStackTo(stack, containerSlots, slots.size(), false);
        };
    }

    @Override
    protected void addContainerSlots()
    {
        this.sackCompartment.getCapability(Capabilities.ITEM).ifPresent(handler -> {
            addSlot(new SlotItemHandler(handler, 0, 62, 19));
            addSlot(new SlotItemHandler(handler, 1, 80, 19));
            addSlot(new SlotItemHandler(handler, 2, 98, 19));
            addSlot(new SlotItemHandler(handler, 3, 62, 37));
            addSlot(new SlotItemHandler(handler, 4, 80, 37));
            addSlot(new SlotItemHandler(handler, 5, 98, 37));
            addSlot(new SlotItemHandler(handler, 6, 62, 55));
            addSlot(new SlotItemHandler(handler, 7, 80, 55));
            addSlot(new SlotItemHandler(handler, 8, 98, 55));
        });
    }
}
