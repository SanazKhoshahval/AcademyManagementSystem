package com.assignment1;

@FunctionalInterface
public interface Writeable
{
	/**
	 * Writes data to a file based on the provided string and range.
	 *
	 * @param s The string to write to the file.
	 * @param min The starting index (inclusive).
	 * @param max The ending index (exclusive).
	 */
	void printData(final String s, final int min, final int max);
}


