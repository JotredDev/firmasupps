package net.jotred.firmasupps.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jotred.firmasupps.common.blockentities.FSGobletBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;

import net.dries007.tfc.client.RenderHelpers;
import net.dries007.tfc.common.capabilities.Capabilities;

public class FSGobletBlockEntityRenderer implements BlockEntityRenderer<FSGobletBlockEntity>
{
    @Override
    public void render(FSGobletBlockEntity goblet, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay)
    {
        goblet.getCapability(Capabilities.FLUID).map(handler -> handler.getFluidInTank(0)).filter(fluid -> !fluid.isEmpty()).ifPresent(fluidStack ->
        {
            final float fillPercent = (float) fluidStack.getAmount() / FSGobletBlockEntity.GOBLET_CAPACITY;
            RenderHelpers.renderFluidFace(poseStack, fluidStack, buffer,
                0.375f, 0.375f,
                0.625f, 0.625f,
                0.25f + 0.25f * fillPercent,
                combinedOverlay, combinedLight);
        });
    }
}
