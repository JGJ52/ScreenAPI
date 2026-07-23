package dev.jgj52.screenapi.util;

import net.minecraft.client.gui.GuiGraphicsExtractor;

public class DrawHelper {
    public static void drawBox(GuiGraphicsExtractor graphics, int x1, int y1, int x2, int y2, int color) {
        // top
        graphics.fill(
                x1,
                y1,
                x2 + 1,
                y1 + 1,
                color
        );
        // bottom
        graphics.fill(
                x1,
                y2,
                x2 + 1,
                y2 + 1,
                color
        );
        // left
        graphics.fill(
                x1,
                y1 + 1,
                x1 + 1,
                y2,
                color
        );
        // right
        graphics.fill(
                x2,
                y1 + 1,
                x2 + 1,
                y2,
                color
        );
    }
}
