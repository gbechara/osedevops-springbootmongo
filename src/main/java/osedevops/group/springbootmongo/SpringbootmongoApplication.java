package osedevops.group.springbootmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("osedevops.group.springbootmongo.repository")
public class SpringbootmongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootmongoApplication.class, args);
	}
}
