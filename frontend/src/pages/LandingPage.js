import styled from 'styled-components/macro'
import {useState} from "react";
import axios from "axios";

export default function LandingPage(){

    const [cardName, setCardName] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();
        axios.get('/api/cards?cardName=' + cardName)
            .then(response => response.data)
            .then ((data) => console.log(data))
    }

    return(
        <Wrapper>
            <h2>Welcome to MagicCrow</h2>
            <form onSubmit = {handleSubmit} >
                <input
                    type ='text'
                    placeholder ='search for a card'
                    value ={cardName}
                    onChange = {event => setCardName(event.target.value)}
                    />
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