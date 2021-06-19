import {Nav, Navbar, NavItem, NavLink,} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';
import {BoxSeam, House, Search} from "react-bootstrap-icons";


export default function MagicCrowFooter(){
    return (

            <Navbar fixed="bottom" bg='dark' variant="dark" className='justify-content-around'>
                    <NavItem>
                        <NavLink href='/'><House size='40px' /></NavLink>
                    </NavItem>
                    <NavItem>
                        <NavLink href='/searchCards'><Search size='40px' /></NavLink>
                    </NavItem>
                    <NavItem>
                        <NavLink href='/myCollection'><BoxSeam size='40px' /></NavLink>
                    </NavItem>
            </Navbar>

    )
}

