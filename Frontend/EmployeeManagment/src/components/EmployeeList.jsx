// // EmployeeList.js
// import { useState, useEffect } from "react";

// const EmployeeList = () => {
//   const [employees, setEmployees] = useState([]);

//   useEffect(() => {
//     fetch("http://localhost:8080/employees")
//       .then((response) => response.json())
//       .then((data) => {
//         console.log("🚀 ~ file: EmployeeList.jsx:11 ~ useEffect ~ data:", data)
//         return setEmployees(data)
//       })
//       .catch((error) => console.error("Error fetching data:", error));
//   }, []);

//   return (
//     <div>
//       <h1>Employee List</h1>
//       <ul>
//         {employees.map((employee) => (
//           <li key={employee.employeeID}>
//             {`${employee.firstName} ${employee.lastName} - ${employee.designation}`}
//           </li>
//         ))}
//       </ul>
//     </div>
//   );
// };

// export default EmployeeList;

// EmployeeList.js
import { useState, useEffect } from 'react';
import axios from 'axios';

const EmployeeList = () => {
  const [employees, setEmployees] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/employees')
      .then(response => setEmployees(response.data))
      .catch(error => console.error('Error fetching data:', error));
  }, []);

  return (
    <div>
      <h1>Employee List</h1>
      <ul>
        {employees.map(employee => (
          <li key={employee.employeeID}>
            {`${employee.firstName} ${employee.lastName} - ${employee.designation}`}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default EmployeeList;
