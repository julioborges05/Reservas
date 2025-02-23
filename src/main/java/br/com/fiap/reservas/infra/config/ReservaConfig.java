package br.com.fiap.reservas.infra.config;

import br.com.fiap.reservas.controller.CadastrarReservaController;
import br.com.fiap.reservas.infra.gateway.ReservaRepositorioJpa;
import br.com.fiap.reservas.infra.repository.reserva.ReservaRepository;
import br.com.fiap.reservas.interfaces.IMesaGateway;
import br.com.fiap.reservas.interfaces.IReservaGateway;
import br.com.fiap.reservas.interfaces.IRestauranteGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservaConfig {

    @Bean
    CadastrarReservaController criaCadastrarReservaController(IReservaGateway reservaGateway,
                                                              IRestauranteGateway restauranteGateway,
                                                              IMesaGateway mesasGateway) {
        return new CadastrarReservaController(reservaGateway, restauranteGateway, mesasGateway);
    }

    @Bean
    ReservaRepositorioJpa criaReservaRepositorioJpa(ReservaRepository reservaRepository) {
        return new ReservaRepositorioJpa(reservaRepository);
    }
}
