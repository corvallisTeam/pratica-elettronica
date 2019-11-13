package it.cors.peav.kie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class KieserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(KieserverApplication.class, args);
	}

}
