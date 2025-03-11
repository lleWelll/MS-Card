package org.example.mscard.mapper;


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
	public void toDate_WithInvalidFormats_ThrowsIllegalArgumentException(String date) {
		Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
				() -> dateMapper.toDate(date)
		);
		Assertions.assertEquals("date format is not supported, please enter in this format: dd-MM-yyyy", exception.getMessage());
	}

	@Test
	public void toDate_WithDateLengthMoreThanTen_TrowsIllegalArgumentException() {
		String date = "2026 - 01 - 31";
		Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
				() -> dateMapper.toDate(date)
				);
		Assertions.assertEquals("expiryDate length should be = 10, " + date + ", enter expiryDate in format: dd-MM-yyyy", exception.getMessage());
	}
}
