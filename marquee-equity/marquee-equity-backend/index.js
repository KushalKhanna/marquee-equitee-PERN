const express = require('express');
const app = express();
const port = 5000;
const cors = require('cors');
const pool = require('./db')

// middlewear
app.use(cors());
app.use(express.json());

// ROUTES

// CRETATE company
app.post('/new-company', async(req, res) => {
  try {
    const {cin, company_name} = req.body;
    const newCompany = await pool.query("INSERT INTO company_list(cin, company_name) VALUES($1, $2) RETURNING *", [cin, company_name]);
    res.json(newCompany.rows[0]);
  } catch (err) {
    console.error(err.message);
  }
})

// GET ALL COMPANY
app.get('/get-all-companies', async(req, res) => {
  try {
    const allCompanies = await pool.query("select * from company_list");
    res.json(allCompanies.rows);
  } catch (e) {
    console.error(err.message);
  }
})
// GET SINGLE COMPANY
app.get('/get-company/:id', async(req, res) => {
  try {
    const {id} = req.params;
    const getCompanyById = await pool.query("SELECT * FROM company_list WHERE id = $1", [id]);
    res.json(getCompanyById.rows[0]);
  } catch (err) {
    console.error(err.message);
  }
})

// EDIT A COMPANY
app.put('/edit/:id', async(req, res) => {
  try {
    const {id} = req.params;
    const {cin, company_name} = req.body;
    const updateCompanyListDao = await pool.query("UPDATE company_list SET cin = $1, company_name = $2 WHERE id = $3", [cin, company_name, id]);
    res.json("Company updated successfully!");
  } catch (err) {
    console.error(err.message);
  }
})

// DELETE A COMPANY
app.delete('/delete/:id', async(req, res) => {
  try {
    const {id} = req.params;
    const deleteCompanyListDao = await pool.query("DELETE FROM company_list WHERE id = $1", [id]);
    res.json("Company deleted successfully!");
  } catch (err) {
    console.error(err.message);
  }
})

app.listen(port, () => {
  console.log(`Server running on port ${port}`);
})
