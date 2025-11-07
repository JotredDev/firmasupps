package net.jotred.firmasupps.common.items;

import java.util.Map;
import java.util.function.Supplier;
import net.jotred.firmasupps.common.blocks.FSBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import net.dries007.tfc.common.TFCCreativeTabs;
import net.dries007.tfc.common.blocks.DecorationBlockHolder;

import static net.jotred.firmasupps.FirmaSupplementaries.*;

public class FSCreativeTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final Id FIRMASUPPS =
        register("firmasupps", () -> new ItemStack(FSBlocks.CANDLE_HOLDER.get()), FSCreativeTabs::fillTab);

    private static void fillTab(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output out)
    {
        // Technically it isn't necessary to explicitly accept these items due to the loop, but this allows ordering them.
        // Notably, non-light blocks come before the light blocks, and candle holders come last
        out.accept(FSBlocks.SACK.get().asItem());
        out.accept(FSBlocks.PLANTER.get().asItem());
        out.accept(FSBlocks.GOBLET.get().asItem());
        out.accept(FSItems.PANCAKE.get());
        out.accept(FSItems.SCONCE.get());
        out.accept(FSItems.SCONCE_SOUL.get());
        out.accept(FSBlocks.SCONCE_LEVER.get().asItem());
        out.accept(FSBlocks.FIRE_PIT.get().asItem());

        for (DeferredHolder<Item, ? extends Item> item : FSItems.ITEMS.getEntries()) {
            out.accept(item.get());
        }
    }

    private static Id register(String name, Supplier<ItemStack> icon, CreativeModeTab.DisplayItemsGenerator displayItems)
    {
        final var holder = CREATIVE_TABS.register(name, () -> CreativeModeTab.builder()
            .icon(icon)
            .title(Component.translatable("firmasupps.creative_tab." + name))
            .displayItems(displayItems)
            .build());
        return new Id(holder, displayItems);
    }

    private static <R extends ItemLike, K1, K2> void accept(CreativeModeTab.Output out, Map<K1, Map<K2, R>> map, K1 key1, K2 key2)
    {
        if (map.containsKey(key1))
        {
            accept(out, map.get(key1), key2);
        }
    }

    private static <R extends ItemLike, K> void accept(CreativeModeTab.Output out, Map<K, R> map, K key)
    {
        if (map.containsKey(key))
        {
            out.accept(map.get(key));
        }
    }

    private static void accept(CreativeModeTab.Output out, DecorationBlockHolder decoration)
    {
        out.accept(decoration.stair());
        out.accept(decoration.slab());
        out.accept(decoration.wall());
    }

    public record Id(DeferredHolder<CreativeModeTab, CreativeModeTab> tab, CreativeModeTab.DisplayItemsGenerator generator) {}
}
