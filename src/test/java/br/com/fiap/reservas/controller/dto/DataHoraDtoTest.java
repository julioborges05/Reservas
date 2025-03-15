package br.com.fiap.reservas.controller.dto;

import org.junit.jupiter.api.Test;

public class DataHoraDtoTest {

    @Test
    void test() {
        DataHoraDto dataHoraDto = new DataHoraDto("horaChegada");
        String result = dataHoraDto.horaChegada();
        assert(result.equals("horaChegada"));
    }

}
