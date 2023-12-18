package com.employee.employeemanagment;

import org.json.JSONException;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.web.bind.annotation.*;

// import java.util.ArrayList;
// import java.util.Comparator;
// import java.util.List;
// import java.util.stream.Collectors;

// @SpringBootApplication
// @RestController
// @RequestMapping("/employees")
// public class EmployeeService {

//     private static final String FILE_PATH = "employees.json";
//     private List<Employee> employeeList = EmployeeFileHandler.readEmployeesFromFile();

//     public static void main(String[] args) {
//         SpringApplication.run(EmployeeService.class, args);
//     }

//     @GetMapping
//     public List<Employee> getAllEmployees() {
//         return employeeList;
//     }

//     @PostMapping("/add")
//     public void addEmployee(@RequestBody Employee employee) {
//         employeeList.add(employee);
//         EmployeeFileHandler.writeEmployeesToFile(employeeList);
//     }

//     @GetMapping("/search")
//     public List<Employee> searchEmployee(@RequestParam(required = false) Integer employeeId,
//                                         @RequestParam(required = false) String designation) {
//         return employeeList.stream()
//                 .filter(employee -> (employeeId == null || employee.getEmployeeID().equals(employeeId))
//                         && (designation == null || employee.getDesignation().equalsIgnoreCase(designation)))
//                 .collect(Collectors.toList());
//     }

//     @DeleteMapping("/delete/{employeeId}")
//     public void deleteEmployee(@PathVariable Integer employeeId) {
//         employeeList.removeIf(employee -> employee.getEmployeeID().equals(employeeId));
//         EmployeeFileHandler.writeEmployeesToFile(employeeList);
//     }

//     @PatchMapping("/update/{employeeId}")
//     public void updateEmployee(@PathVariable Integer employeeId, @RequestParam String newDesignation) {
//         employeeList.forEach(employee -> {
//             if (employee.getEmployeeID().equals(employeeId)) {
//                 employee.setDesignation(newDesignation);
//             }
//         });
//         EmployeeFileHandler.writeEmployeesToFile(employeeList);
//     }

//     // @GetMapping("/java-experts")
//     // public List<Employee> getJavaExperts() {
//     //     return employeeList.stream()
//     //             .filter(employee -> employee.getKnownLanguages().stream()
//     //                     .anyMatch(lang -> "Java".equalsIgnoreCase(lang.getLanguageName()) && lang.getScoreOutof100() > 50))
//     //             .sorted(Comparator.comparing(Employee::getFirstName))
//     //             .collect(Collectors.toList());
//     // }
// }


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
@RequestMapping("/employees")
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

    @DeleteMapping("/delete/{employeeId}")
    public void deleteEmployee(@PathVariable Integer employeeId) throws JSONException, IOException {
        employeeList.removeIf(employee -> employee.getEmployeeID().equals(employeeId));
        EmployeeFileHandler.writeEmployeesToFile(employeeList);
    }

    @PatchMapping("/update/{employeeId}")
    public void updateEmployee(@PathVariable Integer employeeId, @RequestParam String newDesignation) throws JSONException, IOException {
        employeeList.forEach(employee -> {
            if (employee.getEmployeeID().equals(employeeId)) {
                employee.setDesignation(newDesignation);
            }
        });
        EmployeeFileHandler.writeEmployeesToFile(employeeList);
    }
}
