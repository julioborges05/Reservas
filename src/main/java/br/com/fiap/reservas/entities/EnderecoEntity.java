package br.com.fiap.reservas.entities;

import io.micrometer.common.util.StringUtils;

public class EnderecoEntity {

    private Long id;
    private final String cep;
    private final String logradouro;
    private final String bairro;
    private final String cidade;
    private final String numero;
    private final String complemento;

    public EnderecoEntity(Long id, String cep, String logradouro, String bairro, String cidade, String numero, String complemento) {
        validaCep(cep);
        validaLogradouro(logradouro);
        validaBairro(bairro);
        validaCidade(cidade);
        validaNumero(numero);
        validaComplemento(complemento);
        this.id = id;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.numero = numero;
        this.complemento = complemento;
    }

    public EnderecoEntity(String cep, String logradouro, String bairro, String cidade, String numero, String complemento) {
        validaCep(cep);
        validaLogradouro(logradouro);
        validaBairro(bairro);
        validaCidade(cidade);
        validaNumero(numero);
        validaComplemento(complemento);
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.numero = numero;
        this.complemento = complemento;
    }

    private static void validaComplemento(String complemento) {
        if (StringUtils.isBlank(complemento)) {
            throw new IllegalArgumentException("Complemento inválido");
        }
    }

    private static void validaNumero(String numero) {
        if (StringUtils.isBlank(numero)) {
            throw new IllegalArgumentException("Número inválido");
        }
    }

    private static void validaCidade(String cidade) {
        if (StringUtils.isBlank(cidade)) {
            throw new IllegalArgumentException("Cidade inválido");
        }
    }

    private static void validaBairro(String bairro) {
        if (StringUtils.isBlank(bairro)) {
            throw new IllegalArgumentException("Bairro inválido");
        }
    }

    private static void validaLogradouro(String logradouro) {
        if (StringUtils.isBlank(logradouro)) {
            throw new IllegalArgumentException("Logradouro inválido");
        }
    }

    private static void validaCep(String cep) {
        if (StringUtils.isBlank(cep)) {
            throw new IllegalArgumentException("CEP inválido");
        }
    }

    public Long getId() {
        return id;
    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }
}
