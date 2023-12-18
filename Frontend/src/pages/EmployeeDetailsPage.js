import React, { Suspense } from "react";
import {
  useRouteLoaderData,
  json,
  redirect,
  defer,
  Await,
} from "react-router-dom";
import EmployeeItem from "../components/EmployeeItem";
import Spinner from "../components/UI/Spinner";

const EmployeeDetailsPage = () => {
  const employee = useRouteLoaderData("ourParent");

  return (
    <>
      <Suspense fallback={<Spinner color="#f9c762" />}>
        <Await resolve={employee}>
          {(loademployee) => <EmployeeItem employee={loademployee} />}
        </Await>
      </Suspense>
    </>
  );
};

export default EmployeeDetailsPage;

export const loademployee = async (id) => {
  const response = await fetch(
    "http://localhost:8080/employees/search?employeeId=" + id
  );
  const data = await response.json();

  if (!response.ok) {
    throw json({ message: "can't get the event details " }, { status: 500 });
  } else {
    return data;
  }
};

export const loader = async ({ request, params }) => {
  const id = params.id;

  return defer(...(await loademployee(id)));
};

export const action = async ({ request, params }) => {
  const id = params.id;

  const response = await fetch("http://localhost:8080/employee?id=" + id, {
    method: request.method,
  });

  if (!response.ok) {
    throw json({ message: "can't delete the employee.. " }, { status: 500 });
  } else {
    return redirect("/employees");
  }
};
