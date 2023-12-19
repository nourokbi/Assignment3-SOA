import { createBrowserRouter, RouterProvider } from "react-router-dom";

// import HomePage from "./pages/HomePage";
import SearchPage from "./pages/SearchPage";
import LayoutPage from "./pages/LayoutPage";
import EditEmployeePage from "./pages/EditEmployeePage";
import EmployeesPage, { loader as fetchData } from "./pages/EmployeesPage";
import NewEmployeePage from "./pages/NewEmployeePage";
import EmployeeDetailsPage, {
  loader as fetchEventDetails,
  action as deleteEventAction,
} from "./pages/EmployeeDetailsPage";
import StudentsLayoutPage from "./pages/StudentsLayoutPage";
import ErrorPage from "./pages/ErrorPage";
import SearchByLanguage from "./pages/SearchByLanguage";

const router = createBrowserRouter([
  {
    path: "/",
    element: <LayoutPage />,
    errorElement: <ErrorPage />,

    children: [
      { path: "search", element: <SearchPage /> },
      { path: "language", element: <SearchByLanguage /> },

      {
        path: "employees",
        element: <StudentsLayoutPage />,

        children: [
          {
            path: "",
            element: <EmployeesPage />,
            loader: fetchData,
          },
          {
            path: "new",
            element: <NewEmployeePage />,
          },

          {
            path: ":id",
            id: "ourParent",
            loader: fetchEventDetails,
            children: [
              {
                index: true,
                element: <EmployeeDetailsPage />,
                action: deleteEventAction,
              },
              {
                path: "edit",
                element: <EditEmployeePage />,
              },
            ],
          },
        ],
      },
    ],
  },
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
