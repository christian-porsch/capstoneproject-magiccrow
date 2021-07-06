import {useContext, useEffect, useState} from "react";
import axios from "axios";
import AuthContext from "../context/AuthContext";

export default function useCardsInUserPile() {

    const [cardsInPile, setCardsInPile] = useState();

    const {token, jwtDecoded} = useContext(AuthContext)

    console.log(jwtDecoded)

    const header = () => (
        {headers: {
            Authorization: `Bearer ${token}`,
            },
        }
    )

    useEffect(() => {
        axios
            .get('/api/cardsInPile/' + jwtDecoded.sub, header())
            .then(response => response.data)
            .then(setCardsInPile)
    }, [])

    return {
        cardsInPile
    }

}