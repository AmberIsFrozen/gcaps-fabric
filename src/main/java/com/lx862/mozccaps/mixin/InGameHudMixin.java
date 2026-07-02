package com.lx862.mozccaps.mixin;

import com.lx862.mozccaps.MainClient;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Hud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Hud.class)
public class InGameHudMixin {
    @Inject(method = "extractCrosshair", at = @At("HEAD"), cancellable = true)
    public void cancelCrossHair(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if(MainClient.getInput().inputEnabled()) ci.cancel();
    }
}
