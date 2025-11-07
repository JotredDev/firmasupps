package net.jotred.firmasupps.common.items;

import java.util.Locale;
import java.util.function.Supplier;
import net.jotred.firmasupps.common.blocks.FSBlocks;
import net.mehvahdjukaar.supplementaries.reg.ModSounds;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.EitherHolder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.JukeboxPlayable;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import net.dries007.tfc.util.registry.RegistryHolder;

import static net.jotred.firmasupps.FirmaSupplementaries.*;

public class FSItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, MOD_ID);

    public static final ItemId SCONCE = register("sconce",
        () -> new FSSconceItem(
            FSBlocks.SCONCE.get(),
            FSBlocks.SCONCE_WALL.get(),
            new Item.Properties()));

    public static final ItemId SCONCE_SOUL = register("sconce_soul",
        () -> new FSSconceItem(
            FSBlocks.SCONCE_SOUL.get(),
            FSBlocks.SCONCE_WALL_SOUL.get(),
            new Item.Properties()));

    public static final ItemId PANCAKE = register("pancake",
        () -> new FSPancakeItem(new Item.Properties()
            .component(DataComponents.JUKEBOX_PLAYABLE, new JukeboxPlayable(new EitherHolder<>(ModSounds.PANCAKE_MUSIC_JUKEBOX.getKey()), false))
        ));

    private static ItemId register(String name)
    {
        return register(name, () -> new Item(new Item.Properties()));
    }

    private static ItemId register(String name, Item.Properties properties)
    {
        return new ItemId(ITEMS.register(name.toLowerCase(Locale.ROOT), () -> new Item(properties)));
    }

    private static ItemId register(String name, Supplier<Item> item)
    {
        return new ItemId(ITEMS.register(name.toLowerCase(Locale.ROOT), item));
    }

    public record ItemId(DeferredHolder<Item, Item> holder) implements RegistryHolder<Item, Item>, ItemLike
    {
        @Override
        public Item asItem()
        {
            return get();
        }
    }
}
