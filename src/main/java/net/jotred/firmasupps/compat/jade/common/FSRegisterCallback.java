package net.jotred.firmasupps.compat.jade.common;

import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.tooltip.RegisterCallback;

import static net.jotred.firmasupps.FirmaSupplementaries.*;

@FunctionalInterface
public interface FSRegisterCallback<T, C> extends RegisterCallback<T, C>
{
    default void register(String name, T tooltip, Class<? extends C> thing)
    {
        register(Helpers.resourceLocation(MOD_ID, name), tooltip, thing);
    }
}
