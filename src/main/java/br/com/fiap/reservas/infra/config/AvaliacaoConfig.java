package br.com.fiap.reservas.infra.config;

import br.com.fiap.reservas.controller.BuscarAvaliacaoController;
import br.com.fiap.reservas.controller.RealizarAvaliacaoController;

import br.com.fiap.reservas.infra.gateway.AvaliacaoRespositorioJpa;
import br.com.fiap.reservas.infra.repository.avaliacao.AvaliacaoRepository;
import br.com.fiap.reservas.infra.springController.AvaliacaoSpringController;
import br.com.fiap.reservas.interfaces.IAvaliacaoGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AvaliacaoConfig {

    @Bean
    BuscarAvaliacaoController criaBuscarAvaliacaoController(IAvaliacaoGateway avaliacaoGateway) {
        return new BuscarAvaliacaoController(avaliacaoGateway);
    }

    @Bean
    RealizarAvaliacaoController criaAvaliacaoSpringController(IAvaliacaoGateway avaliacaoGateway) {
        return new RealizarAvaliacaoController(avaliacaoGateway);
    }

    @Bean
    AvaliacaoRespositorioJpa criaAvaliacaoRepositorioJpa(AvaliacaoRepository avaliacaoRepository) {
        return new AvaliacaoRespositorioJpa(avaliacaoRepository);
    }
}
