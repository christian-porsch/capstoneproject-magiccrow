import {useState} from "react";
import axios from "axios";

export default function useCardSearch(){

    const [cards, setCards] = useState([]);

    const getSpecificCard = (cardName) => {
        return axios
            .get('/api/cards?cardName=' + cardName)
            .then((response) => response.data)
            .then (setCards)
            .catch((error) => console.log(error))

    }

    return {
        cards,
        getSpecificCard,

    }

}