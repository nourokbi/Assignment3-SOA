import React, { useRef } from "react";
import { useRouteLoaderData, useNavigate } from "react-router-dom";

const EditEmployeePage = () => {
  const data = useRouteLoaderData("ourParent");
  const navigate = useNavigate();
  console.log(data);

  const designationRef = useRef();
  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      await updateDesignation(data.employeeID, designationRef.current.value);
      console.log("Designation updated successfully!");
    } catch (error) {
      console.error("Failed to update designation:", error.message);
    }
  };

  const updateDesignation = async (employeeId, newDesignation) => {
    const url = `http://localhost:8080/employees/update/${employeeId}?newDesignation=${newDesignation}`;
    const response = await fetch(url, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
    });

    if (!response.ok) {
      throw new Error("Failed to update designation");
    }

    navigate("../../");
  };

  return (
    <div>
      <h1>Edit Employee</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="designation">New Designation:</label>
          <input
            type="text"
            id="designation"
            name="designation"
            ref={designationRef}
            required
          />
        </div>
        <div>
          <button type="submit">Update Designation</button>
        </div>
      </form>
    </div>
  );
};

export default EditEmployeePage;
