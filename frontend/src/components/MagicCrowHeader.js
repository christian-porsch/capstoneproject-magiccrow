import mainLogo from '../assets/magicCrow_1.png'
import styled from 'styled-components/macro'
import 'bootstrap/dist/css/bootstrap.min.css';

export default function MagicCrowHeader(){
    return(

        <Logo src={mainLogo} alt='MagicCrowLogo'/>

    )
}

const Logo = styled.img`
    
    display: block;
    margin: auto;
    width: 50%;
    height: auto;

    `