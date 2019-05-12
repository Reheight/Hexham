package me.reheight.hexham.events.actions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.reheight.hexham.Main;
import me.reheight.hexham.events.MySQL;
import me.reheight.hexham.strings.Languages;
import me.reheight.hexham.utils.Color;
import me.reheight.hexham.utils.TimeDifference;

public class BanKickEvent {
	public static void execute(Player player) throws SQLException {
		PreparedStatement statementCheckBanned = MySQL.connection.prepareStatement("SELECT * FROM playerinformation WHERE uuid = ?;");
		statementCheckBanned.setString(1, player.getUniqueId().toString());
		ResultSet checkBanned = statementCheckBanned.executeQuery();
		
		while(checkBanned.next()) {
			if (!(checkBanned.getInt("banned") == 1)) {
				Main.getInstance().getLogger().info(player.getName().toString() + " has passed ban check!");
				return;
			}
		}
		
		PreparedStatement statement = MySQL.connection.prepareStatement("SELECT * FROM playerbans WHERE uuid = ? and datetime = (SELECT MAX(datetime) FROM playerbans);");
		statement.setString(1, player.getUniqueId().toString());
		
		ResultSet retreiveBanInformation = statement.executeQuery();
		
		while (retreiveBanInformation.next()) {
			String actor = retreiveBanInformation.getString("actor");
			
			// Duration Algorithm
			Timestamp timestamp = retreiveBanInformation.getTimestamp("until");
			String remainingTime = TimeDifference.execute(timestamp);
			
			Main.getInstance().getLogger().info(remainingTime);
			
			if (remainingTime.equalsIgnoreCase("Finished")) {
				PreparedStatement unbanStatement = MySQL.connection.prepareStatement("UPDATE playerinformation SET banned = 0 WHERE uuid = ?;");
				unbanStatement.setString(1, player.getUniqueId().toString());
				unbanStatement.executeUpdate();
				
				Main.getInstance().getLogger().info(player.getName() + " has been unbanned due to duration exceeded!");
				return;
			}
			
			String reason = retreiveBanInformation.getString("reason");
			
			String bannedDate = retreiveBanInformation.getTimestamp("datetime").toString();
			
			Bukkit.getPlayer(player.getUniqueId()).kickPlayer(Color.format(Languages.banFormat.replace("{reason}", reason).replace("{actor}", actor).replace("{bannedDate}", bannedDate.toString()).replace("{duration}", remainingTime.toString()).replace("{appeal}", Languages.appealMessage)));
			Main.getInstance().getLogger().info(player.getName() + " has failed the ban check!");
			return;
		}
	}
}
