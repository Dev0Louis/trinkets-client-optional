package dev.louis.trinketsclientoptional.mixin;

import dev.emi.trinkets.api.LivingEntityTrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import dev.louis.trinketsclientoptional.TrinketsClientOptional;
import net.minecraft.server.network.ServerPlayerEntity;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LivingEntityTrinketComponent.class)
public abstract class LivingEntityTrinketComponentMixin implements AutoSyncedComponent {

    @Override
    public boolean shouldSyncWith(ServerPlayerEntity player) {
        return TrinketsClientOptional.PLAYERS_WITH_TRINKETS.contains(player.getUuid());
    }
}
