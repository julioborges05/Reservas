package br.com.fiap.reservas.usecases;

import br.com.fiap.reservas.entities.EnderecoEntity;

public class CadastrarEnderecoUseCase {

    public static EnderecoEntity cadastrarEndereco(String rua, String numero, String bairro, String cidade, String estado, String cep) {
        return new EnderecoEntity(rua, numero, bairro, cidade, estado, cep);
    }

}
