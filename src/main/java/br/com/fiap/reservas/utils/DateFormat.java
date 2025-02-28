package br.com.fiap.reservas.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormat {

    public static String convertFromLocalDateTimeToString(LocalDateTime dataHoraChegada) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dataHoraChegada.format(formatter);
    }

    public static LocalDateTime convertFromStringToLocalDateTime(String dataHoraChegada) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dataHoraChegada, formatter);
        return dateTime.withSecond(0).withNano(0);
    }
}
