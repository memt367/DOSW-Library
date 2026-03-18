package edu.eci.dosw.tdd.util;

public class ValidationUtil {

	private ValidationUtil() {
	}

	public static String requireNonBlank(String value, String fieldName) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException(fieldName + " is required");
		}
		return value;
	}

	public static int requirePositive(int value, String fieldName) {
		if (value <= 0) {
			throw new IllegalArgumentException(fieldName + " must be greater than 0");
		}
		return value;
	}
}
