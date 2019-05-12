package me.reheight.hexham.commands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.reheight.hexham.Main;
import me.reheight.hexham.events.MySQL;
import me.reheight.hexham.events.actions.BanEvent;

public class ban implements CommandExecutor {
	Main plugin;
	public ban(Main instance) {
		plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		PreparedStatement checkExists = null;
		
		try {
			checkExists = MySQL.connection.prepareStatement("SELECT COUNT(*) FROM playerinformation WHERE uuid = ?;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (!(sender instanceof Player)) {
			if (args.length == 0) {
				Main.getInstance().getLogger().info("Correct Usage: /ban <player> <reason> <duration>");
				return true;
			} else if (args.length == 1) {
				if (!Bukkit.getPlayer(args[0]).hasPlayedBefore()) {
					Main.getInstance().getLogger().info("Player is not in our database!");
					return true;
				}
				
				Player bannedPlayer = Bukkit.getPlayer(args[0]);
				
				String UUID = bannedPlayer.getUniqueId().toString();
				
				try {
					checkExists.setString(1, UUID);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				ResultSet resultCheck = null;
				
				int numberOfRows = 0;
				
				try {
					resultCheck = checkExists.executeQuery();
					resultCheck.first();
					numberOfRows = resultCheck.getInt("COUNT(*)");
				} catch (SQLException e) {
					Main.getInstance().getLogger().info("There wa an error while retrieving player information!");
					numberOfRows = 0;
					return true;
				}
				
				if (numberOfRows != 1) {
					Main.getInstance().getLogger().info("Player is not in our database!");
					return true;
				}
				
				String[] banDuration = {"20", "years"};
				
				try {
					BanEvent.execute(bannedPlayer, sender, "Unspecified", banDuration);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return true;
			}
		}
		return true;
	}
}
