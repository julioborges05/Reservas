package br.com.fiap.reservas.infra.repository.reserva;

import br.com.fiap.reservas.enums.StatusReserva;
import br.com.fiap.reservas.infra.repository.endereco.Endereco;
import br.com.fiap.reservas.infra.repository.endereco.EnderecoRepository;
import br.com.fiap.reservas.infra.repository.restaurante.Restaurante;
import br.com.fiap.reservas.infra.repository.restaurante.RestauranteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class ReservaTest {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    private final LocalTime horarioAbertura = LocalTime.of(10, 0);
    private final LocalTime horarioFechamento = LocalTime.of(17, 0);

    @Test
    public void validarSalvarReserva() {
        Endereco endereco = new Endereco("13181701", "logradouro","bairro","cidade","numero","complemento");
        enderecoRepository.save(endereco);

        Restaurante restaurante = new Restaurante("restaurante", endereco.getId(), "tipo", horarioAbertura, horarioFechamento, 12);
        restauranteRepository.save(restaurante);

        Reserva reserva = new Reserva();
            reserva.setNomeUsuario("nome");
            reserva.setRestaurante(restaurante);
            reserva.setHoraChegada(LocalDateTime.now());
            reserva.setReservaVMesaList(List.of(new ReservaVMesa(StatusReserva.RESERVADA)));

        Reserva savedReserva = reservaRepository.save(reserva);

        assertNotNull(savedReserva.getId());
        assertEquals("nome", savedReserva.getNomeUsuario());
        assertNotNull(savedReserva.getRestaurante());
        assertEquals(restaurante.getId(), savedReserva.getRestaurante().getId());
        assertEquals(1, savedReserva.getReservaVMesaList().size());
    }

    @Test
    public void validarSalvarReservaComValores() {
        Endereco endereco = new Endereco("13181701", "logradouro","bairro","cidade","numero","complemento");
        enderecoRepository.save(endereco);

        Restaurante restaurante = new Restaurante("restaurante", endereco.getId(), "tipo", horarioAbertura, horarioFechamento, 12);
        restauranteRepository.save(restaurante);

        Reserva reserva = new Reserva(restaurante, "nome", List.of(new ReservaVMesa(StatusReserva.RESERVADA)), LocalDateTime.now());

        Reserva savedReserva = reservaRepository.save(reserva);

        assertNotNull(savedReserva.getId());
        assertEquals("nome", savedReserva.getNomeUsuario());
        assertNotNull(savedReserva.getRestaurante());
        assertEquals(restaurante.getId(), savedReserva.getRestaurante().getId());
        assertEquals(1, savedReserva.getReservaVMesaList().size());
    }

    @Test
    public void validarUpdateReserva() {
        Endereco endereco = new Endereco("13181701", "logradouro","bairro","cidade","numero","complemento");
        enderecoRepository.save(endereco);

        Restaurante restaurante = new Restaurante("restaurante", endereco.getId(), "tipo", horarioAbertura, horarioFechamento, 12);
        restauranteRepository.save(restaurante);

        Reserva reserva = new Reserva(/*1L*/ null, "nome", restaurante, List.of(new ReservaVMesa(StatusReserva.RESERVADA)), LocalDateTime.now());

        Reserva savedReserva = reservaRepository.save(reserva);

        assertNotNull(savedReserva.getId());
        assertEquals("nome", savedReserva.getNomeUsuario());
        assertNotNull(savedReserva.getRestaurante());
        assertEquals(restaurante.getId(), savedReserva.getRestaurante().getId());
        assertEquals(1, savedReserva.getReservaVMesaList().size());
    }

    @Test
    public void validarGettersAndSetters() {
        Reserva reserva = new Reserva();
        reserva.setNomeUsuario("nome");
        reserva.setHoraChegada(LocalDateTime.now());

        assertEquals("nome", reserva.getNomeUsuario());
        assertNotNull(reserva.getHoraChegada());
    }
}
