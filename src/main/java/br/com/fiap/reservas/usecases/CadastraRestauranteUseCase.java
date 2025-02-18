package br.com.fiap.reservas.usecases;

import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class CadastraRestauranteUseCase {

    public static RestauranteEntity cadastrarRestaurante(String nome, EnderecoEntity endereco, String tipoCozinha,
                                                  LocalTime horarioAbertura, LocalTime horarioFechamento,
                                                  int capacidade, List<MesaEntity> listaMesa) {
        return new RestauranteEntity(nome, endereco, tipoCozinha, horarioAbertura, horarioFechamento, capacidade, listaMesa);
    }
}
