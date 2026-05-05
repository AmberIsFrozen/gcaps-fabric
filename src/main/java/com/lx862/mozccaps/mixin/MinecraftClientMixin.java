package com.lx862.mozccaps.mixin;

import com.lx862.mozccaps.MainClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.player.LocalPlayer;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class MinecraftClientMixin {

    @Shadow @Final public Gui gui;

    @Shadow
    @Nullable
    public LocalPlayer player;

    @Inject(method = "startAttack", at = @At("HEAD"), cancellable = true)
    public void cancelBlockInteraction(CallbackInfoReturnable<Boolean> cir) {
        if(MainClient.capEquipped(player) && MainClient.getInput().inputEnabled()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "continueAttack", at = @At("HEAD"), cancellable = true)
    public void cancelBlockBreaking(boolean breaking, CallbackInfo ci) {
        if(MainClient.capEquipped(player) && MainClient.getInput().inputEnabled()) {
            ci.cancel();
        }
    }

    @Inject(method = "handleKeybinds", at = @At(value = "HEAD"))
    public void moveCapsContentToChat(CallbackInfo ci) {
        if(MainClient.capEquipped(player) && MainClient.getInput().inputEnabled()) {
            gui.getChat().saveAsDraft(MainClient.getInput().getInputted());
        }
    }
}
