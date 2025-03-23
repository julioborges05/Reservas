package br.com.fiap.reservas.bdd;

import br.com.fiap.reservas.controller.dto.AvaliacaoDto;
import br.com.fiap.reservas.controller.dto.EnderecoDto;
import br.com.fiap.reservas.controller.dto.RestauranteDto;
import br.com.fiap.reservas.controller.dto.UsuarioDto;
import br.com.fiap.reservas.entities.AvaliacaoEntity;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalTime;

import static io.restassured.RestAssured.given;

public class DefinicaoDePassosAvaliacaoRestaurante {

    private Response response;

    private AvaliacaoEntity avaliacaoResponse;

    @Dado("que existe restaurante cadastrado")
    public void queExisteRestauranteCadastrado() {
        EnderecoDto endereco = new EnderecoDto("123", "rua 1", "bairro a", "cidade 1",
                "1", "complemento");

        RestauranteDto request = new RestauranteDto(1L, "Restaurante", endereco, "TipoCozinha",
                LocalTime.of(10, 20), LocalTime.of(18, 30), 10);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/restaurante");
    }

    @Dado("que existe usuario cadastrado")
    public void queExisteUsuarioCadastrado() {
        UsuarioDto request = new UsuarioDto(1L, "User", "user@email.com", "12345");

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/usuario");
    }

    @Quando("submeter o formulario para avaliacao de um restaurante")
    public AvaliacaoEntity avaliarUmRestaurante() {
        AvaliacaoDto request = new AvaliacaoDto(1L, 4, "Bom!", 1L);

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/avaliacao");

        return response.then().extract().as(AvaliacaoEntity.class);
    }

    @Então("a avaliacao deve ser registrada com sucesso")
    public void aAvaliacaoERegistradaComSucesso() {
        response.then().statusCode(HttpStatus.OK.value());
    }

    @Então("deve retornar a avaliacao criada")
    public void deveRetornarAAvaliacaoCriada() {
        avaliacaoResponse = response.then().extract().as(AvaliacaoEntity.class);
    }

}
