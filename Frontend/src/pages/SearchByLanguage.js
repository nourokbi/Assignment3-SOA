import React, { useState, useRef } from "react";
import classes from "../components/SearchInput.module.css";
import EmployeesList from "../components/EmployeesList";

const SearchByLanguage = () => {
  const [employees, setEmployees] = useState();
  const languageName = useRef("");
  const languageScore = useRef("");

  const fetchData = async (event) => {
    event.preventDefault();

    const name = languageName.current.value;
    const score = languageScore.current.value;
    const order = "ASC";

    try {
      const response = await fetch(
        `http://localhost:8080/employees/language?languageName=${name}&minScore=${score}&sortOrder=${order}`,
        {
          method: "GET", // or use the method you prefer

          headers: {
            "content-type": "application/json",
          },
        }
      );

      if (!response.ok) {
        throw new Error("Failed to fetch data");
      }

      const result = await response.json();
      setEmployees(result);
    } catch (error) {
      console.error("Error fetching data:", error.message);
    }
  };

  return (
    <>
      <form className={classes.search} onSubmit={fetchData}>
        <input
          type="text"
          name="languageName"
          placeholder="Add languageName..."
          aria-label="Add languageName..."
          ref={languageName}
        />
        <input
          type="number"
          max={100}
          name="score"
          placeholder="Add minScore..."
          aria-label="Add minScore..."
          ref={languageScore}
        />
        <button>Search</button>
      </form>

      <div>{employees && <EmployeesList employees={employees} />}</div>
    </>
  );
};

export default SearchByLanguage;
