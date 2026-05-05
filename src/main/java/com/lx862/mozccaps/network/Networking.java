package com.lx862.mozccaps.network;

import com.lx862.mozccaps.render.CapArmorRenderer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.world.entity.player.Player;

public class Networking {
    public static void registerClient() {
        ClientPlayNetworking.registerGlobalReceiver(UpdatePlayerTypePayload.PACKET_ID, (payload, context) -> {
            String playerTyped = payload.getPlayerName();
            CapArmorRenderer.startPlayerTypedAnimation(playerTyped);
        });
    }

    public static void registerServer() {
        PayloadTypeRegistry.serverboundPlay().register(PlayerTypePayload.PACKET_ID, PlayerTypePayload.PACKET_CODEC);
        PayloadTypeRegistry.clientboundPlay().register(UpdatePlayerTypePayload.PACKET_ID, UpdatePlayerTypePayload.PACKET_CODEC);

        ServerPlayNetworking.registerGlobalReceiver(PlayerTypePayload.PACKET_ID, (payload, context) -> {
            String playerId = payload.getPlayerName();
            context.server().getPlayerList().getPlayers().forEach(player -> {
                ServerPlayNetworking.send(player, new UpdatePlayerTypePayload(playerId));
            });
        });
    }

    public static void sendKeyPressedClient(Player player) {
        ClientPlayNetworking.send(new PlayerTypePayload(player.getName().getString()));
    }
}
