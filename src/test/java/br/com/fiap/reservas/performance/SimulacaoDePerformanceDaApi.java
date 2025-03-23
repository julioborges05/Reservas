package br.com.fiap.reservas.performance;

import br.com.fiap.reservas.controller.dto.EnderecoDto;
import br.com.fiap.reservas.controller.dto.RestauranteDto;
import br.com.fiap.reservas.utils.JsonFormatUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.gatling.javaapi.core.ActionBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;
import java.time.LocalTime;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.internal.HttpCheckBuilders.status;

public class SimulacaoDePerformanceDaApi extends Simulation {

    private final EnderecoDto endereco = new EnderecoDto("cep", "logradouro", "bairro",
            "cidade", "numero", "complemento");
    private RestauranteDto request = new RestauranteDto(1L, "Nome", endereco, "TipoCozinha",
            LocalTime.of(10, 20), LocalTime.of(18, 30), 10);
    private final String jsonString = JsonFormatUtil.asJsonString(request);

    private final HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")
            .header("Content-Type", "application/json");

    public SimulacaoDePerformanceDaApi() throws JsonProcessingException {
    }

    ActionBuilder criaActionBuilderParaCadatroDeRestaurante = http("cadastrar restaurante")
                .post("/restaurante")
                .body(StringBody(jsonString))
                .check(status().is(200));

    ScenarioBuilder cenarioDePerformance = scenario("Cenário de performance")
            .exec(criaActionBuilderParaCadatroDeRestaurante);

    {
        setUp(
                cenarioDePerformance.injectOpen(
                        rampUsersPerSec(1)
                                .to(10)
                                .during(Duration.ofSeconds(10)),
                        constantUsersPerSec(10)
                                .during(Duration.ofSeconds(60)),
                        rampUsersPerSec(10)
                                .to(1)
                                .during(Duration.ofSeconds(10))))
                .protocols(httpProtocol)
                .assertions(
                        global().responseTime().max().lt(50),
                        global().failedRequests().count().is(0L)
                );
    }

}
