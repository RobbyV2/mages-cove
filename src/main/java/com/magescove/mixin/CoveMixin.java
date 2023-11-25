package com.magescove.mixin;

import com.magescove.CoveMod;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(MinecraftServer.class)
public class CoveMixin {
	private static final Random RANDOM = new Random();
	private static final Text[] MESSAGES = {
			Text.of("You can use /kits to get some free items! This includes a kit where you can reroll your origin for a new random origin!"),
			Text.of("You can put weapons on your back/waist by using G and Shift + G (this may have an interfering keybind)"),
			Text.of("You can check the Guides channel on the discord to see some guides on the more important mods!"),
			Text.of("You can look at the discord to find a list of all the origins, as well as their rankings on the tier list!"),
			Text.of("There is a mob spawner farm with 3 spawners at -262 70 26 that anyone can use to get some XP or string!"),
			Text.of("The world spawn is at exactly 0, 0!")
	};

	@Inject(at = @At("RETURN"), method = "tick")
	private void onServerTick(CallbackInfo ci) {
		// Check if it's been 4 minutes (2400 ticks)
		if (((MinecraftServer) (Object) this).getTicks() % 2400 == 0) {
			sendRandomMessage();
		}
	}

	private void sendRandomMessage() {
		// Get all online players and send a random message to each
		((MinecraftServer) (Object) this).getPlayerManager().getPlayerList().forEach(player -> {
			Text randomMessage = MESSAGES[RANDOM.nextInt(MESSAGES.length)];
			player.sendMessage(randomMessage, false);
		});

		CoveMod.LOGGER.info("Sent random message to all players.");
	}
}
