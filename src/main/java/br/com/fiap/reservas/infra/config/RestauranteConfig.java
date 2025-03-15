package br.com.fiap.reservas.infra.config;

import br.com.fiap.reservas.controller.BuscaRestauranteController;
import br.com.fiap.reservas.controller.CadastrarRestauranteController;
import br.com.fiap.reservas.infra.gateway.EnderecoRepositorioJpa;
import br.com.fiap.reservas.infra.gateway.RestauranteRepositorioJpa;
import br.com.fiap.reservas.infra.repository.endereco.EnderecoRepository;
import br.com.fiap.reservas.infra.repository.restaurante.RestauranteRepository;
import br.com.fiap.reservas.interfaces.IEnderecoGateway;
import br.com.fiap.reservas.interfaces.IMesaGateway;
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
    CadastrarRestauranteController criaCadastrarRestauranteController(IRestauranteGateway restauranteGateway,
                                                                      IEnderecoGateway enderecoGateway) {
        return new CadastrarRestauranteController(restauranteGateway, enderecoGateway);
    }

    @Bean
    RestauranteRepositorioJpa criaRestauranteRepositorioJpa(RestauranteRepository restauranteRepository, EnderecoRepository enderecoRepository) {
        return new RestauranteRepositorioJpa(restauranteRepository, enderecoRepository);
    }

    @Bean
    EnderecoRepositorioJpa criaEnderecoRepositorioJpa(EnderecoRepository enderecoRepository) {
        return new EnderecoRepositorioJpa(enderecoRepository);
    }

}
