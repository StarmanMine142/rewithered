package me.pajic.rewithered.potion;

import me.pajic.rewithered.Main;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;

public class ModPotions {
    public static final Potion DECAY = Registry.register(
            BuiltInRegistries.POTION,
            Main.withModNamespace("decay"),
            new DecayPotion(
                    Main.CONFIG.decayPotion.duration.get() * 20,
                    Main.CONFIG.decayPotion.strength.get() - 1
            )
    );
    public static final Potion LONG_DECAY = Registry.register(
            BuiltInRegistries.POTION,
            Main.withModNamespace("long_decay"),
            new DecayPotion(
                    Main.CONFIG.decayPotion.longDuration.get() * 20,
                    Main.CONFIG.decayPotion.longStrength.get() - 1
            )
    );
    public static final Potion STRONG_DECAY = Registry.register(
            BuiltInRegistries.POTION,
            Main.withModNamespace("strong_decay"),
            new DecayPotion(
                    Main.CONFIG.decayPotion.strongDuration.get() * 20,
                    Main.CONFIG.decayPotion.strongStrength.get() - 1
            )
    );

    public static void init() {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(
                    Potions.AWKWARD,
                    Ingredient.of(Items.WITHER_ROSE),
                    BuiltInRegistries.POTION.wrapAsHolder(DECAY)
            );
            builder.registerPotionRecipe(
                    BuiltInRegistries.POTION.wrapAsHolder(DECAY),
                    Ingredient.of(Items.REDSTONE),
                    BuiltInRegistries.POTION.wrapAsHolder(LONG_DECAY)
            );
            builder.registerPotionRecipe(
                    BuiltInRegistries.POTION.wrapAsHolder(DECAY),
                    Ingredient.of(Items.GLOWSTONE_DUST),
                    BuiltInRegistries.POTION.wrapAsHolder(STRONG_DECAY)
            );
        });
    }
}
