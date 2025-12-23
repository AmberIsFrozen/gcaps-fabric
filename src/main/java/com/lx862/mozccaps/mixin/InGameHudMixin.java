package com.lx862.mozccaps.mixin;

import com.lx862.mozccaps.MainClient;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class InGameHudMixin {
    @Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
    public void cancelCrossHair(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci) {
        if(MainClient.getAtamaInput().inputEnabled()) ci.cancel();
    }
}
