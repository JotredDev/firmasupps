package net.jotred.firmasupps.client;

import com.alekiponi.alekiships.client.render.entity.vehicle.vehiclehelper.BlockCompartmentRenderer;
import com.eerussianguy.barrels_2012.Barrels2012;
import com.eerussianguy.barrels_2012.client.BodyCurioModel;
import net.jotred.firmasupps.client.render.curio.FSSackCurioRenderer;
import net.jotred.firmasupps.common.blocks.FSBlocks;
import net.jotred.firmasupps.common.entities.FSEntities;
import net.minecraft.client.model.geom.PartPose;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

public class RenderEventHandler
{
    public static void init()
    {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(RenderEventHandler::setup);
        bus.addListener(RenderEventHandler::registerBodyLayers);
        bus.addListener(RenderEventHandler::registerRenderers);
    }

    public static void setup(FMLClientSetupEvent event)
    {
        if (ModList.get().isLoaded("barrels_2012"))
        {
            CuriosRendererRegistry.register(FSBlocks.SACK.get().asItem(), FSSackCurioRenderer::new);
        }
    }

    public static void registerBodyLayers(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        if (ModList.get().isLoaded("barrels_2012"))
        {
            event.registerLayerDefinition(Barrels2012.modelLayer("sack"), () -> BodyCurioModel.create("body", PartPose.offset(-8f, 0f, 0f)));
        }
    }

    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        if (ModList.get().isLoaded("firmaciv"))
        {
            event.registerEntityRenderer(FSEntities.SACK_COMPARTMENT_ENTITY.get(), BlockCompartmentRenderer::new);
        }
    }
}
