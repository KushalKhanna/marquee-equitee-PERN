const Pool = require("pg").Pool

const pool = new Pool({
  user: "postgres",
  password: "abcd",
  host: "localhost",
  port: "5432",
  database: "marquee_equity_company_list"
})

module.exports = pool;
