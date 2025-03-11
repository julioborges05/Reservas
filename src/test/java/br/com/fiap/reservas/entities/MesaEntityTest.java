package br.com.fiap.reservas.entities;

import br.com.fiap.reservas.enums.StatusMesa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MesaEntityTest {

    @Test
    void retornaErroQuandoNumeroMesaForMenorQueZero_SemRestauranteId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new MesaEntity(-1, StatusMesa.LIVRE),
                "Numero de mesa inválido"
        );
    }

    @Test
    void retornaErroQuandoNumeroMesaForMenorQueZero_ComRestauranteId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new MesaEntity(1L, -1, StatusMesa.LIVRE),
                "Numero de mesa inválido"
        );
    }

    @Test
    void retornaErroQuandoStatusMesaForNulo_SemRestauranteId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new MesaEntity(1, null),
                "Status inválido"
        );
    }

    @Test
    void retornaErroQuandoStatusMesaForNulo_ComRestauranteId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new MesaEntity(1L, 1, null),
                "Status inválido"
        );
    }

    @Test
    void criaMesaEntityComSucesso_SemRestauranteId() {
        MesaEntity mesa = new MesaEntity(1, StatusMesa.LIVRE);

        assertEquals(1, mesa.getNumero());
        assertEquals(StatusMesa.LIVRE, mesa.getStatusMesa());
    }

    @Test
    void criaMesaEntityComSucesso_ComRestauranteId() {
        MesaEntity mesa = new MesaEntity(1L, 1, StatusMesa.LIVRE);

        assertEquals(1, mesa.getNumero());
        assertEquals(StatusMesa.LIVRE, mesa.getStatusMesa());
        assertEquals(1L, mesa.getRestauranteId());
    }

}
