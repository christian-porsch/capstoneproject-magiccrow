import styled from "styled-components/macro";
import {Link} from "react-router-dom";
import CardImage from "./CardImage";

export default function CardSearchResult({cards}){

    return(
        <CardsAppearance>
            {cards.map((card) => (
                <Link key={card.id} to={card.name + '/' + card.id}><CardImage singleCard={card}/></Link>
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