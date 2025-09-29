package me.pajic.rewithered;

import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.pajic.rewithered.config.ModConfig;
import me.pajic.rewithered.mixson.LootTableEvents;
import me.pajic.rewithered.mixson.WorldgenDataEvents;
import me.pajic.rewithered.potion.ModPotions;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.common.NeoForge;
import net.ramixin.mixson.debug.DebugMode;
import net.ramixin.mixson.inline.Mixson;
import org.slf4j.Logger;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.LoggerFactory;

@Mod(Main.MOD_ID)
public class Main {
    public static final String MOD_ID = "rewithered";
    private static final Logger LOGGER = LoggerFactory.getLogger("Rewithered");
    private static final boolean DEBUG = !FMLLoader.isProduction();
    public static final ResourceLocation CONFIG_RL = withModNamespace("config");
    public static ModConfig CONFIG = ConfigApiJava.registerAndLoadConfig(ModConfig::new);

    public Main(IEventBus modEventBus) {
        if (DEBUG) Mixson.setDebugMode(DebugMode.EXPORT);
        LootTableEvents.register();
        WorldgenDataEvents.register();
        modEventBus.addListener(ModPotions::initPotions);
        NeoForge.EVENT_BUS.addListener(ModPotions::initBrewingRecipes);
    }

    public static ResourceLocation withModNamespace(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static void debugLog(String message, Object ... args) {
        if (DEBUG) LOGGER.info(message, args);
    }
}
