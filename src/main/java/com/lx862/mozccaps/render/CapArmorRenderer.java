package com.lx862.mozccaps.render;

import com.lx862.mozccaps.Main;
import com.lx862.mozccaps.armor.CapModel;
import com.lx862.mozccaps.armor.ChinModel;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.model.*;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.joml.Quaternionf;

import java.util.HashMap;
import java.util.Map;

public class CapArmorRenderer implements ArmorRenderer {
    private static final HashMap<String, Double> typeAnimationMap = new HashMap<>();
    private static final Identifier TEXTURE_ID = Main.id("textures/armor/mozc_caps.png");
    private static final ModelPart capModel = CapModel.getMainModel();
    private static final ModelPart chinModel = ChinModel.getMainModel();
    private static final float CAP_TILT = -0.2F;

    private final boolean hasStrap;

    public CapArmorRenderer(boolean hasStrap) {
        this.hasStrap = hasStrap;
    }

    private void renderCap(MatrixStack matrices, OrderedRenderCommandQueue orderedRenderCommandQueue, BipedEntityModel<BipedEntityRenderState> contextModel, double pressedAmount, int light) {
        Quaternionf rotation = new Quaternionf();
        rotation.rotateX(CAP_TILT);
        rotation.rotateX(contextModel.getHead().pitch);

        matrices.push();
        capModel.setTransform(contextModel.hat.getTransform());
        matrices.translate(0, -0.1F, -0.07F); //Small offset to make things look right
        matrices.multiply(rotation);
        matrices.scale(0.6F, 0.6F, 0.6F);
        matrices.translate(0, 0.1F * pressedAmount, 0);
        orderedRenderCommandQueue.submitModelPart(capModel, matrices, RenderLayers.entityCutout(TEXTURE_ID), light, OverlayTexture.DEFAULT_UV, null);
        matrices.pop();
    }

    private void renderStrap(MatrixStack matrices, OrderedRenderCommandQueue orderedRenderCommandQueue, BipedEntityModel<BipedEntityRenderState> contextModel, int light) {
        matrices.push();
        matrices.scale(0.6F, 0.6F, 0.6F);
        chinModel.setTransform(contextModel.getHead().getTransform());
        orderedRenderCommandQueue.submitModelPart(chinModel, matrices, RenderLayers.entityCutout(TEXTURE_ID), light, OverlayTexture.DEFAULT_UV, null);
        matrices.pop();
    }

    public static void updateCapPressedAnimation(float delta) {
        for(Map.Entry<String, Double> entry : new HashMap<>(typeAnimationMap).entrySet()) {
            double newProgress = entry.getValue() + (delta);
            if(newProgress >= 1) {
                typeAnimationMap.remove(entry.getKey());
            } else {
                typeAnimationMap.put(entry.getKey(), newProgress);
            }
        }
    }

    public static void startPlayerTypedAnimation(String playerName) {
        typeAnimationMap.put(playerName, 0.0);
    }

    public static double getTypeAnimationProgress(String playerName, double defaultValue) {
        return typeAnimationMap.getOrDefault(playerName, defaultValue);
    }

    @Override
    public void render(MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, ItemStack itemStack, BipedEntityRenderState bipedEntityRenderState, EquipmentSlot equipmentSlot, int light, BipedEntityModel<BipedEntityRenderState> bipedEntityModel) {
        final double pressedAmount;
        if(bipedEntityRenderState instanceof PlayerEntityRenderState playerEntityRenderState) {
            double animationProgress = getTypeAnimationProgress(((PlayerNameStorage)playerEntityRenderState).gcaps$getPlayerName(), 0.0);
            pressedAmount = animationProgress > 0.5 ? (1 - animationProgress) : (animationProgress);
        } else {
            pressedAmount = 0;
        }

        renderCap(matrixStack, orderedRenderCommandQueue, bipedEntityModel, pressedAmount, light);
        if(hasStrap) renderStrap(matrixStack, orderedRenderCommandQueue, bipedEntityModel, light);
    }
}
