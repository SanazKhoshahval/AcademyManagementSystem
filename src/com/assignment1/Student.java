package com.assignment1;

/**
 * Represents a student, extending the Person class with a student number.
 *
 * @author Sanaz khoshahval
 * @version 1.0
 */
public class Student extends Person
{
	private final       String studentNumber;
	public static final int    MAX_STUDENT_NUMBER = 9;

	/**
	 * Constructs a Student with a birthdate, name, and student number.
	 *
	 * @param dateBorn      the birthdate of the student
	 * @param name          the name of the student
	 * @param studentNumber the student number, must be nine characters long
	 * @throws IllegalPersonException if any parameters are invalid
	 */
	public Student(final Date dateBorn, final Name name, final String studentNumber)
	{
		super(dateBorn, name);
		if(studentNumber == null || studentNumber.isBlank() || studentNumber.length() != MAX_STUDENT_NUMBER)
		{
			throw new IllegalPersonException("bad student number");
		}
		this.studentNumber = studentNumber;
	}

	/**
	 * Gets the student number.
	 *
	 * @return the student number
	 */
	public String getStudentNumber()
	{
		return studentNumber;
	}

	/**
	 * Returns a string representation of this student.
	 *
	 * @return formatted string indicating the student's name, student number, birth, and possibly death date
	 */
	@Override
	public String toString()
	{
		String result;
		result = getName().getPrettyName() + " (student number: " + studentNumber + ") was born " + getDateOfBirth().getYyyyMmDd();
		if(getDateOfDeath() != null)
		{
			result += " and died " + getDateOfDeath().getYyyyMmDd();
		}
		else
		{
			result += " and is still alive";
		}
		return result;
	}
}