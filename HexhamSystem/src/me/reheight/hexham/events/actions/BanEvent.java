package me.reheight.hexham.events.actions;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.reheight.hexham.Main;
import me.reheight.hexham.events.MySQL;
import me.reheight.hexham.strings.Languages;
import me.reheight.hexham.utils.Color;
import me.reheight.hexham.utils.TimeConversion;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class BanEvent {
	public static void execute(Player player, CommandSender actor, String reason, String[] duration) throws SQLException {
		PreparedStatement statementBan = MySQL.connection.prepareStatement("INSERT INTO playerbans (uuid, name, datetime, actor, reason, until) VALUES (?, ?, ?, ?, ?, ?);");
		
		PreparedStatement playerInfo = MySQL.connection.prepareStatement("UPDATE playerinformation SET banned = 1 WHERE uuid = ?;");
		
		Timestamp currentTimestamp = new Timestamp(new Date().getTime());
		
		if (!(actor instanceof Player)) {
			statementBan.setString(1, player.getUniqueId().toString());
			statementBan.setString(2, player.getName().toString());
			statementBan.setTimestamp(3, currentTimestamp);
			statementBan.setString(4, "CONSOLE");
			statementBan.setString(5, reason);
			statementBan.setTimestamp(6, TimeConversion.conversion(duration));
			
			statementBan.execute();
			
			Bukkit.broadcast(Color.color(Languages.broadcastBan), "hexham.notify.ban");
			
			playerInfo.setString(1, player.getUniqueId().toString());
			playerInfo.execute();
			
			BanKickEvent.execute(player);
			return;
		}
		
		Player actorPlayer = (Player)actor;
		
		if (!actorPlayer.hasPermission("hexham.ban")) {
			actorPlayer.sendMessage(Color.color(Languages.insufficientPermissions));
			return;
		}
		
		if (actorPlayer == player) {
			actorPlayer.sendMessage(Color.color(Languages.denySelf));
			return;
		}
		
		String kickedGroup = Main.getPermissions().getPrimaryGroup(player);
		PermissionGroup kickedRank = PermissionsEx.getPermissionManager().getGroup(kickedGroup);
		int kickedIncrament = kickedRank.getRank();
		
		String actorGroup = Main.getPermissions().getPrimaryGroup(actorPlayer);
		PermissionGroup actorRank = PermissionsEx.getPermissionManager().getGroup(actorGroup);
		int actorIncrament = actorRank.getRank();
		
		if (kickedIncrament > actorIncrament) {
			actorPlayer.sendMessage(Color.color(Languages.higherRank));
			return;
		}
		
		Timestamp currentTime = new Timestamp(new Date().getTime());
		
		statementBan.setString(1, player.getUniqueId().toString());
		statementBan.setString(2, player.getName());
		statementBan.setTimestamp(3, currentTime);
		statementBan.setString(4, actor.getName());
		statementBan.setString(5, reason);
		statementBan.setTimestamp(6, TimeConversion.conversion(duration));
		
		Calendar calendar = Calendar.getInstance();
		Timestamp errorStamp = new Timestamp(new Date().getTime());
		calendar.set(Calendar.YEAR, Calendar.YEAR + 5);
		errorStamp = new Timestamp(calendar.getTime().getTime());
		
		if (TimeConversion.conversion(duration) == errorStamp) {
			actorPlayer.sendMessage(Color.format(Languages.banError));
			return;
		}
		
		playerInfo.setString(1, player.getUniqueId().toString());
		playerInfo.execute();
		
		statementBan.execute();
		
		Bukkit.broadcast(Color.color(Languages.broadcastBan.replace("{banned}", player.getName().toString()).replace("{reason}", reason).replace("{duration}", TimeConversion.conversion(duration).toString()).replace("{actor}", actor.getName())), "hexham.notify.ban");
		
		BanKickEvent.execute(player);
		return;
	}
}
