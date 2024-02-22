package id.my.hilmiat.sping_h2.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import id.my.hilmiat.sping_h2.model.Person;
import id.my.hilmiat.sping_h2.repository.PersonRepository;

@Configuration
public class LoadDatabase {
    //inisialisasi data dummy
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PersonRepository personRepository){
        return args ->{
            for(int i=1;i<1000;i++){
                log.info("Inisialisasi data "+personRepository.save(new Person((long) i,"Hilmy "+i,"Tawakal")));
            }
            // log.info("Inisialisasi data awal "+personRepository.save(new Person(1L,"Hilmy","Tawakal")));
            // log.info("Inisialisasi data awal "+personRepository.save(new Person(2L,"Adi","Sutisno")));
        };
    }
}
