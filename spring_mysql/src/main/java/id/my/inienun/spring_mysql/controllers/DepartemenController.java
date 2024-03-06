package id.my.inienun.spring_mysql.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("departemen")
public class DepartemenController {
    @GetMapping
    String helo(){
        return "hello from departemen";
    }
}