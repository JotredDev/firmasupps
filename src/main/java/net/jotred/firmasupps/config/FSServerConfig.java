package net.jotred.firmasupps.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class FSServerConfig
{
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue candleHolderTicks = BUILDER
        .comment("# ")
        .comment("# Number of ticks required for a candle holder to burn out (1000 = 1 in game hour = 50 seconds), default is 384 hours. Set to -1 to disable candle holder burnout.")
        .defineInRange("candleHolderTicks",384000, -1, Integer.MAX_VALUE);

    public static final ModConfigSpec.IntValue sconceTicks = BUILDER
        .comment("# ")
        .comment("# Number of ticks required for a sconce to burn out (1000 = 1 in game hour = 50 seconds), default is 144 hours. Set to -1 to disable sconce burnout.")
        .defineInRange("sconceTicks",144000, -1, Integer.MAX_VALUE);

    public static final ModConfigSpec.IntValue firePitTicks = BUILDER
        .comment("# ")
        .comment("# Number of ticks required for a fire pit to burn out (1000 = 1 in game hour = 50 seconds), default is 120 hours. Set to -1 to disable fire pit burnout.")
        .defineInRange("firePitTicks",120000, -1, Integer.MAX_VALUE);

    public static final ModConfigSpec.DoubleValue planterNutrientMultiplier = BUILDER
        .comment("# ")
        .comment("# Multiplier for nutrients added to planters, default is 1.25. Set to 1 to make planters equivalent to regular farmland")
        .defineInRange("planterNutrientMultiplier",1.25d, 1d, Float.MAX_VALUE);


    public static final ModConfigSpec SPEC = BUILDER.build();
}
