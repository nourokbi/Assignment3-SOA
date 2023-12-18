// EmployeeForm.js
import { useState } from "react";
import axios from "axios";

const EmployeeForm = () => {
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    employeeID: 0,
    designation: "",
    knownLanguages: [
      { languageName: "", scoreOutof100: 0 },
      { languageName: "", scoreOutof100: 0 },
      { languageName: "", scoreOutof100: 0 },
    ],
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post("http://localhost:8080/employees/add", formData);
      alert("Employee added successfully!");
    } catch (error) {
      console.error("Error adding employee:", error);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;

    if (name === "knownLanguages") {
      const languages = [...formData.knownLanguages];
      const index = parseInt(e.target.dataset.index, 10);
      languages[index][e.target.dataset.field] = value;

      setFormData({
        ...formData,
        knownLanguages: languages,
      });
    } else {
      setFormData({
        ...formData,
        [name]: value,
      });
    }
  };

  return (
    <div>
      <h1>Add Employee</h1>
      <form onSubmit={handleSubmit}>
        <label>
          Employee ID:
          <input
            type="number"
            name="employeeID"
            value={formData.employeeID}
            onChange={handleChange}
            required
          />
        </label>
        <br />
        <label>
          First Name:
          <input
            type="text"
            name="firstName"
            value={formData.firstName}
            onChange={handleChange}
            required
          />
        </label>
        <br />
        <label>
          Last Name:
          <input
            type="text"
            name="lastName"
            value={formData.lastName}
            onChange={handleChange}
            required
          />
        </label>
        <br />
        <label>
          Designation:
          <input
            type="text"
            name="designation"
            value={formData.designation}
            onChange={handleChange}
            required
          />
        </label>
        <br />
        <label>
          Known Languages:
          {formData.knownLanguages.map((language, index) => (
            <div key={index}>
              <input
                type="text"
                name="knownLanguages"
                data-index={index}
                data-field="languageName"
                value={language.languageName}
                onChange={handleChange}
                placeholder="Language Name"
                required
              />
              <input
                type="number"
                name="knownLanguages"
                max={100}
                min={0}
                data-index={index}
                data-field="scoreOutof100"
                value={language.scoreOutof100}
                onChange={handleChange}
                placeholder="Score Outof100"
                required
              />
            </div>
          ))}
        </label>
        <br />
        <button type="submit">Add Employee</button>
      </form>
    </div>
  );
};

export default EmployeeForm;
