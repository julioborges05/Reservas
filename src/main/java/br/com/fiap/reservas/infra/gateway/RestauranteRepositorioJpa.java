package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.enums.StatusMesa;
import br.com.fiap.reservas.infra.repository.mesa.Mesa;
import br.com.fiap.reservas.infra.repository.mesa.MesaPK;
import br.com.fiap.reservas.infra.repository.mesa.MesaRepository;
import br.com.fiap.reservas.infra.repository.endereco.Endereco;
import br.com.fiap.reservas.infra.repository.endereco.EnderecoRepository;
import br.com.fiap.reservas.infra.repository.restaurante.Restaurante;
import br.com.fiap.reservas.infra.repository.restaurante.RestauranteRepository;
import br.com.fiap.reservas.interfaces.IRestauranteGateway;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RestauranteRepositorioJpa implements IRestauranteGateway {

    private final RestauranteRepository restauranteRepository;
    private final EnderecoRepository enderecoRepository;
    private final MesaRepository mesaRepository;

    public RestauranteRepositorioJpa(RestauranteRepository restauranteRepository, EnderecoRepository enderecoRepository, MesaRepository mesaRepository) {
        this.restauranteRepository = restauranteRepository;
        this.enderecoRepository = enderecoRepository;
        this.mesaRepository = mesaRepository;
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

        Restaurante restaurante = new Restaurante(restauranteEntity);

        Restaurante restauranteSalvo = restauranteRepository.save(restaurante);

        List<Mesa> mesas = restauranteEntity.getListaMesa().stream().map(mesaEntity -> {
            MesaPK mesaPK = new MesaPK();
            mesaPK.setNumeroMesa(mesaEntity.getNumero());
            mesaPK.setRestauranteId(restauranteSalvo.getId());
            return new Mesa(mesaPK, restauranteSalvo, mesaEntity.getStatusMesa());
        }).toList();

        List<MesaEntity> mesasSalvas =  mesaRepository.saveAll(mesas)
                .stream()
                .map(mesa -> new MesaEntity(restaurante.getId(), mesa.getNumero(), mesa.getStatusMesa())).toList();



        return new RestauranteEntity(
                restauranteSalvo.getId(),
                restauranteSalvo.getNome(),
                restauranteEntity.getEndereco(),
                restauranteSalvo.getTipo(),
                restauranteSalvo.getHorarioAbertura(),
                restauranteSalvo.getHorarioFechamento(),
                restauranteSalvo.getCapacidade(),
                mesasSalvas
        );
    }

    @Override
    public RestauranteEntity findById(Long id) {
        Optional<Restaurante> restauranteOptional = restauranteRepository.findById(id);

        if (restauranteOptional.isPresent()) {
            return converteEmRestauranteEntity(restauranteOptional.get());
        }

        throw new RuntimeException("Restaurante não encontrado");
    }

    @Override
    public RestauranteEntity buscarRestaurantePorId(Long restauranteId) {
        Optional<Restaurante> restauranteOptional = restauranteRepository.findById(restauranteId);

        if (restauranteOptional.isPresent()) {
            return converteEmRestauranteEntity(restauranteOptional.get());
        }

        throw new RuntimeException("Restaurante não encontrado");
    }

    private RestauranteEntity converteEmRestauranteEntity(Restaurante restaurante) {
        Optional<Endereco> endereco = enderecoRepository.findById(restaurante.getIdEndereco());

        EnderecoEntity enderecoEntity = endereco
                .map(value -> new EnderecoEntity(value.getCep(), value.getLogradouro(), value.getBairro(),
                        value.getCidade(), value.getNumero(), value.getComplemento()))
                .orElse(null);

        return new RestauranteEntity(
                restaurante.getId(),
                restaurante.getNome(),
                enderecoEntity,
                restaurante.getTipo(),
                restaurante.getHorarioAbertura(),
                restaurante.getHorarioFechamento(),
                restaurante.getCapacidade(),
                new ArrayList<>()
        );
    }


}