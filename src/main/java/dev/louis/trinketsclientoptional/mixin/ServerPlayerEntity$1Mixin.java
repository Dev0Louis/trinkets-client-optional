package dev.louis.trinketsclientoptional.mixin;

import dev.louis.trinketsclientoptional.TrinketsClientOptional;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(targets = "net/minecraft/server/network/ServerPlayerEntity$1")
public class ServerPlayerEntity$1Mixin {

    @Shadow @Final private ServerPlayerEntity field_29182;

    @ModifyVariable(
            method = "updateState",
            at = @At(value = "STORE", ordinal = 0)
    )
    public DefaultedList<ItemStack> a(DefaultedList<ItemStack> contents) {
        if (TrinketsClientOptional.PLAYERS_WITH_TRINKETS.contains(this.field_29182.getUuid())) return contents;
        DefaultedList<ItemStack> list = DefaultedList.ofSize(46);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, contents.get(i));
        }
        return contents;
    }
}
