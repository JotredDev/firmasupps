package net.jotred.firmasupps.common.blockentities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.INBTSerializable;

import net.dries007.tfc.common.blockentities.TFCBlockEntity;
import net.dries007.tfc.util.Helpers;

public class FSPancakeBlockEntity extends TFCBlockEntity implements INBTSerializable<CompoundTag>
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

    public void storePancake(int index, ItemStack pancake)
    {
        pancakes[index] = pancake.copyWithCount(1);
    }

    public ItemStack getPancake(int index)
    {
        return pancakes[index];
    }

    @Override
    protected void saveAdditional(CompoundTag tag)
    {
        tag.put("pancakeNBT", Helpers.writeItemStacksToNbt(pancakes));
        super.saveAdditional(tag);
    }

    @Override
    protected void loadAdditional(CompoundTag tag)
    {
        Helpers.readItemStacksFromNbt(pancakes, tag.getList("pancakeNBT", ListTag.TAG_COMPOUND));
        super.loadAdditional(tag);
    }
}
