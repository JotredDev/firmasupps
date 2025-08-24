package net.jotred.firmasupps.client;

import com.alekiponi.alekiships.client.render.entity.vehicle.vehiclehelper.BlockCompartmentRenderer;
import net.jotred.firmasupps.common.entities.FSEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import static net.jotred.firmasupps.FirmaSupplementaries.*;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RenderEventHandler
{
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        if (ModList.get().isLoaded("firmaciv"))
        {
            event.registerEntityRenderer(FSEntities.SACK_COMPARTMENT_ENTITY.get(), BlockCompartmentRenderer::new);
        }
    }
}
