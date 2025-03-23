package br.com.fiap.reservas.bdd;

import br.com.fiap.reservas.controller.dto.AvaliacaoDto;
import br.com.fiap.reservas.controller.dto.EnderecoDto;
import br.com.fiap.reservas.controller.dto.RestauranteDto;
import br.com.fiap.reservas.controller.dto.UsuarioDto;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalTime;

import static io.restassured.RestAssured.given;

public class DefinicaoDePassosBuscaAvaliacao {

    private Response response;

    private AvaliacaoDto avaliacaoResponse;

    @Dado("que existe um restaurante cadastrado")
    public void queExisteUmRestauranteCadastrado() {
        EnderecoDto endereco = new EnderecoDto("123", "rua 1", "bairro a", "cidade 1",
                "1", "complemento");

        RestauranteDto request = new RestauranteDto(2L, "Restaurante", endereco, "TipoCozinha",
                LocalTime.of(10, 20), LocalTime.of(18, 30), 10);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/restaurante");
    }

    @Dado("que existe um usuario cadastrado")
    public void queExisteUmUsuarioCadastrado() {
        UsuarioDto request = new UsuarioDto(2L, "User", "user@email.com", "12345");

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/usuario");
    }

    @Dado("que existe uma avaliacao cadastrada")
    public void queExisteUmaAvaliacaoCadastrada() {
        AvaliacaoDto request = new AvaliacaoDto(2L, 4, "Bom!", 2L);

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/avaliacao");
    }

    @Quando("submeter a busca de uma avaliacao por id")
    public AvaliacaoDto buscarAvaliacao() {

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/avaliacao/avaliacao-por-restaurante/2");

        return response.then().extract().as(AvaliacaoDto.class);
    }

    @Então("a avaliacao deve ser encontrada com sucesso")
    public void aAvaliacaoEEncontradaComSucesso() {
        response.then().statusCode(HttpStatus.OK.value());
    }

    @Então("deve retornar a avaliacao encontrada")
    public void deveRetornarAAvaliacaoEncontrada() {
        avaliacaoResponse = response.then().extract().as(AvaliacaoDto.class);
    }

}
