package org.example.mscard.mapper;


import org.example.mscard.exceptions.DateMapperException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class DateMapperTest {

	@Autowired
	private DateMapper dateMapper;

	@Test
	public void toDate_WithValidFormat_ReturnsCorrectDate() {
		String date = "2026-01-31";
		LocalDate expected = LocalDate.of(2026, 1, 31);
		Assertions.assertEquals(expected, dateMapper.toDate(date));
	}

	@ParameterizedTest
	@ValueSource(strings = {"31.01.2026", "01:31:2026", "2026 01 31", "2026/31/01"})
	public void toDate_WithDifferentFormats_ReturnsDateInCorrectForm(String date) {
		LocalDate expected = LocalDate.of(2026, 1, 31);
		Assertions.assertEquals(expected, dateMapper.toDate(date));
	}

	@ParameterizedTest
	@ValueSource(strings = {"2026031001", "2026-31/01", "01/31-2026"})
	public void toDate_WithInvalidFormats_ThrowsDateMapperException(String date) {
		Exception exception = Assertions.assertThrows(DateMapperException.class,
				() -> dateMapper.toDate(date)
		);
		Assertions.assertEquals("Unsupported date format. Expected format: dd-MM-yyyy", exception.getMessage());
	}

	@Test
	public void toDate_WithDateLengthMoreThanTen_TrowsDateMapperException() {
		String date = "2026 - 01 - 31";
		Exception exception = Assertions.assertThrows(DateMapperException.class,
				() -> dateMapper.toDate(date)
				);
		Assertions.assertEquals("Invalid expiry date length. Expected 10 characters, but got: " + date + ". Format: dd-MM-yyyy", exception.getMessage());
	}
}
