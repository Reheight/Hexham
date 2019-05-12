package me.reheight.hexham.strings;

import me.reheight.hexham.configuration.ConfigurationUtil;

public class Variables {
	/* MySQL */
	public static String host = ConfigurationUtil.mysqlC.getString("Host");
	public static Integer port = ConfigurationUtil.mysqlC.getInt("Port");
	public static String username = ConfigurationUtil.mysqlC.getString("Username");
	public static String database = ConfigurationUtil.mysqlC.getString("Database");
	public static String password = ConfigurationUtil.mysqlC.getString("Password");
}
