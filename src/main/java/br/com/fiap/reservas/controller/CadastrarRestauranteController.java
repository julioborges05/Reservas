package br.com.fiap.reservas.controller;

import br.com.fiap.reservas.controller.dto.EnderecoDto;
import br.com.fiap.reservas.controller.dto.RestauranteDto;
import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.infra.repository.mesa.Mesa;
import br.com.fiap.reservas.infra.repository.mesa.MesaPK;
import br.com.fiap.reservas.infra.repository.restaurante.Restaurante;
import br.com.fiap.reservas.interfaces.IEnderecoGateway;
import br.com.fiap.reservas.interfaces.IMesaGateway;
import br.com.fiap.reservas.interfaces.IRestauranteGateway;
import br.com.fiap.reservas.usecases.CadastrarMesasUseCase;
import br.com.fiap.reservas.usecases.CadastraRestauranteUseCase;
import br.com.fiap.reservas.usecases.CadastrarEnderecoUseCase;

import java.util.ArrayList;
import java.util.List;

public class CadastrarRestauranteController {

    private final IRestauranteGateway restauranteGateway;
    private final IEnderecoGateway enderecoGateway;
    private final IMesaGateway mesaGateway;

    public CadastrarRestauranteController(IRestauranteGateway restauranteGateway,
                                          IEnderecoGateway enderecoGateway,
                                          IMesaGateway mesaGateway) {
        this.restauranteGateway = restauranteGateway;
        this.enderecoGateway = enderecoGateway;
        this.mesaGateway = mesaGateway;
    }

    public RestauranteDto cadastrarRestaurante(RestauranteDto restaurante) {
        EnderecoDto endereco = restaurante.endereco();
        EnderecoEntity enderecoEntity = CadastrarEnderecoUseCase.cadastrarEndereco(endereco.cep(), endereco.logradouro(),
                endereco.bairro(), endereco.cidade(), endereco.numero(), endereco.complemento());
        EnderecoEntity enderecoSalvo = enderecoGateway.cadastrarEndereco(enderecoEntity);
        List<MesaEntity> listaMesas = CadastrarMesasUseCase.cadastrarMesas(restaurante.capacidade());

        RestauranteEntity restauranteEntity = CadastraRestauranteUseCase.cadastrarRestaurante(restaurante.nome(),
                enderecoSalvo, restaurante.tipoCozinha(), restaurante.horarioAbertura(), restaurante.horarioFechamento(),
                restaurante.capacidade(), listaMesas);

        RestauranteEntity restauranteSalvo = restauranteGateway.cadastrarRestaurante(restauranteEntity);

        return new RestauranteDto(restauranteSalvo);
    }

}
