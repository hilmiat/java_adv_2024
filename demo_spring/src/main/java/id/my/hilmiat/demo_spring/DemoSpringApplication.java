package id.my.hilmiat.demo_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@RestController
public class DemoSpringApplication {

	@GetMapping("/helo")
	public String hello(){
		return "Hello World";
	}

	@GetMapping("")
	public String home(){
		return "Welcome";
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoSpringApplication.class, args);
	}

}
