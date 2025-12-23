package com.lx862.mozccaps.armor;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ChinModel {

    /* Code generated from Blockbench */
    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition bb_main = modelPartData.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(42, 10).addBox(-7.0F, -13.0F, -1.5F, 0.0F, 13.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(42, 10).addBox(7.0F, -13.0F, -1.5F, 0.0F, 13.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(-3, 50).addBox(-7.0F, 0.1F, -1.5F, 14.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        return LayerDefinition.create(modelData, 48, 96);
    }

    public static ModelPart getMainModel() {
        return getTexturedModelData().bakeRoot().getChild("bb_main");
    }
}
