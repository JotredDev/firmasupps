package net.jotred.firmasupps.common.blocks;

import net.jotred.firmasupps.common.blockentities.FSPancakeBlockEntity;
import net.jotred.firmasupps.common.items.FSItems;
import net.mehvahdjukaar.supplementaries.common.block.blocks.PancakeBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import net.dries007.tfc.common.blocks.EntityBlockExtension;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.IForgeBlockExtension;
import net.dries007.tfc.common.capabilities.food.TFCFoodData;

public class FSPancakeBlock extends PancakeBlock implements IForgeBlockExtension, EntityBlockExtension
{
    private final ExtendedProperties properties;

    public FSPancakeBlock(ExtendedProperties properties)
    {
        super(properties.properties());
        this.properties = properties;
    }

    @Override
    public ExtendedProperties getExtendedProperties()
    {
        return this.properties;
    }

    /**
     * Since the superclass only calls {@link TFCFoodData#eat(int, float)} instead of {@link TFCFoodData#eat(Item, ItemStack, LivingEntity)},
     * we have to call it ourselves to be able to actually modify the food and nutrition data of the player.
     */
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        // Eating the last pancake destroys the block and therefore also the blockentity, so we need to retrieve data before using the superclass method
        final int pancakeIndex = state.getValue(PANCAKES) - 1;
        ItemStack fakePancakeStack = null;
        if (level.getBlockEntity(pos) instanceof FSPancakeBlockEntity pancake)
        {
            fakePancakeStack = pancake.getPancake(pancakeIndex);
        }

        InteractionResult result = super.use(state, level, pos, player, hand, hit);

        if (result.equals(InteractionResult.CONSUME) || result.equals(InteractionResult.SUCCESS))
        {
            if (player instanceof ServerPlayer)
            {
                // If the pancake data does for any reason not load correctly, we use a generic pancake item instead
                // Otherwise, failing to load the data might lead to a NullPointerException
                if (fakePancakeStack == null)
                {
                    fakePancakeStack = new ItemStack(FSItems.PANCAKE.get());
                }

                player.getFoodData().eat(FSItems.PANCAKE.get(), fakePancakeStack, player);
            }
        }

        return result;
    }

    /**
     * When placing a pancake, we want to also save the pancake to a blockentity, so that we can "eat" that pancake with correct nutrition data later on.
     * <p>
     * Since all additions of pancakes have to go through this method, it is the ideal place to intercept the itemstack of the pancake for this purpose.
     */
    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
    {
        super.setPlacedBy(level, pos, state, placer, stack);

        if (level.getBlockEntity(pos) instanceof FSPancakeBlockEntity pancake && placer instanceof ServerPlayer)
        {
            final int pancakeIndex = state.getValue(PANCAKES) - 1;
            pancake.storePancake(pancakeIndex, stack);
        }
    }

    /**
     * We need to set the related item to this, since otherwise {@link PancakeBlock#use} will treat an empty hand as being a pancake item
     */
    @Override
    public Item asItem()
    {
        return FSItems.PANCAKE.get();
    }
}
