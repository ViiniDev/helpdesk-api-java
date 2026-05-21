package com.viinidev.helpdesk;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelpdeskApiApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldListCreatedTickets() {
        Map<String, String> request = Map.of(
                "requesterName", "Ana Silva",
                "requesterEmail", "ana@email.com",
                "title", "Erro ao acessar sistema",
                "description", "Falha ao tentar acessar o painel principal.",
                "priority", "HIGH"
        );

        ResponseEntity<String> createResponse = restTemplate.postForEntity(
                url("/api/tickets"),
                new HttpEntity<>(request),
                String.class
        );

        ResponseEntity<String> listResponse = restTemplate.getForEntity(url("/api/tickets"), String.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(listResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(listResponse.getBody()).contains("Erro ao acessar sistema");
    }

    private String url(String path) {
        return "http://localhost:" + port + path;
    }
}
