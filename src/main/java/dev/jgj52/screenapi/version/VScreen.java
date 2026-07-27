package dev.jgj52.screenapi.version;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.spectator.SpectatorGui;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class VScreen {
    private static final Minecraft mc = Minecraft.getInstance();

    public static void setScreen(Screen screen) {
        mc.
        //? >= 26.2 {
        gui.
        //? }
        setScreen(screen);
    }

    public static Screen current() {
        return
            //? >= 26.2 {
            mc.gui.screen();
            //? } else {
            /*mc.screen;
             *///? }
    }

    public static SpectatorGui spectatorGui() {
        return mc.gui
                //? >= 26.2 {
                .hud
                //? }
                .getSpectatorGui();
    }

    public static void setTimes(int fadeIn, int stay, int fadeOut) {
        mc.gui
            //? >= 26.2 {
            .hud
            //? }
            .setTimes(fadeIn, stay, fadeOut);
    }

    public static void setTitle(Component title) {
        mc.gui
            //? >= 26.2 {
            .hud
            //? }
            .setTitle(title);
    }
}
