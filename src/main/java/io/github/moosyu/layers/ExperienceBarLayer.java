package io.github.moosyu.layers;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;

import static io.github.moosyu.helpers.TextShadowHelper.drawShadowText;
import static io.github.moosyu.registers.TextureRegister.LARGE_BAR;

public class ExperienceBarLayer implements LayeredDraw.Layer {
    @Override
    public void render(GuiGraphics graphics, DeltaTracker deltaTracker) {
        final int SPRITE_WIDTH = 181;
        final int SPRITE_HEIGHT = 15;
        final int POS_X_BAR = (graphics.guiWidth() / 2) - (SPRITE_WIDTH / 2);
        final int POS_Y_BAR = graphics.guiHeight() - SPRITE_HEIGHT - 17;

        graphics.enableScissor(POS_X_BAR, POS_Y_BAR, POS_X_BAR + SPRITE_WIDTH, POS_Y_BAR + 8);
        graphics.setColor(0.0425f, 0.85f, 0.258f, 1.0f);
        graphics.blit(LARGE_BAR, POS_X_BAR, POS_Y_BAR, 0, 0, SPRITE_WIDTH, SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT);
        graphics.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        graphics.disableScissor();

        drawShadowText(graphics, "520", POS_X_BAR + (SPRITE_WIDTH / 2), POS_Y_BAR, 0x7EFC20);
    }
}
