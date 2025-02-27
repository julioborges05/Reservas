package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.infra.repository.endereco.Endereco;
import br.com.fiap.reservas.infra.repository.endereco.EnderecoRepository;
import br.com.fiap.reservas.interfaces.IEnderecoGateway;

import java.util.Optional;

public class EnderecoRepositorioJpa implements IEnderecoGateway {

    private final EnderecoRepository enderecoRepository;

    public EnderecoRepositorioJpa(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public EnderecoEntity cadastrarEndereco(EnderecoEntity enderecoEntity) {
        Optional<Endereco> enderecoExistente = enderecoRepository.findByCepAndNumeroAndComplemento(enderecoEntity.getCep(),
                enderecoEntity.getNumero(), enderecoEntity.getComplemento());

        if (enderecoExistente.isPresent()) {
            Endereco endereco = enderecoExistente.get();
            return new EnderecoEntity(endereco.getId(), endereco.getCep(), endereco.getLogradouro(), endereco.getBairro(), endereco.getCidade(),
                    endereco.getNumero(), endereco.getComplemento());
        }

        Endereco endereco = new Endereco(enderecoEntity.getCep(), enderecoEntity.getLogradouro(), enderecoEntity.getBairro(),
                enderecoEntity.getCidade(), enderecoEntity.getNumero(), enderecoEntity.getComplemento());

        Endereco enderecoSalvo = enderecoRepository.save(endereco);
        return new EnderecoEntity(enderecoSalvo.getId(), enderecoSalvo.getCep(), enderecoSalvo.getLogradouro(),
                enderecoSalvo.getBairro(), enderecoSalvo.getCidade(), enderecoSalvo.getNumero(), enderecoSalvo.getComplemento());
    }

    @Override
    public EnderecoEntity buscarEnderecoPeloId(Long id) {
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);

        if (enderecoOptional.isPresent()) {
            Endereco endereco = enderecoOptional.get();
            return new EnderecoEntity(endereco.getCep(), endereco.getLogradouro(),
                    endereco.getBairro(), endereco.getCidade(), endereco.getNumero(), endereco.getComplemento());
        } else {
            throw new RuntimeException("Endereço não encontrado");
        }
    }


}
