package employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeesIT {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void save() {
        webTestClient
                .post()
                .uri("/api/employees")
                .bodyValue(new EmployeeResource("John Doe"))
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    void findById() {
        var created = webTestClient
                .post()
                .uri("/api/employees")
                .bodyValue(new EmployeeResource("John Doe"))
                .exchange()
                .expectStatus()
                .isCreated()
                .returnResult(EmployeeResource.class)
                .getResponseBody()
                .blockFirst();

        webTestClient
                .get()
                .uri("/api/employees/{id}", created.id())
                .exchange()
                .expectStatus().isOk()
                .expectBody(EmployeeResource.class)
                .value(employee -> assertEquals("John Doe", employee.name()));

    }
}
