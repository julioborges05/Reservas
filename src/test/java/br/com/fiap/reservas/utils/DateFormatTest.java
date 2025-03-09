package br.com.fiap.reservas.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateFormatTest {

    @Test
    void validaConverterDateTimeToString() {
        LocalDateTime dataHoraChegada = LocalDateTime.of(2025, 3, 9, 14, 30);
        String dataHoraFormatada = DateFormat.convertFromLocalDateTimeToString(dataHoraChegada);
        assertEquals("09/03/2025 14:30", dataHoraFormatada);
    }

    @Test
    void validaConverterStringToDateTime() {
        String dataHoraChegada = "09/03/2025 14:30";
        LocalDateTime dataHora = DateFormat.convertFromStringToLocalDateTime(dataHoraChegada);
        assertEquals(
                LocalDateTime.of(2025, 3, 9, 14, 30, 0, 0),
                dataHora
        );
    }

    @Test
    void validaConverterStringToDateFormatoInvalido() {
        assertThrows(
                DateTimeParseException.class,
                () -> DateFormat.convertFromStringToLocalDateTime("2025-03-09 14:30")
        );
    }

    @Test
    void validaConverterStringToDateTimeComHorarioInvalido() {
        assertThrows(
                DateTimeParseException.class,
                () -> DateFormat.convertFromStringToLocalDateTime("09/03/2025 25:00")
        );
    }
}
