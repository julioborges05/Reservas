package br.com.fiap.reservas.usecases;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CadastrarEnderecoUseCaseTest {

    @Test
    void cadastrandoEndereco() {
        assertDoesNotThrow(() -> {
            CadastrarEnderecoUseCase.cadastrarEndereco("Rua 00", "0", "Jardim das Oliveiras",
                    "São Paulo", "SP", "00000-000");
        });
    }

}
