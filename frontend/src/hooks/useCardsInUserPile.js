import {useContext, useEffect, useState} from "react";
import axios from "axios";
import AuthContext from "../context/AuthContext";

export default function useCardsInUserPile() {

    const [cardsInPile, setCardsInPile] = useState();

    const {token, config, jwtDecoded} = useContext(AuthContext)

    useEffect(() => {
        axios
            .get('/api/cardsInPile/' + jwtDecoded.sub, config)
            .then(response => response.data)
            .then(setCardsInPile)
    }, [token, config, jwtDecoded])

    return {
        cardsInPile
    }

}