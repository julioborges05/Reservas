package br.com.fiap.reservas.infra.springController;

import br.com.fiap.reservas.controller.AtualizarQtdPessoasReservaController;
import br.com.fiap.reservas.controller.AtualizarStatusReservaController;
import br.com.fiap.reservas.controller.BuscarReservasController;
import br.com.fiap.reservas.controller.CadastrarReservaController;
import br.com.fiap.reservas.controller.dto.BuscarReservasDto;
import br.com.fiap.reservas.controller.dto.DataHoraDto;
import br.com.fiap.reservas.controller.dto.ReservaDto;
import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.utils.JsonFormatUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReservaSpringControllerTest {

    private ReservaSpringController reservaSpringController;

    @Mock
    private CadastrarReservaController cadastrarReservaController;

    @Mock
    private BuscarReservasController buscarReservasController;

    @Mock
    private AtualizarStatusReservaController atualizarStatusReservaController;

    @Mock
    private AtualizarQtdPessoasReservaController atualizarQtdPessoasReservaController;

    private MockMvc mockMvc;
    private AutoCloseable mock;

    private final EnderecoEntity endereco = new EnderecoEntity("cep", "logradouro", "bairro", "cidade", "numero", "complemento");
    private final RestauranteEntity restauranteEntity = new RestauranteEntity("nome", endereco, "tipo", LocalTime.of(10, 0),
            LocalTime.of(22, 0), 10);

    @BeforeEach
    public void setup() {
        mock = MockitoAnnotations.openMocks(this);
        reservaSpringController = new ReservaSpringController(cadastrarReservaController, buscarReservasController,
                atualizarStatusReservaController, atualizarQtdPessoasReservaController);

        mockMvc = MockMvcBuilders
                .standaloneSetup(reservaSpringController)
                .build();
    }

    @AfterEach
    public void close() throws Exception {
        mock.close();
    }

    @Test
    public void cadastrarReserva() throws Exception {
        ReservaDto reservaDto = new ReservaDto(new ReservaEntity(1L, restauranteEntity, "usuario", List.of(),
                LocalDateTime.of(2021, 10, 10, 10, 0)));

        when(cadastrarReservaController.cadastrarReserva(reservaDto)).thenReturn(reservaDto);

        mockMvc.perform(post("/reservas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonFormatUtil.asJsonString(reservaDto))
        ).andExpect(status().isOk());

        verify(cadastrarReservaController, times(1)).cadastrarReserva(reservaDto);
    }

    @Test
    public void buscarReservasPorRestauranteId() throws Exception {
        Long restauranteId = 1L;
        List<BuscarReservasDto> reservas = Collections.singletonList(new BuscarReservasDto(new ReservaEntity(restauranteEntity, "usuario", List.of(), LocalDateTime.now())));

        when(buscarReservasController.buscarReservasPorRestaurante(restauranteId)).thenReturn(reservas);

        mockMvc.perform(get("/reservas/{restauranteId}", restauranteId)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        verify(buscarReservasController, times(1)).buscarReservasPorRestaurante(restauranteId);
    }

    @Test
    public void atualizarStatusReserva() throws Exception {
        String nomeUsuario = "usuario";
        DataHoraDto dataHoraDto = new DataHoraDto("10:00");

        doNothing().when(atualizarStatusReservaController).atualizarStatusReserva(nomeUsuario, dataHoraDto.horaChegada());

        mockMvc.perform(put("/reservas/atualiza-status/{nomeUsuario}", nomeUsuario)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonFormatUtil.asJsonString(dataHoraDto))
        ).andExpect(status().isOk());

        verify(atualizarStatusReservaController, times(1)).atualizarStatusReserva(nomeUsuario, dataHoraDto.horaChegada());
    }

    @Test
    public void atualizarQtdPessoasReserva() throws Exception {
        ReservaDto reservaDto = new ReservaDto(new ReservaEntity(1L, restauranteEntity, "usuario", List.of(),
                LocalDateTime.of(2021, 10, 10, 10, 0)));

        doNothing().when(atualizarQtdPessoasReservaController).atualizarQtdPessoasReserva(reservaDto);

        mockMvc.perform(put("/reservas/atualiza-qtd-pessoas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonFormatUtil.asJsonString(reservaDto))
        ).andExpect(status().isOk());

        verify(atualizarQtdPessoasReservaController, times(1)).atualizarQtdPessoasReserva(reservaDto);
    }
}