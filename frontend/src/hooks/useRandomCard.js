import {useEffect, useState} from "react";
import axios from "axios";

export default function useRandomCard() {

    const [randomCard, setRandomCard] = useState()

    useEffect(() => {
        axios
            .get('https://api.scryfall.com/cards/random')
            .then(response => response.data)
            .then(setRandomCard)
    }, [])

    return {
        randomCard
    }
}