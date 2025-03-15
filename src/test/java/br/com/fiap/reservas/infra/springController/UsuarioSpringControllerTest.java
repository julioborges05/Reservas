package br.com.fiap.reservas.infra.springController;

import br.com.fiap.reservas.controller.CadastrarUsuarioController;
import br.com.fiap.reservas.controller.dto.UsuarioDto;
import br.com.fiap.reservas.utils.JsonFormatUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UsuarioSpringControllerTest {

    private MockMvc mockMvc;

    private UsuarioSpringController usuarioSpringController;

    @Mock
    private CadastrarUsuarioController cadastrarUsuarioController;

    AutoCloseable mock;

    @BeforeEach
    public void setup() {
        mock = MockitoAnnotations.openMocks(this);
        usuarioSpringController = new UsuarioSpringController(cadastrarUsuarioController);

        mockMvc = MockMvcBuilders
                .standaloneSetup(usuarioSpringController)
                .build();
    }

    @AfterEach
    public void close() throws Exception {
        mock.close();
    }

    @Test
    public void testCadastrarUsuario() throws Exception {
        UsuarioDto usuarioDto = new UsuarioDto("nome", "email", "senha");
        when(cadastrarUsuarioController.cadastrarUsuario(anyString(), anyString(), anyString())).thenReturn(usuarioDto);

        mockMvc.perform(
                        post("/usuario")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonFormatUtil.asJsonString(usuarioDto))
                ).andExpect(status().isOk());
    }
}
