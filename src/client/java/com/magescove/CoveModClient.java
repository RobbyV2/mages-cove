package com.magescove;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.client.MinecraftClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.option.ServerList;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CoveModClient implements ClientModInitializer {

	public void continuousTitleUpdate() {
		String newTitle = "Mage's Cove 1.20.1";

		while (true) {
			MinecraftClient.getInstance().execute(() -> MinecraftClient.getInstance().getWindow().setTitle(newTitle));

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onInitializeClient() {
		new Thread(this::continuousTitleUpdate).start();
		FabricLoader.getInstance().getModContainer("magescove").ifPresent(modContainer -> {
			ResourceManagerHelper.registerBuiltinResourcePack(new Identifier("magescove:magescoveresources"),  modContainer, ResourcePackActivationType.ALWAYS_ENABLED);
		});
		ServerList serverList = new ServerList(MinecraftClient.getInstance());
		ServerInfo server = new ServerInfo("Mage's Cove", "cove.robby.blue", false);
		if (inList(server, serverList)) {
		} else {
			serverList.add(server,false);
			serverList.saveFile();
		}
	}

	public static Boolean inList(ServerInfo server, ServerList list) {
		if (list == null) {
			return false;
		}
		int i = 0;
		while (i < list.size()) {
			ServerInfo data = list.get(i);
			if (data.address != null &&
					data.address.equalsIgnoreCase(server.address) &&
					data.name != null &&
					data.name.equalsIgnoreCase(server.name)
			) {
				return true;
			}
			++i;
		}
		return false;
	}
	public static final Logger LOGGER = LoggerFactory.getLogger("Mage's Cove");

	public void log(String message)  {
	}
}

