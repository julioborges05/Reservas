package br.com.fiap.reservas.usecases;

import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.enums.StatusMesa;
import br.com.fiap.reservas.enums.StatusReserva;

import java.util.ArrayList;
import java.util.List;

public class CadastrarMesasUseCase {

    public static List<MesaEntity> cadastrarMesas(int capacidade) {
        List<MesaEntity> listaMesa = new ArrayList<>();

        int qtdMesas = capacidade / 4;
        for (int i = 0; i < qtdMesas; i++) {
            MesaEntity mesa = new MesaEntity(i, StatusMesa.LIVRE);
            listaMesa.add(mesa);
        }

        return listaMesa;
    }
}
