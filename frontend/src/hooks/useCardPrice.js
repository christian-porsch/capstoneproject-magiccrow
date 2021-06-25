import {useEffect, useState} from "react";
import axios from "axios";

export default function useCardPrice(id){

    const [cardPrice, setCardPrice] = useState({});

    useEffect(() => {
        axios
            .get('https://api.scryfall.com/cards/' + id)
            .then(response => response.data)
            .then(setCardPrice)

    }, [id])

    return {
        cardPrice
    }
}
