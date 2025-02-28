package br.com.fiap.reservas.infra.config;

import br.com.fiap.reservas.controller.AtualizarQtdPessoasReservaController;
import br.com.fiap.reservas.controller.AtualizarStatusReservaController;
import br.com.fiap.reservas.controller.BuscarReservasController;
import br.com.fiap.reservas.controller.CadastrarReservaController;
import br.com.fiap.reservas.infra.gateway.EnderecoRepositorioJpa;
import br.com.fiap.reservas.infra.gateway.ReservaRepositorioJpa;
import br.com.fiap.reservas.infra.gateway.ReservaVMesaRepositorioJpa;
import br.com.fiap.reservas.infra.gateway.RestauranteRepositorioJpa;
import br.com.fiap.reservas.infra.repository.reserva.ReservaRepository;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesaRepository;
import br.com.fiap.reservas.interfaces.IMesaGateway;
import br.com.fiap.reservas.interfaces.IReservaGateway;
import br.com.fiap.reservas.interfaces.IReservaVMesaGateway;
import br.com.fiap.reservas.interfaces.IRestauranteGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservaConfig {

    @Bean
    CadastrarReservaController criaCadastrarReservaController(IReservaGateway reservaGateway,
                                                              IRestauranteGateway restauranteGateway,
                                                              IMesaGateway mesasGateway, IReservaVMesaGateway reservaVMesaGateway) {
        return new CadastrarReservaController(reservaGateway, restauranteGateway, mesasGateway, reservaVMesaGateway);
    }

    @Bean
    BuscarReservasController buscarReservasController(IReservaGateway reservaGateway,
                                                              IRestauranteGateway restauranteGateway) {
        return new BuscarReservasController(reservaGateway, restauranteGateway);
    }

    @Bean
    AtualizarStatusReservaController atualizarStatusReservaController(IReservaGateway reservaGateway) {
        return new AtualizarStatusReservaController(reservaGateway);
    }

    @Bean
    AtualizarQtdPessoasReservaController atualizarQtdPessoasReservaController(IReservaGateway reservaGateway,
                                                                              IRestauranteGateway restauranteGateway,
                                                                              IMesaGateway mesaGateway) {
        return new AtualizarQtdPessoasReservaController(reservaGateway, restauranteGateway, mesaGateway);
    }

    @Bean
    ReservaRepositorioJpa criaReservaRepositorioJpa(ReservaRepository reservaRepository,
                                                    EnderecoRepositorioJpa enderecoRepositorioJpa,
                                                    RestauranteRepositorioJpa restauranteRepositorioJpa) {
        return new ReservaRepositorioJpa(reservaRepository, enderecoRepositorioJpa, restauranteRepositorioJpa);
    }

    @Bean
    ReservaVMesaRepositorioJpa criaReservaVMesaRepositorioJpa(ReservaVMesaRepository reservaVMesaRepository) {
        return new ReservaVMesaRepositorioJpa(reservaVMesaRepository);
    }
}
