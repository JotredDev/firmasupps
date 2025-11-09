package net.jotred.firmasupps.common.blockentities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blockentities.TFCBlockEntity;
import net.dries007.tfc.common.capabilities.DelegateFluidHandler;
import net.dries007.tfc.common.capabilities.FluidTankCallback;
import net.dries007.tfc.common.capabilities.InventoryFluidTank;
import net.dries007.tfc.common.component.TFCComponents;
import net.dries007.tfc.common.component.fluid.FluidComponent;
import net.dries007.tfc.common.component.fluid.FluidContainerInfo;
import net.dries007.tfc.util.Helpers;

public class FSGobletBlockEntity extends TFCBlockEntity implements FluidTankCallback
{
    protected GobletTank tank;

    public static final int GOBLET_CAPACITY = 100;

    public FSGobletBlockEntity(BlockPos pos, BlockState state)
    {
        this(FSBlockEntities.GOBLET.get(), pos, state);
    }

    protected FSGobletBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
        this.tank = new GobletTank(this);
    }

    public IFluidHandler getTank(@Nullable Direction context)
    {
        return tank;
    }

    public boolean isEmpty()
    {
        return tank.isEmpty();
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider provider)
    {
        tank.deserializeNBT(provider, tag.getCompound("tank"));
        super.loadAdditional(tag, provider);
    }

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider provider)
    {
        tag.put("tank", tank.serializeNBT(provider));
        super.saveAdditional(tag, provider);
    }

    @Override
    protected void applyImplicitComponents(DataComponentInput components)
    {
        final FluidComponent goblet = components.getOrDefault(TFCComponents.FLUID, FluidComponent.EMPTY);
        if (!goblet.content().isEmpty())
        {
            tank.tank.setFluid(goblet.content().copy());
        }
        super.applyImplicitComponents(components);
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder builder)
    {
        builder.set(TFCComponents.FLUID, new FluidComponent(tank.tank.getFluid().copy()));
        super.collectImplicitComponents(builder);
    }

    /**
     * Custom internal tank class, used to control which fluids and can enter
     */
    public static class GobletTank implements DelegateFluidHandler, INBTSerializable<CompoundTag>, FluidTankCallback
    {
        public static final FluidContainerInfo INFO = new FluidContainerInfo() {
            @Override
            public boolean canContainFluid(Fluid input)
            {
                return Helpers.isFluid(input, TFCTags.Fluids.DRINKABLES);
            }

            @Override
            public int fluidCapacity()
            {
                return GOBLET_CAPACITY;
            }
        };

        private final FluidTankCallback callback;
        private final InventoryFluidTank tank;

        GobletTank(FSGobletBlockEntity entity)
        {
            this((FluidTankCallback) entity);
        }

        public GobletTank(FluidTankCallback callback)
        {
            this.callback = callback;
            this.tank = new InventoryFluidTank(GOBLET_CAPACITY, stack -> Helpers.isFluid(stack.getFluid(), TFCTags.Fluids.DRINKABLES), this);
        }

        @NotNull
        @Override
        public IFluidHandler getFluidHandler()
        {
            return tank;
        }

        public boolean isEmpty()
        {
            return tank.isEmpty();
        }

        @Override
        public CompoundTag serializeNBT(HolderLookup.Provider holder)
        {
            final CompoundTag nbt = new CompoundTag();
            nbt.put("goblet", tank.writeToNBT(holder, new CompoundTag()));
            return nbt;
        }

        @Override
        public void deserializeNBT(HolderLookup.Provider holder, CompoundTag tag)
        {
            tank.readFromNBT(holder, tag.getCompound("goblet"));
        }

        @Override
        public void fluidTankChanged()
        {
            callback.fluidTankChanged();
        }
    }
}
