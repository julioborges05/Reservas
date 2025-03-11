package br.com.fiap.reservas.entities;

import br.com.fiap.reservas.enums.StatusMesa;
import br.com.fiap.reservas.enums.StatusReserva;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReservaEntityTest {

    private final EnderecoEntity enderecoEntity = new EnderecoEntity("1318000", "logradouro", "bairro", "cidade", "numero", "complemento");

    private final ReservaVMesa reservaVMesa = new ReservaVMesa(1L, StatusReserva.LIVRE);
    private final MesaEntity mesaEntity = new MesaEntity(1, StatusMesa.LIVRE);
    private final RestauranteEntity restaurante = new RestauranteEntity("nome", enderecoEntity, "tipoCozinha", LocalTime.now(), LocalTime.now(), 10, List.of(mesaEntity));

    private final LocalDateTime horarioChegada = LocalDateTime.of(2025, 2, 2, 10, 37);

    @Test
    void validaRestauranteComErro() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaEntity(null, "nome", List.of(reservaVMesa), horarioChegada),
                "Restaurante Inválido"
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaEntity(restaurante, null, List.of(reservaVMesa), horarioChegada),
                "Nome do usuário deve ser informado"
        );
    }

    @Test
    void validaRestauranteComSucesso() {
        ReservaEntity reserva = new ReservaEntity(restaurante, "nome", List.of(reservaVMesa), horarioChegada);
        assertNotNull(reserva);
        assertEquals(restaurante, reserva.getRestaurante());
        assertEquals("nome", reserva.getNomeUsuario());
        assertEquals(1, reserva.getReservaVMesaList().size());
    }

    @Test
    void validaRestauranteComIdAndHoraChegadaComErro() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaEntity(1L, null, "nome", List.of(reservaVMesa), horarioChegada),
                "Restaurante Inválido"
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaEntity(1L, restaurante, null, List.of(reservaVMesa), horarioChegada),
                "Nome do usuário deve ser informado"
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaEntity(null, restaurante, "nome", List.of(reservaVMesa), horarioChegada),
                "Id deve ser informado"
        );
    }

    @Test
    void validaRestauranteComIdAndHoraChegadaComSucesso() {
        ReservaEntity reserva = new ReservaEntity(1L, restaurante, "nome", List.of(reservaVMesa), horarioChegada);

        assertNotNull(reserva);
        assertEquals(restaurante, reserva.getRestaurante());
        assertEquals("nome", reserva.getNomeUsuario());
        assertEquals(1, reserva.getReservaVMesaList().size());
        assertEquals(horarioChegada, reserva.getHoraChegada());
        assertEquals(1L, reserva.getId());
    }

    @Test
    void validaRestauranteComIdComErro() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaEntity(1L, null, "nome", List.of(reservaVMesa)),
                "Restaurante Inválido"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaEntity(1L, restaurante, null, List.of(reservaVMesa)),
                "Nome do usuário deve ser informado"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaEntity(null, restaurante, "nome", List.of(reservaVMesa)),
                "Id deve ser informado"
        );

        ReservaEntity reserva = new ReservaEntity(1L, restaurante, "nome", List.of(reservaVMesa));

        assertNotNull(reserva);
        assertEquals(restaurante, reserva.getRestaurante());
        assertEquals("nome", reserva.getNomeUsuario());
        assertEquals(1, reserva.getReservaVMesaList().size());
        assertEquals(1L, reserva.getId());
    }

    @Test
    void validaRestauranteComIdComSucesso() {
        ReservaEntity reserva = new ReservaEntity(1L, restaurante, "nome", List.of(reservaVMesa));

        assertNotNull(reserva);
        assertEquals(restaurante, reserva.getRestaurante());
        assertEquals("nome", reserva.getNomeUsuario());
        assertEquals(1, reserva.getReservaVMesaList().size());
        assertEquals(1L, reserva.getId());
    }

    @Test
    void validaRestauranteComQtdPessoasComErro() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaEntity(null, "nome", 4),
                "Restaurante deve ser informado"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaEntity(restaurante, null, 4),
                "Usuário deve ser informado"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaEntity(restaurante, "nome", 0),
                "Quantidade de pessoas deve ser informado"
        );
    }

    @Test
    void validaRestauranteComQtdPessoasComSucesso() {
        ReservaEntity reserva = new ReservaEntity(restaurante, "nome", 4);

        assertNotNull(reserva);
        assertEquals(restaurante, reserva.getRestaurante());
        assertEquals("nome", reserva.getNomeUsuario());
        assertEquals(4, reserva.getQtdPessoas());
    }

    @Test
    void validaSetters() {
        ReservaEntity reserva = new ReservaEntity(1L, restaurante, "nome", List.of(reservaVMesa));
        reserva.setHoraChegada(horarioChegada);

        assertEquals(horarioChegada, reserva.getHoraChegada());
    }
}
