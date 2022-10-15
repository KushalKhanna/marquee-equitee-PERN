CREATE DATABASE marquee_equity_company_list;

CREATE TABLE company_list(
  id SERIAL PRIMARY KEY,
  cin varchar(50),
  company_name varchar(200)
);
