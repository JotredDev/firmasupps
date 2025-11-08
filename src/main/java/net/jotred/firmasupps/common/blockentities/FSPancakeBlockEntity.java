package net.jotred.firmasupps.common.blockentities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.util.INBTSerializable;

import net.dries007.tfc.common.blockentities.TFCBlockEntity;
import net.dries007.tfc.util.Helpers;

public class FSPancakeBlockEntity extends TFCBlockEntity// implements INBTSerializable<CompoundTag>
{
    private static final int MAX_PANCAKES = 8;
    private final ItemStack[] pancakes;

    public FSPancakeBlockEntity(BlockPos pos, BlockState state)
    {
        this(FSBlockEntities.PANCAKE.get(), pos, state);
    }

    public FSPancakeBlockEntity(BlockEntityType<? extends FSPancakeBlockEntity> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
        pancakes = new ItemStack[MAX_PANCAKES];
    }

    public void setPancake(int index, ItemStack pancake)
    {
        pancakes[index] = pancake.copyWithCount(1);
    }

    public ItemStack getPancake(int index)
    {
        return pancakes[index];
    }


    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider)
    {
        ListTag list = new ListTag();

        for (ItemStack stack : pancakes)
        {
            list.add(Objects.requireNonNullElse(stack, ItemStack.EMPTY).saveOptional(provider));
        }

        tag.put("pancakeNBT", list);
        super.saveAdditional(tag, provider);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider)
    {
        ListTag list = tag.getList("pancakeNBT", ListTag.TAG_COMPOUND);

        for (int i = 0; i < list.size(); i++)
        {
            pancakes[i] = ItemStack.parseOptional(provider, list.getCompound(i));
        }

        super.loadAdditional(tag, provider);
    }
}
