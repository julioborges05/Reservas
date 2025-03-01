package br.com.fiap.reservas.entities;

import br.com.fiap.reservas.infra.repository.restaurante.Restaurante;
import io.micrometer.common.util.StringUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class RestauranteEntity {
    private Long id;
    private final String nome;
    private final EnderecoEntity endereco;
    private final String tipoCozinha;
    private final LocalTime horarioAbertura;
    private final LocalTime horarioFechamento;
    private final int capacidade;
    private int qtdReservas = 0;
    private List<MesaEntity> listaMesa;

    public RestauranteEntity(String nome, EnderecoEntity endereco, String tipoCozinha, LocalTime horarioAbertura,
                             LocalTime horarioFechamento, int capacidade) {
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

    public RestauranteEntity(String nome, EnderecoEntity endereco, String tipoCozinha, LocalTime horarioAbertura,
                              LocalTime horarioFechamento, int capacidade, List<MesaEntity> listaMesa) {
        validarNome(nome);
        validarEndereco(endereco);
        validarTipoCozinha(tipoCozinha);
        validarHorarios(horarioAbertura, horarioFechamento);
        validarCapacidade(capacidade);
        validarMesas(listaMesa);

        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
        this.capacidade = capacidade;
        this.listaMesa = listaMesa;
    }

    public RestauranteEntity(Long id, String nome, EnderecoEntity endereco, String tipoCozinha, LocalTime horarioAbertura,
                             LocalTime horarioFechamento, int capacidade, List<MesaEntity> listaMesa) {
        validarNome(nome);
        validarEndereco(endereco);
        validarTipoCozinha(tipoCozinha);
        validarHorarios(horarioAbertura, horarioFechamento);
        validarCapacidade(capacidade);
        validarMesas(listaMesa);

        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
        this.capacidade = capacidade;
        this.listaMesa = listaMesa;
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

    private static void validarHorarios(LocalTime horarioAbertura, LocalTime horarioFechamento) {
        if (horarioAbertura == null || horarioFechamento == null) {
            throw new IllegalArgumentException("Horário de funcionamento inválido");
        }
    }

    private static void validarCapacidade(int capacidade) {
        if (capacidade < 0) {
            throw new IllegalArgumentException("Capacidade inválida");
        }
    }

    private static void validarMesas(List<MesaEntity> listaMesa) {
//        if (listaMesa == null || listaMesa.isEmpty()) {
//            throw new IllegalArgumentException("Quantidade de mesas inválida");
//        }
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public EnderecoEntity getEndereco() {
        return endereco;
    }

    public String getTipoCozinha() {
        return tipoCozinha;
    }

    public LocalTime getHorarioAbertura() {
        return horarioAbertura;
    }

    public LocalTime getHorarioFechamento() {
        return horarioFechamento;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public List<MesaEntity> getListaMesa() {
        return listaMesa;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
