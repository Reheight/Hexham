package me.reheight.hexham.events.actions;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.reheight.hexham.Main;
import me.reheight.hexham.events.MySQL;
import me.reheight.hexham.strings.Languages;
import me.reheight.hexham.utils.Color;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class KickEvent {
	public static void execute(Player player, String reason, CommandSender actor) throws SQLException {
		PreparedStatement kickStatement = MySQL.connection.prepareStatement("INSERT INTO playerkicks (uuid, name, datetime, reason, actor) VALUES (?, ?, ?, ?, ?);");
		
		Calendar calendar = Calendar.getInstance();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTimeInMillis());
		
		if (!(actor instanceof Player)) {
			Bukkit.broadcast(Color.color(Languages.broadcastKick.replace("{kicked}", player.getName()).replace("{actor}", actor.getName()).replace("{reason}", reason.trim())), "hexham.broadcast.kick");
			player.kickPlayer(Color.format(Languages.kickFormat.replace("{reason}", reason.trim()).replace("{actor}", actor.getName())));
			
			kickStatement.setString(1, player.getUniqueId().toString());
			kickStatement.setString(2, player.getName());
			kickStatement.setTimestamp(3, timestamp);
			kickStatement.setString(4, reason.trim());
			kickStatement.setString(5, actor.getName());
			kickStatement.executeUpdate();
			return;
		}
		
		Player actorPlayer = (Player)actor;
		
		if (!actor.hasPermission("hexham.kick")) {
			actor.sendMessage(Color.color(Languages.insufficientPermissions));
			return;
		}
		
		String kickedGroup = Main.getPermissions().getPrimaryGroup(player);
		PermissionGroup kickedRank = PermissionsEx.getPermissionManager().getGroup(kickedGroup);
		int kickedIncrament = kickedRank.getRank();
		
		String actorGroup = Main.getPermissions().getPrimaryGroup(actorPlayer);
		PermissionGroup actorRank = PermissionsEx.getPermissionManager().getGroup(actorGroup);
		int actorIncrament = actorRank.getRank();
		
		if (player == actor) {
			actor.sendMessage(Color.color(Languages.denySelf));
			return;
		}
		
		if (kickedIncrament >= actorIncrament) {
			actor.sendMessage(Color.color(Languages.higherRank));
			return;
		}
		
		Bukkit.broadcast(Color.color(Languages.broadcastKick.replace("{kicked}", player.getName()).replace("{actor}", actor.getName()).replace("{reason}", reason.trim())), "hexham.broadcast.kick");
		player.kickPlayer(Color.format(Languages.kickFormat.replace("{reason}", reason.trim()).replace("{actor}", actor.getName())));
		
		kickStatement.setString(1, player.getUniqueId().toString());
		kickStatement.setString(2, player.getName());
		kickStatement.setTimestamp(3, timestamp);
		kickStatement.setString(4, reason);
		kickStatement.setString(5, actor.getName());
		kickStatement.executeUpdate();
	}
}
