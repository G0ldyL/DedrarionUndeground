package Hivens.hdu.Common.worldgen;

import Hivens.hdu.HDU;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class PlacedFeatures {
    public static final ResourceKey<PlacedFeature> ETHEREUM_ORE_PLACED_KEY = registerKey("ethereum_ore_placed");
    public static final ResourceKey<PlacedFeature> STONE_OF_HOPES_PLACED_KEY = registerKey("stone_of_hopes_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, ETHEREUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ConfiguredFeatures.OVERWORLD_ETHEREUM_ORE_KEY),
                OrePlacement.rareOrePlacement(1,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-50))));
        register(context, STONE_OF_HOPES_PLACED_KEY, configuredFeatures.getOrThrow(ConfiguredFeatures.OVERWORLD_STONE_OF_HOPES_ORE_KEY),
                OrePlacement.rareOrePlacement(2,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-30))));

    }



    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(HDU.MODID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
