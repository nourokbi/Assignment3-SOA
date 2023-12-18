package com.employee.employeemanagment;
import java.util.List;

public class Employee {

  private String firstName;
  private String lastName;
  private Integer employeeID;
  private String designation;
  private List<Language> knownLanguages;

  // Constructors, getters, and setters

  public Employee() {
  }

  public Employee(String firstName, String lastName, Integer employeeID, String designation,
      List<Language> knownLanguages) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.employeeID = employeeID;
    this.designation = designation;
    this.knownLanguages = knownLanguages;
  }

  // Getters and setters (generated using Lombok for brevity)

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Integer getEmployeeID() {
    return employeeID;
  }

  public void setEmployeeID(Integer employeeID) {
    this.employeeID = employeeID;
  }

  public String getDesignation() {
    return designation;
  }

  public void setDesignation(String designation) {
    this.designation = designation;
  }

  public List<Language> getKnownLanguages() {
    return knownLanguages;
  }

  public void setKnownLanguages(List<Language> knownLanguages) {
    this.knownLanguages = knownLanguages;
  }

  // toString method for easy printing

  @Override
  public String toString() {
    return "Employee{" +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", employeeID=" + employeeID +
        ", designation='" + designation + '\'' +
        ", knownLanguages=" + knownLanguages +
        '}';
  }
}