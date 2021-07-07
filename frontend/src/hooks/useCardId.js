import {useContext, useEffect, useState} from "react";
import axios from "axios";
import AuthContext from "../context/AuthContext";

export default function useCardId(id) {

    const [card, setCard] = useState({});

    const {token} = useContext(AuthContext)

    const config = {
        headers: {
                Authorization: `Bearer ${token}`,
            },
        }


    useEffect(() => {
        axios
            .get('/api/cards/' + id, config)
            .then(response => response.data)
            .then(setCard)
    }, [id])

    return {
        card
    }
}

