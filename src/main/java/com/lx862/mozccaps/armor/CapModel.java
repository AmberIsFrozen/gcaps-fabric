package com.lx862.mozccaps.armor;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class CapModel {

    /* Code generated from Blockbench */
    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition bb_main = modelPartData.addOrReplaceChild("bb_main", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Front_r1 = bb_main.addOrReplaceChild("Front_r1", CubeListBuilder.create().texOffs(26, 42).addBox(1.0F, -3.5F, 0.0F, 2.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(22, 42).addBox(-1.0F, -3.5F, 0.0F, 2.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5017F, -5.5573F, -13.5F, -1.5708F, 0.0F, 0.3491F));

        PartDefinition Front_r2 = bb_main.addOrReplaceChild("Front_r2", CubeListBuilder.create().texOffs(18, 42).addBox(1.0F, -3.5F, 0.0F, 2.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6076F, -6.4203F, -13.5F, -1.5708F, 0.0F, 0.1745F));

        PartDefinition Front_r3 = bb_main.addOrReplaceChild("Front_r3", CubeListBuilder.create().texOffs(14, 42).addBox(-1.0F, -3.5F, 0.0F, 2.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5934F, -6.2902F, -13.5F, -1.5708F, 0.0F, 0.0436F));

        PartDefinition Front_r4 = bb_main.addOrReplaceChild("Front_r4", CubeListBuilder.create().texOffs(10, 42).addBox(4.1F, -3.5F, -0.8F, 2.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3491F, -4.7445F, -13.5F, -1.5708F, 0.0F, -0.1309F));

        PartDefinition Front_r5 = bb_main.addOrReplaceChild("Front_r5", CubeListBuilder.create().texOffs(6, 42).addBox(2.1F, -3.5F, -0.8F, 2.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.2182F, -4.4044F, -13.5F, -1.5708F, 0.0F, -0.2182F));

        PartDefinition Front_r6 = bb_main.addOrReplaceChild("Front_r6", CubeListBuilder.create().texOffs(0, 42).addBox(-1.9F, -3.5F, -0.8F, 3.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -4.45F, -13.5F, -1.5708F, 0.0F, -0.4363F));

        PartDefinition cube_r1 = bb_main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(-4, 20).addBox(-2.0F, 0.0F, -3.5F, 4.0F, 0.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(-4, 13).addBox(-2.0F, 0.0F, 3.5F, 4.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7248F, -15.1885F, -4.2F, 0.0F, 0.0F, 0.0698F));

        PartDefinition cube_r2 = bb_main.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 20).addBox(-2.0F, 0.0F, -3.5F, 4.0F, 0.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 13).addBox(-2.0F, 0.0F, 3.5F, 4.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2656F, -15.1885F, -4.2F, 0.0F, 0.0F, -0.0698F));

        PartDefinition cube_r3 = bb_main.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(4, 20).addBox(2.0F, 0.0F, -3.5F, 3.0F, 0.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(4, 13).addBox(2.0F, 0.0F, 3.5F, 3.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2912F, -14.9807F, -4.2F, 0.0F, 0.0F, -0.1745F));

        PartDefinition cube_r4 = bb_main.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(-7, 20).addBox(-3.1F, 0.7F, -3.2F, 3.0F, 0.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(-7, 13).addBox(-3.1F, 0.7F, 3.8F, 3.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -16.0F, -4.5F, 0.0F, 0.0F, 0.1745F));

        PartDefinition Left_r1 = bb_main.addOrReplaceChild("Left_r1", CubeListBuilder.create().texOffs(0, 62).addBox(-11.5F, -5.8745F, 0.0F, 23.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.6425F, -10.3255F, 0.5F, 0.288F, 1.5708F, 0.0F));

        PartDefinition Right_r1 = bb_main.addOrReplaceChild("Right_r1", CubeListBuilder.create().texOffs(0, 62).addBox(-20.0F, -6.8F, -6.85F, 23.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -7.6F, -8.0F, -0.288F, 1.5708F, 0.0F));

        PartDefinition Back_r1 = bb_main.addOrReplaceChild("Back_r1", CubeListBuilder.create().texOffs(6, 0).addBox(-8.0F, -1.0F, 16.25F, 21.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -7.6F, -8.0F, 0.48F, 0.0F, 0.0F));

        PartDefinition Front_r7 = bb_main.addOrReplaceChild("Front_r7", CubeListBuilder.create().texOffs(0, 84).addBox(-8.0F, -8.2F, -1.85F, 21.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -7.6F, -8.0F, -0.288F, 0.0F, 0.0F));
        return LayerDefinition.create(modelData, 48, 96);
    }

    public static ModelPart getMainModel() {
        return getTexturedModelData().bakeRoot().getChild("bb_main");
    }
}
