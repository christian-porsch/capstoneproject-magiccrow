import {useParams} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';

import styled from "styled-components/macro";
import {BoxSeam, DashCircleFill, PlusCircleFill} from "react-bootstrap-icons";
import useCardPrice from "../hooks/useCardPrice";
import useCardInPile from "../hooks/useCardInPile";


export default function SingleCardInCollectionPage() {

    const {id} = useParams();

    const {addCardToPile, decreaseCardFromPile, cardInPile} = useCardInPile(id);
    const {cardPrice} = useCardPrice(id);


    const handleOnClickAdd = () => {
        addCardToPile(id);
    }

    const handleOnClickDecrease = () => {
        decreaseCardFromPile(id)
    }


    return (
        <div className='container'>
            <div className='row'>
                <div className='col'>
                    <SingleCardImage src={cardInPile.image_uris?.normal}/>
                    <div className='d-flex justify-content-between'>
                        <DashCircleFill color='#dc3545' size='25px' onClick={handleOnClickDecrease}/>
                        <PlusCircleFill color='#28a745' size='25px' onClick={handleOnClickAdd}/>
                    </div>
                </div>
                <div className='col'>
                    <div className='w-100 badge badge-info'>
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
