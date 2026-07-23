package dev.jgj52.screenapi;

import dev.jgj52.screenapi.util.Config;
import net.fabricmc.api.ModInitializer;

public class ScreenAPI implements ModInitializer {
    public static Config config;

    @Override
    public void onInitialize() {
        config = new Config("config", "screenapi");
    }
}
