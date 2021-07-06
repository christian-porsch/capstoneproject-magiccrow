import {useContext, useEffect, useState} from "react";
import axios from "axios";
import AuthContext from "../context/AuthContext";

export default function useCardInPile(id) {

    const [cardInPile, setCardInPile] = useState({})

    const {token} = useContext(AuthContext)

    const header = () => (
        {headers: {
                Authorization: `Bearer ${token}`,
            },
        }
    )

    useEffect(() => {
        axios
            .get('/api/cardsInPile/specificCardInPile/' + id, header())
            .then(response => response.data)
            .then(setCardInPile)
            .catch(error => console.log(error))
    }, [id])

    const addCardToPile = (id) => {
        axios
            .post('/api/cardsInPile/', {id}, header())
            .then(response => response.data)
            .then(setCardInPile)
            .catch(error => console.log(error))
    }

    const decreaseCardFromPile = (id) => {
        axios
            .put('/api/cardsInPile/updateCardInPile/' + id,{} , header())
            .then(response => response.data)
            .then(setCardInPile)
            .catch(error => console.log(error))
    }

    const deleteCardFromPile = (id) => {
        axios
            .delete('/api/cardsInPile/' + id, header())
            .then(response => response.data)
            .then(setCardInPile)
            .catch(error => console.log(error))
    }


    return {
        addCardToPile,
        decreaseCardFromPile,
        deleteCardFromPile,
        cardInPile
    }
}