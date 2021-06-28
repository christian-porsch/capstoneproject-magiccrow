import {useEffect, useState} from "react";
import axios from "axios";

export default function useCardInPileId(id) {

    const [cardInPile, setCardInPile] = useState({})

    useEffect(() => {
        axios
            .get('/api/cardsInPile/specificCardInPile/' + id)
            .then(response => response.data)
            .then(setCardInPile)
    }, [id])

    return {
        cardInPile
    }
}