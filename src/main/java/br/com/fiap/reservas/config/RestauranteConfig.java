package br.com.fiap.reservas.config;

import br.com.fiap.reservas.controller.BuscaRestauranteController;
import br.com.fiap.reservas.infra.gateway.RestauranteRepositorioJpa;
import br.com.fiap.reservas.infra.repository.RestauranteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestauranteConfig {

    @Bean
    BuscaRestauranteController criaRestauranteController(RestauranteRepositorioJpa restauranteGateway) {
        return new BuscaRestauranteController(restauranteGateway);
    }

    @Bean
    RestauranteRepositorioJpa criaRestauranteRepositorioJpa(RestauranteRepository restauranteGateway) {
        return new RestauranteRepositorioJpa(restauranteGateway);
    }

}
