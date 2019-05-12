package me.reheight.hexham.checks;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import org.bukkit.entity.Player;

import me.reheight.hexham.events.MySQL;

public class PlayerInformation {
	public static void update(Player player) throws SQLException {
		PreparedStatement statement = MySQL.connection.prepareStatement("INSERT INTO playerinformation (uuid, name, joindate, recentdate, banned, muted, investigation) VALUES (?, ?, ?, ?, 0, 0, 0) ON DUPLICATE KEY UPDATE recentdate = ?;");
		
		String playerUUID = player.getUniqueId().toString();
		String playerName = player.getName().toString();
		
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		Date date = new Date(currentDate.getTime());
		
		statement.setString(1, playerUUID);
		statement.setString(2, playerName);
		statement.setDate(3, date);
		statement.setDate(4, date);
		statement.setDate(5, date);
		statement.executeUpdate();
	}
}
