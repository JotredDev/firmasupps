package net.jotred.firmasupps;

import net.jotred.firmasupps.client.ClientEventHandler;
import net.jotred.firmasupps.common.blockentities.FSBlockEntities;
import net.jotred.firmasupps.common.blocks.FSBlocks;
import net.jotred.firmasupps.common.capabilities.FSBlockCapabilities;
import net.jotred.firmasupps.common.container.FSContainerTypes;
import net.jotred.firmasupps.common.items.FSCreativeTabs;
import net.jotred.firmasupps.common.items.FSItems;


import net.jotred.firmasupps.compat.jade.TheOneProbeIntegration;
import net.jotred.firmasupps.config.FSServerConfig;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.InterModComms;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(FirmaSupplementaries.MOD_ID)
public class FirmaSupplementaries
{
    public static final String MOD_ID = "firmasupps";

    public FirmaSupplementaries(IEventBus bus, ModContainer mod)
    {
        mod.registerConfig(ModConfig.Type.SERVER, FSServerConfig.SPEC);
        bus.addListener(this::onInterModComms);
        bus.addListener(FSBlockCapabilities::register);

        FSBlocks.BLOCKS.register(bus);
        FSItems.ITEMS.register(bus);
        FSContainerTypes.CONTAINERS.register(bus);
        FSBlockEntities.BLOCK_ENTITIES.register(bus);
        FSCreativeTabs.CREATIVE_TABS.register(bus);

        ForgeEventHandler.init();

        if (FMLEnvironment.dist == Dist.CLIENT)
        {
            ClientEventHandler.init(bus, mod);
            //RenderEventHandler.init();
        }

        /*
        if (ModList.get().isLoaded("firmaciv"))
        {
            FSContainerTypes.FIRMACIV_CONTAINERS.register(bus);
            FSEntities.FIRMACIV_ENTITY_TYPES.register(bus);
        }
         */
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
