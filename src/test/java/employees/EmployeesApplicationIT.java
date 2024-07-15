package employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeesApplicationIT {

	@Autowired
	WebTestClient webTestClient;

	@Test
	void contextLoads() {
	}

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

}
