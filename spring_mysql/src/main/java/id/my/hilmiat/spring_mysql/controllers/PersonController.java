package id.my.hilmiat.spring_mysql.controllers;

import java.util.List;

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
    @GetMapping("/bydept")
    List<Person> getByDept(@RequestParam String name){
        return personService.getPersonByDeptName(name);
    }


}
