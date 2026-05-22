package io.github.moosyu.screens;

import io.github.moosyu.attachments.PlayerSkillsAttachment;
import io.github.moosyu.registers.AttachmentRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import static io.github.moosyu.registers.TextureRegister.EMPTY_SCREEN;

public class SkillsScreen extends Screen {
    public SkillsScreen() {
        super(Component.literal("Skills"));
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);

        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        final int SCREEN_WIDTH = 176;
        final int SCREEN_HEIGHT = 222;
        int cornerPositionX =  (this.width - SCREEN_WIDTH) / 2;
        int cornerPositionY =  (this.height - SCREEN_HEIGHT) / 2;
        graphics.blit(EMPTY_SCREEN, (this.width - SCREEN_WIDTH) / 2, (this.height - SCREEN_HEIGHT) / 2, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_WIDTH, SCREEN_HEIGHT);

        float textSpacing = (float) (SCREEN_HEIGHT - cornerPositionY) / PlayerSkillsAttachment.Skill.values().length;
        for (int i = 0; i < PlayerSkillsAttachment.Skill.values().length; i++) {
            PlayerSkillsAttachment.Skill currentSkill = PlayerSkillsAttachment.Skill.values()[i];
            PlayerSkillsAttachment playerData = player.getData(AttachmentRegistry.PLAYER_SKILLS.get());
            graphics.drawString(mc.font, currentSkill.name() + ": " + playerData.getLevel(playerData.getExp(currentSkill)), cornerPositionX + 20, cornerPositionY + i * textSpacing + 5, 0xFF005AFF, false);
        }
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderTransparentBackground(guiGraphics);
    }

    // so it wont try to save and what not
    @Override
    public boolean isPauseScreen() {
        return false;
    }
}