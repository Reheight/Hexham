package me.reheight.hexham.utils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class TimeConversion {
	public static Timestamp conversion(String[] variables) {
		Timestamp returnValue = null;
		
		Timestamp currentTimestamp = new Timestamp(new Date().getTime());
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(currentTimestamp.getTime());
		
		
		if (variables.length > 2) {
			calendar.set(Calendar.YEAR, Calendar.YEAR + 5);
			currentTimestamp = new Timestamp(calendar.getTime().getTime());
			returnValue = currentTimestamp;
			
			return returnValue;
		} else if (variables.length == 1) {
			int seconds = 0;
			try {
				seconds = Integer.parseInt(variables[0]);
			} catch (NumberFormatException e) {
				calendar.set(Calendar.YEAR, Calendar.YEAR + 5);
				currentTimestamp = new Timestamp(calendar.getTime().getTime());
				returnValue = currentTimestamp;
				
				return returnValue;
			}
			
			calendar.add(Calendar.SECOND, seconds);
			currentTimestamp = new Timestamp(calendar.getTime().getTime());
			returnValue = currentTimestamp;
			
			return returnValue;
		} else if (variables.length == 2) {
			if (variables[1].equalsIgnoreCase("seconds")) {
				int units = 0;
				try {
					units = Integer.parseInt(variables[0]);
				} catch (NumberFormatException e) {
					calendar.set(Calendar.YEAR, Calendar.YEAR + 5);
					currentTimestamp = new Timestamp(calendar.getTime().getTime());
					returnValue = currentTimestamp;
					
					return returnValue;
				}
				
				calendar.add(Calendar.SECOND, units);
				currentTimestamp = new Timestamp(calendar.getTime().getTime());
				returnValue = currentTimestamp;
				return returnValue;
			} else if (variables[1].equalsIgnoreCase("minutes")) {
				int units = 0;
				try {
					units = Integer.parseInt(variables[0]);
				} catch (NumberFormatException e) {
					calendar.set(Calendar.YEAR, Calendar.YEAR + 5);
					currentTimestamp = new Timestamp(calendar.getTime().getTime());
					returnValue = currentTimestamp;
					
					return returnValue;
				}
				
				calendar.add(Calendar.MINUTE, units);
				currentTimestamp = new Timestamp(calendar.getTime().getTime());
				returnValue = currentTimestamp;
				return returnValue;
			} else if (variables[1].equalsIgnoreCase("hours")) {
				int units = 0;
				try {
					units = Integer.parseInt(variables[0]);
				} catch (NumberFormatException e) {
					calendar.set(Calendar.YEAR, Calendar.YEAR + 5);
					currentTimestamp = new Timestamp(calendar.getTime().getTime());
					returnValue = currentTimestamp;
					
					return returnValue;
				}
				
				calendar.add(Calendar.HOUR, units);
				currentTimestamp = new Timestamp(calendar.getTime().getTime());
				returnValue = currentTimestamp;
				return returnValue;
			} else if (variables[1].equalsIgnoreCase("days")) {
				int units = 0;
				try {
					units = Integer.parseInt(variables[0]);
				} catch (NumberFormatException e) {
					calendar.set(Calendar.YEAR, Calendar.YEAR + 5);
					currentTimestamp = new Timestamp(calendar.getTime().getTime());
					returnValue = currentTimestamp;
					
					return returnValue;
				}
				
				calendar.add(Calendar.DAY_OF_MONTH, units);
				currentTimestamp = new Timestamp(calendar.getTime().getTime());
				returnValue = currentTimestamp;
				return returnValue;
			} else if (variables[1].equalsIgnoreCase("months")) {
				int units = 0;
				try {
					units = Integer.parseInt(variables[0]);
				} catch (NumberFormatException e) {
					calendar.set(Calendar.YEAR, Calendar.YEAR + 5);
					currentTimestamp = new Timestamp(calendar.getTime().getTime());
					returnValue = currentTimestamp;
					
					return returnValue;
				}
				
				calendar.set(Calendar.MONTH, Calendar.MONTH + units);
				currentTimestamp = new Timestamp(calendar.getTime().getTime());
				returnValue = currentTimestamp;
				return returnValue;
			} else if (variables[1].equalsIgnoreCase("years")) {
				int units = 0;
				try {
					units = Integer.parseInt(variables[0]);
				} catch (NumberFormatException e) {
					calendar.add(Calendar.YEAR, 5);
					currentTimestamp = new Timestamp(calendar.getTime().getTime());
					returnValue = currentTimestamp;
					
					return returnValue;
				}
				
				calendar.add(Calendar.YEAR, units);
				calendar.add(Calendar.SECOND, 1);
				currentTimestamp = new Timestamp(calendar.getTime().getTime());
				returnValue = currentTimestamp;
				return returnValue;
			} else {
				calendar.set(Calendar.YEAR, Calendar.YEAR + 5);
				currentTimestamp = new Timestamp(calendar.getTime().getTime());
				returnValue = currentTimestamp;
				
				return returnValue;
			}
		}
		return returnValue;
	}
}
