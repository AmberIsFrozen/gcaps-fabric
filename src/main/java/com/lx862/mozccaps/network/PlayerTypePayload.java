package com.lx862.mozccaps.network;

import com.lx862.mozccaps.Main;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record PlayerTypePayload(String playerName) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<PlayerTypePayload> PACKET_ID = new CustomPacketPayload.Type<>(Main.id("player_typed"));
    public static final StreamCodec<RegistryFriendlyByteBuf, PlayerTypePayload> PACKET_CODEC = StreamCodec.composite(ByteBufCodecs.STRING_UTF8, PlayerTypePayload::playerName, PlayerTypePayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return PACKET_ID;
    }

    public String getPlayerName() {
        return playerName;
    }
}
