import {Navbar, NavItem, NavLink,} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';
import {BoxSeam, House, Search} from "react-bootstrap-icons";


export default function MagicCrowFooter(){
    return (
            <Navbar fixed='bottom' bg='dark' className='justify-content-around'>
                    <NavItem>
                        <NavLink href='/'><House size='30px' /></NavLink>
                    </NavItem>
                    <NavItem>
                        <NavLink href='/searchCards'><Search size='30px' /></NavLink>
                    </NavItem>
                    <NavItem>
                        <NavLink href='/myCollection'><BoxSeam size='30px' /></NavLink>
                    </NavItem>
            </Navbar>
    )
}

