package br.com.fiap.reservas.controller.dto;

import br.com.fiap.reservas.entities.*;
import br.com.fiap.reservas.enums.StatusMesa;
import br.com.fiap.reservas.enums.StatusReserva;
import br.com.fiap.reservas.infra.repository.mesa.MesaPK;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BuscarReservasDtoTest {

    private final EnderecoEntity enderecoEntity = new EnderecoEntity("1318000", "logradouro", "bairro", "cidade", "numero", "complemento");
    private final MesaEntity mesaEntity = new MesaEntity(1, StatusMesa.LIVRE);
    private final RestauranteEntity restaurante = new RestauranteEntity("nome", enderecoEntity, "tipoCozinha", LocalTime.now(), LocalTime.now(), 10, List.of(mesaEntity));
    private final LocalDateTime horarioChegada = LocalDateTime.of(2025, 2, 2, 10, 37);
    private final ReservaVMesaEntity reservaVMesa = new ReservaVMesaEntity(1L, 1L,new MesaPK(1L, 1), StatusReserva.LIVRE);

    @Test
    void validaBuscarReservasDto() {
        BuscarReservasDto buscarReservasDto = new BuscarReservasDto(new ReservaEntity(1L, restaurante, "nome", List.of(reservaVMesa), horarioChegada));
        assertNotNull(buscarReservasDto);
    }

    @Test
    public void validaBuscarReservasDtoComErro() {
        assertThrows(
                NullPointerException.class,
                () -> new BuscarReservasDto(null)
        );
    }
}
