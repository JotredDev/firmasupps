package net.jotred.firmasupps.common.container;

import java.util.function.Supplier;
import net.jotred.firmasupps.common.blockentities.FSBlockEntities;
import net.jotred.firmasupps.common.blockentities.FSSackBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import net.dries007.tfc.common.blockentities.InventoryBlockEntity;
import net.dries007.tfc.common.container.BlockEntityContainer;
import net.dries007.tfc.util.registry.RegistrationHelpers;

import static net.jotred.firmasupps.FirmaSupplementaries.*;


@SuppressWarnings("RedundantTypeArguments")
public final class FSContainerTypes
{
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(Registries.MENU, MOD_ID);

    public static final RegistryObject<MenuType<FSSackContainer>> SACK =
        FSContainerTypes.<FSSackBlockEntity, FSSackContainer>registerBlock("sack", FSBlockEntities.SACK, FSSackContainer::create);


    private static <T extends InventoryBlockEntity<?>, C extends BlockEntityContainer<T>> RegistryObject<MenuType<C>> registerBlock(String name, Supplier<BlockEntityType<T>> type, BlockEntityContainer.Factory<T, C> factory)
    {
        return RegistrationHelpers.registerBlockEntityContainer(CONTAINERS, name, type, factory);
    }
}
