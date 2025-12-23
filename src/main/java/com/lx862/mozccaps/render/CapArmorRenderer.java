package com.lx862.mozccaps.render;

import com.lx862.mozccaps.Main;
import com.lx862.mozccaps.armor.CapModel;
import com.lx862.mozccaps.armor.ChinModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
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

    private void renderCap(PoseStack matrices, SubmitNodeCollector orderedRenderCommandQueue, HumanoidModel<HumanoidRenderState> contextModel, double pressedAmount, int light) {
        Quaternionf rotation = new Quaternionf();
        rotation.rotateX(CAP_TILT);
        rotation.rotateX(contextModel.getHead().xRot);

        matrices.pushPose();
        capModel.loadPose(contextModel.hat.storePose());
        matrices.translate(0, -0.1F, -0.07F); //Small offset to make things look right
        matrices.mulPose(rotation);
        matrices.scale(0.6F, 0.6F, 0.6F);
        matrices.translate(0, 0.1F * pressedAmount, 0);
        orderedRenderCommandQueue.submitModelPart(capModel, matrices, RenderTypes.entityCutout(TEXTURE_ID), light, OverlayTexture.NO_OVERLAY, null);
        matrices.popPose();
    }

    private void renderStrap(PoseStack matrices, SubmitNodeCollector orderedRenderCommandQueue, HumanoidModel<HumanoidRenderState> contextModel, int light) {
        matrices.pushPose();
        matrices.scale(0.6F, 0.6F, 0.6F);
        chinModel.loadPose(contextModel.getHead().storePose());
        orderedRenderCommandQueue.submitModelPart(chinModel, matrices, RenderTypes.entityCutout(TEXTURE_ID), light, OverlayTexture.NO_OVERLAY, null);
        matrices.popPose();
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
    public void render(PoseStack matrixStack, SubmitNodeCollector orderedRenderCommandQueue, ItemStack itemStack, HumanoidRenderState bipedEntityRenderState, EquipmentSlot equipmentSlot, int light, HumanoidModel<HumanoidRenderState> bipedEntityModel) {
        final double pressedAmount;
        if(bipedEntityRenderState instanceof AvatarRenderState playerEntityRenderState) {
            double animationProgress = getTypeAnimationProgress(((PlayerNameStorage)playerEntityRenderState).gcaps$getPlayerName(), 0.0);
            pressedAmount = animationProgress > 0.5 ? (1 - animationProgress) : (animationProgress);
        } else {
            pressedAmount = 0;
        }

        renderCap(matrixStack, orderedRenderCommandQueue, bipedEntityModel, pressedAmount, light);
        if(hasStrap) renderStrap(matrixStack, orderedRenderCommandQueue, bipedEntityModel, light);
    }
}
