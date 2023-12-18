import classes from "./EmployeesList.module.css";
import { Link } from "react-router-dom";

function EmployeesList({ employees }) {
  return (
    <div className={classes.events}>
      <h1>All Employees ({employees.length})</h1>
      <ul className={classes.list}>
        {employees.map((employee) => (
          <li key={employee.employeeID} className={classes.item}>
            <Link to={`/employees/${employee.employeeID}`}>
              <div className={classes.content}>
                <h2>{`${employee.firstName} ${employee.lastName}`}</h2>
                <p className={classes.id}>{`ID: ${employee.employeeID}`}</p>
              </div>
            </Link>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default EmployeesList;
