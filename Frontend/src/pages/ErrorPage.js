import React from "react";
import PageContent from "../components/PageContent";
import { useRouteError } from "react-router-dom";
import MainNavigation from "../components/MainNavigation";

const ErrorPage = () => {
  const errorObj = useRouteError();
  console.log(errorObj);

  // const message = JSON.parse(errorObj.data).message;
  const message = errorObj.data.message;
  const title = "an error occured";

  return (
    <>
      <MainNavigation />
      <PageContent title={title}>
        <p>{message}</p>
      </PageContent>
    </>
  );
};

export default ErrorPage;
