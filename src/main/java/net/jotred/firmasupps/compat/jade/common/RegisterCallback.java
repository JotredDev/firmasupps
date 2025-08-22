package net.jotred.firmasupps.compat.jade.common;

import net.minecraft.resources.ResourceLocation;
import static net.jotred.firmasupps.FirmaSupplementaries.*;

@FunctionalInterface
public interface RegisterCallback<T, C>
{
    default void register(String name, T tooltip, Class<? extends C> thing)
    {
        register(new ResourceLocation(MOD_ID, name), tooltip, thing);
    }

    void register(ResourceLocation name, T tooltip, Class<? extends C> thing);
}
