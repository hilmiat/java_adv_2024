package id.my.hilmiat.spring_mysql.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.my.hilmiat.spring_mysql.model.Departemen;
import id.my.hilmiat.spring_mysql.model.Person;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class PersonService {
    @Autowired
    PersonRepository repo;
    //CRUD


    public String getHello(){
        return "heloo";
    }

    @RateLimiter(name = "backendB")
    @Retry(name = "retryA")
    // @RateLimiter(name = "auth-service")
    public List<Person> getAll(){
        return repo.findAll();
    }

    public Person getById(Long id){
        return repo.findById(id).orElseThrow();
    }
    public void delete(Long id){
        repo.deleteById(id);
    }

    @Secured("ADMIN")
    public Person update(Person p){
        return repo.save(p);
    }


    @Secured("OPERATOR")
    public Person update(Person newPerson, Long id) {
        newPerson.setId(id);
        return repo.save(newPerson);
    }

    @RateLimiter(name="backendB",fallbackMethod = "ambilDariCache")
    public List<Person> getActivePerson(){
        return repo.getActivePerson();
    }

    private List<Person> ambilDariCache(RequestNotPermitted rnp){
        List<Person> cache = new ArrayList<>();
        Departemen d = new Departemen();
        d.setId(1L);
        cache.add(new Person(2L, "Person cache", "dummy", false,new Date(),new Date(),d));
        return cache;
    }

    public List<Person> getPersonByDeptName(String name){
        return repo.findAllByDepartemen_name(name);
    }

    //transaction
    @Transactional
    public void executeBeberapaQuery(){
        //query 1
        //query 2
        asyncQuery(); //--> diluar trasaction
        //query 3
    }
    @Async
    public void asyncQuery(){
        //query x
    }

}

