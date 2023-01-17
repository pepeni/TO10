package com.example.TO9;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Service
public class GetEmployees {

    @GetMapping("/getEmployees")
    public ResponseEntity<String> getEmployees() {
        List<Employee> table = EmployeeTable.getInstance().get_employees();
        String response = "";
        for(Employee employee: table){
            response += employee.getName();
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
