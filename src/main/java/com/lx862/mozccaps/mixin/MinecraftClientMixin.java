package com.lx862.mozccaps.mixin;

import com.lx862.mozccaps.MainClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

    @Shadow @Final public InGameHud inGameHud;

    @Shadow
    @Nullable
    public ClientPlayerEntity player;

    @Inject(method = "doAttack", at = @At("HEAD"), cancellable = true)
    public void cancelBlockInteraction(CallbackInfoReturnable<Boolean> cir) {
        if(MainClient.capEquipped(player) && MainClient.getAtamaInput().inputEnabled()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "handleBlockBreaking", at = @At("HEAD"), cancellable = true)
    public void cancelBlockBreaking(boolean breaking, CallbackInfo ci) {
        if(MainClient.capEquipped(player) && MainClient.getAtamaInput().inputEnabled()) {
            ci.cancel();
        }
    }

    @Inject(method = "handleInputEvents", at = @At(value = "HEAD"))
    public void moveCapsContentToChat(CallbackInfo ci) {
        if(MainClient.capEquipped(player) && MainClient.getAtamaInput().inputEnabled()) {
            inGameHud.getChatHud().saveDraft(MainClient.getAtamaInput().getInputted());
        }
    }
}
