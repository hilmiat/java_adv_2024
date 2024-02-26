package id.my.hilmiat.spring_mysql.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import id.my.hilmiat.spring_mysql.model.Departemen;
import id.my.hilmiat.spring_mysql.model.Person;
import id.my.hilmiat.spring_mysql.service.DepartemenRepository;
import id.my.hilmiat.spring_mysql.service.PersonRepository;

@Configuration
public class InitData {
    //inisialisasi data dummy
    private static final Logger log = LoggerFactory.getLogger(InitData.class);

    @Bean
    CommandLineRunner initDatabase(PersonRepository personRepository,DepartemenRepository deptRepo){
        return args ->{
            Departemen d = new Departemen();
            d.setName("HRD");

            Departemen d2 = new Departemen();
            d2.setName("Finance");

            log.info("Inisiasi Dept "+deptRepo.save(d));
            log.info("Inisiasi Dept "+deptRepo.save(d2));

            Person p1 = new Person();
            p1.setFirstname("Test");
            p1.setLastname("Test juga");
            p1.setDepartemen(d);

            for(int i=1;i<10;i++){
                log.info("Inisialisasi data "+personRepository.save(p1));
            }
            
        };
    }

}
