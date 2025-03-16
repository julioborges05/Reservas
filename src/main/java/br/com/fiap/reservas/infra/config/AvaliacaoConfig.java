package br.com.fiap.reservas.infra.config;

import br.com.fiap.reservas.controller.BuscarAvaliacaoController;
import br.com.fiap.reservas.controller.RealizarAvaliacaoController;

import br.com.fiap.reservas.infra.gateway.AvaliacaoRepositorioJpa;
import br.com.fiap.reservas.infra.gateway.RestauranteRepositorioJpa;
import br.com.fiap.reservas.infra.gateway.UsuarioRepositorioJpa;
import br.com.fiap.reservas.infra.repository.avaliacao.AvaliacaoRepository;
import br.com.fiap.reservas.infra.repository.usuario.UsuarioRepository;
import br.com.fiap.reservas.interfaces.IAvaliacaoGateway;
import br.com.fiap.reservas.interfaces.IRestauranteGateway;
import br.com.fiap.reservas.interfaces.IUsuarioGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AvaliacaoConfig {

    @Bean
    BuscarAvaliacaoController criaBuscarAvaliacaoController(IAvaliacaoGateway avaliacaoGateway) {
        return new BuscarAvaliacaoController(avaliacaoGateway);
    }

    @Bean
    RealizarAvaliacaoController criaAvaliacaoSpringController(IAvaliacaoGateway avaliacaoGateway, IUsuarioGateway usuarioGateway,
                                                              IRestauranteGateway restauranteGateway) {
        return new RealizarAvaliacaoController(avaliacaoGateway, usuarioGateway, restauranteGateway);
    }

    @Bean
    AvaliacaoRepositorioJpa criaAvaliacaoRepositorioJpa(AvaliacaoRepository avaliacaoRepository,
                                                        RestauranteRepositorioJpa restauranteRepositorioJpa,
                                                        UsuarioRepositorioJpa usuarioRepositorioJpa) {
        return new AvaliacaoRepositorioJpa(avaliacaoRepository, restauranteRepositorioJpa, usuarioRepositorioJpa);
    }

    @Bean
    UsuarioRepositorioJpa criaUsuarioRepositorioJpa(UsuarioRepository usuarioRepository) {
        return new UsuarioRepositorioJpa(usuarioRepository);
    }
}
