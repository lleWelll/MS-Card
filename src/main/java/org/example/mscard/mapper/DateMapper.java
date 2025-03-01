package org.example.mscard.mapper;

import org.apache.commons.lang3.StringUtils;
import org.example.mscard.util.Validator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class DateMapper {

	public LocalDate toDate(String expiryDate) {
		return formatDateToCorrect(expiryDate);
	}

	private LocalDate formatDateToCorrect(String expiryDate) {
		if (! Validator.isValidDateString(expiryDate)) throw new IllegalArgumentException("expiryDate length should be = 10, " + expiryDate + ", enter expiryDate in format: dd-MM-yyyy");
		String delimiter = findDelimiter(expiryDate);
		String[] dateElements = StringUtils.split(expiryDate, delimiter);
		DateTimeFormatter format = createFormat(dateElements, delimiter);
		return LocalDate.parse(expiryDate, format);
	}

	private static String findDelimiter(String str) {
		Pattern delimiterPattern = Pattern.compile("\\D");
		Matcher matcher = delimiterPattern.matcher(str);
		if (matcher.find()) {
			return matcher.group();
		} else {
			throw new IllegalArgumentException("date format is not supported, pls enter in this format: dd-MM-yyyy");
		}
	}

	private static DateTimeFormatter createFormat(String[] dateElements, String delimiter) {
		if (dateElements[0].length() == 4 && Integer.parseInt(dateElements[1]) <= 12) {
			return DateTimeFormatter.ofPattern("yyyy" + delimiter + "MM" + delimiter + "dd");
		}
		else if (dateElements[0].length() == 4 && Integer.parseInt(dateElements[1]) > 12) {
			return DateTimeFormatter.ofPattern("yyyy" + delimiter + "dd" + delimiter + "MM");
		}
		else if (Integer.parseInt(dateElements[1]) <= 12) {
			return DateTimeFormatter.ofPattern("dd" + delimiter + "MM" + delimiter + "yyyy");
		}
		else {
			return DateTimeFormatter.ofPattern("MM" + delimiter + "dd" + delimiter + "yyyy");
		}
	}
}