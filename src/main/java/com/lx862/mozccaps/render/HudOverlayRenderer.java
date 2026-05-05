package com.lx862.mozccaps.render;

import com.lx862.mozccaps.MainClient;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;
import net.minecraft.util.CommonColors;
import org.joml.Matrix3x2fStack;

public class HudOverlayRenderer implements HudElement {
    private static final int PADDING = 4;
    private static final int TEXT_FIELD_HEIGHT = 12;

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) {
        Minecraft minecraft = Minecraft.getInstance();
        CapArmorRenderer.updateCapPressedAnimation(deltaTracker.getGameTimeDeltaTicks() / 4);

        if(!minecraft.options.hideGui && (!(minecraft.screen instanceof ChatScreen)) && MainClient.capEquipped(minecraft.player) && MainClient.getInput().inputEnabled() && minecraft.player != null) {
            String selectedChar = MainClient.getInput().getSelection(minecraft.player.getYHeadRot());
            float typeAnimation = (float) CapArmorRenderer.getTypeAnimationProgress(minecraft.player.getGameProfile().name(), 1.0);

            drawSelectedChar(graphics, minecraft.font, selectedChar, typeAnimation);
            drawTextField(graphics, minecraft.font, minecraft.options, selectedChar);
        }
    }

    private static void drawSelectedChar(GuiGraphicsExtractor graphics, Font font, String selectedChar, float typeAnimationProgress) {
        Matrix3x2fStack matrices = graphics.pose();
        float halfTextWidth = font.width(selectedChar) / 2f;
        float halfFontHeight = font.lineHeight / 2f;

        float halfScreenWidth = graphics.guiWidth() / 2f;
        float halfScreenHeight = graphics.guiHeight() / 2f;

        float textScale = 1.5f + (typeAnimationProgress * 0.5f);
        matrices.pushMatrix();
        matrices.translate(halfScreenWidth - halfTextWidth, halfScreenHeight - halfFontHeight);
        matrices.translate(halfTextWidth, halfFontHeight);
        matrices.scale(textScale, textScale);
        matrices.translate(-halfTextWidth, -halfFontHeight);
        graphics.text(font, Component.literal(selectedChar), 0, 0, CommonColors.WHITE);
        matrices.popMatrix();
    }

    private static void drawTextField(GuiGraphicsExtractor graphics, Font textRenderer, Options gameOptions, String selectedChar) {
        int width = graphics.guiWidth();
        int height = graphics.guiHeight();
        int textFieldY = height - 34;

        // Background
        graphics.fill(0, textFieldY, width, textFieldY + TEXT_FIELD_HEIGHT, ARGB.color(128, CommonColors.BLACK));

        int sentenceWidth = textRenderer.width(MainClient.getInput().getInputted());
        int textY = textFieldY + (TEXT_FIELD_HEIGHT / 2) - (textRenderer.lineHeight / 2);

        // Detail
        drawText(graphics, textRenderer, Component.translatable("hud.mozc_caps.left_click", Component.translatable(gameOptions.keyAttack.saveString()).getString()), 0, textY - PADDING - textRenderer.lineHeight * 3, CommonColors.WHITE);
        drawText(graphics, textRenderer, Component.translatable("hud.mozc_caps.middle_click", Component.translatable(gameOptions.keyPickItem.saveString()).getString()), 0, textY - PADDING - textRenderer.lineHeight * 2, CommonColors.WHITE);
        drawText(graphics, textRenderer, Component.translatable("hud.mozc_caps.right_click", Component.translatable(gameOptions.keyUse.saveString()).getString()), 0, textY - PADDING - textRenderer.lineHeight, CommonColors.WHITE);
        drawTextRightAligned(graphics, textRenderer, Component.translatable("hud.mozc_caps.layout", MainClient.getInput().getLayoutName()), 0, textY - PADDING - textRenderer.lineHeight, CommonColors.WHITE);

        // Text
        drawText(graphics, textRenderer, Component.literal(MainClient.getInput().getInputted()), 0, textY, 0xFFFFFFFF);
        drawText(graphics, textRenderer, Component.literal(selectedChar), sentenceWidth, textY, 0xFFAAAAAA);
    }

    private static void drawText(GuiGraphicsExtractor graphics, Font textRenderer, Component text, int x, int y, int color) {
        graphics.text(textRenderer, text, PADDING + x, y, color);
    }

    private static void drawTextRightAligned(GuiGraphicsExtractor graphics, Font font, Component text, int x, int y, int color) {
        graphics.text(font, text, graphics.guiWidth() - PADDING - PADDING - x - font.width(text), y, color);
    }
}
