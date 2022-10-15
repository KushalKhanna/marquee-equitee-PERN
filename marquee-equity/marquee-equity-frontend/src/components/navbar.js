import React from "react";
import {Navbar, Nav, NavDropdown, Container} from "react-bootstrap";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Link
} from "react-router-dom";

import ListCompany from "./listCompany";
import AddCompany from "./addCompany";

const NavbarItem = () => {

  return (
    <Router>
      <div>
        <Navbar bg="dark" variant={"dark"} expand="lg">
          <Container fluid>
            <Navbar.Brand href="/">Marquee-Equitee</Navbar.Brand>
            <Navbar.Toggle aria-controls="navbarScroll" />
            <Navbar.Collapse id="navbarScroll">
              <Nav
                className="me-auto my-2 my-lg-0"
                style={{ maxHeight: '100px' }}
                navbarScroll
              >
                <Nav.Link as={Link} to={"/"} href="#action1">Home</Nav.Link>
                <Nav.Link as={Link} to={"/add"} href="#action2">Add Company</Nav.Link>
                <NavDropdown title="References" id="navbarScrollingDropdown">
                  <NavDropdown.Item href="https://github.com/KushalKhanna">GitHub</NavDropdown.Item>
                  <NavDropdown.Item href="https://www.linkedin.com/in/kushal-khanna/">
                    LinkedIn
                  </NavDropdown.Item>
                  <NavDropdown.Divider />
                  <NavDropdown.Item href="#action5">
                    Under construction
                  </NavDropdown.Item>
                </NavDropdown>
              </Nav>
            </Navbar.Collapse>
          </Container>
        </Navbar>
      </div>
      <div>
      <Routes>
         <Route path="/" element={<ListCompany />} />
         <Route path="/add" element={<AddCompany />} />
       </Routes>
       </div>
    </Router>
  );
}

export default NavbarItem;
