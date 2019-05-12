package me.reheight.hexham.events;

import java.sql.SQLException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.reheight.hexham.Main;
import me.reheight.hexham.checks.PlayerInformation;
import me.reheight.hexham.events.actions.BanKickEvent;

public class JoinEvent implements Listener {
	Main plugin;
	public JoinEvent(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onJoinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		event.setJoinMessage("");
		
		try {
			PlayerInformation.update(player);
		} catch (SQLException e) {
			Main.getInstance().getLogger().info("There was an issue inserting/updating the 'playerbans' table!");
			Main.getInstance().getLogger().info("---- BELOW IS THE STACKTRACE ----");
			e.printStackTrace();
		}
		
		try {
			BanKickEvent.execute(player);
		} catch (SQLException e) {
			Main.getInstance().getLogger().info("There was an issue retreiving date from the 'playerbans' tables!");
			Main.getInstance().getLogger().info("---- BELOW IS THE STACKTRACE ----");
			e.printStackTrace();
		}
	}
}
