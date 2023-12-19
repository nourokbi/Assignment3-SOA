import { NavLink } from "react-router-dom";
import classes from "./MainNavigation.module.css";

const links = [
  // { title: "Home", to: "" },
  { title: "Employees", to: "employees" },
  { title: "Search", to: "search" },
  { title: "SearchByLanguage", to: "language" },
  { title: "New Employee", to: "employees/new" },
];

function MainNavigation() {
  return (
    <header className={classes.header}>
      <nav>
        <ul className={classes.list}>
          {links.map((link) => {
            return (
              <li key={link.title}>
                <NavLink
                  className={({ isActive }) =>
                    isActive ? classes.active : undefined
                  }
                  to={link.to}
                  end
                >
                  {link.title}
                </NavLink>
              </li>
            );
          })}
        </ul>
      </nav>
    </header>
  );
}

export default MainNavigation;
