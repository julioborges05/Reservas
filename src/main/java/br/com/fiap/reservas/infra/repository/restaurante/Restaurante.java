package br.com.fiap.reservas.infra.repository.restaurante;

import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.infra.repository.mesa.Mesa;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Mesa> mesas = new ArrayList<>();

    public Restaurante() {
    }

    public Restaurante(String nome, Long idEndereco, String tipo, LocalTime horarioAbertura, LocalTime horarioFechamento,
                       int capacidade) {
        this.nome = nome;
        this.idEndereco = idEndereco;
        this.tipo = tipo;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
        this.capacidade = capacidade;
    }

    public Restaurante(Long id, String nome, Long idEndereco, String tipo, LocalTime horarioAbertura, LocalTime horarioFechamento,
                       int capacidade) {
        this.id = id;
        this.nome = nome;
        this.idEndereco = idEndereco;
        this.tipo = tipo;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
        this.capacidade = capacidade;
    }

    public Restaurante(RestauranteEntity restauranteEntity) {
        this.id = restauranteEntity.getId();
        this.nome = restauranteEntity.getNome();
        this.idEndereco = restauranteEntity.getEndereco().getId();
        this.tipo = restauranteEntity.getTipoCozinha();
        this.horarioAbertura = restauranteEntity.getHorarioAbertura();
        this.horarioFechamento = restauranteEntity.getHorarioFechamento();
        this.capacidade = restauranteEntity.getCapacidade();
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

    public List<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(List<Mesa> mesas) {
        this.mesas = mesas;
    }
}
