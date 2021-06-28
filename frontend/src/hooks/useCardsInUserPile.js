import {useEffect, useState} from "react";
import axios from "axios";

export default function useCardsInUserPile(){

    const [cardsInPile, setCardsInPile] = useState();

    useEffect(() => {
        axios
            .get('/api/cardsInPile/60d2f120c76f8707f38e9a99')
            .then(response => response.data)
            .then(setCardsInPile)
    }, [])

    return {
        cardsInPile
    }

}