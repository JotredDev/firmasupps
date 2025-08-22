package net.jotred.firmasupps.compat.jade;

import java.util.function.Function;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ITheOneProbe;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import net.jotred.firmasupps.compat.jade.common.BlockEntityTooltip;
import net.jotred.firmasupps.compat.jade.common.BlockEntityTooltips;

public class TheOneProbeIntegration implements Function<ITheOneProbe, Void>
{
    @Override
    public Void apply(ITheOneProbe registry)
    {
        BlockEntityTooltips.register((name, tooltip, block) -> register(registry, name, tooltip, block));
        return null;
    }

    private void register(ITheOneProbe top, ResourceLocation name, BlockEntityTooltip tooltip, Class<? extends Block> blockClass)
    {
        top.registerProvider(new IProbeInfoProvider() {
            @Override
            public ResourceLocation getID()
            {
                return name;
            }

            @Override
            public void addProbeInfo(ProbeMode probeMode, IProbeInfo info, Player player, Level level, BlockState blockState, IProbeHitData data)
            {
                if (data.getPos() != null && blockClass.isInstance(blockState.getBlock()))
                {
                    tooltip.display(level, blockState, data.getPos(), level.getBlockEntity(data.getPos()), info::text);
                }
            }
        });
    }
}
