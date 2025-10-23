package ua.ucu.apps.lab_6;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Lab6Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab6Application.class, args);
	}

	@GetMapping("/list")
	public List<String> getRandomUUID() {
		return Stream.generate(() -> UUID.randomUUID())
		.map(UUID::toString)
		.limit(10)
		.toList();
	}

}
