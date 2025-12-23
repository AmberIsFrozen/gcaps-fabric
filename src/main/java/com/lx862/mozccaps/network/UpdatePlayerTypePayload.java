package com.lx862.mozccaps.network;

import com.lx862.mozccaps.Main;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record UpdatePlayerTypePayload(String playerName) implements CustomPacketPayload {
    public static final Type<UpdatePlayerTypePayload> PACKET_ID = new Type<>(Main.id("update_player_typed"));
    public static final StreamCodec<RegistryFriendlyByteBuf, UpdatePlayerTypePayload> PACKET_CODEC = StreamCodec.composite(ByteBufCodecs.STRING_UTF8, UpdatePlayerTypePayload::playerName, UpdatePlayerTypePayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return PACKET_ID;
    }

    public String getPlayerName() {
        return playerName;
    }
}
