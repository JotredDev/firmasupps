package net.jotred.firmasupps.compat.jade.common;

import net.minecraft.resources.ResourceLocation;

import net.dries007.tfc.util.Helpers;

import static net.jotred.firmasupps.FirmaSupplementaries.*;

@FunctionalInterface
public interface RegisterCallback<T, C>
{
    default void register(String name, T tooltip, Class<? extends C> thing)
    {
        register(Helpers.resourceLocation(MOD_ID, name), tooltip, thing);
    }

    void register(ResourceLocation name, T tooltip, Class<? extends C> thing);
}
