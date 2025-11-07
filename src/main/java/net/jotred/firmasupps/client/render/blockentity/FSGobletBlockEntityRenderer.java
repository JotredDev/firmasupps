package net.jotred.firmasupps.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jotred.firmasupps.common.blockentities.FSGobletBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

import net.dries007.tfc.client.RenderHelpers;

public class FSGobletBlockEntityRenderer implements BlockEntityRenderer<FSGobletBlockEntity>
{
    @Override
    public void render(FSGobletBlockEntity goblet, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay)
    {
        final IFluidHandler fluidHandler = goblet.getTank(null);
        final FluidStack fluidStack = fluidHandler.getFluidInTank(0);
        if (!fluidStack.isEmpty())
        {
            final float fillPercent = (float) fluidStack.getAmount() / FSGobletBlockEntity.GOBLET_CAPACITY;
            RenderHelpers.renderFluidFace(poseStack, fluidStack, buffer,
                0.375f, 0.375f,
                0.625f, 0.625f,
                0.25f + 0.25f * fillPercent,
                combinedOverlay, combinedLight);
        }
    }
}
