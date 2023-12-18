package com.employee.employeemanagment;

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
public class EmployeeService {

  // private static final String FILE_PATH = "employees.json";
  private static final List<Employee> employeeList = EmployeeFileHandler.readEmployeesFromFile();

  public static void main(String[] args) {
    SpringApplication.run(EmployeeService.class, args);
  }

  @GetMapping
  public List<Employee> getAllEmployees() {
    return employeeList;
  }

  @PostMapping("/add")
  public void addEmployee(@RequestBody Employee employee) throws JSONException, IOException {
    employeeList.add(employee);
    EmployeeFileHandler.writeEmployeesToFile(employeeList);
  }

  @GetMapping("/search")
  public List<Employee> searchEmployee(@RequestParam(required = false) Integer employeeId,
      @RequestParam(required = false) String designation) {
    return employeeList.stream()
        .filter(employee -> (employeeId == null || employee.getEmployeeID().equals(employeeId))
            && (designation == null || employee.getDesignation().equalsIgnoreCase(designation)))
        .collect(Collectors.toList());
  }

  @GetMapping("/by-language")
  public List<Employee> getEmployeesByLanguage(
      @RequestParam String languageName,
      @RequestParam int minScore,
      @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
    List<Employee> result = employeeList.stream()
        .filter(employee -> employee.getKnownLanguages().stream()
            .anyMatch(lang -> lang.getLanguageName().equalsIgnoreCase(languageName)
                && lang.getScoreOutof100() >= minScore))
        .collect(Collectors.toList());

    Comparator<Employee> scoreComparator = Comparator.comparingInt(emp -> emp.getKnownLanguages().stream()
        .filter(lang -> lang.getLanguageName().equalsIgnoreCase(languageName))
        .findFirst()
        .map(Language::getScoreOutof100)
        .orElse(0));

    if ("desc".equalsIgnoreCase(sortOrder)) {
      result.sort(scoreComparator.reversed().thenComparing(Comparator.comparing(Employee::getFirstName)));
    } else {
      result.sort(scoreComparator.thenComparing(Comparator.comparing(Employee::getFirstName)));
    }

    return result;
  }

  @DeleteMapping("/delete/{employeeId}")
  public void deleteEmployee(@PathVariable Integer employeeId) throws JSONException, IOException {
    employeeList.removeIf(employee -> employee.getEmployeeID().equals(employeeId));
    EmployeeFileHandler.writeEmployeesToFile(employeeList);
  }

  @PatchMapping("/update/{employeeId}")
  public void updateEmployee(@PathVariable Integer employeeId, @RequestParam String newDesignation)
      throws JSONException, IOException {
    employeeList.forEach(employee -> {
      if (employee.getEmployeeID().equals(employeeId)) {
        employee.setDesignation(newDesignation);
      }
    });
    EmployeeFileHandler.writeEmployeesToFile(employeeList);
  }
}
