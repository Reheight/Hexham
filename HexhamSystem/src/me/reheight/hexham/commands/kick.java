package me.reheight.hexham.commands;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.reheight.hexham.Main;
import me.reheight.hexham.events.actions.KickEvent;
import me.reheight.hexham.strings.Languages;
import me.reheight.hexham.utils.Color;

public class kick implements CommandExecutor {
	Main plugin;
	public kick(Main instance) {
		plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			if (args.length == 0) {
				Main.getInstance().getLogger().info("Correct Usage: /kick <player> <reason>");
				return true;
			} else if (args.length == 1) {
				if (Bukkit.getPlayer(args[0]) == null) {
					Main.getInstance().getLogger().info("The player you specified is not online!");
					return true;
				} else if (!Bukkit.getPlayer(args[0]).isOnline()) {
					Main.getInstance().getLogger().info("The player you specified is not online!");
					return true;
				}
				
				Player kickedPlayer = Bukkit.getPlayer(args[0]);
				
				try {
					KickEvent.execute(kickedPlayer, "Unspecified", Bukkit.getConsoleSender());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return true;
			} else {
				if (Bukkit.getPlayer(args[0]) == null) {
					Main.getInstance().getLogger().info("The player you specified is not online!");
					return true;
				} else if (!Bukkit.getPlayer(args[0]).isOnline()) {
					Main.getInstance().getLogger().info("The player you specified is not online!");
					return true;
				}
				
				Player kickedPlayer = Bukkit.getPlayer(args[0]);
				
				StringBuilder kickReason = new StringBuilder();
				for (int i = 1; i < args.length; i++) {
					kickReason.append(args[i]).append(" ");
				}
				String reason = kickReason.toString();
				
				try {
					KickEvent.execute(kickedPlayer, reason, Bukkit.getConsoleSender());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return true;
		}
		
		Player player = (Player)sender;
		if (!player.hasPermission("hexham.kick")) {
			player.sendMessage(Color.color(Languages.insufficientPermissions));
			return true;
		}
		
		if (args.length == 0) {
			player.sendMessage(Color.color(Languages.correctKickFormat));
			return true;
		} else if (args.length == 1) {
			if (Bukkit.getPlayer(args[0]) == null) {
				player.sendMessage(Color.color(Languages.kickedPlayerNotFound.replace("{player}", args[0])));
				return true;
			} else if (!Bukkit.getPlayer(args[0]).isOnline()) {
				player.sendMessage(Color.color(Languages.kickedPlayerNotFound.replace("{player}", args[0])));
				return true;
			} 
			
			Player kickedPlayer = Bukkit.getPlayer(args[0]);
			
			try {
				KickEvent.execute(kickedPlayer, "Unspecified", sender);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			if (Bukkit.getPlayer(args[0]) == null) {
				player.sendMessage(Color.color(Languages.kickedPlayerNotFound.replace("{player}", args[0])));
				return true;
			} else if (!Bukkit.getPlayer(args[0]).isOnline()) {
				player.sendMessage(Color.color(Languages.kickedPlayerNotFound.replace("{player}", args[0])));
				return true;
			}
			
			Player kickedPlayer = Bukkit.getPlayer(args[0]);
			
			StringBuilder kickReason = new StringBuilder();
			for (int i = 1; i < args.length; i++) {
				kickReason.append(args[i]).append(" ");
			}
			String reason = kickReason.toString();
			
			try {
				KickEvent.execute(kickedPlayer, reason, sender);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}
}
