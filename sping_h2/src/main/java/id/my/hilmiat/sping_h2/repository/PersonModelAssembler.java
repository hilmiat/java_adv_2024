package id.my.hilmiat.sping_h2.repository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import id.my.hilmiat.sping_h2.controllers.PersonController;
import id.my.hilmiat.sping_h2.model.Person;

@Component
public class PersonModelAssembler implements RepresentationModelAssembler<Person, EntityModel<Person>>{

    @Override
    public EntityModel<Person> toModel(Person entity) {
        Link self = linkTo(methodOn(PersonController.class)
            .getById(entity.getId()))
            .withSelfRel();
        Link image = linkTo(methodOn(PersonController.class)
            .getImages(entity.getId())
        ).withRel("images");
        Link all = linkTo(methodOn(PersonController.class)
            .getPersons(0, 10,null))
            .withRel("persons");

        return EntityModel.of(entity,self,image,all);
    }
    
}
