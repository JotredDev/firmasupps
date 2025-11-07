package net.jotred.firmasupps.common.blockentities;

import java.util.ArrayList;
import java.util.List;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.util.INBTSerializable;

import net.dries007.tfc.common.blockentities.TFCBlockEntity;
import net.dries007.tfc.util.Helpers;

public class FSPancakeBlockEntity extends TFCBlockEntity// implements INBTSerializable<CompoundTag>
{
    private static final int MAX_PANCAKES = 8;
    private final List<ItemStack> pancakes;

    public FSPancakeBlockEntity(BlockPos pos, BlockState state)
    {
        this(FSBlockEntities.PANCAKE.get(), pos, state);
    }

    public FSPancakeBlockEntity(BlockEntityType<? extends FSPancakeBlockEntity> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
        pancakes = new ArrayList<>(MAX_PANCAKES);
    }

    public void addPancake(ItemStack pancake)
    {
        pancakes.add(pancake.copyWithCount(1));
        LogUtils.getLogger().warn("added one, now got {} pancakes", pancakes.size());
    }

    public ItemStack getPancake()
    {
        LogUtils.getLogger().warn("removing one, at {} pancakes", pancakes.size());
        return pancakes.removeLast();
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider)
    {
        tag.put("pancakeNBT", Helpers.writeItemStacksToNbt(provider, pancakes));
        super.saveAdditional(tag, provider);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider)
    {
        Helpers.readItemStacksFromNbt(provider, pancakes, tag.getList("pancakeNBT", ListTag.TAG_COMPOUND));
        super.loadAdditional(tag, provider);
    }
}
