package net.jotred.firmasupps.common.blockentities;

import java.util.function.Supplier;
import java.util.stream.Stream;
import net.jotred.firmasupps.common.blocks.FSBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import net.dries007.tfc.common.blockentities.TickCounterBlockEntity;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.registry.RegistrationHelpers;

import static net.jotred.firmasupps.FirmaSupplementaries.*;

public class FSBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MOD_ID);

    public static final RegistryObject<BlockEntityType<TickCounterBlockEntity>> TICK_COUNTER = register("tick_counter", TickCounterBlockEntity::new, Stream.of(
            FSBlocks.CANDLE_HOLDER,
            FSBlocks.DYED_CANDLE_HOLDERS.values(),
            FSBlocks.SCONCE,
            FSBlocks.SCONCE_WALL,
            FSBlocks.SCONCE_SOUL,
            FSBlocks.SCONCE_WALL_SOUL,
            FSBlocks.SCONCE_LEVER,
            FSBlocks.FIRE_PIT
        ).<Supplier<? extends Block>>flatMap(Helpers::flatten)
    );

    public static final RegistryObject<BlockEntityType<FSSackBlockEntity>> SACK = register("sack", FSSackBlockEntity::new, FSBlocks.SACK);

    public static final RegistryObject<BlockEntityType<FSPancakeBlockEntity>> PANCAKE = register("pancake", FSPancakeBlockEntity::new, FSBlocks.PANCAKE);

    public static final RegistryObject<BlockEntityType<FSPlanterBlockEntity>> PLANTER = register("planter", FSPlanterBlockEntity::new, FSBlocks.PLANTER);

    public static final RegistryObject<BlockEntityType<FSGobletBlockEntity>> GOBLET = register("goblet", FSGobletBlockEntity::new, FSBlocks.GOBLET);


    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> factory, Supplier<? extends Block> block)
    {
        return RegistrationHelpers.register(BLOCK_ENTITIES, name, factory, block);
    }

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> factory, Stream<? extends Supplier<? extends Block>> blocks)
    {
        return RegistrationHelpers.register(BLOCK_ENTITIES, name, factory, blocks);
    }
}
