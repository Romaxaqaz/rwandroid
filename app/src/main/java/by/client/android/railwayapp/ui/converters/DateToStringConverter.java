package by.client.android.railwayapp.ui.converters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.nfc.FormatException;
import by.client.android.railwayapp.ui.utils.Utils;

/**
 * Класс конвертер для преобразования даты в строку и наоборт.
 *
 * <p>Формат даты по умолчанию {@link DateToStringConverter#DEFAULT_FORMAT}</p>
 *
 * @author Q-RPA
 */
public class DateToStringConverter {

    private static final String DEFAULT_FORMAT = "yyyy-MM-dd";
    private final String stringDateFormat;

    public DateToStringConverter() {
        this.stringDateFormat = DEFAULT_FORMAT;
    }

    public DateToStringConverter(String dateFormat) {
        this.stringDateFormat = dateFormat;
    }

    /**
     * Конвертирует дату из типа {@link Date} в строку.
     *
     * @param date дата для преобразования в строку.
     * @return дата в строковом представлении.
     */
    public String convert(Date date) {
        if (date == null) {
            return null;
        }
        return getFormat(stringDateFormat).format(date);
    }

    /**
     * Конвертирует строку в дату типа {@link Date}
     *
     * @param date строковое представление даты.
     * @return возвращает {@link Date}.
     * @throws FormatException в случае невозможности преобразования строки в тип {@link Date}.
     */
    public Date convertBack(String date) throws FormatException {
        if (Utils.isBlank(date)) {
            return null;
        }

        Date parsedDate;
        try {
            DateFormat dateFormat = getFormat(stringDateFormat);
            dateFormat.setLenient(false);
            parsedDate = dateFormat.parse(date);
        }
        catch (ParseException e) {
            String message = String.format("Error parsing a string '%s' on a date. Correct format: %s.", date, DEFAULT_FORMAT);
            throw new FormatException(message);
        }
        return parsedDate;
    }

    private static DateFormat getFormat(String format) {
        return new SimpleDateFormat(format, new Locale("ru"));
    }
}
