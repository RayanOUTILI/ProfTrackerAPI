package iut.outili.profTrackerAPI;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import iut.outili.profTrackerAPI.Firebase.FirebaseInitializer;

@SpringBootApplication
public class ProfTrackerApiApplication {

	public static void main(String[] args) {
		FirebaseInitializer.initialize();
		SpringApplication.run(ProfTrackerApiApplication.class, args);
	}

	// on va créé un web client pour faire des requêtes
	@Bean
	public CommandLineRunner displayProf() {
		return args -> {
			// WebClient webClient = WebClient.create("http://localhost:8080");
			// String[] result =
			// webClient.get().uri("/listerprofs").retrieve().bodyToMono(String[].class).block();
			// System.out.println(Arrays.toString(result));
		};
	}

}
