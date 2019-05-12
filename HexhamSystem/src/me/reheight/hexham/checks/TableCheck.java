package me.reheight.hexham.checks;

import java.sql.SQLException;
import java.sql.Statement;

import me.reheight.hexham.events.MySQL;

public class TableCheck {
	public static void executeBans() throws SQLException {
		Statement statement = MySQL.connection.createStatement();
		
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS playerbans (uuid TEXT, name TEXT, datetime DATETIME, actor TEXT, reason TEXT, until DATETIME);");
		
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS playerkicks (uuid TEXT, name TEXT, datetime DATETIME, reason TEXT, actor TEXT);");
		
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS playermutes (uuid TEXT, name TEXT, datetime DATETIME, actor TEXT, reason TEXT, until DATETIME);");
		
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS playerwarnings (uuid TEXT, name TEXT, datetime DATETIME, actor TEXT, reason TEXT);");
		
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS playernotes (uuid TEXT, name TEXT, datetime DATETIME, actor TEXT, note TEXT);");
		
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS playerinvestigation (uuid TEXT, name TEXT, datetime DATETIME, actor TEXT, reason TEXT, until DATETIME);");
		
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS playerinformation (uuid TEXT, name TEXT, joindate DATE, recentdate DATE, banned INT, muted INT, investigation INT, PRIMARY KEY(uuid(36)));");
	}
}
