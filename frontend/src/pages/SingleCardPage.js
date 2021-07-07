import {useParams} from 'react-router-dom'
import useCardId from "../hooks/useCardId";
import styled from "styled-components/macro";
import useCardPrice from "../hooks/useCardPrice";
import useCardInPile from "../hooks/useCardInPile";
import {BoxSeam} from "react-bootstrap-icons";

export default function SingleCardPage() {

    const {id} = useParams();

    const {card} = useCardId(id);
    const {cardPrice} = useCardPrice(id);
    const {addCardToPile, cardInPile} = useCardInPile(id);

    const handleOnClick = () => {
        addCardToPile(id);
    }

    return (
        <div className='container'>
            <div className='row p-1'>
                <div className='col'>
                    <SingleCardImage src={card.image_uris?.normal}/>
                </div>
                <div className='col'>
                    <div className='w-100 badge badge-info'>
                        {cardPrice.prices?.usd} $
                    </div>
                    <div className='w-100 badge badge-info'>
                        {cardPrice.prices?.eur} €
                    </div>
                    <div className='w-100 badge badge-info'>
                        {cardPrice.prices?.tix} tix
                    </div>
                    <button className='p-1 w-100 btn btn-success' onClick={handleOnClick}>Add to collection</button>
                    <div className='col d-flex justify-content-center p-2'>
                        <BoxSeam size='25px'/>: {cardInPile.amount}
                    </div>
                </div>
            </div>
            <div className='card border-light p-3'>
                <div className='card-header'>

                </div>
                <div className='card-body'>
                    <h3 className='card-title text-monospace'>
                        {card.name}
                    </h3>
                    <h6 className='text-monospace'>{card.set_name}</h6>
                </div>
                <p className='card-text'>{card.oracle_text}</p>
            </div>
        </div>

    )

}

const SingleCardImage = styled.img`
    
        width: 200px;
        height: auto;
        border-radius: 7%;
    
    `

