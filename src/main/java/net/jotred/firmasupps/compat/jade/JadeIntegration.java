package net.jotred.firmasupps.compat.jade;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;
import snownee.jade.api.config.IPluginConfig;

import net.jotred.firmasupps.compat.jade.common.FSBlockEntityTooltips;

import net.dries007.tfc.util.tooltip.BlockEntityTooltip;

@WailaPlugin
public class JadeIntegration implements IWailaPlugin
{
    @Override
    public void registerClient(IWailaClientRegistration registry)
    {
        FSBlockEntityTooltips.register((name, tooltip, entity) -> register(registry, name, tooltip, entity));
    }

    private void register(IWailaClientRegistration registry, ResourceLocation name, BlockEntityTooltip blockEntityTooltip, Class<? extends Block> block)
    {
        registry.registerBlockComponent(new IBlockComponentProvider()
        {
            @Override
            public void appendTooltip(ITooltip tooltip, BlockAccessor access, IPluginConfig config)
            {
                blockEntityTooltip.display(access.getLevel(), access.getBlockState(), access.getPosition(), access.getBlockEntity(), tooltip::add);
            }

            @Override
            public ResourceLocation getUid()
            {
                return name;
            }
        }, block);
    }
}
