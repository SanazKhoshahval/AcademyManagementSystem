package com.assignment1;

/**
 * Represents a person's name.
 *
 * @author Sanaz Khoshahval
 * @version 1.0
 */
public class Name
{
	private String first;
	private String last;

	private final static int    MIN_NUMBER_OF_CHARS = 45;
	private static final String FIRST_NAME          = "admin";
	private static final String LAST_NAME           = "admin";
	private static final int    INDEX_FIRST         = 0;
	private static final int    INDEX_LAST          = 0;

	/**
	 * Constructs a Name object with the given first and last names.
	 *
	 * @param first The first name.
	 * @param last  The last name.
	 */
	public Name(final String first,
			final String last)

	{
		validateFirstName(first);
		validateLastName(last);
	}

	/**
	 * Generates and returns the initials from the given first and last names.
	 * @return The initials.
	 */
	public String getInitials()
	{
		char firstInitial = Character.toUpperCase(first.charAt(INDEX_FIRST));
		char lastInitial = Character.toUpperCase(last.charAt(INDEX_FIRST));

		return firstInitial + "." + lastInitial + ".";
	}


	/**
	 * Validates the last name according to specific rules.
	 * @param first the last name to validate
	 * @throws IllegalArgumentException if the last name is invalid
	 */
	public void validateFirstName(final String first)
	{
		if(first == null || first.trim().isEmpty())
		{
			throw new IllegalArgumentException("invalid first name");
		}
		this.first = first.trim();
	}

	public void validateLastName(final String last)
	{
		if(last == null || last.trim().isEmpty())
		{
			throw new IllegalArgumentException("invalid last name");
		}
		this.last = last.trim();
	}

	/**
	 * Generates and returns the full name (first name and last name) with the first letter of each name capitalized.
	 * @return The full name.
	 */
	public String getPrettyName()
	{
		final String capitalizedFirstName;
		final String capitalizedLastName;

		capitalizedFirstName = first.substring(0, 1).toUpperCase() + first.substring(1).toLowerCase();
		capitalizedLastName= last.substring(0, 1).toUpperCase() + last.substring(1).toLowerCase();

		return capitalizedFirstName + " " + capitalizedLastName;
	}


	/**
	 * Returns the first name.
	 *
	 * @return The first name.
	 */
	public String getFirst()
	{
		return first;
	}

	/**
	 * Sets the first name.
	 *
	 * @param first The first name to set.
	 */
	private void setFirst(final String first)
	{
		validateFirstName(first);
		this.first = first;
	}

	/**
	 * Returns the last name.
	 *
	 * @return The last name.
	 */
	public String getLast()
	{
		return last;
	}

	/**
	 * Sets the last name.
	 *
	 * @param last The last name to set.
	 */
	private void setLast(final String last)
	{
		validateLastName(last);
		this.last = last;
	}
}
