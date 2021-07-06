import {useContext} from "react";
import AuthContext from "../context/AuthContext";
import 'bootstrap/dist/css/bootstrap.min.css';
import useRandomCard from "../hooks/useRandomCard";


export default function LandingPage(){

    const {jwtDecoded} = useContext(AuthContext)

    const {randomCard} = useRandomCard();

    console.log(randomCard)

    return(
        <div>
            <h4 className='text-center text-uppercase text-monospace'>Hello {jwtDecoded.sub}, welcome to MagicCrow</h4>
            {randomCard && <div className='position-relative'>
            <div className=' position-absolute top-50 start-50 translate-middle'>
                {randomCard.flavor_text}
            </div>
            </div>}
        </div>
    )
}