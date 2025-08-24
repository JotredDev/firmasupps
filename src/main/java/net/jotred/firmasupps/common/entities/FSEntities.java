package net.jotred.firmasupps.common.entities;

import java.util.Locale;
import com.alekiponi.alekiships.common.entity.vehiclehelper.CompartmentType;
import com.alekiponi.alekiships.common.entity.vehiclehelper.compartment.AbstractCompartmentEntity;
import net.jotred.firmasupps.common.entities.compartment.FSSackCompartmentEntity;
import net.jotred.firmasupps.common.items.FSSackItem;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.jotred.firmasupps.FirmaSupplementaries.*;

public class FSEntities
{
    public static final DeferredRegister<EntityType<?>> FIRMACIV_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MOD_ID);

    public static final RegistryObject<CompartmentType<FSSackCompartmentEntity>> SACK_COMPARTMENT_ENTITY = CompartmentType.register(
        registerCompartment("sack_compartment", CompartmentType.Builder.of(FSSackCompartmentEntity::new, FSSackCompartmentEntity::new)),
        itemStack -> itemStack.getItem() instanceof FSSackItem);

    private static <E extends AbstractCompartmentEntity> RegistryObject<CompartmentType<E>> registerCompartment(String name, CompartmentType.Builder<E> builder)
    {
        return register(name, builder.sized(0.6F, 0.7F).fireImmune().noSummon(), true);
    }

    private static <E extends AbstractCompartmentEntity> RegistryObject<CompartmentType<E>> register(String name, CompartmentType.Builder<E> builder, boolean serialize)
    {
        final String id = name.toLowerCase(Locale.ROOT);
        return FIRMACIV_ENTITY_TYPES.register(id, () ->
        {
            if (!serialize) builder.noSave();
            return builder.build(MOD_ID + ":" + id);
        });
    }
}
