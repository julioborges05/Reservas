package br.com.fiap.reservas.controller;

import br.com.fiap.reservas.controller.dto.EnderecoDto;
import br.com.fiap.reservas.controller.dto.RestauranteDto;
import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.infra.gateway.EnderecoRepositorioJpa;
import br.com.fiap.reservas.infra.gateway.RestauranteRepositorioJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CadastrarRestauranteControllerTest {

    private CadastrarRestauranteController cadastrarRestauranteController;

    @Mock
    private RestauranteRepositorioJpa restauranteRepositorioJpa;

    @Mock
    private EnderecoRepositorioJpa enderecoRepositorioJpa;

    @Captor
    private ArgumentCaptor<RestauranteEntity> restauranteEntityArgumentCaptor;

    @Captor
    private ArgumentCaptor<EnderecoEntity> enderecoEntityArgumentCaptor;

    private final LocalTime horarioAbertura = LocalTime.of(10, 0);
    private final LocalTime horarioFechamento = LocalTime.of(22, 0);
    private final EnderecoDto enderecoDto = new EnderecoDto("cep", "logradouro", "bairro", "cidade", "numero", "complemento");
    private final RestauranteDto restauranteDto = new RestauranteDto(1L, "nome", enderecoDto,
            "tipoCozinha", horarioAbertura, horarioFechamento, 10);

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        cadastrarRestauranteController = new CadastrarRestauranteController(restauranteRepositorioJpa, enderecoRepositorioJpa);
    }

    @Test
    void cadastrarRestauranteComSucesso() {
        when(enderecoRepositorioJpa.cadastrarEndereco(any())).thenReturn(new EnderecoEntity("cep", "logradouro",
                "bairro", "cidade", "numero", "complemento"));
        when(restauranteRepositorioJpa.cadastrarRestaurante(any())).thenReturn(new RestauranteEntity("nome",
                new EnderecoEntity("cep", "logradouro", "bairro", "cidade", "numero", "complemento"),
                "tipoCozinha", horarioAbertura, horarioFechamento, 10, List.of()));

        cadastrarRestauranteController.cadastrarRestaurante(restauranteDto);

        verify(restauranteRepositorioJpa, times(1)).cadastrarRestaurante(restauranteEntityArgumentCaptor.capture());
        verify(enderecoRepositorioJpa, times(1)).cadastrarEndereco(enderecoEntityArgumentCaptor.capture());

        RestauranteEntity resultado = restauranteEntityArgumentCaptor.getValue();
        EnderecoEntity resultadoEndereco = enderecoEntityArgumentCaptor.getValue();
        List<MesaEntity> resultadoMesas = resultado.getListaMesa();

        assertEquals("nome", resultado.getNome());
        assertEquals("cep", resultadoEndereco.getCep());
        assertEquals("logradouro", resultadoEndereco.getLogradouro());
        assertEquals("bairro", resultadoEndereco.getBairro());
        assertEquals("cidade", resultadoEndereco.getCidade());
        assertEquals("numero", resultadoEndereco.getNumero());
        assertEquals("complemento", resultadoEndereco.getComplemento());
        assertEquals(3, resultadoMesas.size());
    }

}
