package net.jotred.firmasupps.common.blocks;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import net.jotred.firmasupps.common.blockentities.FSBlockEntities;
import net.jotred.firmasupps.common.items.FSItems;
import net.jotred.firmasupps.common.items.FSSackItem;
import net.jotred.firmasupps.common.items.GenericFireStarterBlockItem;
import net.mehvahdjukaar.supplementaries.reg.ModSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import net.dries007.tfc.common.blockentities.TFCBlockEntities;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.registry.RegistrationHelpers;

import static net.jotred.firmasupps.FirmaSupplementaries.*;

public class FSBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, MOD_ID);

    public static final RegistryObject<Block> CANDLE_HOLDER = register("candle_holder",
        () -> new FSCandleHolderBlock(null,
            ExtendedProperties.of(MapColor.SAND)
                .noCollission()
                .pushReaction(PushReaction.DESTROY)
                .noOcclusion()
                .instabreak()
                .sound(SoundType.LANTERN)
                .lightLevel(FSCandleHolderBlock.LIGHTING_SCALE)
                .randomTicks()
                .blockEntity(TFCBlockEntities.TICK_COUNTER)),
        b -> new BlockItem(b, new Item.Properties()));

    public static final Map<DyeColor, RegistryObject<Block>> DYED_CANDLE_HOLDERS = Helpers.mapOfKeys(DyeColor.class, color ->
        register("candle_holder/" + color.getName(),
            () -> new FSCandleHolderBlock(color,
                ExtendedProperties.of(MapColor.SAND)
                    .noCollission()
                    .pushReaction(PushReaction.DESTROY)
                    .noOcclusion()
                    .instabreak()
                    .sound(SoundType.LANTERN)
                    .lightLevel(FSCandleHolderBlock.LIGHTING_SCALE)
                    .randomTicks()
                    .blockEntity(FSBlockEntities.TICK_COUNTER)),
            b -> new BlockItem(b, new Item.Properties()))
    );

    public static final RegistryObject<Block> SCONCE = registerNoItem("sconce",
        () -> new FSSconceBlock(() -> ParticleTypes.FLAME,
            ExtendedProperties.of(MapColor.SAND)
                .noCollission()
                .pushReaction(PushReaction.DESTROY)
                .instabreak()
                .sound(SoundType.LANTERN)
                .lightLevel(FSSconceBlock.REGULAR_LIGHTING_SCALE)
                .randomTicks()
                .blockEntity(FSBlockEntities.TICK_COUNTER)));

    public static final RegistryObject<Block> SCONCE_WALL = registerNoItem("sconce_wall",
        () -> new FSSconceWallBlock(() -> ParticleTypes.FLAME,
            ExtendedProperties.of(MapColor.SAND)
                .noCollission()
                .pushReaction(PushReaction.DESTROY)
                .instabreak()
                .sound(SoundType.LANTERN)
                .dropsLike(SCONCE)
                .lightLevel(FSSconceBlock.REGULAR_LIGHTING_SCALE)
                .randomTicks()
                .blockEntity(FSBlockEntities.TICK_COUNTER)));

    public static final RegistryObject<Block> SCONCE_SOUL = registerNoItem("sconce_soul",
        () -> new FSSconceBlock(() -> ParticleTypes.SOUL_FIRE_FLAME,
            ExtendedProperties.of(MapColor.SAND)
                .noCollission()
                .pushReaction(PushReaction.DESTROY)
                .instabreak()
                .sound(SoundType.LANTERN)
                .lightLevel(FSSconceBlock.SOUL_LIGHTING_SCALE)
                .randomTicks()
                .blockEntity(FSBlockEntities.TICK_COUNTER)));

    public static final RegistryObject<Block> SCONCE_WALL_SOUL = registerNoItem("sconce_wall_soul",
        () -> new FSSconceWallBlock(() -> ParticleTypes.SOUL_FIRE_FLAME,
            ExtendedProperties.of(MapColor.SAND)
                .noCollission()
                .pushReaction(PushReaction.DESTROY)
                .instabreak()
                .sound(SoundType.LANTERN)
                .dropsLike(SCONCE_SOUL)
                .lightLevel(FSSconceBlock.SOUL_LIGHTING_SCALE)
                .randomTicks()
                .blockEntity(FSBlockEntities.TICK_COUNTER)));

    public static final RegistryObject<Block> SCONCE_LEVER = register("sconce_lever",
        () -> new FSSconceLeverBlock(() -> ParticleTypes.FLAME,
            ExtendedProperties.of(MapColor.SAND)
                .noCollission()
                .pushReaction(PushReaction.DESTROY)
                .instabreak()
                .sound(SoundType.LANTERN)
                .lightLevel(FSSconceBlock.REGULAR_LIGHTING_SCALE)
                .randomTicks()
                .blockEntity(FSBlockEntities.TICK_COUNTER)),
        b -> new GenericFireStarterBlockItem(b, new Item.Properties()));

    public static final RegistryObject<Block> FIRE_PIT = register("fire_pit",
        () -> new FSFirePitBlock(1,
            ExtendedProperties.of(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK))
                .noCollission()
                .sound(SoundType.COPPER)
                .lightLevel(FSFirePitBlock.LIGHTING_SCALE)
                .randomTicks()
                .blockEntity(FSBlockEntities.TICK_COUNTER)),
        b -> new GenericFireStarterBlockItem(b, new Item.Properties()));

    public static final RegistryObject<Block> PANCAKE = registerNoItem("pancake",
        () -> new FSPancakeBlock(
            ExtendedProperties.of(BlockBehaviour.Properties.copy(Blocks.CAKE))
                .mapColor(MapColor.TERRACOTTA_ORANGE)
                .strength(0.5f)
                .sound(SoundType.WOOL)
                .blockEntity(FSBlockEntities.PANCAKE)));

    public static final RegistryObject<Block> SACK = register("sack",
        () -> new FSSackBlock(
            ExtendedProperties.of(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                .mapColor(MapColor.WOOD)
                .pushReaction(PushReaction.DESTROY)
                .strength(0.8f)
                .sound(ModSounds.SACK)
                .blockEntity(FSBlockEntities.SACK)),
        block -> new FSSackItem(block, new Item.Properties()));

    public static final RegistryObject<Block> PLANTER = register("planter",
        () -> new FSPlanterBlock(
            ExtendedProperties.of(BlockBehaviour.Properties.copy(Blocks.TERRACOTTA))
                .mapColor(MapColor.TERRACOTTA_RED)
                .strength(2f, 6f)
                .blockEntity(FSBlockEntities.PLANTER)));

    public static final RegistryObject<Block> GOBLET = register("goblet",
        () -> new FSGobletBlock(
            ExtendedProperties.of(BlockBehaviour.Properties.copy(Blocks.IRON_BARS))
                .pushReaction(PushReaction.DESTROY)
                .strength(1.5f, 2f)
                .sound(SoundType.METAL)
                .blockEntity(FSBlockEntities.GOBLET)));

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> blockSupplier)
    {
        return register(name, blockSupplier, null);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier)
    {
        return register(name, blockSupplier, block -> new BlockItem(block, new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory)
    {
        return RegistrationHelpers.registerBlock(FSBlocks.BLOCKS, FSItems.ITEMS, name, blockSupplier, blockItemFactory);
    }
}
