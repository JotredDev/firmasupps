package net.jotred.firmasupps.config;


import java.util.function.Function;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;

import static net.jotred.firmasupps.FirmaSupplementaries.*;

public class FSServerConfig
{
    public final ForgeConfigSpec.IntValue candleHolderTicks;
    public final ForgeConfigSpec.IntValue sconceTicks;
    public final ForgeConfigSpec.IntValue firePitTicks;
    public final ForgeConfigSpec.BooleanValue uniformSconceLeverBehaviour;
    public final ForgeConfigSpec.DoubleValue planterNutrientMultiplier;

    FSServerConfig(Builder innerBuilder)
    {
        Function<String, Builder> builder = name -> innerBuilder.translation(MOD_ID + ".config.server." + name);

        innerBuilder.push("general");

        candleHolderTicks = builder.apply("candleHolderTicks")
            .comment("Number of ticks required for a candle holder to burn out (1000 = 1 in game hour = 50 seconds), default is 384 hours. Set to -1 to disable candle holder burnout.")
            .defineInRange("candleHolderTicks",384000, -1, Integer.MAX_VALUE);

        sconceTicks = builder.apply("sconceTicks")
            .comment("Number of ticks required for a sconce to burn out (1000 = 1 in game hour = 50 seconds), default is 144 hours. Set to -1 to disable sconce burnout.")
            .defineInRange("sconceTicks",144000, -1, Integer.MAX_VALUE);

        firePitTicks = builder.apply("firePitTicks")
            .comment("Number of ticks required for a fire pit to burn out (1000 = 1 in game hour = 50 seconds), default is 120 hours. Set to -1 to disable fire pit burnout.")
            .defineInRange("firePitTicks",120000, -1, Integer.MAX_VALUE);

        uniformSconceLeverBehaviour = builder.apply("uniformSconceLeverBehaviour")
            .comment("Change the behaviour of the sconce lever to not invert its redstone signal if it is not lit, default is true. Set to false to experience the same behaviour as in regular Supplementaries")
            .define("uniformSconceLeverBehaviour", true);

        planterNutrientMultiplier = builder.apply("planterNutrientMultiplier")
            .comment("Multiplier for nutrients added to planters, default is 1.25. Set to 1 to make planters equivalent to regular farmland")
            .defineInRange("planterNutrientMultiplier",1.25d, 1d, Float.MAX_VALUE);

        innerBuilder.pop();
    }

}
