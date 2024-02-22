package id.my.hilmiat.sping_h2;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import id.my.hilmiat.sping_h2.model.Person;
import id.my.hilmiat.sping_h2.repository.PersonRepository;


@SpringBootApplication
public class SpingH2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpingH2Application.class, args);
	}

}
