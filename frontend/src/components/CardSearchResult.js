import styled from "styled-components/macro";
import {Link} from "react-router-dom";
import CardImage from "./CardImage";

export default function CardSearchResult({cards}) {

    return (
        <CardsAppearance>
            {cards.map((card) => (
                <Link key={card.id} to={'/' + card.id}><CardImage singleCard={card}/></Link>
            ))}
        </CardsAppearance>
    )
}

const CardsAppearance = styled.section`
       
        display: flex;
        flex-flow: row wrap;
        justify-content: center;
        padding: 50px 0px;
         
`