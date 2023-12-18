import { Link } from "react-router-dom";
import { json, useNavigate } from "react-router-dom";
import classes from "./EmployeeItem.module.css";

function EmployeeItem({ employee }) {
  // const submit = useSubmit();
  const navigate = useNavigate();

  async function startDeleteHandler() {
    const choice = window.confirm("Are you sure to delete it ?");

    if (choice) {
      // submit(null, { method: "DELETE" });
      // localhost:8080/employees/delete/1001

      let url = `http://localhost:8080/employees/delete/${employee.employeeID}`;
      const response = await fetch(url, {
        method: "DELETE",
        headers: {
          "content-type": "application/json",
        },
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
      }
    }
  }
  console.log(employee);

  return (
    <>
      <div className={classes.employee}>
        <h1>
          {employee.firstName} {employee.lastName}
        </h1>
        <p>
          ID: <strong> {employee.employeeID}</strong>
        </p>
        <p>
          Designation: <strong> {employee.designation}</strong>
        </p>
        <h3>Languages</h3>
        <ul>
          {employee.knownLanguages.map((lang) => {
            return (
              <li key={lang}>
                {lang.languageName}: {lang.scoreOutof100}{" "}
              </li>
            );
          })}
        </ul>
      </div>
      <menu className={classes.actions}>
        <Link to="edit">Edit</Link>
        <button onClick={startDeleteHandler}>Delete</button>
      </menu>
    </>
  );
}

export default EmployeeItem;
