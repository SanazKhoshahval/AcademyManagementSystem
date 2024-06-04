package com.assignment1;

import java.util.Calendar;
import java.util.Comparator;

/**
 * Represents a date with day, month, and year. It provides functionality to validate date components,
 * determine the day of the week, and manipulate date values.
 * This class implements the Orderable and Comparable interfaces.
 *
 * @author Sanaz Khoshahval
 * @version 1.0
 */
public class Date implements Orderable , Comparable<Date>
{
	private static final int MIN_YEAR  = 0;
	private static final int MIN_MONTH = 1;
	private static final int MAX_MONTH = 12;
	private static final int MIN_DAY   = 1;

	private int day;
	private int month;
	private int year;

	/**
	 * Initializes a new Date instance with the specified day, month, and year.
	 *
	 * @param day   the day of the date
	 * @param month the month of the date
	 * @param year  the year of the date
	 */
	public Date(final int day, final int month, final int year)
	{
		validateYear(year);
		validateMonth(month);
		validateDay(day);
	}

	private void validateDay(final int day)
	{
		if(day < MIN_DAY || day > getNumberOfDaysPerMonth(month, year))
		{
			throw new IllegalArgumentException("invalid day of the month");
		}
		this.day = day;
	}

	private void validateMonth(final int month)
	{
		if(month < MIN_MONTH || month > MAX_MONTH)
		{
			throw new IllegalArgumentException("invalid month");
		}
		this.month = month;
	}

	/**
	 * Compares this date with the specified date for order.
	 *
	 * @param year the year of the date to be compared
	 */
	private void validateYear(final int year)
	{
		if(year <= MIN_YEAR)
		{
			throw new IllegalArgumentException("invalid year");
		}
		this.year = year;
	}

	/**
	 * Returns the date in the format "YYYY-MM-DD".
	 *
	 * @return the date in the format "YYYY-MM-DD"
	 */
	public String getYyyyMmDd()
	{
		return String.format("%04d-%02d-%02d", year, month, day);
	}

	/**
	 * Returns the day of the week for the date.
	 *
	 * @return the day of the week for the date
	 */
	public String getDayOfTheWeek()
	{
		final int lastTwoDigitsOfYear;
		final int twelveFits;
		final int remainder;
		final int fourFits;
		final int centuryCode;
		final int total;
		final int dayOfWeek;

		int monthCode;

		lastTwoDigitsOfYear = year % 100;
		twelveFits          = lastTwoDigitsOfYear / 12;
		remainder           = lastTwoDigitsOfYear % 12;
		fourFits            = remainder / 4;
		monthCode           = getCodeForMonth(month);

		if(month == 1 || month == 2)
		{
			if(isLeapYear(year)) monthCode += 6;
		}

		centuryCode = getCenturyCode(year);
		total       = twelveFits + remainder + fourFits + day + monthCode + centuryCode;
		dayOfWeek   = total % 7;

		return switch(dayOfWeek)
		{
			case 0 -> "Saturday";
			case 1 -> "Sunday";
			case 2 -> "Monday";
			case 3 -> "Tuesday";
			case 4 -> "Wednesday";
			case 5 -> "Thursday";
			case 6 -> "Friday";
			default -> "Unknown";
		};
	}

	/**
	 * Returns the code for the month.
	 *
	 * @param month the month
	 * @return the code for the month
	 */
	private int getCodeForMonth(final int month)
	{
		return switch(month)
		{
			case 1, 10 -> 1;
			case 2, 3, 11 -> 4;
			case 4, 7 -> 0;
			case 5 -> 2;
			case 6 -> 5;
			case 8 -> 3;
			case 9, 12 -> 6;
			default -> throw new IllegalArgumentException("Invalid month");
		};
	}

	/**
	 * Returns the code for the century.
	 *
	 * @param year the year
	 * @return the code for the century
	 */
	private int getCenturyCode(final int year)
	{
		final int century;

		century = year / 100;

		return switch (century)
		{
			case 16, 20 -> 6;
			case 17, 21 -> 4;
			case 18 -> 2;
			default -> 0;
		};
	}

	/**
	 * Returns the number of days in the specified month.
	 *
	 * @param month the month
	 * @param year  the year
	 * @return the number of days in the specified month
	 */
	private int getNumberOfDaysPerMonth(final int month, final int year)
	{
		switch(month)
		{
		case 1, 3, 5, 7, 8, 10, 12:
			return 31;
		case 4, 6, 9, 11:
			return 30;
		case 2:
			if(isLeapYear(year))
			{
				return 29;
			}
			else
			{
				return 28;
			}
		default:
			throw new IllegalArgumentException("Invalid month: " + month);
		}
	}

	/**
	 * Returns true if the specified year is a leap year; otherwise, false.
	 *
	 * @param year the year
	 * @return true if the specified year is a leap year; otherwise, false
	 */
	private boolean isLeapYear(final int year)
	{
		if(year % 4 != 0)
		{
			return false;
		}
		else if (year % 100 != 0)
		{
			return true;
		}
		else
		{
			return year % 400 == 0;
		}
	}

	/**
	 * Returns the day of the date.
	 *
	 * @return the day of the date
	 */
	public int getDay()
	{
		return day;
	}

	/**
	 * Returns the month of the date.
	 *
	 * @return the month of the date
	 */
	public int getMonth()
	{
		return month;
	}

	/**
	 * Returns the year of the date.
	 *
	 * @return the year of the date
	 */
	public int getYear()
	{
		return year;
	}

	/**
	 * Returns a string representation of the date in the format "MM/DD/YYYY".
	 *
	 * @return a string representation of the date
	 */
	@Override
	public String toString()
	{
		return String.format("%04d-%02d-%02d", year, month, day);
	}

	/**
	 * Returns the previous date in the sequence.
	 * @return the previous date in the sequence
	 */
	@Override
	public Date previous()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return new Date(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR));
	}

	/**
	 * Returns the next date in the sequence.
	 * @return the next date in the sequence
	 */
	@Override
	public Date next()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return new Date(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR));
	}

	/**
	 * Compares this Date object to another Date object.
	 * @param d the Date object to compare
	 * @return a negative integer, zero, or a positive integer as this Date is less than, equal to, or greater than the specified Date
	 */
	@Override
	public int compareTo(final Date d)
	{
		return Comparator.comparingInt((Date date) -> date.year)
				.thenComparingInt(date -> date.month)
				.thenComparingInt(date -> date.day)
				.compare(this, d);
	}
}