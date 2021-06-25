import {useParams} from "react-router-dom";
import useCardId from "../hooks/useCardId";

import styled from "styled-components/macro";

export default function SingleCardInCollectionPage(){

    const {id} = useParams();
    const {card} = useCardId(id);

    return(
        <div>
            <h3>{card.name}</h3>
            <SingleCardAppearance>

                <SingleCardImage src={card.image_uris?.normal}/>
                <p>{card.oracle_text}</p>
            </SingleCardAppearance>
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
