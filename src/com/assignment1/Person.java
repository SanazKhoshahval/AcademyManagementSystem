package com.assignment1;

/**
 * This class represents a person with a name, birthdate, and optionally a death date. It provides methods to retrieve
 * person details, check life status, and access personal information.
 *
 * @author Sanaz Khoshahval
 * @version 1.0
 */
public class Person implements Comparable<Person>
{
	private final Name name;
	private final Date born;

	private Date died;

	/**
	 * Constructs a new Person object.
	 *
	 * @param born The person's birthdate.
	 * @param name The person's name.
	 * @throws IllegalPersonException if the birthdate or name is null.
	 */
	public Person(final Date born, final Name name) throws IllegalPersonException
	{
		if(born == null)
		{
			throw new IllegalPersonException("invalid date of birth");
		}
		if(name == null)
		{
			throw new IllegalPersonException("invalid name");
		}
		this.born = born;
		this.name = name;
	}

	/**
	 * Kills the person by setting the death date.
	 *
	 * @param dateOfDeath The date the person died.
	 */
	public void die(final Date dateOfDeath)
	{
		this.died = dateOfDeath;
	}

	/**
	 * Checks if the person is alive.
	 *
	 * @return true if the person is alive, false otherwise.
	 */
	public boolean isAlive()
	{
		return this.died == null;
	}

	/**
	 * Returns the person's birthdate.
	 *
	 * @return The person's birthdate.
	 */
	public Date getDateOfBirth()
	{
		return born;
	}

	/**
	 * Returns the person's death date.
	 *
	 * @return The person's death date.
	 */
	public Date getDateOfDeath()
	{
		return died;
	}

	/**
	 * Returns the person's name.
	 *
	 * @return The person's name.
	 */
	public Name getName()
	{
		return name;
	}

	/**
	 * Compares this person to another person based on birthdate.
	 *
	 * @param p The person to compare to.
	 * @return A negative integer, zero, or a positive integer as this person is less than, equal to, or greater than
	 * the specified person.
	 */
	@Override
	public int compareTo(final Person p)
	{
		return this.born.compareTo(p.born);
	}

	/**
	 * Returns a string representation of the person.
	 *
	 * @return A string representation of the person.
	 */
	@Override
	public String toString()
	{
		if(isAlive())
		{
			return String.format("%s (%s) was born on %s %s.", name.getPrettyName(), name.getInitials(),
					born.getDayOfTheWeek(), born.getYyyyMmDd());
		}
		else
		{
			return String.format("%s (%s) was born on %s %s and died on %s %s", name.getPrettyName(), name.getInitials(),
					born.getDayOfTheWeek(), born.getYyyyMmDd(),
					died.getDayOfTheWeek() , died.getYyyyMmDd());
		}
	}

}
