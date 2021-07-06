import {Navbar, NavItem} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';
import {BoxSeam, House, Search} from "react-bootstrap-icons";
import {Link} from "react-router-dom";
import {useContext} from "react";
import AuthContext from "../context/AuthContext";


export default function MagicCrowFooter() {

    const {token} = useContext(AuthContext);

    console.log(token)

    return (
        token ? <Navbar fixed='bottom' bg='dark' className='justify-content-around'>
            <NavItem>
                <Link to='/home'><House size='30px'/></Link>
            </NavItem>
            <NavItem>
                <Link to='/searchCards'><Search size='30px'/></Link>
            </NavItem>
            <NavItem>
                <Link to='/myCollection'><BoxSeam size='30px'/></Link>
            </NavItem>
        </Navbar> : <></>
    )
}

