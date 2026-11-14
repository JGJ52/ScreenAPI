package dev.jgj52.screenapi.version;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.network.chat.Component;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.jetbrains.annotations.Nullable;

public class VGraphics {
    public static void text(GuiGraphicsExtractor graphics, Font font, Component text, int x, int y, int color) {
        graphics.
                //? if >= 26.1 {
                text
                //? } else {
                /*drawString
                 *///? }
                        (
                                font,
                                text,
                                x,
                                y,
                                color
                        );
    }
    public static void entity(GuiGraphicsExtractor graphics, EntityRenderState state, float scale, Vector3f translation, Quaternionf rotation, @Nullable Quaternionf overrideCameraAngle, int x0, int y0, int x1, int y1) {
        graphics.
                //? if >= 26.1 {
                entity
                //? } else {
                /*submitEntityRenderState
                 *///? }
                        (
                                state,
                                scale,
                                translation,
                                rotation,
                                overrideCameraAngle,
                                x0,
                                y0,
                                x1,
                                y1
                        );
    }
}
