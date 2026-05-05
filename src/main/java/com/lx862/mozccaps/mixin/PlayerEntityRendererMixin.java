package com.lx862.mozccaps.mixin;

import com.lx862.mozccaps.render.PlayerNameStorage;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.entity.Avatar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AvatarRenderer.class)
public class PlayerEntityRendererMixin {
    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/Avatar;Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;F)V", at = @At("TAIL"))
    public void update(Avatar entity, AvatarRenderState state, float partialTicks, CallbackInfo ci) {
        if(entity instanceof AbstractClientPlayer abstractClientPlayerEntity) {
            ((PlayerNameStorage)state).gcaps$setPlayerName(abstractClientPlayerEntity.getGameProfile().name());
        }
    }
}
