package com.example.client;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import com.example.BedmonsterNetworking;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.chat.Component;
public class BedmonsterClient implements ClientModInitializer {
    private static volatile boolean serverHasBedmonster;
    @Override public void onInitializeClient() {
        ClientPlayConnectionEvents.INIT.register((handler, client) -> serverHasBedmonster = false);
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> serverHasBedmonster = false);
        ClientPlayNetworking.registerGlobalReceiver(BedmonsterNetworking.ServerMarkerPayload.ID, (payload, context) -> serverHasBedmonster = true);
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            if (client.isSingleplayer() || client.isLocalServer()) return;
            CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS).execute(() -> client.execute(() -> {
                if (!serverHasBedmonster && client.getConnection() == handler) {
                    handler.getConnection().disconnect(Component.literal("BedMonster is not installed on this server. Client-side BedMonster requires a BedMonster server."));
                }
            }));
        });
    }
}