package id.my.hilmiat.spring_mysql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.my.hilmiat.spring_mysql.model.Person;

@Service
public class PersonService {
    @Autowired
    PersonRepository repo;
    //CRUD
    public List<Person> getAll(){
        return repo.findAll();
    }
    public Person getById(Long id){
        return repo.findById(id).orElseThrow();
    }
    public void delete(Long id){
        repo.deleteById(id);
    }
    public Person update(Person p){
        return repo.save(p);
    }
    public Person update(Person newPerson, Long id) {
        newPerson.setId(id);
        return repo.save(newPerson);
    }

    public List<Person> getActivePerson(){
        return repo.getActivePerson();
    }

    public List<Person> getPersonByDeptName(String name){
        return repo.findAllByDepartemen_name(name);
    }
}
