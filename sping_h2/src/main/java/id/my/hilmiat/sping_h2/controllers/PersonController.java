package id.my.hilmiat.sping_h2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.my.hilmiat.sping_h2.model.Person;
import id.my.hilmiat.sping_h2.repository.PersonRepository;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private final PersonRepository personRepository;

	public PersonController(PersonRepository repo){
		this.personRepository = repo;
	}

	@GetMapping
	public List<Person> getPersons(){
		return this.personRepository.findAll();
	}

	@PostMapping
	public Person addPerson(@RequestBody Person p){
		return this.personRepository.save(p);
	}

    @GetMapping("/{id}")
    public Person getById(@PathVariable Long id){
        return this.personRepository.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Person updatePerson(@RequestBody Person p, @PathVariable Long id){
        p.setId(id);
        return this.personRepository.save(p);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id){
        this.personRepository.deleteById(id);
    }
}
