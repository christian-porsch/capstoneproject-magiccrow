import {useParams} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';

import styled from "styled-components/macro";
import {BoxSeam, DashCircleFill, PlusCircleFill} from "react-bootstrap-icons";
import useCardPrice from "../hooks/useCardPrice";
import useCardAddToPile from "../hooks/useCardAddToPile";
import useCardInPileId from "../hooks/useCardInPileId";


export default function SingleCardInCollectionPage() {

    const {id} = useParams();

    const {cardInPile} = useCardInPileId(id);
    const {cardPrice} = useCardPrice(id);
    const {addCardToPile} = useCardAddToPile();

    const handleOnClickAdd = () => {
        addCardToPile(id);
    }

    return (
        <div className='container'>
            <div className='row'>
                <div className='col'>
                    <SingleCardImage src={cardInPile.image_uris?.normal}/>
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
                    <div className='justify-content-around'>
                        <DashCircleFill color='#dc3545' size='25px'/>
                        <PlusCircleFill color='#28a745' size='25px' onClick={handleOnClickAdd}/>
                    </div>
                </div>
            </div>
            <div className='card border-light'>
                <div className='card-header badge badge-info'>
                    <BoxSeam size='25px'/>: {cardInPile.amount}
                </div>
                <div className='card-body'>
                    <h3 className='card-title text-monospace'>
                        {cardInPile.name}
                    </h3>
                    <h6 className='text-monospace'>{cardInPile.set_name}</h6>
                </div>
                <p className='card-text'>{cardInPile.oracle_text}</p>
            </div>
        </div>
    )
}

const SingleCardImage = styled.img`
    
        width: 200px;
        height: auto;
        border-radius: 7%;
    
    `
