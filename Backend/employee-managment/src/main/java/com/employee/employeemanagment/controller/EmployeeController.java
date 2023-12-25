package com.employee.employeemanagment.controller;

import com.employee.employeemanagment.service.EmployeeFileHandler;
import com.employee.employeemanagment.models.Employee;
import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/employees")
public class EmployeeController {

  public static void main(String[] args) {
    SpringApplication.run(EmployeeController.class, args);
  }

  @GetMapping("")
  public List<Employee> getAllEmployees() {
    // System.out.println("getting all employees");
    return EmployeeFileHandler.getAll();
  }

  @PostMapping("/add")
  public String addEmployee(@RequestBody Employee employee) throws JSONException, IOException {
    Employee existedEmployee = EmployeeFileHandler.getEmployeeById(employee.getEmployeeID());

    if (existedEmployee != null) {
      // System.out.println("there is an employee with the same id");
      return "there is an employee with the same id";
    } else {
      EmployeeFileHandler.Add(employee);
      // System.out.println("employee added successfully");
      return "employee added successfully";
    }

  }

  @PutMapping("/update/{employeeId}")
  public void updateEmployee(@PathVariable Integer employeeId, @RequestParam String newDesignation)
      throws JSONException, IOException {
    EmployeeFileHandler.Update(employeeId, newDesignation);

  }

  @DeleteMapping("/delete/{employeeId}")
  public void deleteEmployee(@PathVariable Integer employeeId) throws JSONException, IOException {
    EmployeeFileHandler.Delete(employeeId);
  }

  @GetMapping("/search")
  public List<Employee> searchEmployee(@RequestParam(required = false) Integer employeeId,
      @RequestParam(required = false) String designation) {
    System.out.println(employeeId);
    return EmployeeFileHandler.Search(employeeId, designation);

  }

  @GetMapping("/language")
  public List<Employee> getEmployeesByLanguage(
      @RequestParam String languageName,
      @RequestParam int minScore,
      @RequestParam(required = false, defaultValue = "asc") String sortOrder) {

    return EmployeeFileHandler.searchByLanguage(languageName, minScore, sortOrder);

  }

}
