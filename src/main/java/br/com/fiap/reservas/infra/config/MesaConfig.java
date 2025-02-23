package br.com.fiap.reservas.infra.config;

import br.com.fiap.reservas.controller.CadastrarMesaController;
import br.com.fiap.reservas.infra.gateway.MesaRepositorioJpa;
import br.com.fiap.reservas.infra.repository.mesa.MesaRepository;
import br.com.fiap.reservas.interfaces.IMesaGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MesaConfig {

    @Bean
    CadastrarMesaController criaCadastrarMesasController(IMesaGateway mesaGateway) {
        return new CadastrarMesaController(mesaGateway);
    }

    @Bean
    MesaRepositorioJpa criaMesasRepositorioJpa(MesaRepository mesaRepository) {
        return new MesaRepositorioJpa(mesaRepository);
    }
}
