import styled from "styled-components/macro";

export default function CardImage({singleCard}) {

    return (

        <ImageAppearance key={singleCard.id} src={singleCard.image_uris?.normal} alt={singleCard.name}/>
    )
}

const ImageAppearance = styled.img`
    
        justify-content: space-evenly;
        margin: 5px;
        width: 150px;
        height: auto;
        border-radius: 7%;
       
    `