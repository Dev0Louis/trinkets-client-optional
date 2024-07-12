package dev.louis.trinketsclientoptional.networking;

import net.fabricmc.fabric.api.networking.v1.ServerConfigurationNetworking;
import net.minecraft.network.packet.Packet;
import net.minecraft.server.network.ServerPlayerConfigurationTask;

import java.util.function.Consumer;

public class CheckModPresentsTask implements ServerPlayerConfigurationTask {
    public static final Key KEY = new Key(RequestModConfirmationPayload.ID.id().toString());

    @Override
    public void sendPacket(Consumer<Packet<?>> sender) {
        var packet = new RequestModConfirmationPayload();
        sender.accept(ServerConfigurationNetworking.createS2CPacket(packet));
    }

    @Override
    public Key getKey() {
        return KEY;
    }
}
