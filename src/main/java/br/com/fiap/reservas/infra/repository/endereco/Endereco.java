package br.com.fiap.reservas.infra.repository.endereco;

import br.com.fiap.reservas.entities.EnderecoEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String numero;
    private String complemento;

    public Endereco() {
    }

    public Endereco(String cep, String logradouro, String bairro, String cidade, String numero, String complemento) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.numero = numero;
        this.complemento = complemento;
    }

    public Endereco(EnderecoEntity enderecoEntity) {
        this.cep = enderecoEntity.getCep();
        this.logradouro = enderecoEntity.getLogradouro();
        this.bairro = enderecoEntity.getBairro();
        this.cidade = enderecoEntity.getCidade();
        this.numero = enderecoEntity.getNumero();
        this.complemento = enderecoEntity.getComplemento();
    }

    public Long getId() {
        return id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public EnderecoEntity converterParaEntity() {
        return new EnderecoEntity(id, cep, logradouro, bairro, cidade, numero, complemento);
    }
}
