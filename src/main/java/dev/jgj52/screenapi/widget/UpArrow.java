package dev.jgj52.screenapi.widget;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class UpArrow extends AbstractWidget {
    private final Supplier<Button> supplier;
    private final Runnable onClick;

    public UpArrow(int x, int y, int width, int height, Supplier<Button> supplier, Runnable onClick) {
        super(x, y, width, height, Component.empty());
        this.supplier = supplier;
        this.onClick = onClick;
    }

    @Override
    protected void extractWidgetRenderState(@NotNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        if (!this.visible) return;

        int backgroundColor = this.isHoveredOrFocused() ? 0xFF555555 : 0xFF333333;

        graphics.fill(this.getX(), this.getY(), this.getX() + this.width, this.getY() + this.height, backgroundColor);

        int padding = 1;

        int topX = this.getX() + this.width / 2;
        int topY = this.getY() + padding;

        int bottomLeftX = this.getX() + padding;
        int bottomLeftY = this.getY() + this.height - padding;

        int bottomRightX = this.getX() + this.width - padding;
        int bottomRightY = this.getY() + this.height - padding;

        int arrowColor = this.active ? 0xFFFFFFFF : 0xFFA0A0A0;

        drawArrow(graphics, topX, topY, bottomLeftX, bottomLeftY, bottomRightX, bottomRightY, arrowColor);
    }

    private void drawArrow(GuiGraphicsExtractor graphics, int tx, int ty, int blx, int bly, int brx, int bry, int color) {
        int height = bly - ty;
        for (int i = 0; i <= height; i++) {
            float ratio = (float) i / height;
            int left = (int) (tx + (blx - tx) * ratio);
            int right = (int) (tx + (brx - tx) * ratio);
            graphics.fill(left, ty + i, right, ty + i + 1, color);
        }
    }

    @Override
    protected void updateWidgetNarration(@NotNull NarrationElementOutput output) {

    }

    @Override
    public void setFocused(boolean focused) {

    }

    @Override
    public void onClick(@NotNull MouseButtonEvent event, boolean doubleClick) {
        supplier.get().setFocused(true);
        onClick.run();
    }
}
