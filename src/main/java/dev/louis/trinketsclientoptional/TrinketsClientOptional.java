package dev.louis.trinketsclientoptional;

import dev.emi.trinkets.api.TrinketsAttributeModifiersComponent;
import dev.louis.trinketsclientoptional.networking.CheckModPresentsTask;
import dev.louis.trinketsclientoptional.networking.ModConfirmationPayload;
import dev.louis.trinketsclientoptional.networking.RequestModConfirmationPayload;
import eu.pb4.polymer.core.api.other.PolymerComponent;
import eu.pb4.polymer.core.impl.other.PolymerComponentImpl;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerConfigurationConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerConfigurationNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.component.ComponentType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TrinketsClientOptional implements ModInitializer {
    public static final String MOD_ID = "trinkets-client-optional";
    public static List<UUID> PLAYERS_WITH_TRINKETS = new ArrayList<>();

    @Override
    public void onInitialize() {
        PayloadTypeRegistry.configurationS2C().register(RequestModConfirmationPayload.ID, RequestModConfirmationPayload.CODEC);
        PayloadTypeRegistry.configurationC2S().register(ModConfirmationPayload.ID, ModConfirmationPayload.CODEC);

        ServerConfigurationConnectionEvents.CONFIGURE.register((handler, server) -> {
            if (ServerConfigurationNetworking.canSend(handler, RequestModConfirmationPayload.ID)) {
                handler.addTask(new CheckModPresentsTask());
            }
        });

        ServerConfigurationNetworking.registerGlobalReceiver(ModConfirmationPayload.ID, ((payload, context) -> {
            PLAYERS_WITH_TRINKETS.add(context.networkHandler().getDebugProfile().getId());

            context.networkHandler().completeTask(CheckModPresentsTask.KEY);
        }));

        ServerConfigurationConnectionEvents.DISCONNECT.register((handler, server) -> {
            PLAYERS_WITH_TRINKETS.remove(handler.getDebugProfile().getId());
        });

        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            PLAYERS_WITH_TRINKETS.remove(handler.getPlayer().getUuid());
        });

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            PLAYERS_WITH_TRINKETS.add(handler.getPlayer().getUuid());
        });

        PolymerComponent.registerDataComponent(TrinketsAttributeModifiersComponent.TYPE);
    }
}
