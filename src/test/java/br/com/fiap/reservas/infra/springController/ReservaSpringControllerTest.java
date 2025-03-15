package br.com.fiap.reservas.infra.springController;

import br.com.fiap.reservas.controller.AtualizarQtdPessoasReservaController;
import br.com.fiap.reservas.controller.AtualizarStatusReservaController;
import br.com.fiap.reservas.controller.BuscarReservasController;
import br.com.fiap.reservas.controller.CadastrarReservaController;
import br.com.fiap.reservas.controller.dto.BuscarReservasDto;
import br.com.fiap.reservas.controller.dto.DataHoraDto;
import br.com.fiap.reservas.controller.dto.ReservaDto;
import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.enums.StatusMesa;
import br.com.fiap.reservas.utils.DateFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReservaSpringControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CadastrarReservaController cadastrarReservaController;

    @Mock
    private ReservaSpringController reservaSpringController;

    @Mock
    private BuscarReservasController buscarReservasController;

    @Mock
    private AtualizarStatusReservaController atualizarStatusReservaController;

    @Mock
    private AtualizarQtdPessoasReservaController atualizarQtdPessoasReservaController;

    AutoCloseable mock;

    private ReservaEntity reservaEntity;

    private final EnderecoEntity enderecoEntityMock = new EnderecoEntity("13181701", "logradouro",
            "bairro", "cidade", "numero", "complemento");

    @BeforeEach
    public void setup() {
        mock = MockitoAnnotations.openMocks(this);
        reservaSpringController = new ReservaSpringController(
                cadastrarReservaController, buscarReservasController, atualizarStatusReservaController,
                atualizarQtdPessoasReservaController);

        List<MesaEntity> mesasLivres = List.of(new MesaEntity(1, StatusMesa.LIVRE));
        RestauranteEntity restauranteEntity = new RestauranteEntity(
                "Restaurante", enderecoEntityMock, "tipoCozinha", LocalTime.now(), LocalTime.now(),
                10, mesasLivres);
        reservaEntity = new ReservaEntity(restauranteEntity, "nome", List.of(),
                LocalDateTime.now());

        mockMvc = MockMvcBuilders
                .standaloneSetup(reservaSpringController)
                .build();
    }

    @Test
    void cadastrarReservaDeveRetornarReservaDto() throws Exception {
        ReservaDto reservaDto = new ReservaDto(reservaEntity);

        Mockito.when(cadastrarReservaController.cadastrarReserva(Mockito.any()))
                .thenReturn(reservaDto);

        mockMvc.perform(post("/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(reservaDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reservaId").value(reservaDto.reservaId()))
                .andExpect(jsonPath("$.usuario").value(reservaDto.usuario()));
    }

    @Test
    void buscarReservasPorRestauranteIdDeveRetornarListaDeReservas() throws Exception {
        Long restauranteId = 1L;
        List<BuscarReservasDto> reservas = List.of(new BuscarReservasDto(reservaEntity));

        Mockito.when(buscarReservasController.buscarReservasPorRestaurante(Mockito.eq(restauranteId)))
                .thenReturn(reservas);

        mockMvc.perform(get("/reservas/{restauranteId}", restauranteId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(reservas.size()))
                .andExpect(jsonPath("$[0].restauranteId").value(reservas.getFirst().restauranteId()));
    }

    @Test
    void atualizarStatusReservaDeveChamarController() throws Exception {
        String nomeUsuario = "nome";
        DataHoraDto dataHoraDto = new DataHoraDto(DateFormat.convertFromLocalDateTimeToString(LocalDateTime.now()));

        mockMvc.perform(put("/reservas/atualiza-status/{nomeUsuario}", nomeUsuario)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dataHoraDto)))
                .andExpect(status().isOk());

        Mockito.verify(atualizarStatusReservaController).atualizarStatusReserva(nomeUsuario, dataHoraDto.horaChegada());
    }

    @Test
    void atualizarQtdPessoasReservaDeveChamarController() throws Exception {
        ReservaDto reservaDto = new ReservaDto(reservaEntity);

        mockMvc.perform(put("/reservas/atualiza-qtd-pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(reservaDto)))
                .andExpect(status().isOk());

        Mockito.verify(atualizarQtdPessoasReservaController).atualizarQtdPessoasReserva(reservaDto);
    }
}
