package br.com.fiap.reservas.infra.repository.avaliacao;

import br.com.fiap.reservas.infra.repository.endereco.Endereco;
import br.com.fiap.reservas.infra.repository.endereco.EnderecoRepository;
import br.com.fiap.reservas.infra.repository.restaurante.Restaurante;
import br.com.fiap.reservas.infra.repository.restaurante.RestauranteRepository;
import br.com.fiap.reservas.infra.repository.usuario.Usuario;
import br.com.fiap.reservas.infra.repository.usuario.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AvaliacaoTest {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void validaSalvarAvaliacao() {
        Endereco endereco = new Endereco("89670000", "logradouro","bairro","cidade","numero","complemento");
        Endereco savedEndereco = enderecoRepository.save(endereco);

        Restaurante restaurante = new Restaurante("restaurante", savedEndereco.getId(), "tipo", LocalTime.now(), LocalTime.now(), 50);
        Restaurante savedRestaurante = restauranteRepository.save(restaurante);

        Usuario usuario = new Usuario(1L, "nome", "email", "senha");
        Usuario savedUsuario = usuarioRepository.save(usuario);

        Avaliacao avaliacao = new Avaliacao(savedRestaurante.getId(), 5, "Ótimo restaurante", savedUsuario.getId());;
        Avaliacao savedAvaliacao = avaliacaoRepository.save(avaliacao);

        assertNotNull(savedAvaliacao.getId());
        assertEquals(5, savedAvaliacao.getNota());
        assertEquals("Ótimo restaurante", savedAvaliacao.getComentario());
        assertEquals(1L, savedAvaliacao.getUsuarioId());
    }

    @Test
    void validaUpdateAvaliacao() {
        Endereco endereco = new Endereco("89670000", "logradouro","bairro","cidade","numero","complemento");
        Endereco savedEndereco = enderecoRepository.save(endereco);

        Restaurante restaurante = new Restaurante("restaurante", savedEndereco.getId(), "tipo", LocalTime.now(), LocalTime.now(), 50);
        Restaurante savedRestaurante = restauranteRepository.save(restaurante);

        Usuario usuario = new Usuario( "nome", "email", "senha");
        Usuario savedUsuario = usuarioRepository.save(usuario);

        Avaliacao avaliacao = new Avaliacao(savedRestaurante.getId(), 5, "Ótimo restaurante", savedUsuario.getId());;
        Avaliacao savedAvaliacao = avaliacaoRepository.save(avaliacao);

        Restaurante newRestaurante = new Restaurante("novo restaurante", savedEndereco.getId(), "novo tipo", LocalTime.now(), LocalTime.now(), 50);
        Restaurante savedNewRestaurante = restauranteRepository.save(newRestaurante);

        Usuario newUsuario = new Usuario("novo nome", "novo email", "nova senha");
        Usuario savedNewUsuario = usuarioRepository.save(newUsuario);

        savedAvaliacao.setRestauranteId(savedNewRestaurante.getId());
        savedAvaliacao.setNota(10);
        savedAvaliacao.setComentario("Melhor restaurante");
        savedAvaliacao.setUsuarioId(savedNewUsuario.getId());
        avaliacaoRepository.save(savedAvaliacao);

        assertEquals(savedNewRestaurante.getId(), savedAvaliacao.getRestauranteId());
        assertEquals(10, savedAvaliacao.getNota());
        assertEquals("Melhor restaurante", savedAvaliacao.getComentario());
        assertEquals(savedNewUsuario.getId(), savedAvaliacao.getUsuarioId());
    }
}