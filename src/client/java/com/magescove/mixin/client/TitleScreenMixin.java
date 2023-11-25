package com.magescove.mixin.client;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
    @Shadow @Final @Mutable private static Text COPYRIGHT;

    protected TitleScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("HEAD"))
    protected void removeCopyrightText(CallbackInfo info) {
        COPYRIGHT = Text.of("Mage's Cove Mod");
    }
    @Inject(at = @At("RETURN"), method = "initWidgetsNormal")
    private void customButtonMod(int y, int spacingY, CallbackInfo ci) {
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Mage's Cove Discord"), (button) -> {Util.getOperatingSystem().open("https://discord.gg/PvXN8PH4Fb"); }).dimensions(this.width / 2 - 55, -10, 110, 20).build());
    }

}