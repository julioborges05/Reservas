package br.com.fiap.reservas.bdd;

import br.com.fiap.reservas.controller.dto.BuscarReservasDto;
import br.com.fiap.reservas.controller.dto.DataHoraDto;
import br.com.fiap.reservas.controller.dto.ReservaDto;
import br.com.fiap.reservas.controller.dto.RestauranteDto;
import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.utils.DateFormat;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.junit.platform.commons.util.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class DefinicaoDePassosGerenciamentoDeReservas {

    private Response response;

    @Dado("que existem reservas cadastradas")
    public void queExistemReservasCadastradas(){
        LocalDateTime localDateTime = LocalDateTime.of(2025, 2, 2, 10, 37);
        ReservaDto request = new ReservaDto(1L, "Rodrigo Dias Flamia", 5, 1L, DateFormat.convertFromLocalDateTimeToString(localDateTime));

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/reservas/");
    }

    @Quando("buscar uma reserva")
    public void buscarUmaReserva(){
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/reservas/1");
    }

    @Então("a reserva deve ser encontrada com sucesso")
    public void aReservaDeveSerEncontradaComSucesso(){
        response.then().statusCode(HttpStatus.OK.value());
    }

    @Então("deve retornar a reserva encontrada")
    public void deveRetornarAReservaEncontrada(){
        response.jsonPath().getList(".", BuscarReservasDto.class);
    }

    @Dado("que existe reserva para o usuário")
    public void queExisteReservaParaOUsuario(){
        LocalDateTime localDateTime = LocalDateTime.of(2025, 3, 25, 8, 30);
        ReservaDto request = new ReservaDto(1L, "Rodrigo Dias Flamia", 5, 1L, DateFormat.convertFromLocalDateTimeToString(localDateTime));

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/reservas/");
    }

    @Quando("atualizar a reserva do usuário")
    public void atualizarReservaDoUsuario(){
        LocalDateTime localDateTime = LocalDateTime.of(2025, 3, 25, 8, 30);
        ReservaDto request = new ReservaDto(1L, "Rodrigo Dias Flamia", 5, 1L, DateFormat.convertFromLocalDateTimeToString(localDateTime));

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/reservas/atualiza-status/Rodrigo Dias Flamia" + new DataHoraDto("10:00"));
    }
}
