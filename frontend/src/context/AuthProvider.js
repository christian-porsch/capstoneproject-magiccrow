import AuthContext from "./AuthContext";
import {useState} from "react";
import axios from "axios";
import {useHistory} from "react-router-dom";
import jwt_decode from "jwt-decode";

export default function AuthProvider({children}) {

    const history = useHistory();

    const [token, setToken] = useState();

    const [jwtDecoded, setJwtDecoded] = useState();

    const config = {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    }

    const login = credentials => {
        axios
            .post('/auth/login', credentials)
            .then(response => response.data)
            .then(data => {
                setToken(data)
                setJwtDecoded(jwt_decode(data.toString()))
            })
            .then(() => history.push('/home'))
            .catch(error => console.error(error.message))
    }

    return (
        <AuthContext.Provider value={{token, config, login, jwtDecoded}}>
            {children}
        </AuthContext.Provider>
    )
}