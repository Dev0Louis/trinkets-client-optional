package dev.louis.trinketsclientoptional.client;

import dev.louis.trinketsclientoptional.networking.ModConfirmationPayload;
import dev.louis.trinketsclientoptional.networking.RequestModConfirmationPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientConfigurationNetworking;

public class TrinketsClientOptionalClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientConfigurationNetworking.registerGlobalReceiver(RequestModConfirmationPayload.ID, (payload, context) -> {
            context.responseSender().sendPacket(new ModConfirmationPayload());
        });
    }
}
