package me.reheight.hexham.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateTimeToDate {
	public static String Date(Timestamp timestamp) {
		String returnValue = "Error retreiving action date!";
		
		String pattern = "MM/dd/yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		
		returnValue = dateFormat.format(timestamp);
		
		return returnValue;
	}
}
