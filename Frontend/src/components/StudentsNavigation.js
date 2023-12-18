import { NavLink } from "react-router-dom";
import classes from "./StudentsNavigation.module.css";

function StudentsNavigation() {
  return (
    <header className={classes.header}>
      <nav>
        <ul className={classes.list}>
          <li>
            <NavLink
              to=""
              className={({ isActive }) =>
                isActive ? classes.active : undefined
              }
              end
            >
              {" "}
              All Students
            </NavLink>
          </li>
          <li>
            <NavLink
              to="new"
              className={({ isActive }) =>
                isActive ? classes.active : undefined
              }
              end
            >
              {" "}
              New Student
            </NavLink>
          </li>
        </ul>
      </nav>
    </header>
  );
}

export default StudentsNavigation;
