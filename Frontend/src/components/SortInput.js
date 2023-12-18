import { useRef } from "react";
import classes from "./SortInput.module.css";

function SortInput({ onSort }) {
  const sortMode = useRef("");
  const sortProperty = useRef("");

  function submitHandler(event) {
    event.preventDefault();
    const sortData = {
      mode: sortMode.current.value,
      property: sortProperty.current.value,
    };
    console.log(sortData);
    onSort(sortData);
  }

  return (
    <form className={classes.search} onSubmit={submitHandler}>
      <select
        name="sortProperty"
        aria-label="Select sort property"
        ref={sortProperty}
      >
        <option value="id">ID</option>
        <option value="firstName">First Name</option>
        <option value="lastName">Last Name</option>
        <option value="level">Level</option>
        <option value="gpa">GPA</option>
        <option value="address">Address</option>
        <option value="gender">Gender</option>
      </select>
      <select name="modeField" aria-label="Select sort mode" ref={sortMode}>
        <option value="ascending">Ascending</option>
        <option value="descending">Descending</option>
      </select>

      <button>Sort</button>
    </form>
  );
}

export default SortInput;
