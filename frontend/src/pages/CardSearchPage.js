import styled from "styled-components/macro";
import useCardSearch from "../hooks/useCardSearch";
import CardSearchResult from "../components/CardSearchResult";
import CardSearch from "../components/CardSearch";


export default function CardSearchPage(){

    const {cards, getSpecificCard} = useCardSearch();


    return(
            <Wrapper>
                <CardSearch searchForCard={getSpecificCard}/>
                <CardSearchResult cards={cards}/>
            </Wrapper>
        )
}

const Wrapper = styled.div`
  
  text-align: center;
  
`