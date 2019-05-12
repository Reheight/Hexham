package me.reheight.hexham;

import java.sql.SQLException;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.reheight.hexham.checks.TableCheck;
import me.reheight.hexham.commands.ban;
import me.reheight.hexham.commands.kick;
import me.reheight.hexham.configuration.ConfigurationUtil;
import me.reheight.hexham.events.JoinEvent;
import me.reheight.hexham.events.MySQL;
import me.reheight.hexham.strings.Variables;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class Main extends JavaPlugin {
	public static Main instance;
	private static Permission perms = null;
	public static Economy econ = null;
	
	private ConfigurationUtil configurationUtil = new ConfigurationUtil(this);
	private MySQL mysql = new MySQL(this);
	
	private JoinEvent joinEvent = new JoinEvent(this);
	
	private kick kickCommand = new kick(this);
	private ban banCommand = new ban(this);
	
	public static final Main getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		instance = this;
		configurationUtil.checkDirectoryExists();
		configurationUtil.checkKeyExists();
		
		configurationUtil.checkBaseExists();
		configurationUtil.checkLanguageExists();
		configurationUtil.checkMySQLExists();
		
		MySQL.host = Variables.host;
		MySQL.port = Variables.port;
		MySQL.database = Variables.database;
		MySQL.username = Variables.username;
		MySQL.password = Variables.password;
		
		try {
			mysql.openConnection();
		} catch (ClassNotFoundException | SQLException e) {
			getLogger().info("Connection to the Database was not successful!");
			getLogger().info("---- BELOW IS THE STACKTRACE ----");
			e.printStackTrace();
			return;
		}
		
		if (!setupEconomy())
	    {
	      getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", new Object[] { getDescription().getName() }));
	      getServer().getPluginManager().disablePlugin(this);
	      return;
	    }
	    if (!setupPermissions())
	    {
	      getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", new Object[] { getDescription().getName() }));
	      getServer().getPluginManager().disablePlugin(this);
	      return;
	    }
		
		try {
			TableCheck.executeBans();
		} catch (SQLException e) {
			getLogger().info("There was an issue creating the 'playerbans' table!");
			getLogger().info("---- BELOW IS THE STACKTRACE ----");
			e.printStackTrace();
			return;
		}
		
		getServer().getPluginManager().registerEvents(joinEvent, this);
		
		getCommand("kick").setExecutor(kickCommand);
		getCommand("ban").setExecutor(banCommand);
	}
	
	private boolean setupEconomy()
	  {
	    if (getServer().getPluginManager().getPlugin("Vault") == null) {
	      return false;
	    }
	    RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
	    if (rsp == null) {
	      return false;
	    }
	    econ = (Economy)rsp.getProvider();
	    return econ != null;
	  }
	  
	  private boolean setupPermissions()
	  {
	    RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
	    perms = (Permission)rsp.getProvider();
	    return perms != null;
	  }
	  
	  public static Permission getPermissions()
	  {
	    return perms;
	  }
	
	@Override
	public void onDisable() {
		getLogger().info("Hexham has successfully disabled!");
	}
}
