package br.com.fiap.reservas.infra.config;

import br.com.fiap.reservas.gateway.AvaliacaoGateway;
import br.com.fiap.reservas.infra.gateway.AvaliacaoRespositorioJpa;
import br.com.fiap.reservas.infra.repository.avaliacao.AvaliacaoRepository;
import br.com.fiap.reservas.interfaces.IAvaliacaoGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AvaliacaoConfig {

    @Bean
    AvaliacaoGateway criaBuscarAvaliacaoController( IAvaliacaoGateway avaliacaoGateway) {
        return new AvaliacaoGateway(avaliacaoGateway);

    }

    @Bean
    AvaliacaoRespositorioJpa criaAvaliacaoRepositorioJpa(AvaliacaoRepository avaliacaoRepository) {
        return new AvaliacaoRespositorioJpa(avaliacaoRepository);
    }
}
