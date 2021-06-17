import styled from "styled-components/macro";
import {Link} from "react-router-dom";

export default function CardSearchResult({cards}){
    return(
        <CardsAppearance>
            {cards.map((card) => (
                <Link to={'singleCardPage'}><img key={card.id} src={card.image_uris?.normal} alt={card.name}/></Link>
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