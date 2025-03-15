package br.com.fiap.reservas.infra.config;

import br.com.fiap.reservas.controller.CadastrarUsuarioController;
import br.com.fiap.reservas.interfaces.IUsuarioGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioConfig {

    @Bean
    CadastrarUsuarioController criCadastrarUsuarioController(IUsuarioGateway usuarioGateway) {
        return new CadastrarUsuarioController(usuarioGateway);
    }

}
