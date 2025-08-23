package net.jotred.firmasupps.mixin;

import net.mehvahdjukaar.supplementaries.common.block.blocks.GunpowderBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.dries007.tfc.common.blocks.DeadTorchBlock;
import net.dries007.tfc.common.blocks.DeadWallTorchBlock;

/**
 * Mixin to change the behaviour of the GunpowderBlock class to avoid getting lit by {@link DeadTorchBlock} and {@link DeadWallTorchBlock}.
 * <p>
 * This is necessary because those classes descend from {@link net.minecraft.world.level.block.TorchBlock}, which always lights up gunpowder trails
 */
@Mixin(GunpowderBlock.class)
public abstract class GunpowderBlockMixin
{
    @Inject(method = "canLightMeOnFire(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Z", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true, remap = false)
    private static void fixCanLightMeOnFire(BlockState state, BlockGetter level, BlockPos pos, CallbackInfoReturnable<Boolean> cir, Block b)
    {
        if (b instanceof DeadTorchBlock || b instanceof DeadWallTorchBlock)
        {
            cir.setReturnValue(false);
        }
    }
}
