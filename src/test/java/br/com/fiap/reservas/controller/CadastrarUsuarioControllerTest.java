package br.com.fiap.reservas.controller;

import br.com.fiap.reservas.controller.dto.UsuarioDto;
import br.com.fiap.reservas.entities.UsuarioEntity;
import br.com.fiap.reservas.infra.gateway.UsuarioRepositorioJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CadastrarUsuarioControllerTest {

    private CadastrarUsuarioController cadastrarUsuarioController;

    @Mock
    private UsuarioRepositorioJpa usuarioRepositorioJpa;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        cadastrarUsuarioController = new CadastrarUsuarioController(usuarioRepositorioJpa);
    }

    @Test
    void cadastrarUsuarioComSucesso() {
        when(usuarioRepositorioJpa.cadastrarUsuario(any())).thenReturn(new UsuarioEntity("nome", "email", "senha"));

        UsuarioDto resultado = cadastrarUsuarioController.cadastrarUsuario("nome", "email", "senha");

        assertEquals("nome", resultado.nome());
        assertEquals("email", resultado.email());
        assertEquals("senha", resultado.senha());
    }

}
