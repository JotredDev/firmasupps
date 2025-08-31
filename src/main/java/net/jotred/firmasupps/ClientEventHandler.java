package net.jotred.firmasupps;

import net.jotred.firmasupps.client.render.blockentity.FSGobletBlockEntityRenderer;
import net.jotred.firmasupps.client.screen.FSSackScreen;
import net.jotred.firmasupps.client.screen.FSSackCompartmentScreen;
import net.jotred.firmasupps.common.blockentities.FSBlockEntities;
import net.jotred.firmasupps.common.blocks.FSBlocks;
import net.jotred.firmasupps.common.container.FSContainerTypes;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static net.jotred.firmasupps.FirmaSupplementaries.*;


public class ClientEventHandler
{
    public static void init()
    {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(ClientEventHandler::clientSetup);
        bus.addListener(ClientEventHandler::registerEntityRenderers);

        ClientHelper.registerOptionalTexturePack(new ResourceLocation(MOD_ID, "tfc_style_supplementaries"), Component.literal("TFC-ified Supplementaries"), true);
    }

    @SuppressWarnings("deprecation")
    public static void clientSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            MenuScreens.register(FSContainerTypes.SACK.get(), FSSackScreen::new);

            if (ModList.get().isLoaded("firmaciv"))
            {
                MenuScreens.register(FSContainerTypes.SACK_COMPARTMENT_MENU.get(), FSSackCompartmentScreen::new);
            }
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

    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerBlockEntityRenderer(FSBlockEntities.GOBLET.get(), ctw -> new FSGobletBlockEntityRenderer());
    }
}
