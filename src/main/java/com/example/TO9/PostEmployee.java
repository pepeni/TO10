package com.example.TO9;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Service
public class PostEmployee {

    @PostMapping("/postEmployee")
    public ResponseEntity<String> postEmployee( @RequestBody String name, String surname, String data) {
        EmployeeTable.getInstance().add_employee(new Employee(name, surname, data));



        return new ResponseEntity<>("OK :)", HttpStatus.OK);
    }

}
