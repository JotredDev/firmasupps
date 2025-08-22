package net.jotred.firmasupps.client.screen;

import net.jotred.firmasupps.common.blockentities.FSSackBlockEntity;
import net.jotred.firmasupps.common.container.FSSackContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import net.dries007.tfc.client.screen.BlockEntityScreen;
import net.dries007.tfc.util.Helpers;

import static net.jotred.firmasupps.FirmaSupplementaries.*;

public class FSSackScreen extends BlockEntityScreen<FSSackBlockEntity, FSSackContainer>
{
    public static final ResourceLocation BACKGROUND = Helpers.resourceLocation(MOD_ID,"textures/gui/sack.png");

    public FSSackScreen(FSSackContainer container, Inventory playerInventory, Component name)
    {
        super(container, playerInventory, name, BACKGROUND);
    }

    @Override
    protected void drawDefaultBackground(GuiGraphics graphics)
    {
        graphics.blit(texture, leftPos, topPos, 0, 0, 0, imageWidth, imageHeight, 176, 166);
    }
}
