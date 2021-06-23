import {useParams} from 'react-router-dom'
import useCardId from "../hooks/useCardId";
import styled from "styled-components/macro";
import {Button} from "react-bootstrap";
import useCardPrice from "../hooks/useCardPrice";
import useCardAddToPile from "../hooks/useCardAddToPile";

export default function SingleCardPage() {

    const {id} = useParams();

    const {card} = useCardId(id);
    const {cardPrice} = useCardPrice(id);
    const {addCardToPile} = useCardAddToPile();

    const handleOnClick = () => {
        addCardToPile(id);
    }

    return (
        <div>
            <h3>{card.name}</h3>
            <SingleCardAppearance>

                <SingleCardImage src={card.image_uris?.normal}/>
                <p>{card.oracle_text}</p>
                <p>usd: {cardPrice.prices?.usd}</p>
                <p>eur: {cardPrice.prices?.eur}</p>
                <p>tix: {cardPrice.prices?.tix}</p>
            </SingleCardAppearance>
            <Button variant='primary' onClick={handleOnClick}>Add</Button>
        </div>
    )

}

const SingleCardImage = styled.img`
    
        margin: 5px;
        width: 150px;
        height: auto;
        border-radius: 7%;
    
    `

const SingleCardAppearance = styled.section`
        display: grid;
        
    `
