package dev.jgj52.screenapi.widget;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class DownArrow extends AbstractWidget {
    private final Supplier<Button> supplier;
    private final Runnable onClick;

    public DownArrow(int x, int y, int width, int height, Supplier<Button> supplier, Runnable onClick) {
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

        int tipX = this.getX() + this.width / 2;
        int tipY = this.getY() + this.height - padding;

        int topLeftX = this.getX() + padding;
        int topLeftY = this.getY() + padding;

        int topRightX = this.getX() + this.width - padding;
        int topRightY = this.getY() + padding;

        int arrowColor = this.active ? 0xFFFFFFFF : 0xFFA0A0A0;

        drawArrow(graphics, tipX, tipY, topLeftX, topLeftY, topRightX, topRightY, arrowColor);
    }

    private void drawArrow(GuiGraphicsExtractor graphics, int tx, int ty, int lx, int ly, int rx, int ry, int color) {
        int height = ty - ly;
        for (int i = 0; i <= height; i++) {
            float ratio = (float) i / height;
            int left = (int) (lx + (tx - lx) * ratio);
            int right = (int) (rx + (tx - rx) * ratio);
            graphics.fill(left, ly + i, right, ly + i + 1, color);
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
    }}