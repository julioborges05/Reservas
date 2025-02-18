package br.com.fiap.reservas.gateway;

import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.interfaces.IEnderecoGateway;

public class EnderecoGateway implements IEnderecoGateway {

    private final IEnderecoGateway enderecoDatabaseGateway;

    public EnderecoGateway(IEnderecoGateway enderecoDatabaseGateway) {
        this.enderecoDatabaseGateway = enderecoDatabaseGateway;
    }

    @Override
    public EnderecoEntity cadastrarEndereco(EnderecoEntity enderecoEntity) {
        return enderecoDatabaseGateway.cadastrarEndereco(enderecoEntity);
    }
}
