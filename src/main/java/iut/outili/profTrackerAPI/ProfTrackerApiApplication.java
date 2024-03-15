package iut.outili.profTrackerAPI;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ProfTrackerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfTrackerApiApplication.class, args);
	}

	// on va créé un web client pour faire des requêtes
	@Bean
	public CommandLineRunner displayProf () {
		return args -> {
			WebClient webClient = WebClient.create("http://localhost:8080");
			String[] result = webClient.get().uri("/prof").retrieve().bodyToMono(String[].class).block();
			System.out.println(Arrays.toString(result));

		};
	}

}
