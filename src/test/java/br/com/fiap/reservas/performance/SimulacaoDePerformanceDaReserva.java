package br.com.fiap.reservas.performance;

import br.com.fiap.reservas.controller.dto.EnderecoDto;
import br.com.fiap.reservas.controller.dto.ReservaDto;
import br.com.fiap.reservas.controller.dto.RestauranteDto;
import br.com.fiap.reservas.utils.JsonFormatUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.gatling.javaapi.core.ActionBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalTime;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.internal.HttpCheckBuilders.status;

public class SimulacaoDePerformanceDaReserva extends Simulation {

    private final ReservaDto reservaDto;

    public RestauranteDto criaORestaurante() {
        try {
            EnderecoDto endereco = new EnderecoDto("cep", "logradouro", "bairro",
                    "cidade", "numero", "complemento");
            RestauranteDto restauranteDto = new RestauranteDto(1L, "Nome", endereco, "TipoCozinha",
                    LocalTime.of(10,20), LocalTime.of(18, 30), 10);
            String jsonString = JsonFormatUtil.asJsonString(restauranteDto);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/restaurante"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.readValue(response.body(), RestauranteDto.class);
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SimulacaoDePerformanceDaReserva() throws JsonProcessingException {
        RestauranteDto restauranteDto = criaORestaurante();

        reservaDto = new ReservaDto(restauranteDto.id(), "usuario", 10, null,
                "05/02/2025 10:20");
    }


    public ActionBuilder criaActionBuilderParaCadatroDeReserva() throws JsonProcessingException {
        return http("cadastrar reserva")
                .post("/reservas")
                .body(StringBody(JsonFormatUtil.asJsonString(reservaDto)))
                .check(status().is(200));
    }

    public ScenarioBuilder criaCenarioDePerformanceParaCadastroDeReserva() throws JsonProcessingException {
        return scenario("Cenário de performance para cadastro de reserva")
                .exec(criaActionBuilderParaCadatroDeReserva());
    }


}
