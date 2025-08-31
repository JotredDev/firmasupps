package net.jotred.firmasupps;

import net.jotred.firmasupps.client.RenderEventHandler;
import net.jotred.firmasupps.common.blockentities.FSBlockEntities;
import net.jotred.firmasupps.common.blocks.FSBlocks;
import net.jotred.firmasupps.common.container.FSContainerTypes;
import net.jotred.firmasupps.common.entities.FSEntities;
import net.jotred.firmasupps.common.items.FSCreativeTabs;
import net.jotred.firmasupps.common.items.FSItems;
import net.jotred.firmasupps.config.FSConfig;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

import net.jotred.firmasupps.compat.jade.TheOneProbeIntegration;

@Mod(FirmaSupplementaries.MOD_ID)
public class FirmaSupplementaries
{
    public static final String MOD_ID = "firmasupps";

    public FirmaSupplementaries()
    {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        FSConfig.init();
        MinecraftForge.EVENT_BUS.register(this);
        bus.addListener(this::onInterModComms);

        FSBlocks.BLOCKS.register(bus);
        FSItems.ITEMS.register(bus);
        FSContainerTypes.CONTAINERS.register(bus);
        FSBlockEntities.BLOCK_ENTITIES.register(bus);
        FSCreativeTabs.CREATIVE_TABS.register(bus);

        ForgeEventHandler.init();

        if (FMLEnvironment.dist == Dist.CLIENT)
        {
            ClientEventHandler.init();
            RenderEventHandler.init();
        }

        if (ModList.get().isLoaded("firmaciv"))
        {
            FSContainerTypes.FIRMACIV_CONTAINERS.register(bus);
            FSEntities.FIRMACIV_ENTITY_TYPES.register(bus);
        }
    }

    public void onInterModComms(InterModEnqueueEvent event)
    {
        // Adding TOP integration
        // Luckily minimal effort since it mostly relies on Jade integration
        if (ModList.get().isLoaded("theoneprobe"))
        {
            InterModComms.sendTo("theoneprobe", "getTheOneProbe", TheOneProbeIntegration::new);
        }
    }
}
