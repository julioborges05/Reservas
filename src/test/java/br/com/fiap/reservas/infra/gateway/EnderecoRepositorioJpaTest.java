package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.infra.repository.endereco.Endereco;
import br.com.fiap.reservas.infra.repository.endereco.EnderecoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EnderecoRepositorioJpaTest {

    private EnderecoRepositorioJpa enderecoRepositorioJpa;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @BeforeEach
    void setUp() {
        enderecoRepositorioJpa = new EnderecoRepositorioJpa(enderecoRepository);
    }

    @Test
    void cadastrarEndereco_enderecoExistente() {
        EnderecoEntity enderecoEntity = new EnderecoEntity("cep", "logradouro", "bairro", "cidade", "numero", "complemento");
        Endereco endereco = new Endereco(enderecoEntity);

        enderecoRepository.save(endereco);

        EnderecoEntity resultado = enderecoRepositorioJpa.cadastrarEndereco(enderecoEntity);

        assertNotNull(resultado.getId());
        assertEquals(enderecoEntity.getCep(), resultado.getCep());
        assertEquals(enderecoEntity.getLogradouro(), resultado.getLogradouro());
        assertEquals(enderecoEntity.getBairro(), resultado.getBairro());
        assertEquals(enderecoEntity.getCidade(), resultado.getCidade());
        assertEquals(enderecoEntity.getNumero(), resultado.getNumero());
        assertEquals(enderecoEntity.getComplemento(), resultado.getComplemento());
    }



    @Test
    void cadastrarEndereco_enderecoInexistente() {
        EnderecoEntity enderecoEntity = new EnderecoEntity("cep", "logradouro", "bairro", "cidade", "numero", "complemento");

        EnderecoEntity resultado = enderecoRepositorioJpa.cadastrarEndereco(enderecoEntity);

        assertNotNull(resultado.getId());
        assertEquals(enderecoEntity.getCep(), resultado.getCep());
        assertEquals(enderecoEntity.getLogradouro(), resultado.getLogradouro());
        assertEquals(enderecoEntity.getBairro(), resultado.getBairro());
        assertEquals(enderecoEntity.getCidade(), resultado.getCidade());
        assertEquals(enderecoEntity.getNumero(), resultado.getNumero());
        assertEquals(enderecoEntity.getComplemento(), resultado.getComplemento());
    }

    @Test
    void buscarEnderecoPeloId_enderecoExistente() {
        EnderecoEntity enderecoEntity = new EnderecoEntity("cep", "logradouro", "bairro", "cidade", "numero", "complemento");
        Endereco enderecoSalvo = enderecoRepository.save(new Endereco(enderecoEntity));

        EnderecoEntity resultado = enderecoRepositorioJpa.buscarEnderecoPeloId(enderecoSalvo.getId());

        assertEquals(enderecoSalvo.getId(), resultado.getId());
        assertEquals(enderecoEntity.getCep(), resultado.getCep());
        assertEquals(enderecoEntity.getLogradouro(), resultado.getLogradouro());
        assertEquals(enderecoEntity.getBairro(), resultado.getBairro());
        assertEquals(enderecoEntity.getCidade(), resultado.getCidade());
        assertEquals(enderecoEntity.getNumero(), resultado.getNumero());
        assertEquals(enderecoEntity.getComplemento(), resultado.getComplemento());
    }

    @Test
    void buscarEnderecoPeloId_enderecoInexistente() {
        assertThrows(
                RuntimeException.class,
                () -> enderecoRepositorioJpa.buscarEnderecoPeloId(1L),
                "Endereço não encontrado"
        );
    }

}