import React, {Fragment, useEffect, useState} from "react";
import { Form } from "react-bootstrap";

const ListCompany = () => {

  const [companies, setCompanies] = useState([]);
  const [search, setSearch] = useState('');

  const getCompanies = async () => {
    try {
      const response = await fetch('http://localhost:5000/get-all-companies');
      const jsonData = await response.json()
      setCompanies(jsonData);
    } catch (err) {
      console.error(err.message);
    }
  }

  // DELETE FUNCTION
  const deleteCompany = async (id) => {
    try {
      const deleteRecord = await fetch(`http://localhost:5000/delete/${id}`, {
        method: "DELETE"
      });
      console.log(deleteRecord);
      setCompanies(companies.filter(company => company.id !== id));
    } catch (err) {
      console.error(err.message);
    }
  }

  useEffect(() => {
    getCompanies();
  }, [])

  return(
    <Fragment>
    <div className="container">
      <h1 className="text-center mt-5">Company's List</h1>
      <Form.Control className="mt-4" onChange={(e) => setSearch(e.target.value)} placeholder='Search by cin' />

      <table className="table mt-5 text-center">
        <thead>
          <tr>
            <th>ID</th>
            <th>CIN</th>
            <th>Company Name</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
            {companies.filter((item) => {
              return (search.toLowerCase() === '' ? item : item.cin.toLowerCase().includes(search));
            }).map(company => (
              <tr key={company.id}>
                <td>{company.id}</td>
                <td>{company.cin}</td>
                <td>{company.company_name}</td>
                <td><button className="btn btn-danger" onClick={() => deleteCompany(company.id)}>DELETE</button></td>
              </tr>
            ))}
        </tbody>
      </table>
    </div>
    </Fragment>
  );
}

export default ListCompany;
