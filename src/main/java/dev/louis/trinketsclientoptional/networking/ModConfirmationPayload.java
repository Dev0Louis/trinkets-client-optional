package dev.louis.trinketsclientoptional.networking;

import dev.louis.trinketsclientoptional.TrinketsClientOptional;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ModConfirmationPayload() implements CustomPayload {
    public static final CustomPayload.Id<ModConfirmationPayload> ID = new CustomPayload.Id<>(Identifier.of(TrinketsClientOptional.MOD_ID, "mod_conformation"));
    public static final PacketCodec<PacketByteBuf, ModConfirmationPayload> CODEC = PacketCodec.ofStatic(((buf, value) -> {}), (buf -> new ModConfirmationPayload()));

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
