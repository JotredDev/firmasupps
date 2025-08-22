package net.jotred.firmasupps.common.blockentities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.dries007.tfc.common.blockentities.TFCBlockEntity;
import net.dries007.tfc.common.capabilities.Capabilities;
import net.dries007.tfc.common.capabilities.FluidTankCallback;
import net.dries007.tfc.common.capabilities.InventoryFluidTank;

public class FSGobletBlockEntity extends TFCBlockEntity implements FluidTankCallback
{
    protected FluidTank tank;
    private final LazyOptional<IFluidHandler> holder = LazyOptional.of(() -> tank);

    public static final int GOBLET_CAPACITY = 100;

    public FSGobletBlockEntity(BlockPos pos, BlockState state)
    {
        this(FSBlockEntities.GOBLET.get(), pos, state);
    }

    protected FSGobletBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
        this.tank = new InventoryFluidTank(GOBLET_CAPACITY, this);
    }

    public boolean isEmpty()
    {
        return tank.isEmpty();
    }

    @Override
    public void fluidTankChanged()
    {
        markForSync();
    }

    @Override
    public void loadAdditional(CompoundTag tag)
    {
        tank.readFromNBT(tag.getCompound("tank"));
        super.loadAdditional(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag)
    {
        tag.put("tank", tank.writeToNBT(new CompoundTag()));
        super.saveAdditional(tag);
    }

    @Override
    @NotNull
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction facing)
    {
        if (capability == Capabilities.FLUID)
            return holder.cast();
        return super.getCapability(capability, facing);
    }
}
