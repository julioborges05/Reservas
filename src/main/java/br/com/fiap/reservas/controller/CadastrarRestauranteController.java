package br.com.fiap.reservas.controller;

import br.com.fiap.reservas.controller.dto.EnderecoDto;
import br.com.fiap.reservas.controller.dto.RestauranteDto;
import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.enums.StatusMesa;
import br.com.fiap.reservas.interfaces.IEnderecoGateway;
import br.com.fiap.reservas.interfaces.IRestauranteGateway;
import br.com.fiap.reservas.usecases.CadastrarMesasUseCase;
import br.com.fiap.reservas.usecases.CadastraRestauranteUseCase;
import br.com.fiap.reservas.usecases.CadastrarEnderecoUseCase;

public class CadastrarRestauranteController {

    private final IRestauranteGateway restauranteGateway;
    private final IEnderecoGateway enderecoGateway;

    public CadastrarRestauranteController(IRestauranteGateway restauranteGateway, IEnderecoGateway enderecoGateway) {
        this.restauranteGateway = restauranteGateway;
        this.enderecoGateway = enderecoGateway;
    }

    public RestauranteDto cadastrarRestaurante(RestauranteDto restaurante) {
        EnderecoDto endereco = restaurante.endereco();
        EnderecoEntity enderecoEntity = CadastrarEnderecoUseCase.cadastrarEndereco(endereco.cep(), endereco.logradouro(),
                endereco.bairro(), endereco.cidade(), endereco.numero(), endereco.complemento());
        EnderecoEntity enderecoSalvo = enderecoGateway.cadastrarEndereco(enderecoEntity);

        RestauranteEntity restauranteEntity = CadastraRestauranteUseCase.cadastrarRestaurante(restaurante.nome(),
                enderecoSalvo, restaurante.tipoCozinha(), restaurante.horarioAbertura(), restaurante.horarioFechamento(),
                restaurante.capacidade(), CadastrarMesasUseCase.cadastrarMesas(restaurante.capacidade()));

        return new RestauranteDto(restauranteGateway.cadastrarRestaurante(restauranteEntity));
    }

}
