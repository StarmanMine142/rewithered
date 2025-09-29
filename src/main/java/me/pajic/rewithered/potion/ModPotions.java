package me.pajic.rewithered.potion;

import me.pajic.rewithered.Main;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

public class ModPotions {

    public static final Potion DECAY = new DecayPotion(
            Main.CONFIG.decayPotion.duration.get() * 20,
            Main.CONFIG.decayPotion.strength.get() - 1
    );
    public static final Potion LONG_DECAY = new DecayPotion(
            Main.CONFIG.decayPotion.longDuration.get() * 20,
            Main.CONFIG.decayPotion.longStrength.get() - 1
    );
    public static final Potion STRONG_DECAY = new DecayPotion(
            Main.CONFIG.decayPotion.strongDuration.get() * 20,
            Main.CONFIG.decayPotion.strongStrength.get() - 1
    );

    @SubscribeEvent
    public static void initPotions(RegisterEvent event) {
        event.register(
                Registries.POTION,
                registry -> {
                    registry.register(Main.withModNamespace("decay"), DECAY);
                    registry.register(Main.withModNamespace("long_decay"), LONG_DECAY);
                    registry.register(Main.withModNamespace("strong_decay"), STRONG_DECAY);
                }
        );
    }

    @SubscribeEvent
    public static void initBrewingRecipes(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();
        builder.addMix(
                Potions.AWKWARD,
                Items.WITHER_ROSE,
                BuiltInRegistries.POTION.wrapAsHolder(DECAY)
        );
        builder.addMix(
                BuiltInRegistries.POTION.wrapAsHolder(DECAY),
                Items.REDSTONE,
                BuiltInRegistries.POTION.wrapAsHolder(LONG_DECAY)
        );
        builder.addMix(
                BuiltInRegistries.POTION.wrapAsHolder(DECAY),
                Items.GLOWSTONE_DUST,
                BuiltInRegistries.POTION.wrapAsHolder(STRONG_DECAY)
        );
    }
}
