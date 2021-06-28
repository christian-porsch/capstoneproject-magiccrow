import {useEffect, useState} from "react";
import axios from "axios";

export default function useCardInPile(id) {

    const [cardInPile, setCardInPile] = useState({})

    useEffect(() => {
        axios
            .get('/api/cardsInPile/specificCardInPile/' + id)
            .then(response => response.data)
            .then(setCardInPile)
            .catch(error => console.log(error))
    }, [id])

    const addCardToPile = (id) => {
        axios
            .post('/api/cardsInPile/', {id})
            .then(response => response.data)
            .then(setCardInPile)
            .catch(error => console.log(error))
    }


    return {
        addCardToPile,
        cardInPile
    }
}