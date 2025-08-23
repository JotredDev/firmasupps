package net.jotred.firmasupps.mixin;

import net.jotred.firmasupps.common.FSTags;
import net.mehvahdjukaar.supplementaries.common.misc.explosion.GunpowderExplosion;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.dries007.tfc.common.blocks.BowlBlock;
import net.dries007.tfc.common.blocks.DeadTorchBlock;
import net.dries007.tfc.common.blocks.DeadWallTorchBlock;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.events.StartFireEvent;

/**
 * Mixin to change the behaviour of the GunpowderExplosion class to not simply light up blocks, but instead call a {@link StartFireEvent}.
 * <p>
 * This allows for candles, powderkegs, and ceramic bowls to light properly,
 * and thanks to using the {@link StartFireEvent} should be able to avoid any issues arising from simplified checks.
 */
@Mixin(GunpowderExplosion.class)
public abstract class GunpowderExplosionMixin
{
    /**
     * If checking a block that has the {@link FSTags.Blocks#FORCE_GUNPOWDER_LIGHTING} tag, we always treat it as "lightable"
     */
    @Inject(method = "canLight", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true, remap = false)
    private static void fixCanLightGetValue(BlockState state, CallbackInfoReturnable<Boolean> cir, Block b)
    {
        if (Helpers.isBlock(b, FSTags.Blocks.FORCE_GUNPOWDER_LIGHTING))
        {
            cir.setReturnValue(true);
        }
        else
        {
            cir.setReturnValue(cir.getReturnValue());
        }
    }

    /**
     * Blocks that get lit by the explosion instead experience a {@link StartFireEvent}, with the blockstate being manually set only if the property exists in the block
     * <p>
     * This allows triggering blocks like gunpowder bowls, without risking a crash from trying to set a non-existent blockstate property
     */
    @Redirect(method = "explodeBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"))
    private boolean fixExplodeBlockSetBlock(Level level, BlockPos pos, BlockState state, int pFlags)
    {
        StartFireEvent.startFire(level, pos, state, Direction.UP, null, new ItemStack(Items.FLINT_AND_STEEL));
        return !state.hasProperty(BlockStateProperties.LIT) || level.setBlock(pos, state.setValue(BlockStateProperties.LIT, Boolean.TRUE), 11);
    }

    @Redirect(method = "explodeBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;setValue(Lnet/minecraft/world/level/block/state/properties/Property;Ljava/lang/Comparable;)Ljava/lang/Object;"))
    private Object fixExplodeBlockSetValue(BlockState state, Property<Boolean> property, Comparable<Boolean> comparable)
    {
        return state;
    }
}
