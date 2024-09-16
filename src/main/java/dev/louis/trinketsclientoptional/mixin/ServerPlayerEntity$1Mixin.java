package dev.louis.trinketsclientoptional.mixin;

import dev.louis.trinketsclientoptional.TrinketsClientOptional;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.InventoryS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(targets = "net/minecraft/server/network/ServerPlayerEntity$1")
public class ServerPlayerEntity$1Mixin {

    @Shadow @Final private ServerPlayerEntity field_29182;

    @ModifyArg(
            method = "updateState",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;sendPacket(Lnet/minecraft/network/packet/Packet;)V")
    )
    private Packet<?> a(Packet<?> packet) {

        if (TrinketsClientOptional.PLAYERS_WITH_TRINKETS.contains(this.field_29182.getUuid())) return packet;
        InventoryS2CPacket oldInvPacket = (InventoryS2CPacket) packet;
        var oldContents = oldInvPacket.getContents();

        int transmittedSize = Math.min(46, oldContents.size());
        DefaultedList<ItemStack> newContents = DefaultedList.ofSize(transmittedSize, ItemStack.EMPTY);
        for (int i = 0; i < transmittedSize; i++) {
            newContents.set(i, oldContents.get(i));
        }

        return new InventoryS2CPacket(
                oldInvPacket.getSyncId(),
                oldInvPacket.getRevision(),
                newContents,
                oldInvPacket.getCursorStack()
        );
    }
}
