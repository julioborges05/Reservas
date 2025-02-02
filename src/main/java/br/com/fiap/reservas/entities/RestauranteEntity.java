package br.com.fiap.reservas.entities;

import io.micrometer.common.util.StringUtils;

import java.time.LocalDateTime;

public class RestauranteEntity {
    private final String nome;
    private final EnderecoEntity endereco;
    private final String tipoCozinha;
    private final LocalDateTime horarioAbertura;
    private final LocalDateTime horarioFechamento;
    private final int capacidade;

    public RestauranteEntity(String nome, EnderecoEntity endereco, String tipoCozinha, LocalDateTime horarioAbertura, LocalDateTime horarioFechamento, int capacidade) {
        validarNome(nome);
        validarEndereco(endereco);
        validarTipoCozinha(tipoCozinha);
        validarHorarios(horarioAbertura, horarioFechamento);
        validarCapacidade(capacidade);

        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
        this.capacidade = capacidade;
    }

    private static void validarNome(String nome) {
        if (StringUtils.isBlank(nome)) {
            throw new IllegalArgumentException("Nome inválido");
        }
    }

    private static void validarEndereco(EnderecoEntity endereco) {
        if (endereco == null) {
            throw new IllegalArgumentException("Endereço inválido");
        }
    }

    private static void validarTipoCozinha(String tipoCozinha) {
        if (StringUtils.isBlank(tipoCozinha)) {
            throw new IllegalArgumentException("Cozinha inválida");
        }
    }

    private static void validarHorarios(LocalDateTime horarioAbertura, LocalDateTime horarioFechamento) {
        if (horarioAbertura == null || horarioFechamento == null) {
            throw new IllegalArgumentException("Horário de funcionamento inválido");
        }
    }

    private static void validarCapacidade(int capacidade) {
        if (capacidade < 0) {
            throw new IllegalArgumentException("Capacidade inválida");
        }
    }
}
