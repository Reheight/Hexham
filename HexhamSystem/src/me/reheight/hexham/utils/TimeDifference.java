package me.reheight.hexham.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
public class TimeDifference {
	public static String execute(Timestamp dateAction) {
		String returnValue = null;
		
		LocalDate action = dateAction.toLocalDateTime().toLocalDate();
		
		LocalDate now = LocalDate.now();
		
		Period difference = Period.between(now, action);
		
		if (difference.isNegative()) {
			returnValue = "Finished";
			return returnValue;
		} else {
			returnValue = dateAction.toString();
		}
		return returnValue;
	}
}
