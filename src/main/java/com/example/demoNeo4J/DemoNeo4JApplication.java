package com.example.demoNeo4J;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
public class DemoNeo4JApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoNeo4JApplication.class, args);
	}
}
