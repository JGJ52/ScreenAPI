package dev.jgj52.screenapi.version;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.network.chat.Component;
import org.joml.Quaternionfc;
import org.joml.Vector3fc;
import org.jspecify.annotations.Nullable;

public class VGuiGraphics {
    public static void text(GuiGraphicsExtractor graphics, Font font, Component text, int x, int y, int color) {
        graphics.
                //? >= 26.1 {
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
    public static void entity(GuiGraphicsExtractor graphics, EntityRenderState state, float scale, Vector3fc translation, Quaternionfc rotation, @Nullable Quaternionfc overrideCameraAngle, int x0, int y0, int x1, int y1) {
        graphics.
                //? >= 26.1 {
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
