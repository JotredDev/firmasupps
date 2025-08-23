package net.jotred.firmasupps.mixin;

import net.mehvahdjukaar.supplementaries.common.misc.explosion.GunpowderExplosion;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.dries007.tfc.util.events.StartFireEvent;

/**
 * Mixin to change the behaviour of the GunpowderExplosion class to not simply light up blocks, but instead calling a {@link StartFireEvent}.
 * <p>
 * This allows for candles, powderkegs, and ceramic bowls to light properly,
 * and thanks to using the {@link StartFireEvent} should be able to avoid any issues arising from simplified checks.
 */
@Mixin(GunpowderExplosion.class)
public abstract class GunpowderExplosionMixin
{
    @Redirect(method = "canLight", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;hasProperty(Lnet/minecraft/world/level/block/state/properties/Property;)Z"))
    private static boolean fixCanLightHasProperty(BlockState state, Property<Boolean> property)
    {
        return true;
    }

    @Redirect(method = "canLight", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getValue(Lnet/minecraft/world/level/block/state/properties/Property;)Ljava/lang/Comparable;"))
    private static Comparable<Boolean> fixCanLightGetValue(BlockState state, Property<Boolean> property)
    {
        return false;
    }

    @Redirect(method = "explodeBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"))
    private boolean fixExplodeBlockSetBlock(Level level, BlockPos pos, BlockState state, int pFlags)
    {
        return StartFireEvent.startFire(level, pos, state, Direction.UP, null, new ItemStack(Items.FLINT_AND_STEEL));
    }

    @Redirect(method = "explodeBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;setValue(Lnet/minecraft/world/level/block/state/properties/Property;Ljava/lang/Comparable;)Ljava/lang/Object;"))
    private Object fixExplodeBlockSetValue(BlockState state, Property<Boolean> property, Comparable<Boolean> comparable)
    {
        return state;
    }
}
