package com.example;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
public final class BedmonsterNetworking {
    public static final String MOD_ID = "bedmonster";
    private BedmonsterNetworking() {}
    public record ServerMarkerPayload() implements CustomPacketPayload {
        public static final ServerMarkerPayload INSTANCE = new ServerMarkerPayload();
        public static final Type<ServerMarkerPayload> ID = new Type<>(Identifier.fromNamespaceAndPath(MOD_ID, "server_marker"));
        public static final StreamCodec<RegistryFriendlyByteBuf, ServerMarkerPayload> CODEC = StreamCodec.unit(INSTANCE);
        @Override public Type<? extends CustomPacketPayload> type() { return ID; }
    }
}