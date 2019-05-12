package me.reheight.hexham.events;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import me.reheight.hexham.Main;

public class MySQL {
	Main plugin;
	public MySQL(Main instance) {
		plugin = instance;
	}
	
	public static Connection connection;
	public static String host, database, username, password;
	public static int port;
	
	public void openConnection() throws SQLException, ClassNotFoundException {
		synchronized (plugin) {
			if(connection != null && !connection.isClosed()) {
				return;
			}
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + MySQL.host + ":" + MySQL.port + "/" + MySQL.database, MySQL.username, MySQL.password);
		}
	}
}
