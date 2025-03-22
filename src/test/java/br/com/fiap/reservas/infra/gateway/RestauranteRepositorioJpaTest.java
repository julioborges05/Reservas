package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.enums.StatusMesa;
import br.com.fiap.reservas.infra.repository.endereco.Endereco;
import br.com.fiap.reservas.infra.repository.endereco.EnderecoRepository;
import br.com.fiap.reservas.infra.repository.mesa.MesaRepository;
import br.com.fiap.reservas.infra.repository.restaurante.Restaurante;
import br.com.fiap.reservas.infra.repository.restaurante.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RestauranteRepositorioJpaTest {

    private RestauranteRepositorioJpa restauranteRepositorioJpa;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private MesaRepository mesaRepository;

    private Endereco enderecoSalvo;
    private Restaurante restauranteSalvo;

    @BeforeEach
    void setUp() {
        enderecoSalvo = enderecoRepository.save(new Endereco("Rua Teste", "123", "Bairro Teste", "Cidade Teste", "Estado Teste", "CEP Teste"));
        restauranteSalvo = restauranteRepository.save(new Restaurante("Restaurante Teste", enderecoSalvo.getId(), "Comida Brasileira", LocalTime.of(10, 0), LocalTime.of(22, 0), 100));
        restauranteRepositorioJpa = new RestauranteRepositorioJpa(restauranteRepository, enderecoRepository, mesaRepository);
    }

    @Test
    void buscarRestaurantePorNome_restauranteInexistente() {
        assertThrows(
                RuntimeException.class,
                () -> restauranteRepositorioJpa.buscarRestaurantePorNome("Restaurante Inexistente"),
                "Restaurante não encontrado"
        );
    }

    @Test
    void buscarRestaurantePorNome_restauranteExistente() {
        RestauranteEntity result = restauranteRepositorioJpa.buscarRestaurantePorNome("Restaurante Teste");

        assertNotNull(result);
        assertEquals("Restaurante Teste", result.getNome());
    }

    @Test
    void buscarRestaurantePorLocalizacao_restauranteNaoEncontrado() {
        assertThrows(
                RuntimeException.class,
                () -> restauranteRepositorioJpa.buscarRestaurantePorLocalizacao("Cidade Inexistente"),
                "Restaurante não encontrado"
        );
    }

    @Test
    void buscarRestaurantePorLocalizacao_restauranteEncontrado() {
        RestauranteEntity result = restauranteRepositorioJpa.buscarRestaurantePorLocalizacao("Rua Teste");

        assertNotNull(result);
        assertEquals("Restaurante Teste", result.getNome());
    }

    @Test
    void buscarRestaurantePorTipoCozinha_restauranteExistente() {
        RestauranteEntity result = restauranteRepositorioJpa.buscarRestaurantePorTipoCozinha("Comida Brasileira");

        assertNotNull(result);
        assertEquals("Restaurante Teste", result.getNome());
    }

    @Test
    void buscarRestaurantePorTipoCozinha_restauranteNaoEncontrado() {
        assertThrows(
                RuntimeException.class,
                () -> restauranteRepositorioJpa.buscarRestaurantePorTipoCozinha("Cidade Inexistente"),
                "Restaurante não encontrado"
        );
    }

    @Test
    void buscarRestaurantePorNomeLocalizacaoETipoCozinha_restauranteExistente() {
        RestauranteEntity result = restauranteRepositorioJpa.buscarRestaurantePorNomeLocalizacaoETipoCozinha("Restaurante Teste", "Cidade Teste", "Comida Brasileira");

        assertNotNull(result);
        assertEquals("Restaurante Teste", result.getNome());
    }

    @Test
    void buscarRestaurantePorNomeLocalizacaoETipoCozinha_restauranteNaoEncontrado() {
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                restauranteRepositorioJpa.buscarRestaurantePorNomeLocalizacaoETipoCozinha("Restaurante Inexistente", "Cidade Inexistente", "Comida Inexistente"));

        assertEquals("Restaurante não encontrado", exception.getMessage());
    }

    @Test
    void cadastrarRestaurante() {
        EnderecoEntity enderecoEntity = enderecoSalvo.converterParaEntity();
        RestauranteEntity restauranteEntity = new RestauranteEntity("Novo Restaurante", enderecoEntity,
                "Comida Italiana", LocalTime.of(11, 0), LocalTime.of(23, 0),
                80, List.of());

        RestauranteEntity result = restauranteRepositorioJpa.cadastrarRestaurante(restauranteEntity);

        assertNotNull(result);
        assertEquals("Novo Restaurante", result.getNome());
    }

    @Test
    void findById_restauranteExistente() {
        RestauranteEntity result = restauranteRepositorioJpa.findById(restauranteSalvo.getId());

        assertNotNull(result);
        assertEquals("Restaurante Teste", result.getNome());
    }

    @Test
    void findById_restauranteNaoEncontrado() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> restauranteRepositorioJpa.findById(999L));

        assertEquals("Restaurante não encontrado", exception.getMessage());
    }

    @Test
    void buscarRestaurantePorId_restauranteExistente() {
        RestauranteEntity result = restauranteRepositorioJpa.buscarRestaurantePorId(restauranteSalvo.getId());

        assertNotNull(result);
        assertEquals("Restaurante Teste", result.getNome());
    }

    @Test
    void buscarRestaurantePorId_restauranteNaoEncontrado() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> restauranteRepositorioJpa.buscarRestaurantePorId(999L));

        assertEquals("Restaurante não encontrado", exception.getMessage());
    }

    @Test
    void cadastrarRestaurante_comMesas() {
        EnderecoEntity enderecoEntity = enderecoSalvo.converterParaEntity();
        MesaEntity mesaEntity1 = new MesaEntity(1L, 1, StatusMesa.LIVRE);
        MesaEntity mesaEntity2 = new MesaEntity(1L, 2, StatusMesa.OCUPADA);
        RestauranteEntity restauranteEntity = new RestauranteEntity("Novo Restaurante", enderecoEntity, "Comida Italiana", LocalTime.of(11, 0), LocalTime.of(23, 0), 80, List.of(mesaEntity1, mesaEntity2));

        RestauranteEntity result = restauranteRepositorioJpa.cadastrarRestaurante(restauranteEntity);

        assertNotNull(result);
        assertEquals("Novo Restaurante", result.getNome());
        assertEquals(2, result.getListaMesa().size());
        assertEquals(StatusMesa.LIVRE, result.getListaMesa().get(0).getStatusMesa());
        assertEquals(StatusMesa.OCUPADA, result.getListaMesa().get(1).getStatusMesa());
    }
}