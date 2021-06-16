import styled from 'styled-components/macro'
import {Link} from "react-router-dom";


export default function LandingPage(){

    return(
        <Wrapper>
            <h2>Welcome to MagicCrow</h2>
            <button><Link to={'/searchCards'}>search for magic cards</Link></button>
            <button><Link>see my collection</Link></button>
        </Wrapper>
    )
}

const Wrapper = styled.div`
  
  text-align: center;
  
`