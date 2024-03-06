package id.my.inienun.spring_mysql.service;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import id.my.inienun.spring_mysql.model.Person;

public @Service
class LongQuery{
    @Autowired
    PersonRepository repo;
    @Async
    public List<Person> getPersonByDeptName(String name){
        return repo.findAllByDepartemen_name(name);
    }

    @Async
    public Future<List<Person>> getAsyncPersonByDeptName(String name){
        return repo.findVeryHard();
    }


} 