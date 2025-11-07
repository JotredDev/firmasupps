package net.jotred.firmasupps.common.capabilities;

import java.util.function.Supplier;
import net.jotred.firmasupps.common.blockentities.FSBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

import net.dries007.tfc.common.blockentities.InventoryBlockEntity;
import net.dries007.tfc.common.capabilities.BlockCapabilities;

public class FSBlockCapabilities
{
    public static void register(RegisterCapabilitiesEvent event)
    {
        registerInventory(event, FSBlockEntities.SACK);
    }

    private static void registerInventory(RegisterCapabilitiesEvent event, Supplier<? extends BlockEntityType<? extends InventoryBlockEntity<?>>> type)
    {
        event.registerBlockEntity(BlockCapabilities.ITEM, type.get(), InventoryBlockEntity::getSidedInventory);
    }
}
