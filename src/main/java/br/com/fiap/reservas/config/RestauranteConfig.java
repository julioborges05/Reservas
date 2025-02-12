package br.com.fiap.reservas.config;

import br.com.fiap.reservas.controller.BuscaRestauranteController;
import br.com.fiap.reservas.infra.gateway.RestauranteRepositorioJpa;
import br.com.fiap.reservas.infra.repository.RestauranteRepository;
import br.com.fiap.reservas.interfaces.IRestauranteGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestauranteConfig {

    @Bean
    BuscaRestauranteController criaBuscaRestauranteController(IRestauranteGateway restauranteGateway) {
        return new BuscaRestauranteController(restauranteGateway);
    }

    @Bean
    RestauranteRepositorioJpa criaRestauranteRepositorioJpa(RestauranteRepository restauranteRepository) {
        return new RestauranteRepositorioJpa(restauranteRepository);
    }

}
