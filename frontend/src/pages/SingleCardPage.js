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
        <div className='container'>
            <h3 className='fon'>{card.name}</h3>
            <div className='row'>
                <div className='col'>
                    <SingleCardImage src={card.image_uris?.normal}/>
                </div>
                <div className='col'>
                    <div className='col badge badge-info'>
                        {cardPrice.prices?.usd} $
                    </div>
                    <div className='col badge badge-info'>
                        {cardPrice.prices?.eur} €
                    </div>
                    <div className='col badge badge-info'>
                        {cardPrice.prices?.tix} tix
                    </div>

                    <Button className='col btn btn-primary' onClick={handleOnClick}>Add to collection</Button>

                </div>
            </div>
            <p>{card.oracle_text}</p>
        </div>

)

}

const SingleCardImage = styled.img`
    
        margin: 5px;
        width: 150px;
        height: auto;
        border-radius: 7%;
    
    `

