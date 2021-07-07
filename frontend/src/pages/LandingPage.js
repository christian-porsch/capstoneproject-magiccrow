import {useContext} from "react";
import AuthContext from "../context/AuthContext";
import 'bootstrap/dist/css/bootstrap.min.css';
import useRandomCard from "../hooks/useRandomCard";


export default function LandingPage(){

    const {jwtDecoded} = useContext(AuthContext)

    const {randomCard} = useRandomCard();

    console.log(randomCard)

    return(
        <div className='container text-center h-100'>
            <h4 className='text-uppercase text-monospace p-5'>Hello {jwtDecoded.sub}, welcome to MagicCrow</h4>
            <div className='font-italic p-5'>
                {randomCard &&
                <div>
                    {randomCard.flavor_text}
                </div>}
            </div>

        </div>
    )
}