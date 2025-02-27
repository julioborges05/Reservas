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
import java.util.Optional;

public class RestauranteRepositorioJpa implements IRestauranteGateway {

    private final RestauranteRepository restauranteRepository;
    private final EnderecoRepository enderecoRepository;

    public RestauranteRepositorioJpa(RestauranteRepository restauranteRepository, EnderecoRepository enderecoRepository) {
        this.restauranteRepository = restauranteRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public RestauranteEntity buscarRestaurantePorNome(String nome) {
        Restaurante restaurante = restauranteRepository.findByNome(nome);
        Optional<Endereco> endereco = enderecoRepository.findById(restaurante.getIdEndereco());
        EnderecoEntity enderecoEntity = endereco
                .map(value ->
                        new EnderecoEntity(value.getCep(), value.getLogradouro(), value.getBairro(), value.getCidade(), value.getNumero(), value.getComplemento()))
                .orElse(null);

        return new RestauranteEntity(restaurante.getNome(), enderecoEntity, restaurante.getTipo(), restaurante.getHorarioAbertura(), restaurante.getHorarioFechamento(),
                restaurante.getCapacidade(), new ArrayList<>());
    }

    @Override
    public RestauranteEntity buscarRestaurantePorLocalizacao(String localizacao) {
        Restaurante restaurante = restauranteRepository.findByLocalizacao(localizacao);
        Optional<Endereco> endereco = enderecoRepository.findById(restaurante.getIdEndereco());
        EnderecoEntity enderecoEntity = endereco
                .map(value ->
                        new EnderecoEntity(value.getCep(), value.getLogradouro(), value.getBairro(), value.getCidade(), value.getNumero(), value.getComplemento()))
                .orElse(null);

        return new RestauranteEntity(restaurante.getNome(), enderecoEntity, restaurante.getTipo(), restaurante.getHorarioAbertura(), restaurante.getHorarioFechamento(),
                restaurante.getCapacidade(), new ArrayList<>());
    }

    @Override
    public RestauranteEntity buscarRestaurantePorTipoCozinha(String tipoCozinha) {
        Restaurante restaurante = restauranteRepository.findByTipoCozinha(tipoCozinha);
        Optional<Endereco> endereco = enderecoRepository.findById(restaurante.getIdEndereco());
        EnderecoEntity enderecoEntity = endereco
                .map(value ->
                        new EnderecoEntity(value.getCep(), value.getLogradouro(), value.getBairro(), value.getCidade(), value.getNumero(), value.getComplemento()))
                .orElse(null);

        return new RestauranteEntity(restaurante.getNome(), enderecoEntity, restaurante.getTipo(), restaurante.getHorarioAbertura(), restaurante.getHorarioFechamento(),
                restaurante.getCapacidade(), new ArrayList<>());
    }

    @Override
    public RestauranteEntity buscarRestaurantePorNomeLocalizacaoETipoCozinha(String nome, String localizacao, String tipoCozinha) {
        Restaurante restaurante = restauranteRepository.findByNomeLocalizacaoETipoCozinha(nome, localizacao, tipoCozinha);
        Optional<Endereco> endereco = enderecoRepository.findById(restaurante.getIdEndereco());
        EnderecoEntity enderecoEntity = endereco
                .map(value ->
                        new EnderecoEntity(value.getCep(), value.getLogradouro(), value.getBairro(), value.getCidade(), value.getNumero(), value.getComplemento()))
                .orElse(null);

        return new RestauranteEntity(restaurante.getNome(), enderecoEntity, restaurante.getTipo(), restaurante.getHorarioAbertura(), restaurante.getHorarioFechamento(),
                restaurante.getCapacidade(), new ArrayList<>());
    }

    @Override
    public RestauranteEntity cadastrarRestaurante(RestauranteEntity restauranteEntity) {
        Restaurante restaurante = new Restaurante(
                restauranteEntity.getNome(),
                restauranteEntity.getEndereco().getId(),
                restauranteEntity.getTipoCozinha(),
                restauranteEntity.getHorarioAbertura(),
                restauranteEntity.getHorarioFechamento(),
                restauranteEntity.getCapacidade());

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
    public RestauranteEntity findById(Long id) throws Exception {
        Optional<Restaurante> restauranteOptional = restauranteRepository.findById(id);

        if (restauranteOptional.isPresent()) {
            Optional<Endereco> endereco = enderecoRepository.findById(restauranteOptional.get().getIdEndereco());
            EnderecoEntity enderecoEntity = endereco
                    .map(value ->
                            new EnderecoEntity(value.getCep(), value.getLogradouro(), value.getBairro(), value.getCidade(), value.getNumero(), value.getComplemento()))
                    .orElse(null);

            Restaurante restauranteSalvo = restauranteOptional.get();
            RestauranteEntity restauranteEntity = new RestauranteEntity(
                    restauranteSalvo.getNome(),
                    enderecoEntity,
                    restauranteSalvo.getTipo(),
                    restauranteSalvo.getHorarioAbertura(),
                    restauranteSalvo.getHorarioFechamento(),
                    restauranteSalvo.getCapacidade(),
                    new ArrayList<>()
            );
            return restauranteEntity;
        } else {
            throw new Exception("Restaurante não encontrado");
        }
    }
}