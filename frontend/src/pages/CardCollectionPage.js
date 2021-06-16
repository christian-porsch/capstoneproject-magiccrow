import {Link} from "react-router-dom";

export default function CardCollectionPage(){
    return(
        <div>
            <h2>welcome to your collection</h2>
            <button><Link to={'/'}>back to main menu</Link></button>
        </div>
    )
}