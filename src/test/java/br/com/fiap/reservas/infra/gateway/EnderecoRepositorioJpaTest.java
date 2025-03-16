package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.infra.repository.endereco.Endereco;
import br.com.fiap.reservas.infra.repository.endereco.EnderecoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class EnderecoRepositorioJpaTest {

    private EnderecoRepositorioJpa enderecoRepositorioJpa;

    @Mock
    private EnderecoRepository enderecoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        enderecoRepositorioJpa = new EnderecoRepositorioJpa(enderecoRepository);
    }

    @Test
    void deveBuscarEnderecoPeloId() {
        Endereco enderecoMock = new Endereco("13181701", "logradouro", "bairro", "cidade", "numero", "complemento");
        when(enderecoRepository.findById(anyLong())).thenReturn(Optional.of(enderecoMock));

        EnderecoEntity resultado = enderecoRepositorioJpa.buscarEnderecoPeloId(1L);

        assertNotNull(resultado);
        assertEquals("13181701", resultado.getCep());
        assertEquals("logradouro", resultado.getLogradouro());
        verify(enderecoRepository).findById(anyLong());
    }

    @Test
    void deveLancarExcecaoQuandoEnderecoNaoEncontrado() {
        when(enderecoRepository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> enderecoRepositorioJpa.buscarEnderecoPeloId(1L));

        assertEquals("Endereço não encontrado", exception.getMessage());
    }
}