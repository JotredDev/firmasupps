package net.jotred.firmasupps.client;

import net.jotred.firmasupps.client.render.blockentity.FSGobletBlockEntityRenderer;
import net.jotred.firmasupps.client.screen.FSSackScreen;
import net.jotred.firmasupps.common.blockentities.FSBlockEntities;
import net.jotred.firmasupps.common.blocks.FSBlocks;
import net.jotred.firmasupps.common.container.FSContainerTypes;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

import net.dries007.tfc.util.Helpers;

import static net.jotred.firmasupps.FirmaSupplementaries.*;


public class ClientEventHandler
{
    public static void init(IEventBus bus, ModContainer mod)
    {
        bus.addListener(ClientEventHandler::clientSetup);
        bus.addListener(ClientEventHandler::registerMenuScreens);
        bus.addListener(ClientEventHandler::registerEntityRenderers);

        ClientHelper.registerOptionalTexturePack(Helpers.resourceLocation(MOD_ID, "tfc_style_supplementaries"), Component.literal("TFC-ified Supplementaries"), true);
    }

    @SuppressWarnings("deprecation")
    public static void clientSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(() ->
        {
/*
            if (ModList.get().isLoaded("firmaciv"))
            {
                MenuScreens.register(FSContainerTypes.SACK_COMPARTMENT_MENU.get(), FSSackCompartmentScreen::new);
            }
*/
        });

        ItemBlockRenderTypes.setRenderLayer(FSBlocks.CANDLE_HOLDER.get(), RenderType.cutout());
        FSBlocks.DYED_CANDLE_HOLDERS.values().forEach(c -> ItemBlockRenderTypes.setRenderLayer(c.get(), RenderType.cutout()));
        ItemBlockRenderTypes.setRenderLayer(FSBlocks.SCONCE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(FSBlocks.SCONCE_WALL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(FSBlocks.SCONCE_SOUL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(FSBlocks.SCONCE_WALL_SOUL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(FSBlocks.SCONCE_LEVER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(FSBlocks.FIRE_PIT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(FSBlocks.PANCAKE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(FSBlocks.SACK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(FSBlocks.PLANTER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(FSBlocks.GOBLET.get(), RenderType.cutout());
    }

    public static void registerMenuScreens(RegisterMenuScreensEvent event)
    {
        event.register(FSContainerTypes.SACK.get(), FSSackScreen::new);
    }

    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerBlockEntityRenderer(FSBlockEntities.GOBLET.get(), ctw -> new FSGobletBlockEntityRenderer());
    }
}
