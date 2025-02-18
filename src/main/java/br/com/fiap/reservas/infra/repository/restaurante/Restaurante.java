package br.com.fiap.reservas.infra.repository.restaurante;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "id_endereco")
    private Long idEndereco;

    private String tipo;

    @Column(name = "horario_abertura")
    private LocalTime horarioAbertura;

    @Column(name = "horario_fechamento")
    private LocalTime horarioFechamento;

    private int capacidade;

    public Restaurante() {
    }

    public Restaurante(String nome, Long idEndereco, String tipo, LocalTime horarioAbertura, LocalTime horarioFechamento, int capacidade) {
        this.nome = nome;
        this.idEndereco = idEndereco;
        this.tipo = tipo;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
        this.capacidade = capacidade;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public String getTipo() {
        return tipo;
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
}
