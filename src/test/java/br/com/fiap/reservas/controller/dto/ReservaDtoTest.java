package br.com.fiap.reservas.controller.dto;

import br.com.fiap.reservas.entities.*;
import br.com.fiap.reservas.enums.StatusMesa;
import br.com.fiap.reservas.enums.StatusReserva;
import br.com.fiap.reservas.infra.repository.mesa.MesaPK;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;
import br.com.fiap.reservas.utils.DateFormat;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class ReservaDtoTest {
    private final EnderecoEntity enderecoEntity = new EnderecoEntity("1318000", "logradouro", "bairro", "cidade", "numero", "complemento");

    private final LocalDateTime horarioChegada = LocalDateTime.of(2025, 2, 2, 10, 37);
    private final ReservaVMesaEntity reservaVMesa = new ReservaVMesaEntity(1L, 1L,new MesaPK(1L, 1), StatusReserva.LIVRE);
    private final MesaEntity mesaEntity = new MesaEntity(1, StatusMesa.LIVRE);
    private final RestauranteEntity restaurante = new RestauranteEntity("nome", enderecoEntity, "tipoCozinha", LocalTime.now(), LocalTime.now(), 10, List.of(mesaEntity));

    @Test
    public void validaReservaDtoComErro() {
        assertThrows(
                NullPointerException.class,
                () -> new ReservaDto(null)
        );
    }

    @Test
    void validaReservaDto() {
        ReservaDto dto = new ReservaDto(new ReservaEntity(1L, restaurante, "nome", List.of(reservaVMesa), horarioChegada));
        assertNotNull(dto);
        assertEquals(restaurante.getId(), dto.restauranteId());
        assertEquals(0, dto.qtdPessoas());
        assertEquals(DateFormat.convertFromLocalDateTimeToString(horarioChegada), dto.horaChegada());
        assertEquals(1L, dto.reservaId());
    }
}
