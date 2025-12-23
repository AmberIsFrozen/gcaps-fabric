package com.lx862.mozccaps.render;

import com.lx862.mozccaps.MainClient;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;
import net.minecraft.util.CommonColors;
import org.joml.Matrix3x2fStack;

public class HudOverlayRenderer implements HudElement {
    private static final int PADDING = 4;
    private static final int TEXT_FIELD_HEIGHT = 12;

    @Override
    public void render(GuiGraphics drawContext, DeltaTracker renderTickCounter) {
        Minecraft minecraft = Minecraft.getInstance();
        CapArmorRenderer.updateCapPressedAnimation(renderTickCounter.getGameTimeDeltaTicks() / 4);

        if(!minecraft.options.hideGui && (!(minecraft.screen instanceof ChatScreen)) && MainClient.capEquipped(minecraft.player) && MainClient.getAtamaInput().inputEnabled() && minecraft.player != null) {
            String selectedChar = MainClient.getAtamaInput().getSelection(minecraft.player.getYHeadRot());
            float typeAnimation = (float) CapArmorRenderer.getTypeAnimationProgress(minecraft.player.getGameProfile().name(), 1.0);

            drawSelectedChar(drawContext, minecraft.font, selectedChar, typeAnimation);
            drawTextField(drawContext, minecraft.font, minecraft.options, selectedChar);
        }
    }

    private static void drawSelectedChar(GuiGraphics drawContext, Font textRenderer, String selectedChar, float typeAnimationProgress) {
        Matrix3x2fStack matrices = drawContext.pose();
        float halfTextWidth = textRenderer.width(selectedChar) / 2f;
        float halfFontHeight = textRenderer.lineHeight / 2f;

        float halfScreenWidth = drawContext.guiWidth() / 2f;
        float halfScreenHeight = drawContext.guiHeight() / 2f;

        float textScale = 1.5f + (typeAnimationProgress * 0.5f);
        matrices.pushMatrix();
        matrices.translate(halfScreenWidth - halfTextWidth, halfScreenHeight - halfFontHeight);
        matrices.translate(halfTextWidth, halfFontHeight);
        matrices.scale(textScale, textScale);
        matrices.translate(-halfTextWidth, -halfFontHeight);
        drawContext.drawString(textRenderer, Component.literal(selectedChar), 0, 0, CommonColors.WHITE);
        matrices.popMatrix();
    }

    private static void drawTextField(GuiGraphics drawContext, Font textRenderer, Options gameOptions, String selectedChar) {
        int width = drawContext.guiWidth();
        int height = drawContext.guiHeight();
        int textFieldY = height - 34;

        // Background
        drawContext.fill(0, textFieldY, width, textFieldY + TEXT_FIELD_HEIGHT, ARGB.color(128, CommonColors.BLACK));

        int sentenceWidth = textRenderer.width(MainClient.getAtamaInput().getInputted());
        int textY = textFieldY + (TEXT_FIELD_HEIGHT / 2) - (textRenderer.lineHeight / 2);

        // Detail
        drawText(drawContext, textRenderer, Component.translatable("hud.mozc_caps.left_click", Component.translatable(gameOptions.keyAttack.saveString()).getString()), 0, textY - PADDING - textRenderer.lineHeight * 3, CommonColors.WHITE);
        drawText(drawContext, textRenderer, Component.translatable("hud.mozc_caps.middle_click", Component.translatable(gameOptions.keyPickItem.saveString()).getString()), 0, textY - PADDING - textRenderer.lineHeight * 2, CommonColors.WHITE);
        drawText(drawContext, textRenderer, Component.translatable("hud.mozc_caps.right_click", Component.translatable(gameOptions.keyUse.saveString()).getString()), 0, textY - PADDING - textRenderer.lineHeight, CommonColors.WHITE);
        drawTextRightAligned(drawContext, textRenderer, Component.translatable("hud.mozc_caps.layout", MainClient.getAtamaInput().getLayoutName()), 0, textY - PADDING - textRenderer.lineHeight, CommonColors.WHITE);

        // Text
        drawText(drawContext, textRenderer, Component.literal(MainClient.getAtamaInput().getInputted()), 0, textY, 0xFFFFFFFF);
        drawText(drawContext, textRenderer, Component.literal(selectedChar), sentenceWidth, textY, 0xFFAAAAAA);
    }

    private static void drawText(GuiGraphics drawContext, Font textRenderer, Component text, int x, int y, int color) {
        drawContext.drawString(textRenderer, text, PADDING + x, y, color);
    }

    private static void drawTextRightAligned(GuiGraphics drawContext, Font textRenderer, Component text, int x, int y, int color) {
        drawContext.drawString(textRenderer, text, drawContext.guiWidth() - PADDING - PADDING - x - textRenderer.width(text), y, color);
    }
}
