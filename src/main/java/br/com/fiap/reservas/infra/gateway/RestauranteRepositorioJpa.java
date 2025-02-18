package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.infra.repository.restaurante.Restaurante;
import br.com.fiap.reservas.infra.repository.restaurante.RestauranteRepository;
import br.com.fiap.reservas.interfaces.IRestauranteGateway;

import java.time.LocalTime;
import java.util.ArrayList;

public class RestauranteRepositorioJpa implements IRestauranteGateway {

    private final RestauranteRepository restauranteRepository;

    public RestauranteRepositorioJpa(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    @Override
    public RestauranteEntity buscarRestaurantePorNomeELocalizacaoETipo(String nome, String endereco, String tipo) {
        Restaurante restaurante = restauranteRepository.findByNomeAndEnderecoAndTipo(nome, endereco, tipo);

        EnderecoEntity enderecoEntity = new EnderecoEntity("123", "456", "789", "101112",
                "123", "123");

        return new RestauranteEntity(restaurante.getNome(), enderecoEntity, restaurante.getTipo(), LocalTime.now(),
                LocalTime.now(), 10, new ArrayList<>());
    }

    @Override
    public RestauranteEntity cadastrarRestaurante(RestauranteEntity restauranteEntity) {
        Restaurante restaurante = new Restaurante(
                restauranteEntity.getNome(),
                restauranteEntity.getEndereco().getId(),
                restauranteEntity.getTipoCozinha(),
                restauranteEntity.getHorarioAbertura(),
                restauranteEntity.getHorarioFechamento(),
                10);

        Restaurante restauranteSalvo = restauranteRepository.save(restaurante);

        return new RestauranteEntity(
                restauranteSalvo.getNome(),
                restauranteEntity.getEndereco(),
                restauranteSalvo.getTipo(),
                restauranteSalvo.getHorarioAbertura(),
                restauranteSalvo.getHorarioFechamento(),
                restauranteSalvo.getCapacidade(),
                new ArrayList<>()
        );
    }
}
