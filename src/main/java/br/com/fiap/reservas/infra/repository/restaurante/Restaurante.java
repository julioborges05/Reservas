package br.com.fiap.reservas.infra.repository.restaurante;

import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.MesaEntity;
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

    public List<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(List<Mesa> mesas) {
        this.mesas = mesas;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public LocalTime getHorarioFechamento() {
        return horarioFechamento;
    }

    public void setHorarioFechamento(LocalTime horarioFechamento) {
        this.horarioFechamento = horarioFechamento;
    }

    public LocalTime getHorarioAbertura() {
        return horarioAbertura;
    }

    public void setHorarioAbertura(LocalTime horarioAbertura) {
        this.horarioAbertura = horarioAbertura;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public RestauranteEntity converteParaEntity(EnderecoEntity enderecoEntity) {
        List<MesaEntity> listaMesa = this.mesas
                .stream()
                .map(mesa -> new MesaEntity(mesa.getRestaurante().getId(), mesa.getNumero(), mesa.getStatusMesa()))
                .toList();

        return new RestauranteEntity(this.id, this.nome,
                enderecoEntity, this.tipo, this.horarioAbertura,
                this.horarioFechamento, this.capacidade, listaMesa);
    }
}
