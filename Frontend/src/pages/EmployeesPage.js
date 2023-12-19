import { useLoaderData, json, defer, Await } from "react-router-dom";
import EmployeesList from "../components/EmployeesList";
import { Suspense } from "react";
import Spinner from "../components/UI/Spinner";

function EmployeesPage() {
  const { employees } = useLoaderData();

  return (
    <Suspense fallback={<Spinner color="#f9c762" />}>
      <Await resolve={employees}>
        {(loadedEmployees) => <EmployeesList employees={loadedEmployees} />}
      </Await>
    </Suspense>
  );
}

export default EmployeesPage;

const loademployees = async () => {
  const response = await fetch("http://localhost:8080/employees");

  if (!response.ok) {
    throw json({ message: "can't find the data" }, { status: 500 });
  }

  const data = await response.json();
  console.log(data);
  return data;
};

export const loader = () => {
  return defer({
    employees: loademployees(),
  });
};
