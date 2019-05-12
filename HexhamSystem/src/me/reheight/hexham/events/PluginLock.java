package me.reheight.hexham.events;

import me.reheight.hexham.Main;

public class PluginLock {
	Main plugin;
	public PluginLock(Main instance) {
		plugin = instance;
	}
	
	public void checkPlugin(String key) {
		if (!key.equals("3xC3pT10n#98")) {
			Main.getInstance().getLogger().info("[Anti-Piracy] Key check was unnsuccessful, or there was an error!");
			Main.getInstance().getServer().shutdown();
			return;
		}
		
		Main.getInstance().getLogger().info("Key check was successful, plugin is enabled!");
		return;
	}
}
