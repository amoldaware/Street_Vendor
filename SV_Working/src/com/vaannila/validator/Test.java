package com.vaannila.validator;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Test {
	private static Date getFirstDayOfQuarter(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)/3 * 3);
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    return cal.getTime();
	}

	private static Date getLastDayOfQuarter(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)/3 * 3 + 2);
	    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	    return cal.getTime();
	}
	public static void main(String[] args) {
		Test t= new Test();
		Calendar cal = Calendar.getInstance(Locale.US);
		/* Consider whether you need to set the calendar's timezone. */
		Date date = new Date();
		cal.setTime(date);
		System.out.println(t.getFirstDayOfQuarter(date));
		System.out.println(t.getLastDayOfQuarter(date));
		t.getFirstDayOfQuarter(date);
		t.getLastDayOfQuarter(date);
		int month =cal.get(Calendar.MONTH); /* 0 through 11 */
		System.out.println("month : "+month);
		int quarter = (month / 3) + 1;
		System.out.println(" quarter "+quarter);
	}
}
