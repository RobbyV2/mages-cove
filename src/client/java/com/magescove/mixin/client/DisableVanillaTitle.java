package com.magescove.mixin.client;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public final class DisableVanillaTitle {
    private void updateTitle(final CallbackInfo info) {
        info.cancel();
    }
}
