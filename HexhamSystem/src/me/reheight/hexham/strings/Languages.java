package me.reheight.hexham.strings;

import me.reheight.hexham.configuration.ConfigurationUtil;

public class Languages {
	public static String prefix = ConfigurationUtil.language.getString("prefix");
	public static String denySelf = ConfigurationUtil.language.getString("denySelf");
	public static String higherRank = ConfigurationUtil.language.getString("higherRank");
	public static String insufficientPermissions = ConfigurationUtil.language.getString("insufficientPermissions");
	public static String broadcastKick = ConfigurationUtil.language.getString("broadcastKick");
	public static String tooLessArguments = ConfigurationUtil.language.getString("tooLessArguments");
	public static String correctKickFormat = ConfigurationUtil.language.getString("correctKickFormat");
	public static String kickedPlayerNotFound = ConfigurationUtil.language.getString("kickedPlayerNotFound");
	public static String appealMessage = ConfigurationUtil.language.getString("appealMessage");
	public static String banCorrectFormat = ConfigurationUtil.language.getString("banCorrectFormat");
	public static String broadcastBan = ConfigurationUtil.language.getString("broadcastBan");
	public static String banFormat = ConfigurationUtil.language.getString("banFormat");
	public static String kickFormat = ConfigurationUtil.language.getString("kickFormat");
	public static String banError = ConfigurationUtil.language.getString("banError");
}
