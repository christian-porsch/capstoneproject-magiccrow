import {useEffect, useState} from "react";
import axios from "axios";

export default function useCardId(id) {

    const [card, setCard] = useState({});

    useEffect(() => {
        axios
            .get('/api/cards/' + id)
            .then(response => response.data)
            .then(setCard)
    }, [id])

    return {
        card
    }
}

