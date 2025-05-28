package com.lx862.mozccaps.render;

import com.lx862.mozccaps.MainClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.math.ColorHelper;

public class HudOverlayRenderer {
    private static final int PADDING = 4;
    private static final int TEXT_FIELD_HEIGHT = 12;

    public static void draw(DrawContext drawContext, RenderTickCounter delta) {
        MinecraftClient minecraft = MinecraftClient.getInstance();
        CapArmorRenderer.updateCapPressedAnimation(delta.getDynamicDeltaTicks() / 4);

        if(!minecraft.options.hudHidden && (!(minecraft.currentScreen instanceof ChatScreen)) && MainClient.capEquipped() && MainClient.getAtamaInput().inputEnabled() && minecraft.player != null) {
            String selectedChar = MainClient.getAtamaInput().getSelection(minecraft.player.getHeadYaw());
            float typeAnimation = (float) CapArmorRenderer.getTypeAnimationProgress(minecraft.player.getGameProfile().getName(), 1.0);

            drawSelectedChar(drawContext, minecraft.textRenderer, selectedChar, typeAnimation);
            drawTextField(drawContext, minecraft.textRenderer, minecraft.options, selectedChar);
        }
    }

    private static void drawSelectedChar(DrawContext drawContext, TextRenderer textRenderer, String selectedChar, float typeAnimationProgress) {
        MatrixStack matrices = drawContext.getMatrices();
        double halfTextWidth = textRenderer.getWidth(selectedChar) / 2.0;
        double halfFontHeight = textRenderer.fontHeight / 2.0;

        double halfScreenWidth = drawContext.getScaledWindowWidth() / 2.0;
        double halfScreenHeight = drawContext.getScaledWindowHeight() / 2.0;

        float textScale = 1.5f + (typeAnimationProgress * 0.5f);
        matrices.push();
        matrices.translate(halfScreenWidth - halfTextWidth, halfScreenHeight - halfFontHeight, 0);
        matrices.translate(halfTextWidth, halfFontHeight, 0);
        matrices.scale(textScale, textScale, textScale);
        matrices.translate(-halfTextWidth, -halfFontHeight, 0);
        drawContext.drawTextWithShadow(textRenderer, Text.literal(selectedChar), 0, 0, Colors.WHITE);
        matrices.pop();
    }

    private static void drawTextField(DrawContext drawContext, TextRenderer textRenderer, GameOptions gameOptions, String selectedChar) {
        int width = drawContext.getScaledWindowWidth();
        int height = drawContext.getScaledWindowHeight();
        int textFieldY = height - 34;

        // Background
        drawContext.fill(0, textFieldY, width, textFieldY + TEXT_FIELD_HEIGHT, ColorHelper.withAlpha(128, Colors.BLACK));

        int sentenceWidth = textRenderer.getWidth(MainClient.getAtamaInput().getInputted());
        int textY = textFieldY + (TEXT_FIELD_HEIGHT / 2) - (textRenderer.fontHeight / 2);

        // Detail
        drawText(drawContext, textRenderer, Text.translatable("hud.mozc_caps.left_click", Text.translatable(gameOptions.attackKey.getBoundKeyTranslationKey()).getString()), 0, textY - PADDING - textRenderer.fontHeight * 3, Colors.WHITE);
        drawText(drawContext, textRenderer, Text.translatable("hud.mozc_caps.middle_click", Text.translatable(gameOptions.pickItemKey.getBoundKeyTranslationKey()).getString()), 0, textY - PADDING - textRenderer.fontHeight * 2, Colors.WHITE);
        drawText(drawContext, textRenderer, Text.translatable("hud.mozc_caps.right_click", Text.translatable(gameOptions.useKey.getBoundKeyTranslationKey()).getString()), 0, textY - PADDING - textRenderer.fontHeight, Colors.WHITE);
        drawTextRightAligned(drawContext, textRenderer, Text.translatable("hud.mozc_caps.layout", MainClient.getAtamaInput().getLayoutName()), 0, textY - PADDING - textRenderer.fontHeight, Colors.WHITE);

        // Text
        drawText(drawContext, textRenderer, Text.literal(MainClient.getAtamaInput().getInputted()), 0, textY, 0xFFFFFFFF);
        drawText(drawContext, textRenderer, Text.literal(selectedChar), sentenceWidth, textY, 0xFFAAAAAA);
    }

    private static void drawText(DrawContext drawContext, TextRenderer textRenderer, Text text, int x, int y, int color) {
        drawContext.drawTextWithShadow(textRenderer, text, PADDING + x, y, color);
    }

    private static void drawTextRightAligned(DrawContext drawContext, TextRenderer textRenderer, Text text, int x, int y, int color) {
        drawContext.drawTextWithShadow(textRenderer, text, drawContext.getScaledWindowWidth() - PADDING - PADDING - x - textRenderer.getWidth(text), y, color);
    }
}
