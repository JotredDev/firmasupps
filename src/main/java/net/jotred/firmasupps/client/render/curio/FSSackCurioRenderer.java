package net.jotred.firmasupps.client.render.curio;

import com.eerussianguy.barrels_2012.Barrels2012;
import com.eerussianguy.barrels_2012.client.BlockItemCurioRenderer;
import com.eerussianguy.barrels_2012.client.BodyCurioModel;
import net.jotred.firmasupps.common.blocks.FSSackBlock;
import net.jotred.firmasupps.common.items.FSSackItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotResult;

public class FSSackCurioRenderer extends BlockItemCurioRenderer
{
    public FSSackCurioRenderer()
    {
        super(new BodyCurioModel<>(bake("sack"), "body"));
    }

    @Override
    public @Nullable BlockState getBlock(LivingEntity livingEntity, ItemStack itemStack)
    {
        final SlotResult curioResult = Barrels2012.getCurio(livingEntity, stack -> stack.getItem() instanceof FSSackItem);

        if (curioResult != null)
        {
            return defaultState(curioResult.stack()).setValue(FSSackBlock.OPEN, false);
        }

        return null;
    }
}
