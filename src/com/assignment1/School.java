package com.assignment1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a school that can register and manage records of people like students and teachers.
 *
 * @author Sanaz Khoshahval
 * @version 1.0
 */
public class School
{
	private List<Person> people;

	public School()
	{
		people = new ArrayList<>();
	}

	/**
	 * Registers a person in the school if the person is not null.
	 *
	 * @param p the person to register
	 */
	public void register(Person p)
	{
		if(p == null)
		{
			throw new IllegalPersonException("cannot register a non-person");
		}
		people.add(p);
	}

	/**
	 * Prints a roster of the school's people with detailed formatting.
	 */
	public void printRoster()
	{
		people.forEach(person -> System.out.println(formatPersonDetails(person)));
	}

	/**
	 * Formats the details of a person based on their type and life status.
	 * @param p the person to format details for
	 * @return formatted string with person details
	 */
	private String formatPersonDetails(final Person p)
	{
		final String fullName;
		final String birthDate;

		String details;

		fullName = p.getName().getPrettyName();
		birthDate = p.getDateOfBirth().toString();
		details = "";

		if(p instanceof Student s)
		{
			details = String.format("%s (student number: %s) was born %s", fullName, s.getStudentNumber(), birthDate);
		}
		else if
		(p instanceof Teacher t)
		{
			details = String.format("%s (specialty: %s) was born %s", fullName, t.getSpecialty(), birthDate);
		}
		else
		{
			details = String.format("%s was born %s", fullName, birthDate);
		}

		if(p.isAlive())
		{
			details += " and is still alive";
		}
		else
		{
			final String deathDate;
			deathDate = p.getDateOfDeath().toString();
			details += String.format(" and died %s", deathDate);
		}

		return details;
	}

	/**
	 * Prints detailed age and year information for each person in the school using a lambda expression.
	 */
	public void printAgesAndYears()
	{
		people.forEach(person -> {
			final String fullName;
			final int yearBorn;
			final int maxYear;

			fullName = person.getName().getPrettyName();
			yearBorn = person.getDateOfBirth().getYear();
			maxYear  = person.isAlive() ? 2022 : person.getDateOfDeath().getYear();

			Writeable w = (s, min, max) -> {
				for(int year = min; year <= max; year++) {
					System.out.println(s + ": " + year + " (age " + (year - min) + ")");
				}
			};

			w.printData(fullName, yearBorn, maxYear);
		});
	}

	/**
	 * Saves the details of all people in the school to a file called "people.txt".
	 */
	public void saveDetails()
	{
		File file = new File("people.txt");
		file.delete();

		try (FileWriter writer = new FileWriter("people.txt"))
		{
			for(final Person p : people)
			{

				final String formattedDetail;
				if(p.isAlive())
				{
					formattedDetail = String.format("%s (%s) was born on %s %s.%n",
							p.getName().getPrettyName(),
							p.getName().getInitials(),
							p.getDateOfBirth().getDayOfTheWeek(),
							p.getDateOfBirth().getYyyyMmDd());
				}
				else
				{
					formattedDetail = String.format("%s (%s) was born on %s %s and died on %s %s.%n",
							p.getName().getPrettyName(),
							p.getName().getInitials(),
							p.getDateOfBirth().getDayOfTheWeek(),
							p.getDateOfBirth().getYyyyMmDd(),
							p.getDateOfDeath().getDayOfTheWeek(),
							p.getDateOfDeath().getYyyyMmDd());
				}
				writer.write(formattedDetail);
			}
		}
		catch
		(IOException e)
		{
			System.out.println("Failed to write to file: " + e.getMessage());
		}
	}

}
