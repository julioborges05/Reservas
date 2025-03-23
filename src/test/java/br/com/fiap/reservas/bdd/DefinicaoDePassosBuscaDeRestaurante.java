package br.com.fiap.reservas.bdd;

import br.com.fiap.reservas.controller.dto.EnderecoDto;
import br.com.fiap.reservas.controller.dto.RestauranteDto;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalTime;

import static io.restassured.RestAssured.given;

public class DefinicaoDePassosBuscaDeRestaurante {

    private Response response;

    @Dado("que existem restaurantes cadastrados")
    public void queExistemRestaurantesCadastrados() {
        EnderecoDto endereco = new EnderecoDto("123", "rua 1", "bairro a", "cidade 1",
                "1", "complemento");

        RestauranteDto request = new RestauranteDto("Restaurante", endereco, "TipoCozinha",
                LocalTime.of(10, 20), LocalTime.of(18, 30), 10);

        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when()
            .post("/restaurante");
    }

    @Quando("buscar um restaurante")
    public void buscarUmRestaurante() {
        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get("/restaurante/buscar-nome?nome=Restaurante");
    }

    @Então("o restaurante deve ser encontrado com sucesso")
    public void oRestauranteDeveSer() {
        response.then().statusCode(HttpStatus.OK.value());
    }

    @Então("deve retornar o restaurante encontrado")
    public void deveRetornarORestauranteEncontrado() {
        response.then().extract().as(RestauranteDto.class);
    }



}
