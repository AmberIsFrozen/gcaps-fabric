package com.lx862.mozccaps.mixin;

import com.lx862.mozccaps.render.PlayerNameStorage;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(AvatarRenderState.class)
public class PlayerEntityRenderStateMixin implements PlayerNameStorage {
    @Unique
    public String gcaps$playerName;

    @Override
    public String gcaps$getPlayerName() {
        return gcaps$playerName;
    }

    @Override
    public void gcaps$setPlayerName(String str) {
        gcaps$playerName = str;
    }
}
