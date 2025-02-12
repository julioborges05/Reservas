package br.com.fiap.reservas.infra.repository;

import br.com.fiap.reservas.entities.EnderecoEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Restaurante {

    @Id
    private Long id;
    private String nome;
    private String tipoCozinha;
    private LocalDateTime horarioAbertura;
    private LocalDateTime horarioFechamento;

}
