package com.magescove.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Icons;
import net.minecraft.resource.InputSupplier;
import net.minecraft.resource.ResourcePack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.io.InputStream;

@Mixin(Icons.class)
public abstract class IconsMixin {
    @Inject(method = "getIcon(Lnet/minecraft/resource/ResourcePack;Ljava/lang/String;)Lnet/minecraft/resource/InputSupplier;", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private void GetIcon(ResourcePack resourcePack, String string, CallbackInfoReturnable<InputSupplier<InputStream>> cir, String[] strings, InputSupplier<InputStream> inputSupplier) {
        if(strings.length == 2) {
            cir.setReturnValue(() -> MinecraftClient.getInstance().getResourceManager().getResourceOrThrow(new Identifier("magescove", string)).getInputStream());
        }
    }
}
