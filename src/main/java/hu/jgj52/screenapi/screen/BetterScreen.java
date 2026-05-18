package hu.jgj52.screenapi.screen;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final List<Widget<?>> widgets = new ArrayList<>();
    private final Map<String, Widget<?>> fasterWidgets = new HashMap<>();
    private int scrollOffset = 0;

    protected abstract int getScrollSpeed();
    public abstract void render(
            //? >= 26.1.2 {
            net.minecraft.client.gui.GuiGraphicsExtractor guiGraphics,
            //? } else {
            /*net.minecraft.client.gui.@NotNull GuiGraphics guiGraphics,
            *///? }
            int mouseX,
            int mouseY,
            float partialTick
    );
    protected abstract void createWidgets(Font font);

    @Override
    protected void init() {
        addRenderableOnly(this::render);
        createWidgets(font);
        for (Widget<?> widget : widgets) {
            addRenderableWidget(widget.widget);
        }
    }

    protected <T extends AbstractWidget> T widget(T widget, String id, boolean pinned) {
        Widget<T> widg = new Widget<>(widget, id, pinned);
        widgets.add(widg);
        if (id != null) {
            fasterWidgets.put(id, widg);
        }
        return widget;
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
        // NEVER doing this again
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
