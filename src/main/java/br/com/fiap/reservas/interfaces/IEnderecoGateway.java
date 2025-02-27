package br.com.fiap.reservas.interfaces;

import br.com.fiap.reservas.entities.EnderecoEntity;

import java.util.Optional;

public interface IEnderecoGateway {

    EnderecoEntity cadastrarEndereco(EnderecoEntity enderecoEntity);

    EnderecoEntity buscarEnderecoPeloId(Long id);

}
