package net.jotred.firmasupps.client;

import com.eerussianguy.barrels_2012.Barrels2012;
import com.eerussianguy.barrels_2012.client.BodyCurioModel;
import net.jotred.firmasupps.client.render.curio.FSSackCurioRenderer;
import net.jotred.firmasupps.common.blocks.FSBlocks;
import net.minecraft.client.model.geom.PartPose;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.ModList;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

public class RenderEventHandler
{
    public static void init(IEventBus bus, ModContainer mod)
    {
        bus.addListener(RenderEventHandler::setup);
        bus.addListener(RenderEventHandler::registerBodyLayers);
        //bus.addListener(RenderEventHandler::registerRenderers);
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

    /*
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        if (ModList.get().isLoaded("firmaciv"))
        {
            event.registerEntityRenderer(FSEntities.SACK_COMPARTMENT_ENTITY.get(), BlockCompartmentRenderer::new);
        }
    }
    */
}
