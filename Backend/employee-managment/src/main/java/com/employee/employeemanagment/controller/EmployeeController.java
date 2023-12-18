package com.employee.employeemanagment.controller;

import com.employee.employeemanagment.service.EmployeeFileHandler;
import com.employee.employeemanagment.models.Employee;
import com.employee.employeemanagment.models.Language;
import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {

  public static void main(String[] args) {
    SpringApplication.run(EmployeeController.class, args);
  }

  @GetMapping("")
  public List<Employee> getAllEmployees() {
    return EmployeeFileHandler.getAll();
  }

  @PostMapping("/add")
  public void addEmployee(@RequestBody Employee employee) throws JSONException, IOException {
   EmployeeFileHandler.Add(employee);

  }

  @PutMapping("/update/{employeeId}")
  public void updateEmployee(@PathVariable Integer employeeId, @RequestParam String newDesignation)
          throws JSONException, IOException {
    EmployeeFileHandler.Update(employeeId , newDesignation);

  }

  @DeleteMapping("/delete/{employeeId}")
  public void deleteEmployee(@PathVariable Integer employeeId) throws JSONException, IOException {
    EmployeeFileHandler.Delete(employeeId);
  }

  @GetMapping("/search")
  public List<Employee> searchEmployee(@RequestParam(required = false) Integer employeeId,
      @RequestParam(required = false) String designation) {
    System.out.println(employeeId );
    return EmployeeFileHandler.Search(employeeId , designation);

  }



  @GetMapping("/language")
  public List<Employee> getEmployeesByLanguage(
      @RequestParam String languageName,
      @RequestParam int minScore,
      @RequestParam(required = false, defaultValue = "asc") String sortOrder) {

    return EmployeeFileHandler.searchByLanguage(languageName , minScore , sortOrder);

  }




}
