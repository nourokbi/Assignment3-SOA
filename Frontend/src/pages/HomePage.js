import React from "react";
import SortInput from "../components/SortInput";

const HomePage = () => {
  const sendData = async (sortData) => {
    try {
      const response = await fetch("http://localhost:8080/sort", {
        method: "POST",
        headers: {
          "content-type": "application/json",
        },
        body: JSON.stringify(sortData),
      });

      if (!response.ok) {
        throw new Error("Failed to send data");
      }
    } catch (error) {
      console.error("Error sending data:", error.message);
    }
  };

  const handleSort = async (sortData) => {
    console.log(sortData);
    await sendData(sortData);
  };
  return (
    <>
      <div>Home Page</div>
      <SortInput onSort={handleSort} />
    </>
  );
};

export default HomePage;
