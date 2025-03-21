package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.AvaliacaoEntity;
import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.entities.UsuarioEntity;
import br.com.fiap.reservas.infra.repository.avaliacao.Avaliacao;
import br.com.fiap.reservas.infra.repository.avaliacao.AvaliacaoRepository;
import br.com.fiap.reservas.infra.repository.endereco.Endereco;
import br.com.fiap.reservas.infra.repository.endereco.EnderecoRepository;
import br.com.fiap.reservas.infra.repository.mesa.MesaRepository;
import br.com.fiap.reservas.infra.repository.restaurante.Restaurante;
import br.com.fiap.reservas.infra.repository.restaurante.RestauranteRepository;
import br.com.fiap.reservas.infra.repository.usuario.Usuario;
import br.com.fiap.reservas.infra.repository.usuario.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AvaliacaoRepositorioJpaTest {

    private AvaliacaoRepositorioJpa avaliacaoRepositorioJpa;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MesaRepository mesaRepository;

    private Usuario usuarioSalvo;
    private Endereco enderecoSalvo;
    private Restaurante restauranteSalvo;

    @BeforeEach
    void setUp() {
        RestauranteRepositorioJpa restauranteRepositorioJpa = new RestauranteRepositorioJpa(restauranteRepository, enderecoRepository, mesaRepository);
        UsuarioRepositorioJpa usuarioRepositorioJpa = new UsuarioRepositorioJpa(usuarioRepository);

        avaliacaoRepositorioJpa = new AvaliacaoRepositorioJpa(avaliacaoRepository, restauranteRepositorioJpa, usuarioRepositorioJpa);

        Usuario usuario = new Usuario("teste", "email", "senha");
        usuarioSalvo = usuarioRepository.save(usuario);

        Endereco endereco = new Endereco("cep", "logradouro", "bairro", "cidade", "numero", "complemento");
        enderecoSalvo = enderecoRepository.save(endereco);

        Restaurante restaurante = new Restaurante("nome", enderecoSalvo.getId(), "tipo", LocalTime.of(10, 0),
                LocalTime.of(22, 0), 0);
        restauranteSalvo = restauranteRepository.save(restaurante);
    }

    @Test
    void avaliarRestauranteComSucesso() {
        UsuarioEntity usuarioEntity = new UsuarioEntity(usuarioSalvo.getId(), usuarioSalvo.getNome(), usuarioSalvo.getEmail(),
                usuarioSalvo.getSenha());
        RestauranteEntity restauranteEntity = getRestauranteEntity();

        AvaliacaoEntity avaliacaoEntity = new AvaliacaoEntity(5, "Muito bom", usuarioEntity, restauranteEntity);

        avaliacaoRepositorioJpa.avaliarRestaurante(avaliacaoEntity);

        Avaliacao avaliacao = avaliacaoRepository.findAvaliacaoByRestaurante(restauranteSalvo.getId());
        assertEquals(5, avaliacao.getNota());
        assertEquals("Muito bom", avaliacao.getComentario());
    }

    @Test
    void buscarAvaliacaoPorRestaurante() {
        UsuarioEntity usuarioEntity = new UsuarioEntity(usuarioSalvo.getId(), usuarioSalvo.getNome(), usuarioSalvo.getEmail(),
                usuarioSalvo.getSenha());
        RestauranteEntity restauranteEntity = getRestauranteEntity();

        Avaliacao avaliacao = new Avaliacao(restauranteEntity.getId(), 5, "Muito bom", usuarioEntity.getId());
        avaliacaoRepository.save(avaliacao);

        AvaliacaoEntity avaliacaoEntity = avaliacaoRepositorioJpa.buscarAvaliacaoPorRestaurante(restauranteEntity.getId());

        assertEquals(5, avaliacaoEntity.getNota());
        assertEquals("Muito bom", avaliacaoEntity.getComentario());
    }

    @Test
    void buscarAvaliacaoPorRestaurante_AvaliacaoNaoEncontrada() {
        assertThrows(
                RuntimeException.class,
                () -> avaliacaoRepositorioJpa.buscarAvaliacaoPorRestaurante(1L),
                "Avaliação não encontrada"
        );
    }

    private RestauranteEntity getRestauranteEntity() {
        EnderecoEntity enderecoEntity = new EnderecoEntity(enderecoSalvo.getId(), enderecoSalvo.getCep(),
                enderecoSalvo.getLogradouro(), enderecoSalvo.getBairro(), enderecoSalvo.getCidade(),
                enderecoSalvo.getNumero(), enderecoSalvo.getComplemento());
        RestauranteEntity restauranteEntity = new RestauranteEntity(restauranteSalvo.getId(), restauranteSalvo.getNome(), enderecoEntity,
                restauranteSalvo.getTipo(), restauranteSalvo.getHorarioAbertura(), restauranteSalvo.getHorarioFechamento(),
                restauranteSalvo.getCapacidade(), null);
        return restauranteEntity;
    }

}