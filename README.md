# ScreenAPI
![Time](https://hackatime-badge.hackclub.com/U0922GMGGTU/ScreenAPI)

i got so upset trying to make the math mathing in scroll

## build.gradle
```groovy
repositories {
    maven { url "https://maven.jgj52.hu/repository/maven-releases/" }
}

dependencies {
    implementation "hu.jgj52:screenapi:1.0.0+1.21.11"
}
```

## API usage
```java
package me.you.yourprojectwhichusesscreens.screens;

import hu.jgj52.screenapi.screen.BetterScreen;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.network.chat.Component;

public class YourScreen extends BetterScreen {
    @Override
    protected int getScrollSpeed() {
        return 10;
    }

    @Override
    public void render(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
        // just like render method in yarn
        StringWidget stringWidget = getWidget("widget id", StringWidget.class); // will return null if its not StringWidget
    }

    @Override
    protected void createWidgets(Font font) {
        widget(new StringWidget(
                10,
                20,
                font.width("cat"),
                font.lineHeight,
                Component.literal("cat"),
                font
        ), "widget id"); // with widget id you can later get that widget using getWidget(), just like in render
    }
}
```