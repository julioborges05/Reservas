package br.com.fiap.reservas.usecases;

import br.com.fiap.reservas.entities.EnderecoEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CadastrarRestauranteUseCaseTest {

    private final EnderecoEntity endereco = new EnderecoEntity("Rua 00", "0", "Jardim das Oliveiras",
            "São Paulo", "SP", "00000-000");

    @Test
    void cadastrandoRestaurante() {
        assertDoesNotThrow(() -> {
            CadastraRestauranteUseCase.cadastrarRestaurante("Restaurante Teste", endereco, "brasileira",
                    LocalTime.now(), LocalTime.now(), 50, List.of());
        });
    }

}
