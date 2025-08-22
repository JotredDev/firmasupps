package net.jotred.firmasupps.common.items;

import java.util.function.Supplier;
import net.jotred.firmasupps.common.blocks.FSBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.jotred.firmasupps.FirmaSupplementaries.*;

public class FSCreativeTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final FSCreativeTabs.CreativeTabHolder FIRMASUPPS =
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

        for (RegistryObject<Item> item : FSItems.ITEMS.getEntries()) {
            out.accept(item.get());
        }
    }

    private static FSCreativeTabs.CreativeTabHolder register(String name, Supplier<ItemStack> icon, CreativeModeTab.DisplayItemsGenerator displayItems)
    {
        final RegistryObject<CreativeModeTab> reg = CREATIVE_TABS.register(name, () -> CreativeModeTab.builder()
            .icon(icon)
            .title(Component.translatable("firmasupps.creative_tab." + name))
            .displayItems(displayItems)
            .build());
        return new FSCreativeTabs.CreativeTabHolder(reg, displayItems);
    }

    public record CreativeTabHolder(RegistryObject<CreativeModeTab> tab, CreativeModeTab.DisplayItemsGenerator generator) {}
}
