package net.jotred.firmasupps.common.blockentities;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Stream;
import net.jotred.firmasupps.common.blocks.FSBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import net.dries007.tfc.common.blockentities.TickCounterBlockEntity;
import net.dries007.tfc.util.registry.RegistrationHelpers;
import net.dries007.tfc.util.registry.RegistryHolder;

import static net.jotred.firmasupps.FirmaSupplementaries.*;

public class FSBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MOD_ID);

    public static final Id<FSTickCounterBlockEntity> TICK_COUNTER = register("tick_counter", FSTickCounterBlockEntity::new, Stream.of(
            Stream.of(
                FSBlocks.CANDLE_HOLDER,
                FSBlocks.SCONCE,
                FSBlocks.SCONCE_WALL,
                FSBlocks.SCONCE_SOUL,
                FSBlocks.SCONCE_WALL_SOUL,
                FSBlocks.SCONCE_LEVER,
                FSBlocks.FIRE_PIT
            ),
            FSBlocks.DYED_CANDLE_HOLDERS.values().stream()
        ).flatMap(e -> e)
    );

    public static final Id<FSSackBlockEntity> SACK = register("sack", FSSackBlockEntity::new, FSBlocks.SACK);

    public static final Id<FSPancakeBlockEntity> PANCAKE = register("pancake", FSPancakeBlockEntity::new, FSBlocks.PANCAKE);

    public static final Id<FSPlanterBlockEntity> PLANTER = register("planter", FSPlanterBlockEntity::new, FSBlocks.PLANTER);

    public static final Id<FSGobletBlockEntity> GOBLET = register("goblet", FSGobletBlockEntity::new, FSBlocks.GOBLET);



    private static <T extends BlockEntity> Id<T> register(String name, BlockEntityType.BlockEntitySupplier<T> factory, Supplier<? extends Block> block)
    {
        return new Id<>(RegistrationHelpers.register(BLOCK_ENTITIES, name, factory, block));
    }

    private static <T extends BlockEntity> Id<T> register(String name, BlockEntityType.BlockEntitySupplier<T> factory, Stream<? extends Supplier<? extends Block>> blocks)
    {
        return new Id<>(RegistrationHelpers.register(BLOCK_ENTITIES, name, factory, blocks));
    }

    public record Id<T extends BlockEntity>(DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> holder)
        implements RegistryHolder<BlockEntityType<?>, BlockEntityType<T>> {}
}
