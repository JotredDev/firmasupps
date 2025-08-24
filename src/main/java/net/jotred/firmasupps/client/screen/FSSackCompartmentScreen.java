package net.jotred.firmasupps.client.screen;

import net.jotred.firmasupps.common.container.FSSackCompartmentContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import net.dries007.tfc.client.screen.TFCContainerScreen;
import net.dries007.tfc.util.Helpers;

import static net.jotred.firmasupps.FirmaSupplementaries.*;

public class FSSackCompartmentScreen extends TFCContainerScreen<FSSackCompartmentContainer>
{
    public static final ResourceLocation BACKGROUND = Helpers.resourceLocation(MOD_ID,"textures/gui/sack.png");

    public FSSackCompartmentScreen(FSSackCompartmentContainer container, Inventory playerInventory, Component name)
    {
        super(container, playerInventory, name, BACKGROUND);
    }

    @Override
    protected void drawDefaultBackground(GuiGraphics graphics)
    {
        graphics.blit(texture, leftPos, topPos, 0, 0, 0, imageWidth, imageHeight, 176, 166);
    }
}
