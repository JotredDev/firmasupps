package net.jotred.firmasupps.common;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import static net.jotred.firmasupps.FirmaSupplementaries.*;

public class FSTags
{
    public static class Blocks
    {
        public static final TagKey<Block> FORCE_GUNPOWDER_LIGHTING = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, "force_gunpowder_lighting")); // Blocks that can be "lit" by Supplementaries gunpowder trail, despite not having `LIT` as a blockstate value
    }
}
