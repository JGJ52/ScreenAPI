package dev.jgj52.screenapi.widget;

import dev.jgj52.screenapi.util.DrawHelper;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class ItemWidget extends AbstractWidget {
    private final Set<Consumer<Boolean>> onSelected = new HashSet<>();
    private final Component name;
    private final Item item;
    private final Font font;
    private boolean selected;
    public ItemWidget(int x, int y, int width, int height, Item item, Font font) {
        super(x, y, width, height, Component.empty());
        this.item = item;
        this.font = font;
        name = Component.translatable(item.getDescriptionId());
    }
    public ItemWidget(int x, int y, int width, int height, Block block, Font font) {
        super(x, y, width, height, Component.empty());
        this.item = block.asItem();
        this.font = font;
        name = Component.translatable(block.getDescriptionId());
    }

    public Item getItem() {
        return item;
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

        graphics.fakeItem(
                item.getDefaultInstance(),
                getX() + 4,
                getY() + (height - 16) / 2
        );
        graphics.text(
                font,
                name,
                getX() + 24,
                getY() + (height - font.lineHeight) / 2,
                0xFF_FFFFFF
        );
    }

    @Override
    protected void updateWidgetNarration(@NotNull NarrationElementOutput output) {

    }

    @Override
    public void setFocused(boolean focused) {

    }
}
