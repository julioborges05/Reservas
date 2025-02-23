//package br.com.fiap.reservas.entities;
//
//import br.com.fiap.reservas.usecases.AvaliaRestauranteUseCase;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//public class AvaliacaoEntityTest {
//
//    private final EnderecoEntity enderecoEntity = new EnderecoEntity("1318000", "logradouro", "bairro", "cidade", "numero", "complemento");
//    private final LocalTime horarioAbertura = LocalTime.of(2025, 2);
//    private final LocalTime horarioFechamento = LocalTime.of(2025, 02, 02, 17, 37);
//
//    @Test
//    void retornaErroComNotaMenorQueZero() {
//        assertThrows(
//                IllegalArgumentException.class,
//                () -> AvaliaRestauranteUseCase.avaliarRestaurante(-1, "comentario", new UsuarioEntity("usuario"), new RestauranteEntity("restaurante", enderecoEntity, "japa", horarioAbertura, horarioFechamento,  100)),
//                "Nota inválida"
//        );
//    }
//    @Test
//    void retornaErroComNotaMaiorQueCinco() {
//        assertThrows(
//                IllegalArgumentException.class,
//                () -> AvaliaRestauranteUseCase.avaliarRestaurante(6, "comentario", new UsuarioEntity("usuario"),new RestauranteEntity("restaurante", enderecoEntity, "japa", horarioAbertura, horarioFechamento,  100) ),
//                "Nota inválida"
//        );
//    }
//    @Test
//    void retornaErroComComentarioNulo() {
//        assertThrows(
//                IllegalArgumentException.class,
//                () -> AvaliaRestauranteUseCase.avaliarRestaurante(5, null, new UsuarioEntity("usuario"), new RestauranteEntity("restaurante", enderecoEntity, "japa", horarioAbertura, horarioFechamento,  100)),
//                "Comentário inválido"
//        );
//    }
//    @Test
//    void retornaErroComComentarioVazio() {
//        assertThrows(
//                IllegalArgumentException.class,
//                () -> AvaliaRestauranteUseCase.avaliarRestaurante(5, "", new UsuarioEntity("usuario"),new RestauranteEntity("restaurante", enderecoEntity, "japa", horarioAbertura, horarioFechamento,  100)),
//                "Comentário inválido"
//        );
//    }
//    @Test
//    void retornaErroComUsuarioNulo() {
//        assertThrows(
//                IllegalArgumentException.class,
//                () -> AvaliaRestauranteUseCase.avaliarRestaurante(5, "comentario", null, new RestauranteEntity("restaurante", enderecoEntity, "japa", horarioAbertura, horarioFechamento,  100, new ArrayList<MesaEntity>())),
//                "Usuário inválido"
//        );
//    }
//    @Test
//    void retornaErroComRestauranteNulo() {
//        assertThrows(
//                IllegalArgumentException.class,
//                () -> AvaliaRestauranteUseCase.avaliarRestaurante(5, "comentario", new UsuarioEntity("usuario"), null),
//                "Restaurante inválido"
//        );
//    }
//
//}
