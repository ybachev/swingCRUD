package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

//(autoApply = true)
//@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, String> {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public String convertToDatabaseColumn(LocalDate date) {
		if (date == null) {
			return null;
		}
		String text = date.format(formatter);
		return text;
	}

	public LocalDate convertToEntityAttribute(String text) {
		if (text == null) {
			return null;
		}
		LocalDate parsedDate = LocalDate.parse(text, formatter);
		return parsedDate;
	}
}
