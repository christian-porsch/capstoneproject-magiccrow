import { useParams } from 'react-router-dom'
import useCardId from "../hooks/useCardId";
import styled from "styled-components/macro";

export default function SingleCardPage(){

    const { id } = useParams();

    const { card } = useCardId(id);

    return(
        <section>
        <SingleCardImage src={card.image_uris?.normal} />
        <p>{card.name}</p>
        <p>{card.oracle_text}</p>
        </section>
    )

}

const SingleCardImage= styled.img`
    
        margin: 5px;
        width: 150px;
        height: auto;
        border-radius: 7%;
    
    `