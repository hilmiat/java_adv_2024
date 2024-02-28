package id.my.hilmiat.spring_mysql.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import id.my.hilmiat.spring_mysql.model.Departemen;
import id.my.hilmiat.spring_mysql.model.Person;


@SpringBootTest
public class PersonServiceTests {
    // @Autowired
    // PersonService service;

    // @Test
    // void contextLoads(){
    //    assertNotNull(service);
    // }

    // @Test
    // void testGetHello(){
    //     String hasil = service.getHello();
    //     assertEquals("heloo", hasil);
    // }

    @Mock
    private PersonRepository repo;

    @InjectMocks
    private PersonService personServiceMock;

    @Test
    void personGetById(){
        //buat mockup student
        Departemen d = new Departemen();
        d.setId(1L);
        Long id = 6L;
        Person p = new Person(
            id,
            "test",
            "test",
            false,
            new Date(),
            new Date(),d);
        
        //saat repo memanggil method findById kita isi dengan data mock
        when(repo.findById(id)).thenReturn(Optional.of(p));

        Person hasil = personServiceMock.getById(id);
        assertEquals(id, hasil.getId());
    }

    @Test
    void failedPersonGetId(){
        assertThrows(Exception.class, ()->{
            personServiceMock.getById(4L);
        });
    }
}

