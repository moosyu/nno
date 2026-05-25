package io.github.moosyu.screens;

import io.github.moosyu.attachments.PlayerSkillsAttachment;
import io.github.moosyu.helpers.RomanNumeralHelper;
import io.github.moosyu.registers.AttachmentRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import static io.github.moosyu.registers.TextureRegister.PROFILE_SCREEN;
import static io.github.moosyu.registers.TextureRegister.SKILL_BAR;

public class ProfileScreen extends Screen {
    private static final int SCREEN_WIDTH = 352;
    private static final int SCREEN_HEIGHT = 222;
    private static final String PAGE_NAME = "Profile";

    public ProfileScreen() {super(Component.literal(PAGE_NAME));}

    @Override
    protected void init() {
        super.init();

        Player player = Minecraft.getInstance().player;
        if (player == null) return;

        final int CORNER_POS_X = (this.width - SCREEN_WIDTH) / 2;
        final int CORNER_POS_Y = (this.height - SCREEN_HEIGHT) / 2;

        for (int i = 0; i < PlayerSkillsAttachment.Skill.values().length; i++) {
            boolean even = i % 2 == 0;
            int posX = (even ? CORNER_POS_X : CORNER_POS_X + SCREEN_WIDTH - 176);
            int posY = (even ? (CORNER_POS_Y + i * 24) : (CORNER_POS_Y + (i - 1) * 24));
            PlayerSkillsAttachment.Skill currentSkill = PlayerSkillsAttachment.Skill.values()[i];

            this.addRenderableWidget(new SkillWidget(currentSkill, posX + 16, posY + 16, player, new ItemStack(currentSkill.getIcon())));
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {super.render(graphics, mouseX, mouseY, partialTick);}

    @Override
    public void renderBackground(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        // putting this all here to layer the dimmed background bit and the actual empty screen properly
        // idk why this happens i mustve done something wrong at some point
        this.renderTransparentBackground(graphics);
        int cornerPosX = (this.width - SCREEN_WIDTH) / 2;
        int cornerPosY = (this.height - SCREEN_HEIGHT) / 2;

        graphics.blit(PROFILE_SCREEN, cornerPosX, cornerPosY, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    // so it wont try to save and what not
    @Override
    public boolean isPauseScreen() {return false;}

    private static class SkillWidget extends AbstractWidget {
        private final PlayerSkillsAttachment.Skill skill;
        private final Player player;
        private final ItemStack displayIcon;

        public SkillWidget(PlayerSkillsAttachment.Skill skill, int x, int y, Player player, ItemStack displayIcon) {
            super(x, y, 144, 32, Component.literal(skill.name()));
            this.skill = skill;
            this.player = player;
            this.displayIcon = displayIcon;
        }

        @Override
        protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
            PlayerSkillsAttachment playerData = player.getData(AttachmentRegistry.PLAYER_SKILLS.get());

            // debug area for clicks
            graphics.fill(this.getX(), this.getY(), this.getX() + this.width, this.getY() + this.height, 0x44FF0000);

            String levelText = skill.getName() + " " + RomanNumeralHelper.toRoman(playerData.getLevel(playerData.getExp(skill)));
            graphics.drawString(Minecraft.getInstance().font, levelText, this.getX() + 36, this.getY() + 4, 0xFF53F953, true);
            graphics.pose().pushPose();
            graphics.pose().scale(2f, 2f, 2f);
            graphics.renderItem(displayIcon, this.getX() / 2, this.getY() / 2);
            graphics.pose().popPose();
            graphics.blit(SKILL_BAR, this.getX() + 32, this.getY() + 24, 0, 7, 112, 8, 112, 15);
            graphics.blit(SKILL_BAR, this.getX() + 32, this.getY() + 24, 0, 0, (int) (112 * playerData.getPercentageToLevel(playerData.getExp(skill))), 8, 112, 15);
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            if (this.isValidClickButton(button) && this.clicked(mouseX, mouseY)) {
                this.playDownSound(Minecraft.getInstance().getSoundManager());
                System.out.println(this.skill.getName());
                return true;
            }
            return super.mouseClicked(mouseX, mouseY, button);
        }

        @Override
        protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {}
    }

    // this is a widget as i intend for some hover interaction at some point
    private static class StatWidget extends AbstractWidget {
        private final Player player;
        private final Holder<Attribute> attribute;
        private final String attributeName, attributeSymbol;
        private final int attributeColor;

        public StatWidget(String attributeName, String attributeSymbol, Holder<Attribute> attribute, int x, int y, Player player, int attributeColor) {
            super(x, y, 32, 16, Component.literal(attributeName));
            this.player = player;
            this.attribute = attribute;
            this.attributeName = attributeName;
            this.attributeSymbol = attributeSymbol;
            this.attributeColor = attributeColor;
        }

        @Override
        protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
            final double attributeValue = player.getAttributeValue(attribute);
            int textX = this.getX() + 20;
            int textY = this.getY() + 4;

            graphics.drawString(Minecraft.getInstance().font, attributeSymbol + " " + attributeName + ":", textX, textY, attributeColor, false);
            graphics.drawString(Minecraft.getInstance().font, String.format("%.0f", attributeValue), textX + Minecraft.getInstance().font.width(attributeSymbol + " " + attributeName + ": ") + 2, textY, 0xFFDEDFE0, false);
        }

        @Override
        protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {}
    }
}