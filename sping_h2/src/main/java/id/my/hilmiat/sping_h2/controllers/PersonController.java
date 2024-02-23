package id.my.hilmiat.sping_h2.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.my.hilmiat.sping_h2.exception.PersonNotFoundException;
import id.my.hilmiat.sping_h2.model.Person;
import id.my.hilmiat.sping_h2.repository.PersonRepository;
import id.my.hilmiat.sping_h2.repository.PersonService;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private final PersonRepository personRepository;
    private final PersonService service;
	public PersonController(PersonRepository repo, PersonService service){
		this.personRepository = repo;
        this.service = service;
	}

    @GetMapping("/name")
    public List<String> getPersonName(){
        return this.personRepository.getName();
    }


	@GetMapping("/cari")
	public Page<Person> getPersons(
        @RequestParam(defaultValue = "",name = "q") String q,
        @RequestParam(defaultValue = "0") int pageNumber,
        @RequestParam(defaultValue = "10") int pageSize
        ){
        PageRequest paging = PageRequest.of(pageNumber, pageSize);
		return this.service.customQuery(q,paging);
	}
    @GetMapping
	public Page<Person> getPersons(
        @RequestParam(defaultValue = "0") int pageNumber,
        @RequestParam(defaultValue = "10") int pageSize,
        @RequestParam(defaultValue = "id,desc") String[] sort
    ){
        Sort sorting = Sort.by(sort[0]).ascending();
        // return this.personRepository.findAll();
        // filter nilai dari pageNumber dan pageSize agar > 0
        if(pageNumber<0 || pageSize<0){
            //tidak valid
        }

        PageRequest paging = PageRequest.of(pageNumber, pageSize, sorting);
        return this.personRepository.findAll(paging);


        
	}

	@PostMapping
	public Person addPerson(@RequestBody Person p){
		return this.personRepository.save(p);
	}

    @GetMapping("/{id}")
    public EntityModel<Person> getById(@PathVariable Long id){

        Person person = this.personRepository
        .findById(id)
        .orElseThrow(()-> new PersonNotFoundException(id));

        // Link self = Link.of("test");

        Link self = linkTo(methodOn(PersonController.class)
            .getById(id))
            .withSelfRel();
        Link all = linkTo(methodOn(PersonController.class)
            .getPersons(0, 10,null))
            .withRel("persons");

        return EntityModel.of(person,self,all);
        

       
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
