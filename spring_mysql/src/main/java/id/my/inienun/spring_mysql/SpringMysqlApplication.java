package id.my.inienun.spring_mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMysqlApplication.class, args);
	}

}