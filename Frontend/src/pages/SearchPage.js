import React, { useState } from "react";
import SearchInput from "../components/SearchInput";
import EmployeesList from "../components/EmployeesList";

const SearchPage = () => {
  const [employees, setEmployees] = useState();

  const fetchData = async (searchData) => {
    const { word, property } = searchData;
    // console.log(word, property);
    // console.log(`http://localhost:8080/search?${property}=${word}`);
    try {
      const response = await fetch(
        `http://localhost:8080/employees/search?${property}=${word}`,
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

  const handleSearch = async (searchData) => {
    console.log(searchData);
    // Call your fetchData function with the search word
    await fetchData(searchData);
  };

  return (
    <div>
      <SearchInput onSearch={handleSearch} />
      {employees && <EmployeesList employees={employees} />}
    </div>
  );
};

export default SearchPage;
