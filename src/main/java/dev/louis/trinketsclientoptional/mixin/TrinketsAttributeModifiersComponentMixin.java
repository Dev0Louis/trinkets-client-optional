package dev.louis.trinketsclientoptional.mixin;

import dev.emi.trinkets.api.TrinketsAttributeModifiersComponent;
import dev.louis.trinketsclientoptional.TrinketsClientOptional;
import eu.pb4.polymer.core.api.other.PolymerComponent;
import org.spongepowered.asm.mixin.Mixin;
import xyz.nucleoid.packettweaker.PacketContext;

@Mixin(TrinketsAttributeModifiersComponent.class)
public class TrinketsAttributeModifiersComponentMixin implements PolymerComponent {
    @Override
    public boolean canSyncRawToClient(PacketContext context) {
        return TrinketsClientOptional.PLAYERS_WITH_TRINKETS.contains(context.getGameProfile().getId());
    }
}
