import { useNavigate, useNavigation, json } from "react-router-dom";
import { useRef, useState } from "react";
import classes from "./EmployeesForm.module.css";
import LanguageForm from "./LanguageForm";

function EmployeesForm({ method, employee }) {
  const navigate = useNavigate();
  const navigation = useNavigation();
  const isSubmitting = navigation.state === "submitting";
  const [languages, setLanguages] = useState([]);

  const languageRef = useRef();
  const scoreRef = useRef();
  const fNameRef = useRef();
  const lNameRef = useRef();
  const idRef = useRef();
  const designationRef = useRef();

  const addLanguage = () => {
    setLanguages([
      ...languages,
      {
        languageName: languageRef.current.value,
        scoreOutof100: scoreRef.current.value,
      },
    ]);

    languageRef.current.value = "";
    scoreRef.current.value = "";
  };
  function cancelHandler() {
    navigate("..");
  }
  // console.log(employee);

  async function sendDataHandler(event) {
    event.preventDefault();
    console.log(languages);
    const employeeData = {
      firstName: fNameRef.current.value,
      lastName: lNameRef.current.value,
      employeeID: idRef.current.value,
      designation: designationRef.current.value,
      knownLanguages: languages,
    };

    let url = "http://localhost:8080/employees/add";
    const response = await fetch(url, {
      method: "POST",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(employeeData),
    });

    if (response.status === 422) {
      return response;
    }

    if (!response.ok) {
      throw json(
        { message: "can't send the data .." },
        {
          status: 500,
        }
      );
    } else {
      navigate("..");
      // return redirect("/employees");
    }
  }

  return (
    <form onSubmit={sendDataHandler}>
      <div className={classes.form}>
        
        <p>
          <label htmlFor="fname">First Name</label>
          <input
            id="fname"
            type="text"
            name="fname"
            ref={fNameRef}
            required
            defaultValue={employee ? employee.firstName : ""}
          />
        </p>

        <p>
          <label htmlFor="lname">Last Name</label>
          <input
            id="lname"
            type="text"
            name="lname"
            ref={lNameRef}
            required
            defaultValue={employee ? employee.lastName : ""}
          />
        </p>

        <p>
          <label htmlFor="id">ID</label>
          <input
            id="id"
            name="id"
            type="number"
            ref={idRef}
            required
            defaultValue={employee ? employee.level : ""}
          />
        </p>
        <p>
          <label htmlFor="designation">Designation</label>
          <input
            id="designation"
            name="designation"
            type="text"
            ref={designationRef}
            required
            defaultValue={employee ? employee.gpa : ""}
          />
        </p>
          <LanguageForm languages={languages} setLanguages={setLanguages} />
          <LanguageForm languages={languages} setLanguages={setLanguages} />
          <LanguageForm languages={languages} setLanguages={setLanguages} />

      </div>

      <div className={classes.actions}>
        <button type="button" onClick={cancelHandler}>
          Cancel
        </button>
        <button disabled={isSubmitting}>
          {isSubmitting ? "Submitting" : "Save"}
        </button>
      </div>
    </form>
  );
}

export default EmployeesForm;
