package dev.jgj52.screenapi.widget;

import dev.jgj52.screenapi.util.DrawHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.ItemDisplayWidget;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class ItemWidget extends AbstractWidget {
    private static final Minecraft mc = Minecraft.getInstance();

    private final Set<Consumer<Boolean>> onSelected = new HashSet<>();
    private final IDW witem;
    private final StringWidget text;
    private final Item item;
    private final Font font;
    private boolean selected;
    public ItemWidget(int x, int y, int width, int height, Item item, Font font) {
        super(x, y, width, height, Component.empty());
        this.item = item;
        this.font = font;
        Component name = Component.translatable(item.getDescriptionId());
        witem = new IDW(
                mc,
                0, 0,
                16, 16,
                name,
                item.getDefaultInstance(),
                false,
                true
        );
        witem.setPosition(
                x + 4,
                y + (height - 16) / 2
        );
        text = new StringWidget(
            x + 24,
            y + (height - font.lineHeight) / 2,
            font.width(name),
            font.lineHeight,
            name,
            font
        );
    }

    public Item getItem() {
        return item;
    }

    @Override
    public void setX(int x) {
        super.setX(x);
        witem.setX(x + 4);
        text.setX(x + 24);
    }

    @Override
    public void setY(int y) {
        super.setY(y);
        witem.setY(y + (height - 16) / 2);
        text.setY(y + (height - font.lineHeight) / 2);
    }

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
        onSelected.forEach(on -> on.accept(selected));
    }
    public void onSelected(Consumer<Boolean> on) {
        onSelected.add(on);
    }

    @Override
    public void onClick(@NotNull MouseButtonEvent event, boolean doubleClick) {
        setSelected(!isSelected());
    }

    @Override
    protected void extractWidgetRenderState(@NotNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        if (isHovered()) {
            DrawHelper.drawBox(
                    graphics,
                    getX(),
                    getY(),
                    getX() + getWidth() - 1,
                    getY() + getHeight() -1,
                    0x80_FFFFFF
            );
        }
        if (isSelected()) {
            DrawHelper.drawBox(
                    graphics,
                    getX(),
                    getY(),
                    getX() + getWidth() - 1,
                    getY() + getHeight() - 1,
                    0xFF_FFFFFF
            );
        }
        witem.extractWidgetRenderState(graphics, mouseX, mouseY, a);
        text.extractWidgetRenderState(graphics, mouseX, mouseY, a);
    }

    @Override
    protected void updateWidgetNarration(@NotNull NarrationElementOutput output) {

    }

    @Override
    public void setFocused(boolean focused) {

    }

    private static class IDW extends ItemDisplayWidget {
        public IDW(Minecraft minecraft, int offsetX, int offsetY, int width, int height, Component message, ItemStack itemStack, boolean decorations, boolean tooltip) {
            super(minecraft, offsetX, offsetY, width, height, message, itemStack, decorations, tooltip);
        }

        @Override
        public void extractWidgetRenderState(@NotNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
            super.extractWidgetRenderState(graphics, mouseX, mouseY, a);
        }
    }
}
