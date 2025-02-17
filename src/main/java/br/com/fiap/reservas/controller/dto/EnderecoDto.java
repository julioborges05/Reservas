package br.com.fiap.reservas.controller.dto;

import br.com.fiap.reservas.entities.EnderecoEntity;

public record EnderecoDto(String cep, String logradouro, String bairro, String cidade, String numero, String complemento) {

    public EnderecoDto(EnderecoEntity enderecoEntity) {
        this(
                enderecoEntity.getCep(),
                enderecoEntity.getLogradouro(),
                enderecoEntity.getBairro(),
                enderecoEntity.getCidade(),
                enderecoEntity.getNumero(),
                enderecoEntity.getComplemento()
        );
    }

}
