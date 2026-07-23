package dev.jgj52.screenapi.screen;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class BetterScreen extends Screen {
    protected BetterScreen(Component title) {
        super(title);
    }
    protected BetterScreen() {
        super(Component.empty());
    }

    protected record Widget<T extends AbstractWidget>(
            T widget,
            String id,
            boolean pinned
    ) {}
    private final Set<Widget<?>> widgets = new HashSet<>();
    private final Map<String, Widget<?>> fasterWidgets = new HashMap<>();
    private int scrollOffset = 0;
    protected int getScrollOffset() {
        return scrollOffset;
    }

    protected abstract int getScrollSpeed();

    //? < 26.1 {
    /*@Override
    *///? }
    public void render(@NotNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        for (Widget<?> w : widgets) {
            AbstractWidget widget = w.widget;

            boolean isOffscreen = widget.getX() + widget.getWidth() < 0
                    || widget.getX() > width
                    || widget.getY() + widget.getHeight() < 0
                    || widget.getY() > height;

            if (isOffscreen) continue;

            widget.
                    //? >= 26.1 {
                    extractRenderState
                    //? } else {
                    /*render
                    *///? }
            (graphics, mouseX, mouseY, partialTick);
        }
    }

    //? >= 26.1 {
    @Override
    public final void extractRenderState(@NotNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        render(graphics, mouseX, mouseY, a);
    }
    //? }

    protected abstract void createWidgets(Font font);

    @Override
    protected void init() {
        clearWidgets();
        createWidgets(font);
    }

    @Override
    protected void clearWidgets() {
        super.clearWidgets();
        widgets.clear();
    }

    @Override
    public void resize(
            //? <= 1.21.9 {
            /*net.minecraft.client.Minecraft minecraft,
            *///? }
            int width,
            int height
    ) {
        this.width = width;
        this.height = height;
        init();
    }

    protected <T extends AbstractWidget> T widget(T widget, String id, boolean pinned) {
        Widget<T> widg = new Widget<>(widget, id, pinned);
        widgets.add(widg);
        if (id != null) {
            fasterWidgets.put(id, widg);
        }
        return addWidget(widget);
    }
    protected <T extends AbstractWidget> T widget(T widget, String id) {
        return widget(widget, id, false);
    }
    protected <T extends AbstractWidget> T widget(T widget) {
        return widget(widget, null);
    }

    protected <T extends AbstractWidget> T getWidget(String id, Class<T> type) {
        Widget<?> widg = fasterWidgets.get(id);
        if (widg != null && type.isInstance(widg.widget)) {
            return type.cast(widg.widget);
        }
        return null;
    }

    @Override
    public boolean mouseScrolled(double x, double y, double scrollX, double scrollY) {
        int will = scrollOffset + (int) (scrollY * getScrollSpeed());
        int min = 0;
        int max = 0;
        for (Widget<?> widget : widgets) {
            if (widget.pinned) continue;
            int wy = widget.widget.getY() - scrollOffset;
            min = Math.min(min, wy);
            max = Math.max(max, wy + widget.widget.getHeight());
        }
        max = Math.max(0, max - (height - min));
        if (will > min) {
            will = min;
        }
        if (-will > max) {
            will = -max;
        }
        for (Widget<?> widget : widgets) {
            if (widget.pinned) continue;
            widget.widget.setY(
                    widget.widget.getY() + (will - scrollOffset)
            );
        }
        scrollOffset = will;
        return super.mouseScrolled(x, y, scrollX, scrollY);
    }
}
