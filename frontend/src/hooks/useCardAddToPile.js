import axios from "axios";

export default function useCardAddToPile() {


    const addCardToPile = (id) => {
        axios
            .post('/api/cardsInPile/', {id})
            .then(response => response.data)
            .catch(error => console.log(error))
    }

    return {
        addCardToPile
    }

}