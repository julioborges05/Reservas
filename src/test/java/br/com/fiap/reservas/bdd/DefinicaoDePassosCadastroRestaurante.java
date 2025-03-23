package br.com.fiap.reservas.bdd;

import br.com.fiap.reservas.controller.dto.EnderecoDto;
import br.com.fiap.reservas.controller.dto.RestauranteDto;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Então;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalTime;

import static io.restassured.RestAssured.given;

public class DefinicaoDePassosCadastroRestaurante {

    private final EnderecoDto endereco = new EnderecoDto("cep", "logradouro", "bairro", "cidade", "numero", "complemento");

    private Response response;
    private RestauranteDto restauranteResponse;

    @Quando("submeter o registro de um restaurante")
    public RestauranteDto cadastrarUmRestaurante() {
        RestauranteDto request = new RestauranteDto("Nome", endereco, "TipoCozinha",
                LocalTime.of(10, 20), LocalTime.of(18, 30), 10);

        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when()
            .post("/restaurante");

        return response.then().extract().as(RestauranteDto.class);
    }

    @Então("o restaurante deve ser cadastrado com sucesso")
    public void oRestauranteECadastradoComSucesso() {
        response.then().statusCode(HttpStatus.OK.value());
    }

    @Então("deve retornar o restaurante criado")
    public void deveRetornarORestauranteCriado() {
        restauranteResponse = response.then().extract().as(RestauranteDto.class);
    }

}
