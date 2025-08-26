package me.pajic.rewithered;

import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.pajic.rewithered.config.ModConfig;
import me.pajic.rewithered.mixson.LootTableEvents;
import me.pajic.rewithered.mixson.WorldgenDataEvents;
import me.pajic.rewithered.potion.ModPotions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import net.ramixin.mixson.debug.DebugMode;
import net.ramixin.mixson.inline.Mixson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
    public static final String MOD_ID = "rewithered";
    private static final Logger LOGGER = LoggerFactory.getLogger("Rewithered");
    private static final boolean DEBUG = FabricLoader.getInstance().isDevelopmentEnvironment();
    public static final ResourceLocation CONFIG_RL = withModNamespace("config");
    public static ModConfig CONFIG = ConfigApiJava.registerAndLoadConfig(ModConfig::new);

    @Override
    public void onInitialize() {
        if (DEBUG) Mixson.setDebugMode(DebugMode.EXPORT);
        ModPotions.init();
        LootTableEvents.register();
        WorldgenDataEvents.register();
    }

    public static ResourceLocation withModNamespace(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static void debugLog(String message, Object ... args) {
        if (DEBUG) LOGGER.info(message, args);
    }
}
