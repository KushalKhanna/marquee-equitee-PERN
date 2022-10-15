import React, { Fragment, useState } from 'react';

export default function AddCompany() {

  const [cin, setCin] = useState("");
  const [company_name, setCompany_name] = useState("");

  const onFormSubmit = (e) => {
    e.preventDefault()
    try {
      const bodyCompanyNameAndCin = {cin, company_name}
      const response = fetch("http://localhost:5000/new-company", {
        method: "post",
        headers: {"Content-Type":"application/json"},
        body: JSON.stringify(bodyCompanyNameAndCin)
      });
      console.log(response);
    } catch (err) {
      console.error(err.message);
    }
    window.location = '/'
  }

  return (
    <Fragment>
      <div className="container">
      <h1 className="text-center mt-5">Add Company</h1>
      <form onSubmit={onFormSubmit}>
        <label className="mt-5">Enter CIN:</label>
        <input className="form-control mt-1" value={cin} onChange={e => {setCin(e.target.value)}} />
        <label className="mt-3">Enter Company Name:</label>
        <input className="form-control mt-1" value={company_name} onChange={e => {setCompany_name(e.target.value)}} />
        <button className="btn btn-primary mt-3" type="submit">Add</button>
      </form>
      </div>
    </Fragment>
  );
};
