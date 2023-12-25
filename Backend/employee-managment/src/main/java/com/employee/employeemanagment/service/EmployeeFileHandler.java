package com.employee.employeemanagment.service;

import com.employee.employeemanagment.models.Employee;
import com.employee.employeemanagment.models.Language;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeFileHandler {

  private static final String FILE_PATH = "employees.json";
  private static final List<Employee> employeeList = EmployeeFileHandler.readEmployeesFromFile();

  static {
    try {
      initializeFile();
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  // ==========================================
  // ========== working with the endpoints =========
  // ==========================================

  public static List<Employee> getAll() {
    return employeeList;
  }

  public static void Add(Employee employee) throws IOException {
    employeeList.add(employee);
    System.out.println(employee);
    writeEmployeesToFile(employeeList);
  }

  public static void Update(Integer employeeId, String newDesignation) throws IOException {
    employeeList.forEach(employee -> {
      if (employee.getEmployeeID().equals(employeeId)) {
        employee.setDesignation(newDesignation);
      }
    });
    EmployeeFileHandler.writeEmployeesToFile(employeeList);
  }

  public static void Delete(Integer employeeId) throws IOException {
    employeeList.removeIf(employee -> employee.getEmployeeID().equals(employeeId));
    writeEmployeesToFile(employeeList);
  }

  public static List<Employee> searchByLanguage(String languageName, int minScore, String sortOrder) {

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

    if ("dec".equalsIgnoreCase(sortOrder)) {
      result.sort(scoreComparator.reversed().thenComparing(Comparator.comparing(Employee::getFirstName)));
    } else {
      result.sort(scoreComparator.thenComparing(Comparator.comparing(Employee::getFirstName)));
    }

    return result;
  }

  public static List<Employee> Search(Integer employeeId, String designation) {
    return employeeList.stream().filter(employee -> (employeeId == null || employee.getEmployeeID().equals(employeeId))
        && (designation == null || employee.getDesignation().equalsIgnoreCase(designation)))
        .collect(Collectors.toList());

  }

  public static Employee getEmployeeById(Integer employeeId) {
    return employeeList.stream()
        .filter(employee -> employee.getEmployeeID().equals(employeeId))
        .findFirst()
        .orElse(null); // Return null if employee with the specified ID is not found
  }

  // ==========================================
  // ========== working with the file =========
  // ==========================================

  private static void initializeFile() throws JSONException, IOException {
    File file = new File(FILE_PATH);
    if (!file.exists()) {
      List<Employee> initialData = createInitialData();
      writeEmployeesToFile(initialData);
    }
  }

  private static List<Employee> createInitialData() {
    List<Employee> initialData = new ArrayList<>();
    // Add your initial employee data here if needed
    return initialData;
  }

  private static List<Employee> readEmployeesFromFile() {
    List<Employee> employeeList = new ArrayList<>();

    try {
      String jsonString = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
      JSONArray jsonArray = new JSONArray(jsonString);

      for (int i = 0; i < jsonArray.length(); i++) {
        JSONObject jsonEmployee = jsonArray.getJSONObject(i);
        Employee employee = convertJsonToEmployee(jsonEmployee);
        employeeList.add(employee);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return employeeList;
  }

  private static void writeEmployeesToFile(List<Employee> employeeList) throws JSONException, IOException {
    JSONArray jsonArray = new JSONArray();

    for (Employee employee : employeeList) {
      jsonArray.put(convertEmployeeToJson(employee));
    }

    Files.write(Paths.get(FILE_PATH), jsonArray.toString(2).getBytes());

  }

  private static Employee convertJsonToEmployee(JSONObject jsonEmployee) {
    String firstName = jsonEmployee.getString("firstName");
    String lastName = jsonEmployee.getString("lastName");
    int employeeID = jsonEmployee.getInt("employeeID");
    String designation = jsonEmployee.getString("designation");
    JSONArray jsonLanguages = jsonEmployee.getJSONArray("knownLanguages");

    List<Language> knownLanguages = new ArrayList<>();
    for (int i = 0; i < jsonLanguages.length(); i++) {
      JSONObject jsonLanguage = jsonLanguages.getJSONObject(i);
      String languageName = jsonLanguage.getString("languageName");
      int scoreOutof100 = jsonLanguage.getInt("scoreOutof100");
      knownLanguages.add(new Language(languageName, scoreOutof100));
    }

    return new Employee(firstName, lastName, employeeID, designation, knownLanguages);
  }

  private static JSONObject convertEmployeeToJson(Employee employee) {
    JSONObject jsonEmployee = new JSONObject();
    jsonEmployee.put("firstName", employee.getFirstName());
    jsonEmployee.put("lastName", employee.getLastName());
    jsonEmployee.put("employeeID", employee.getEmployeeID());
    jsonEmployee.put("designation", employee.getDesignation());

    JSONArray jsonLanguages = new JSONArray();
    for (Language language : employee.getKnownLanguages()) {
      JSONObject jsonLanguage = new JSONObject();
      jsonLanguage.put("languageName", language.getLanguageName());
      jsonLanguage.put("scoreOutof100", language.getScoreOutof100());
      jsonLanguages.put(jsonLanguage);
    }

    jsonEmployee.put("knownLanguages", jsonLanguages);

    return jsonEmployee;
  }

}
