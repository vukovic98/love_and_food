package com.ftn.uns.ac.rs.love_and_food.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import static java.util.Calendar.*;

public class DateUtil {
	public static int getDiffYears(Date dateOfBirth) {
		Date now = new Date();
	    Calendar a = getCalendar(dateOfBirth);
	    Calendar b = getCalendar(now);
	    int diff = b.get(YEAR) - a.get(YEAR);
	    if (a.get(MONTH) > b.get(MONTH) || 
	        (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
	        diff--;
	    }
	    return diff;
	}

	public static Calendar getCalendar(Date date) {
	    Calendar cal = Calendar.getInstance(Locale.US);
	    cal.setTime(date);
	    return cal;
	}
}
