package id.my.hilmiat.spring_mysql.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.my.hilmiat.spring_mysql.model.Person;
import id.my.hilmiat.spring_mysql.service.LongQuery;
import id.my.hilmiat.spring_mysql.service.PersonService;

@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping
    List<Person> getAll(){
       return personService.getAll();
    }
    @GetMapping("/{id}")
    Person getById(@PathVariable Long id){
        return personService.getById(id);
    }
    @PostMapping
    Person createPerson(@RequestBody Person newPerson){
        return personService.update(newPerson);
    }
    @PutMapping("/{id}")
    Person updatePerson(@RequestBody Person newPerson,@PathVariable Long id){
        return personService.update(newPerson,id);
    }
    @DeleteMapping("/{id}")
    void deletePerson(@PathVariable Long id){
        personService.delete(id);
    }
    
    // demo query
    @GetMapping("/active")
    List<Person> getActive(){
        return personService.getActivePerson();
    }


    @Autowired
    LongQuery longqueryService;

    //test asyc
    @GetMapping("/bydept")
    Future<List<Person>> getByDept(@RequestParam String name){
        Future<List<Person>> dataByDept = longqueryService.getAsyncPersonByDeptName(name);
        //kode ini akan diekseskusi walaupun kode diatas belum selesai.
        return dataByDept;
    }
    @GetMapping("/tesasync")
    List<Person> getAsyncByDept(@RequestParam String name) throws InterruptedException, ExecutionException{
        List<Person> data = new ArrayList<>();
        Future<List<Person>> dataByDept;
        for(int i = 0;i<100;i++){
            dataByDept = longqueryService.getAsyncPersonByDeptName(name);
            data = dataByDept.get();
        }
        return data;
    }

}
