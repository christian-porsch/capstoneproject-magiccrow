import {Link} from "react-router-dom";
import styled from "styled-components/macro";
import {useState} from "react";



export default function CardSearchPage({searchForCard}){

    const [cardName, setCardName] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();
        searchForCard(cardName);
    }

    return(
            <Wrapper>
                <form onSubmit = {handleSubmit} >
                    <input
                        type ='text'
                        placeholder ='search for a card'
                        value ={cardName}
                        onChange = {event => setCardName(event.target.value)}
                    />
                    <button disabled={cardName.length === 0}>
                        search
                    </button>
                </form>

                <button><Link to={'/'}>back to main menu</Link></button>
            </Wrapper>
        )
}

const Wrapper = styled.div`
  
  text-align: center;
  
`