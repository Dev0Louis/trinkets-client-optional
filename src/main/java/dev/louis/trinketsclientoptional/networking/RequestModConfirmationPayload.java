package dev.louis.trinketsclientoptional.networking;

import dev.emi.trinkets.TrinketsNetwork;
import dev.louis.trinketsclientoptional.TrinketsClientOptional;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record RequestModConfirmationPayload() implements CustomPayload {
    public static final CustomPayload.Id<RequestModConfirmationPayload> ID = new CustomPayload.Id<>(Identifier.of(TrinketsClientOptional.MOD_ID, "request_mod_conformation"));
    public static final PacketCodec<PacketByteBuf, RequestModConfirmationPayload> CODEC = PacketCodec.ofStatic(((buf, value) -> {}), (buf -> new RequestModConfirmationPayload()));

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
