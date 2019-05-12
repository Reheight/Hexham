package me.reheight.hexham.configuration;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.reheight.hexham.Main;
import me.reheight.hexham.events.PluginLock;

public class ConfigurationUtil {
	Main plugin;
	
	/* Defining the Files */
	private File baseCFile = null;
	private File languageCFile = null;
	private File keyCFile = null;
	private File mysqlCFile = null;
	
	/* Defining File Configurations */
	public static FileConfiguration configuration = null;
	public static FileConfiguration language = null;
	public static FileConfiguration key = null;
	public static FileConfiguration mysqlC = null;
	
	public ConfigurationUtil(Main instance) {
		plugin = instance;
	}
	
	private PluginLock pluginLock = new PluginLock(plugin);
	
	public void checkDirectoryExists() {
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		} else {
			return;
		}
		
		plugin.getLogger().info("The Hexham directory does not exist, so we created it!");
		return;
	}
	
	public void checkBaseExists() {
		baseCFile = new File(plugin.getDataFolder() + File.separator + "config.yml");
		
		if (!baseCFile.exists()) {
			try {
				baseCFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			configuration = YamlConfiguration.loadConfiguration(baseCFile);
			return;
		}
		
		plugin.getLogger().info("The Hexham configuration file does not exist, so we created it!");
		configuration = YamlConfiguration.loadConfiguration(baseCFile);
		return;
	}
	
	public void checkLanguageExists() {
		languageCFile = new File(plugin.getDataFolder(), "language.yml");
		language = YamlConfiguration.loadConfiguration(languageCFile);
		
		if (!languageCFile.exists()) {
			
			Main.getInstance().saveResource("language.yml", false);
			
			/*try {
				languageCFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}*/
		} /*else {
			language = YamlConfiguration.loadConfiguration(languageCFile);
			return;
		}*/
		
		/*
		List<String> kickFormatList = new ArrayList<String>();
		kickFormatList.add("&d&lCelmic Network");
		kickFormatList.add("&7&oYou have been kicked!");
		kickFormatList.add("");
		kickFormatList.add("&d&l * &7Reason: &d{reason}");
		kickFormatList.add("&d&l * &7Actor: &d{actor}");
		*/
		
		plugin.getLogger().info("The Hexham language file does not exist, so we created it!");
		language = YamlConfiguration.loadConfiguration(languageCFile);
		
		/*
		language.set("prefix", "&d&lCelmic Network &8»");
		language.set("denySelf", "&fYou can not perform this action on yourself!");
		language.set("higherRank", "&fYou can not perform this action on members who are higher power than you!");
		language.set("insufficientPermissions", "&fYou do not have sufficient permissions!");
		language.set("broadcastKick", "&f{kicked} has been kicked by {actor}, for {reason}!");
		language.set("tooLessArguments", "&fYou did not specify the correct arguments!");
		language.set("correctKickFormat", "&f/kick <player> <reason>");
		language.set("kickedPlayerNotFound", "&fThe player {player}, is not online!");
		language.set("appealMessage", "&fYou can appeal on our website at &nhttps://www.celmicnetwork.tk/forums");
		language.set("correctBanFormat", "&f/ban <player> <reason> <duration>");
		language.set("broadcastBan", "&f{banned} has been banned by {actor} for {duration}, by {actor}!");
		
		language.set("kickFormat", "&d&lCelmic Network\" + \"\\n\" + \"&7&oYou have been kicked!\" + \"\\n\" + \"\\n\" + \"&d&l * &7Reason: &d{reason}\" + \"\\n\" + \"&d&l * &7Actor: &d{actor}");
		
		language.set("banFormat", "&d&lCelmic Network\" + \"\\n\" + \"&7&oYou are banned from this server!\" + \"\\n\" + \"\\n\" + \"&d&l * &7Reason: &d{reason}\" + \"\\n\" + \"&d&l * &7Actor: &d{actor}\" + \"\\n\" + \"&d&l * &7Banned on: &d{bannedDate}\" + \"\\n\" + \"&d&l* &7Duration: &d{duration} \" + \"\\n\" + \"{appeal}");
		try {
			language.save(languageCFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		return;
	}
	
	public void checkKeyExists() {
		keyCFile = new File(plugin.getDataFolder() + File.separator + "key.yml");
		
		if (!keyCFile.exists()) {
			try {
				keyCFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			plugin.getLogger().info("The Hexham key file did not exist, so we created it!");
			key = YamlConfiguration.loadConfiguration(keyCFile);
			
			key.set("key", "Replace this with your license key");
			
			try {
				key.save(keyCFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			key = YamlConfiguration.loadConfiguration(keyCFile);
		}
		
		String pluginKey = ConfigurationUtil.key.getString("key");
		
		pluginLock.checkPlugin(pluginKey);
		return;
	}
	
	public void checkMySQLExists() {
		mysqlCFile = new File(plugin.getDataFolder() + File.separator + "mysql.yml");
		
		if (!mysqlCFile.exists()) {
			try {
				mysqlCFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			mysqlC = YamlConfiguration.loadConfiguration(mysqlCFile);
			return;
		}
		
		plugin.getLogger().info("The Hexham mysql file did not exist, so we created it!");
		
		mysqlC = YamlConfiguration.loadConfiguration(mysqlCFile);
		mysqlC.set("Host", "your.host.goes.here");
		mysqlC.set("Port", 3306);
		mysqlC.set("Username", "username-here");
		mysqlC.set("Database", "database-here");
		mysqlC.set("Password", "password-here");
		
		try {
			mysqlC.save(mysqlCFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return;
	}
}
