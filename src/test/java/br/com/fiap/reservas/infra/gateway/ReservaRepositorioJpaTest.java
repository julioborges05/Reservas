package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.entities.ReservaVMesaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.enums.StatusReserva;
import br.com.fiap.reservas.infra.repository.endereco.Endereco;
import br.com.fiap.reservas.infra.repository.endereco.EnderecoRepository;
import br.com.fiap.reservas.infra.repository.mesa.MesaPK;
import br.com.fiap.reservas.infra.repository.mesa.MesaRepository;
import br.com.fiap.reservas.infra.repository.reserva.Reserva;
import br.com.fiap.reservas.infra.repository.reserva.ReservaRepository;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesaRepository;
import br.com.fiap.reservas.infra.repository.restaurante.Restaurante;
import br.com.fiap.reservas.infra.repository.restaurante.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReservaRepositorioJpaTest {

    private ReservaRepositorioJpa reservaRepositorioJpa;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private ReservaVMesaRepository reservaVMesaRepository;

    private Endereco enderecoSalvo;
    private Restaurante restauranteSalvo;

    @BeforeEach
    void setUp() {
        EnderecoRepositorioJpa enderecoRepositorioJpa = new EnderecoRepositorioJpa(enderecoRepository);
        RestauranteRepositorioJpa restauranteRepositorioJpa = new RestauranteRepositorioJpa(restauranteRepository,
                enderecoRepository, mesaRepository);

        reservaRepositorioJpa = new ReservaRepositorioJpa(reservaRepository, enderecoRepositorioJpa,
                 restauranteRepositorioJpa);

        enderecoSalvo = enderecoRepository.save(new Endereco("Rua Teste", "123", "Bairro Teste",
                "Cidade Teste", "Estado Teste", "CEP Teste"));
        restauranteSalvo = restauranteRepository.save(new Restaurante("Restaurante Teste", enderecoSalvo.getId(),
                "Comida Brasileira", LocalTime.of(10, 0), LocalTime.of(22, 0),
                100));
    }

    @Test
    void buscarReservasPorRestaurante_reservaInexistente() {
        List<ReservaEntity> reservaEntityList = reservaRepositorioJpa.buscarReservasPorRestaurante(1L);

        assertEquals(0, reservaEntityList.size());
    }

    @Test
    void buscarReservasPorRestaurante_reservaExistente() {
        Reserva reserva = new Reserva(restauranteSalvo, "Usuario Teste",
                List.of(), null);

        Reserva reservaSalva = reservaRepository.save(reserva);

        reservaVMesaRepository.save(new ReservaVMesa(reservaSalva.getId(), StatusReserva.RESERVADA));

        List<ReservaEntity> reservaEntityList = reservaRepositorioJpa.buscarReservasPorRestaurante(restauranteSalvo.getId());

        assertEquals(1, reservaEntityList.size());
        assertEquals(reservaSalva.getId(), reservaEntityList.get(0).getId());
        assertEquals(reservaSalva.getNomeUsuario(), reservaEntityList.get(0).getNomeUsuario());
    }

    @Test
    void cadastrarReserva() {
        EnderecoEntity enderecoEntity = enderecoSalvo.converterParaEntity();
        RestauranteEntity restauranteEntity = restauranteSalvo.converteParaEntity(enderecoEntity);

        ReservaEntity resultado = reservaRepositorioJpa.cadastrarReserva(restauranteEntity, "Nome do usuario",
                List.of(),LocalDateTime.of(2025, 2, 5, 15, 51));

        assertEquals(restauranteEntity.getId(), resultado.getRestaurante().getId());
        assertEquals("Nome do usuario", resultado.getNomeUsuario());
    }

    @Test
    void atualizarStatusReserva_reservaNaoEncontrada() {
        assertThrows(
                RuntimeException.class,
                () -> reservaRepositorioJpa.atualizarStatusReserva("Usuario Inexistente", "2025-02-05T15:51"),
                "Reserva não encontrada"
        );
    }

    @Test
    void atualizarStatusReserva_reservaExistente() {
        Reserva reserva = new Reserva(restauranteSalvo, "Usuario Teste", new ArrayList<>(), LocalDateTime.of(2025, 2, 5, 15, 51));
        Reserva reservaSalva = reservaRepository.save(reserva);
        ReservaVMesa reservaVMesa = new ReservaVMesa(reservaSalva.getId(), StatusReserva.RESERVADA);
        reservaVMesaRepository.save(reservaVMesa);

        reservaRepositorioJpa.atualizarStatusReserva("Usuario Teste", "05/02/2025 15:51");

        assertNotNull(reservaRepository.findById(reservaSalva.getId()).orElseThrow());
    }

    @Test
    void atualizarQtdPessoasReserva_reservaExistente() {
        Reserva reserva = new Reserva(restauranteSalvo, "Usuario Teste", new ArrayList<>(), null);
        Reserva reservaSalva = reservaRepository.save(reserva);
        ReservaVMesa reservaVMesa = new ReservaVMesa(reservaSalva.getId(), StatusReserva.RESERVADA);
        ReservaVMesa reservaVMesaSalva = reservaVMesaRepository.save(reservaVMesa);

        ReservaVMesaEntity reservaVMesaEntity = new ReservaVMesaEntity(
                reservaVMesaSalva.getId(),
                reservaSalva.getId(),
                new MesaPK(restauranteSalvo.getId(), 1),
                StatusReserva.RESERVADA);

        List<ReservaVMesaEntity> reservaVMesaEntities = new ArrayList<>();
        reservaVMesaEntities.add(reservaVMesaEntity);
        ReservaEntity reservaEntity = new ReservaEntity(
                reservaSalva.getId(),
                restauranteSalvo.converteParaEntity(enderecoSalvo.converterParaEntity()),
                "Usuario Teste",
                reservaVMesaEntities
        );

        reservaRepositorioJpa.atualizarQtdPessoasReserva(reservaEntity);

        Reserva reservaAtualizada = reservaRepository.findById(reservaSalva.getId()).orElseThrow();
        assertEquals(1, reservaAtualizada.getReservaVMesaList().size());
        assertEquals(StatusReserva.RESERVADA, reservaAtualizada.getReservaVMesaList().get(0).getStatus());
    }

    @Test
    void atualizarQtdPessoasReserva_reservaNaoEncontrada() {
        ReservaEntity reservaEntity = new ReservaEntity(999L, restauranteSalvo.converteParaEntity(enderecoSalvo.converterParaEntity()), "Usuario Teste", List.of());

        assertThrows(
                RuntimeException.class,
                () -> reservaRepositorioJpa.atualizarQtdPessoasReserva(reservaEntity),
                "Reserva não encontrada"
        );
    }

    @Test
    void buscaReservaPeloId_reservaExistente() {
        Reserva reserva = new Reserva(restauranteSalvo, "Usuario Teste", List.of(), null);
        Reserva reservaSalva = reservaRepository.save(reserva);

        ReservaEntity reservaEntity = reservaRepositorioJpa.buscaReservaPeloId(reservaSalva.getId());

        assertEquals(reservaSalva.getId(), reservaEntity.getId());
        assertEquals(reservaSalva.getNomeUsuario(), reservaEntity.getNomeUsuario());
    }

    @Test
    void buscaReservaPeloId_reservaNaoEncontrada() {
        assertThrows(
                RuntimeException.class,
                () -> reservaRepositorioJpa.buscaReservaPeloId(999L),
                "Reserva não encontrada"
        );
    }
}
