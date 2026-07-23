package dev.jgj52.screenapi.widget;

import dev.jgj52.screenapi.util.DrawHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.ItemDisplayWidget;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemWidget extends AbstractWidget {
    private static final Minecraft mc = Minecraft.getInstance();

    private final IDW item;
    private final StringWidget text;
    private boolean selected;
    public ItemWidget(int x, int y, int width, int height, Item item, Font font) {
        super(x, y, width, height, Component.empty());
        Component name = Component.translatable(item.getDescriptionId());
        this.item = new IDW(
                mc,
                0, 0,
                16, 16,
                name,
                item.getDefaultInstance(),
                false,
                true
        );
        this.item.setPosition(
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

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
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
        item.extractWidgetRenderState(graphics, mouseX, mouseY, a);
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
