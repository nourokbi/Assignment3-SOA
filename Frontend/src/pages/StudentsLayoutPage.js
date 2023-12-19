import React from "react";
// import StudentsNavigation from "../components/StudentsNavigation";
import { Outlet } from "react-router-dom";

const StudentsLayoutPage = () => {
  return (
    <>
      {/* <StudentsNavigation /> */}
      <main>
        <Outlet />
      </main>
    </>
  );
};

export default StudentsLayoutPage;
