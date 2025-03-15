package br.com.fiap.reservas.infra.springController;

import br.com.fiap.reservas.controller.CadastrarUsuarioController;
import br.com.fiap.reservas.controller.dto.UsuarioDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioSpringController {

    private final CadastrarUsuarioController cadastrarUsuarioController;

    public UsuarioSpringController(CadastrarUsuarioController cadastrarUsuarioController) {
        this.cadastrarUsuarioController = cadastrarUsuarioController;
    }

    @PostMapping
    public UsuarioDto cadastrarUsuario(@RequestBody UsuarioDto usuarioDto) {
        return cadastrarUsuarioController.cadastrarUsuario(usuarioDto.nome(), usuarioDto.email(), usuarioDto.senha());
    }

}
