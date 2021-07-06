import {useContext, useState} from "react";
import axios from "axios";
import AuthContext from "../context/AuthContext";

export default function useCardSearch() {

    const [cards, setCards] = useState([]);

    const {token} = useContext(AuthContext)

    const header = () => (
        {headers: {
                Authorization: `Bearer ${token}`,
            },
        }
    )

    const getSpecificCard = (cardName) => {
        return axios
            .get('/api/cards?cardName=' + cardName, header())
            .then((response) => response.data)
            .then(setCards)
            .catch((error) => console.log(error))

    }

    return {
        cards,
        getSpecificCard,

    }

}