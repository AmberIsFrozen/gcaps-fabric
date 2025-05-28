package com.lx862.mozccaps.network;

import com.lx862.mozccaps.Main;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record PlayerTypePayload(String playerName) implements CustomPayload {
    public static final CustomPayload.Id<PlayerTypePayload> PACKET_ID = new CustomPayload.Id<>(Main.id("player_typed"));
    public static final PacketCodec<RegistryByteBuf, PlayerTypePayload> PACKET_CODEC = PacketCodec.tuple(PacketCodecs.STRING, PlayerTypePayload::playerName, PlayerTypePayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }

    public String getPlayerName() {
        return playerName;
    }
}
