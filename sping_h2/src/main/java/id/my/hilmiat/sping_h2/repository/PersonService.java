package id.my.hilmiat.sping_h2.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.my.hilmiat.sping_h2.model.Person;

@Service
public class PersonService {
    PersonRepository repo;

    public PersonService(PersonRepository repository){
        this.repo = repository;
    }
    public List<Person> customQuery(String q){
        //definisikan query yang sesuai keinginan
        if(q==null || q.equals("")){
            return repo.findAll();
        }
        return repo.searchPersonSQL(q);
    }
}
