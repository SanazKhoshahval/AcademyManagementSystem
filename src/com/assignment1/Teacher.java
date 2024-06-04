package com.assignment1;

/**
 * Represents a teacher, extending the Person class with a specialty.
 *
 * @version 1.0
 * @author Sanaz Khoshahval
 */
public class Teacher extends Person
{
	private final String specialty;

	/**
	 * Constructs a new Teacher object.
	 *
	 * @param dateBorn  The teacher's birthdate.
	 * @param name      The teacher's name.
	 * @param specialty The teacher's specialty.
	 */
	public Teacher(final Date dateBorn, final Name name, final String specialty)
	{
		super(dateBorn , name);
		if(specialty == null || specialty.isEmpty())
		{
			throw new IllegalPersonException("bad specialty");
		}
		this.specialty = specialty;
	}

	/**
	 * Returns the teacher's specialty.
	 *
	 * @return The teacher's specialty.
	 */
	public String getSpecialty()
	{
		return specialty;
	}

	/**
	 * Returns a string representation of this teacher.
	 *
	 * @return formatted string indicating the teacher's name, specialty, birth, and possibly death date
	 */
	@Override
	public String toString()
	{
		if(isAlive())
		{
			return String.format("%s (specialty: %s) was born %s and is still alive", super.getName().getPrettyName(), specialty, super.getDateOfBirth().getYyyyMmDd());
		}
		else
		{
			return String.format("%s (specialty: %s) was born %s and died %s", super.getName().getPrettyName(),
					specialty, super.getDateOfBirth().getYyyyMmDd(), super.getDateOfDeath().getYyyyMmDd());
		}
	}
}
