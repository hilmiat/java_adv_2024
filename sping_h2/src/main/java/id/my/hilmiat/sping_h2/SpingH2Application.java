package id.my.hilmiat.sping_h2;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import id.my.hilmiat.sping_h2.model.Person;
import id.my.hilmiat.sping_h2.repository.UserRepository;

@RestController
@SpringBootApplication
public class SpingH2Application {

	private final UserRepository userRepository;

	public SpingH2Application(UserRepository repo){
		this.userRepository = repo;
	}

	@GetMapping
	public String helo(){
		return "Hello from springboot";
	}

	@GetMapping("/users")
	public List<Person> getUsers(){
		return this.userRepository.findAll();
	}

	@GetMapping("/add")
	public Person addPerson(){
		Person p = new Person(null, "test", "person");
		return this.userRepository.save(p);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpingH2Application.class, args);
	}

}
