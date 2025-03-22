package br.com.fiap.reservas.entities;

import br.com.fiap.reservas.enums.StatusReserva;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReservaVMesaEntityTest {

    @Test
    void test() {
        ReservaVMesaEntity reserva = new ReservaVMesaEntity();
        reserva.setId(1L);
        reserva.setStatus(StatusReserva.CANCELADA);
        assertNotNull(reserva);
        assertEquals(reserva.getId(), 1L);
        assertEquals(reserva.getStatus(), StatusReserva.CANCELADA);
    }
}
