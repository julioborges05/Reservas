package br.com.fiap.reservas.usecases;

import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.enums.StatusMesa;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CadastrarMesasUseCaseTest {

    @Test
    void cadastrarMesas_retornaErroComQuantidadeNegativa() {
        assertThrows(
                IllegalArgumentException.class,
                () ->  CadastrarMesasUseCase.cadastrarMesas(-1),
                "Capacidade de mesas não pode ser negativa"
        );
    }

    @Test
    void cadastrarMesas_permiteUmaMesaQuandoAQuantidadeForMenorQueQuatro() {
        List<MesaEntity> mesas = CadastrarMesasUseCase.cadastrarMesas(2);

        assertEquals(1, mesas.size());
    }

    @Test
    void cadastrarMesas_retornaSeisMesasQuandoTemCapacidadePara22Pessoas() {
        List<MesaEntity> mesas = CadastrarMesasUseCase.cadastrarMesas(22);

        assertEquals(6, mesas.size());
    }

    @Test
    void cadastrarMesas_criaTodasAsMesasComOStatusLivre() {
        List<MesaEntity> mesas = CadastrarMesasUseCase.cadastrarMesas(20);

        assertEquals(5, mesas.size());

        for (MesaEntity mesa : mesas) {
            assertEquals(mesa.getStatusMesa(), StatusMesa.LIVRE);
        }
    }

}
