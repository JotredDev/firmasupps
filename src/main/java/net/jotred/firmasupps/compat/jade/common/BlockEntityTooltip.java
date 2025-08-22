package net.jotred.firmasupps.compat.jade.common;

import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

@FunctionalInterface
public interface BlockEntityTooltip
{
    void display(Level level, BlockState state, BlockPos pos, @Nullable BlockEntity entity, Consumer<Component> tooltip);
}
