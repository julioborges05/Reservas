package br.com.fiap.reservas.entities;

import br.com.fiap.reservas.enums.StatusReserva;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReservaEntityTest {

    private final EnderecoEntity enderecoEntity = new EnderecoEntity("1318000", "logradouro", "bairro", "cidade", "numero", "complemento");
    private final LocalDateTime horarioAbertura = LocalDateTime.of(2025, 02, 02, 10, 37);
    private final LocalDateTime horarioFechamento = LocalDateTime.of(2025, 02, 02, 17, 37);

    @Test
    void validaRestaurante() {
        ReservaVMesa mesa = new ReservaVMesa(Long.valueOf(1), StatusReserva.LIVRE);

        assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaEntity(null, "nome", List.of(mesa), LocalDateTime.now()),
                "Restaurante Inválido"
        );
    }

//    @Test
//    void validaUsuario() {
//        assertThrows(
//                IllegalArgumentException.class,
//                () -> new ReservaEntity(new RestauranteEntity("Nome", enderecoEntity, "tipoCozinha", horarioAbertura, horarioFechamento, 10, listaMesa), null),
//                "Usuário Inválido"
//        );
//    }
}
