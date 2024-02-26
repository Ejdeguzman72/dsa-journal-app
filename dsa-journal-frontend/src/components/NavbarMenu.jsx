import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import Offcanvas from 'react-bootstrap/Offcanvas';

function NavbarMenu() {
  return (
    <>
      {[false].map((expand) => (
        <Navbar key={expand} expand={expand} variant='dark' className='navbar-bg' fixed='top' >
          <Container fluid>
            <Navbar.Brand href="#" className="navbar-text">DeGuzmanStuffAnywhere</Navbar.Brand>
            <Navbar.Toggle aria-controls={`offcanvasNavbar-expand-${expand}`} />
            <Navbar.Offcanvas
              id={`offcanvasNavbar-expand-${expand}`}
              aria-labelledby={`offcanvasNavbarLabel-expand-${expand}`}
              placement="end"s
              variant="dark"
            >
              <Offcanvas.Header closeButton>
                <Offcanvas.Title id={`offcanvasNavbarLabel-expand-${expand}`}>
                  Offcanvas
                </Offcanvas.Title>
              </Offcanvas.Header>
              <Offcanvas.Body>
                <Nav className="justify-content-end flex-grow-1 pe-3" variant="dark">
                  <Nav.Link href="/" className="navbar-text">View All Entries</Nav.Link>
                  <Nav.Link href="/add-journal-entry" className="navbar-text">Write Entry</Nav.Link>
                </Nav>
              </Offcanvas.Body>
            </Navbar.Offcanvas>
          </Container>
        </Navbar>
      ))}
    </>
  );
}

export default NavbarMenu;