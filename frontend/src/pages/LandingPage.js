import styled from 'styled-components/macro'

export default function LandingPage(){

    const handleChange = () => {
        console.log('searching for card')
    }

    return(
        <Wrapper>
            <h2>Welcome to MagicCrow</h2>
            <form>
                <input type='text' placeholder='search for a card' onChange={handleChange}/>
                <button>
                    search
                </button>
            </form>
            <button>see my collection</button>
        </Wrapper>
    )
}

const Wrapper = styled.div`
  
  text-align: center;
  
`