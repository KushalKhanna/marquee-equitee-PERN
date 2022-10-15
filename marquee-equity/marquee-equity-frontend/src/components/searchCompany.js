import React, { Fragment } from "react";

export default function SearchCompany() {

  const searchRecord = async (id) => {
    try {
      const response = await fetch(`http://localhost:5000/get-company/${id}`);

    } catch (err) {
      console.error(err.message);
    }
  }

  console.log(response);

  return (
    <Fragment>
      <h1 className="text-center mt-5">Company Record</h1>
      <table className="table mt-5 text-center">
        <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">CIN</th>
            <th scope="col">Company Name</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>Tests</td>
            <td>Tests</td>
            <td>Tests</td>
          <tr>

        </tbody>
      </table>
    </Fragment>
  );

}
