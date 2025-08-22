package net.jotred.firmasupps.common.items;

import java.util.Locale;
import java.util.function.Supplier;
import net.jotred.firmasupps.common.blocks.FSBlocks;
import net.mehvahdjukaar.supplementaries.reg.ModSounds;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.jotred.firmasupps.FirmaSupplementaries.*;

public class FSItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, MOD_ID);

    public static final RegistryObject<Item> SCONCE = register("sconce",
        () -> new FSSconceItem(
            FSBlocks.SCONCE.get(),
            FSBlocks.SCONCE_WALL.get(),
            new Item.Properties()));

    public static final RegistryObject<Item> SCONCE_SOUL = register("sconce_soul",
        () -> new FSSconceItem(
            FSBlocks.SCONCE_SOUL.get(),
            FSBlocks.SCONCE_WALL_SOUL.get(),
            new Item.Properties()));

    public static final RegistryObject<Item> PANCAKE = register("pancake",
        () -> new FSPancakeItem(
            15,
            ModSounds.PANCAKE_MUSIC.get(),
            new Item.Properties(),
            228
        ));

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item)
    {
        return ITEMS.register(name.toLowerCase(Locale.ROOT), item);
    }

}
