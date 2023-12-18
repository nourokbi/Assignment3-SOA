package com.employee.employeemanagment;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.databind.SerializationFeature;
// import java.io.File;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;

// public class EmployeeFileHandler {

//   private static final String FILE_PATH = "employees.json";

//   public static void main(String[] args) {
//     // Example usage
//     List<Employee> employeeList = readEmployeesFromFile();
//     System.out.println("Original Employee List:");
//     employeeList.forEach(System.out::println);

//     // Modify the employeeList as needed (e.g., add, update, delete)

//     writeEmployeesToFile(employeeList);
//     System.out.println("Employee List Updated and Written to File:");
//     readEmployeesFromFile().forEach(System.out::println);
//   }

//   static {
//     initializeFile();
//   }

//   private static void initializeFile() {
//     File file = new File(FILE_PATH);
//     if (!file.exists()) {
//       List<Employee> initialData = createInitialData();
//       writeEmployeesToFile(initialData);
//     }
//   }

//   private static List<Employee> createInitialData() {
//     List<Employee> initialData = new ArrayList<>();
//     // Add your initial employee data here if needed
//     return initialData;
//   }

//   public static List<Employee> readEmployeesFromFile() {
//     List<Employee> employeeList = new ArrayList<>();
//     ObjectMapper objectMapper = new ObjectMapper();

//     try {
//       employeeList = objectMapper.readValue(new File(FILE_PATH),
//           objectMapper.getTypeFactory().constructCollectionType(List.class, Employee.class));
//     } catch (IOException e) {
//       e.printStackTrace();
//     }

//     return employeeList;
//   }

//   public static void writeEmployeesToFile(List<Employee> employeeList) {
//     ObjectMapper objectMapper = new ObjectMapper();
//     objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

//     try {
//       objectMapper.writeValue(new File(FILE_PATH), employeeList);
//     } catch (IOException e) {
//       e.printStackTrace();
//     }
//   }
// }

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFileHandler {

  private static final String FILE_PATH = "employees.json";

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

  public static List<Employee> readEmployeesFromFile() {
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

  public static void writeEmployeesToFile(List<Employee> employeeList) throws JSONException, IOException {
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
