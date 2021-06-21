import styled from 'styled-components/macro'


export default function LandingPage(){

    return(
        <Wrapper>
            <h2>Welcome to MagicCrow</h2>
        </Wrapper>
    )
}

const Wrapper = styled.div`
  
  text-align: center;
  
  h2 {
        color: #0275d8;
     }
  
`