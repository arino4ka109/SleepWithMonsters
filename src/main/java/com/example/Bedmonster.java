package com.example;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
public class Bedmonster implements ModInitializer {
    @Override public void onInitialize() {
        PayloadTypeRegistry.clientboundPlay().register(BedmonsterNetworking.ServerMarkerPayload.ID, BedmonsterNetworking.ServerMarkerPayload.CODEC);
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            if (ServerPlayNetworking.canSend(handler.player, BedmonsterNetworking.ServerMarkerPayload.ID)) {
                ServerPlayNetworking.send(handler.player, BedmonsterNetworking.ServerMarkerPayload.INSTANCE);
            }
        });
    }
}