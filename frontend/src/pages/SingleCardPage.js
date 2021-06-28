import {useParams} from 'react-router-dom'
import useCardId from "../hooks/useCardId";
import styled from "styled-components/macro";
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
                </div>
            </div>
            <div className='card border-light'>
                <div className='card-header'>
                    <button className='btn btn-success align-items-center' onClick={handleOnClick}>Add to collection</button>
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

