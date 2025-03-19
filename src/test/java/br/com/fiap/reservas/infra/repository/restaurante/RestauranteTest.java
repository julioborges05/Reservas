package br.com.fiap.reservas.infra.repository.restaurante;

import br.com.fiap.reservas.infra.repository.endereco.Endereco;
import br.com.fiap.reservas.infra.repository.endereco.EnderecoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RestauranteTest {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Test
    void validaSalvarRestaurante() {
        Endereco endereco = new Endereco("89670000", "logradouro","bairro","cidade","numero","complemento");
        Endereco savedEndereco = enderecoRepository.save(endereco);

        LocalTime horarioAbertura = LocalTime.now();
        LocalTime horarioFechamento = horarioAbertura.plusHours(4);
        Restaurante restaurante = new Restaurante("restaurante", savedEndereco.getId(), "tipo", horarioAbertura, horarioFechamento, 50);
        Restaurante savedRestaurante = restauranteRepository.save(restaurante);

        assertNotNull(savedRestaurante.getId());
        assertEquals("restaurante", savedRestaurante.getNome());
        assertEquals(savedEndereco.getId(), savedRestaurante.getIdEndereco());
        assertEquals("tipo", savedRestaurante.getTipo());
        assertEquals(horarioAbertura, savedRestaurante.getHorarioAbertura());
        assertEquals(horarioFechamento, savedRestaurante.getHorarioFechamento());
        assertEquals(50, savedRestaurante.getCapacidade());
    }

    @Test
    void validaUpdateRestaurante() {
        Endereco endereco = new Endereco("89670000", "logradouro","bairro","cidade","numero","complemento");
        Endereco savedEndereco = enderecoRepository.save(endereco);

        LocalTime horarioAbertura = LocalTime.now();
        LocalTime horarioFechamento = horarioAbertura.plusHours(4);
        Restaurante restaurante = new Restaurante("restaurante", savedEndereco.getId(), "tipo", horarioAbertura, horarioFechamento, 50);
        Restaurante savedRestaurante = restauranteRepository.save(restaurante);

        Endereco newEndereco = new Endereco("89600000", "novo logradouro","novo bairro","nova cidade","novo numero","novo complemento");
        Endereco savedNewEndereco = enderecoRepository.save(newEndereco);

        savedRestaurante.setNome("novo restaurante");
        savedRestaurante.setIdEndereco(newEndereco.getId());
        savedRestaurante.setTipo("novo tipo");
        LocalTime newHorarioAbertura = horarioAbertura.plusHours(1);
        savedRestaurante.setHorarioAbertura(newHorarioAbertura);
        LocalTime newHorarioFechamento = horarioFechamento.plusHours(1);
        savedRestaurante.setHorarioFechamento(newHorarioFechamento);
        savedRestaurante.setCapacidade(100);
        savedRestaurante.setMesas(new ArrayList<>());
        Restaurante savedNewRestaurante = restauranteRepository.save(savedRestaurante);

        assertEquals("novo restaurante", savedNewRestaurante.getNome());
        assertEquals(savedNewEndereco.getId(), savedNewRestaurante.getIdEndereco());
        assertEquals("novo tipo", savedNewRestaurante.getTipo());
        assertEquals(newHorarioAbertura, savedNewRestaurante.getHorarioAbertura());
        assertEquals(newHorarioFechamento, savedNewRestaurante.getHorarioFechamento());
        assertEquals(100, savedNewRestaurante.getCapacidade());
        assertEquals(new ArrayList<>(), savedNewRestaurante.getMesas());
    }
}
