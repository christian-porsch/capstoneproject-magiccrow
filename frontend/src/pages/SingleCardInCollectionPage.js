import {useParams, useHistory} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';

import styled from "styled-components/macro";
import {BoxSeam} from "react-bootstrap-icons";
import useCardPrice from "../hooks/useCardPrice";
import useCardInPile from "../hooks/useCardInPile";


export default function SingleCardInCollectionPage() {

    const history = useHistory();

    const {id} = useParams();

    const {addCardToPile, decreaseCardFromPile, deleteCardFromPile, cardInPile} = useCardInPile(id);
    const {cardPrice} = useCardPrice(id);


    const handleOnClickAdd = () => {
        addCardToPile(id);
    }

    const handleOnClickDecrease = () => {
        decreaseCardFromPile(id);
    }

    const handleOnClickDelete = () => {
        deleteCardFromPile(id);
        history.push('/myCollection')
    }

    return (
        <div className='container pb-5'>
            <div className='row p-1'>
                <div className='col'>
                    <SingleCardImage src={cardInPile.image_uris?.normal}/>
                    <div className='col d-flex justify-content-between p-2'>
                        <button className='btn btn-danger' onClick={handleOnClickDecrease}>-</button>
                        <BoxSeam size='30px'/>: {cardInPile.amount}
                        <button className='btn btn-success' onClick={handleOnClickAdd}>+</button>
                    </div>
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
                </div>
            </div>
            <div className='card border-light'>
                <div className='card-header'>
                </div>
                <div className='card-body'>
                    <h3 className='card-title text-monospace'>
                        {cardInPile.name}
                    </h3>
                    <h6 className='text-monospace'>{cardInPile.set_name}</h6>
                </div>
                <p className='card-text'>{cardInPile.oracle_text}</p>
            </div>
            <div className='d-flex justify-content-center p-2'>
                <button className='btn btn-danger' onClick={handleOnClickDelete}>delete from collection</button>
            </div>
        </div>
    )
}

const SingleCardImage = styled.img`
    
        width: 200px;
        height: auto;
        border-radius: 7%;
    
    `
