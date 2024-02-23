package id.my.hilmiat.sping_h2.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.my.hilmiat.sping_h2.model.Person;

@Service
public class PersonService {
    PersonRepository repo;

    public PersonService(PersonRepository repository){
        this.repo = repository;
    }
    public Page<Person> customQuery(String q, Pageable page){
        //definisikan query yang sesuai keinginan
        if(q==null || q.equals("")){
            return repo.findAll(page);
        }
        return repo.searchPersonSQL(q,page);
    }
}
