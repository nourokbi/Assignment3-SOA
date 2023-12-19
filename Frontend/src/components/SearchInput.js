import { useRef } from "react";
import classes from "./SearchInput.module.css";

function SearchInput({ onSearch }) {
  const searchWord = useRef("");
  const searchProperty = useRef("");

  function submitHandler(event) {
    event.preventDefault();
    const searchData = {
      word: searchWord.current.value,
      property: searchProperty.current.value,
    };
    // const searchData = {
    //   [searchProperty.current.value]: searchWord.current.value,
    // };
    onSearch(searchData);
    console.log(searchData);
  }

  return (
    <form className={classes.search} onSubmit={submitHandler}>
      <select
        name="searchField"
        aria-label="Select search field"
        ref={searchProperty}
      >
        <option value="employeeId">ID</option>
        <option value="designation">Designation</option>
      </select>
      <input
        type="text"
        name="searchWord"
        placeholder="Search for Employee..."
        aria-label="Search for Employee..."
        ref={searchWord}
      />
      <button>Search</button>
    </form>
  );
}

export default SearchInput;
