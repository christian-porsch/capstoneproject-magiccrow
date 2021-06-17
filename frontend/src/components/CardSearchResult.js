import styled from "styled-components/macro";

export default function CardSearchResult({cards}){
    return(
        <CardsAppearance>
            {cards.map((card) => (
                <img key={card.id} src={card.image_uris?.normal} alt={card.name}/>
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