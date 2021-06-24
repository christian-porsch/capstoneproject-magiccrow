import useCardsInPile from "../hooks/useCardsInPile";
import styled from "styled-components/macro";

export default function CardCollectionPage() {

    const {cardsInPile} = useCardsInPile();

    return (
        <div>
            <h2>welcome to your collection</h2>
            {cardsInPile && <section>
                {cardsInPile.map(cardInPile => (
                    <ImageAppearance key={cardInPile.id}src={cardInPile.image_uris?.normal} alt={cardInPile.name} />))}
            </section>}
        </div>
    )
}

const ImageAppearance = styled.img`
    
        justify-content: space-evenly;
        margin: 5px;
        width: 150px;
        height: auto;
        border-radius: 7%;
    
    `