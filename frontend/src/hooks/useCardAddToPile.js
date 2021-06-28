/*
import axios from "axios";
import {useState} from "react";

export default function useCardAddToPile() {

const [addSuccess, setAddSuccess] = useState(false)

    const addCardToPile = (id) => {
        axios
            .post('/api/cardsInPile/', {id})
            .then(response => response.data)
            .then(setAddSuccess(true))
            .catch(error => console.log(error))
    }

    return {
        addCardToPile,
        addSuccess
    }

}*/
