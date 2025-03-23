package br.com.fiap.reservas.bdd;

import br.com.fiap.reservas.controller.dto.BuscarReservasDto;
import br.com.fiap.reservas.controller.dto.ReservaDto;
import br.com.fiap.reservas.utils.DateFormat;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

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
        // Pendente
    }

    @Quando("buscar a reserva do usuário")
    public void buscarReservaDoUsuario(){
        // Pendente
    }

    @Então("deve atualizar o status da reserva")
    public void deveAtualizarOStatusDaReserva(){
        // Pendente
    }

    @Então("deve retornar a reserva atualizada")
    public void deveRetornarAReservaAtualizada(){
        // Pendente
    }
}
