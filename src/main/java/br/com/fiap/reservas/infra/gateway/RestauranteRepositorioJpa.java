package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.infra.repository.endereco.Endereco;
import br.com.fiap.reservas.infra.repository.endereco.EnderecoRepository;
import br.com.fiap.reservas.infra.repository.restaurante.Restaurante;
import br.com.fiap.reservas.infra.repository.restaurante.RestauranteRepository;
import br.com.fiap.reservas.interfaces.IRestauranteGateway;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestauranteRepositorioJpa implements IRestauranteGateway {

    private final RestauranteRepository restauranteRepository;
    private final EnderecoRepository enderecoRepository;

    public RestauranteRepositorioJpa(RestauranteRepository restauranteRepository, EnderecoRepository enderecoRepository) {
        this.restauranteRepository = restauranteRepository;
        this.enderecoRepository = enderecoRepository;
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

    @Override
    public List<RestauranteEntity> buscarRestaurantePorNome(String nome) {
        List<Restaurante> restaurante = restauranteRepository.findByNome(nome);

        ArrayList<RestauranteEntity> restaurantes = new ArrayList<>();

        for(Restaurante r : restaurante) {
            Endereco endereco = enderecoRepository.findById(r.getIdEndereco()).orElseThrow();

            EnderecoEntity enderecoEntity = new EnderecoEntity(endereco.getCep(), endereco.getLogradouro(),
                    endereco.getBairro(), endereco.getCidade(), endereco.getNumero(), endereco.getComplemento());

            restaurantes.add(new RestauranteEntity(r.getNome(), enderecoEntity, r.getTipo(), r.getHorarioAbertura(),
                r.getHorarioFechamento(), r.getCapacidade(), new ArrayList<>()));
        }

        return restaurantes;
    }
}
