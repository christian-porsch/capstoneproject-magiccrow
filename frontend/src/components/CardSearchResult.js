import styled from "styled-components/macro";
import {Link} from "react-router-dom";
import {useState} from "react";
import CardImage from "./CardImage";

export default function CardSearchResult({cards}){

    const [selectedCard, setSelectedCard] = useState ()

    const handleOnClick = (card) => {
        setSelectedCard(card);
        console.log(card);
    }



    return(
        <CardsAppearance>
            {cards.map((card) => (
                <Link key={card.id} onClick={() => handleOnClick(card)} to={card.name + '/' + card.id}><CardImage singleCard={card}/></Link>
            ))}
        </CardsAppearance>
    )
}

const CardsAppearance = styled.section`
       
        display: flex;
        flex-flow: row wrap;
        justify-content: center;
         
        img {
        justify-content: space-evenly;
        margin: 5px;
        width: 150px;
        height: auto;
        }
`