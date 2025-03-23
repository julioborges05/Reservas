package br.com.fiap.reservas.bdd;

import br.com.fiap.reservas.controller.dto.ReservaDto;

import br.com.fiap.reservas.utils.DateFormat;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Então;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;

public class DefinicaoDePassosReservaMesa {

    private Response response;
    private ReservaDto reservaResponse;

    @Quando("submeter o registro de uma reserva")
    public ReservaDto cadastrarUmaReserva(){
        LocalDateTime localDateTime = LocalDateTime.of(2025, 2, 2, 10, 37);;
        ReservaDto request = new ReservaDto(1L, "Rodrigo Dias Flamia", 5, 1L, DateFormat.convertFromLocalDateTimeToString(localDateTime));

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/reservas");

        return response.then().extract().as(ReservaDto.class);
    }

    @Então("a mesa deve ser reservada com sucesso")
    public void aMesaEReservadaComSucesso(){
        response.then().statusCode(HttpStatus.OK.value());
    }

    @Então("deve retornar a reserva")
    public void deveRetornarAReservaCriada(){
        reservaResponse = response.then().extract().as(ReservaDto.class);
    }
}