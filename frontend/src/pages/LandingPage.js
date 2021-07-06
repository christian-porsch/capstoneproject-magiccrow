import styled from 'styled-components/macro'
import {useContext} from "react";
import AuthContext from "../context/AuthContext";


export default function LandingPage(){

    const {jwtDecoded} = useContext(AuthContext)

    return(
        <Wrapper>
            <h2>Hello {jwtDecoded.sub}, welcome to MagicCrow</h2>
        </Wrapper>
    )
}

const Wrapper = styled.div`
  
  text-align: center;
  
  h2 {
        color: #0275d8;
     }
  
`