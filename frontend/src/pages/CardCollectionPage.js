import useCardsInUserPile from "../hooks/useCardsInUserPile";
import 'bootstrap/dist/css/bootstrap.min.css';
import {useContext} from "react";
import AuthContext from "../context/AuthContext";
import {Link} from "react-router-dom";


export default function CardCollectionPage() {

    const {cardsInPile} = useCardsInUserPile();

    const {token} = useContext(AuthContext);

    return (
        token ? <div className='pb-5'>
            <h2 className='text-center text-uppercase text-monospace p-2'>your collection</h2>
            {cardsInPile && <table className='table text-center table-bordered'>
                <thead className='thead-light'>
                <tr>
                    <th>
                        cardname
                    </th>
                    <th>
                        set
                    </th>
                    <th>
                        amount
                    </th>
                </tr>
                </thead>

                {cardsInPile.map(cardInPile => (
                    <tbody key={cardInPile.id}>
                    <tr className='text-monospace'>
                        <td>
                            <Link style={{ color: 'inherit', textDecoration: 'inherit'}} to={'/myCollection/' + cardInPile.id}>
                            {cardInPile.name}</Link>
                        </td>
                        <td>
                            <Link style={{ color: 'inherit', textDecoration: 'inherit'}} to={'/myCollection/' + cardInPile.id}>
                                {cardInPile.set_name}</Link>
                        </td>
                        <td>
                            <Link style={{ color: 'inherit', textDecoration: 'inherit'}} to={'/myCollection/' + cardInPile.id}>
                                {cardInPile.amount}</Link>
                        </td>
                    </tr>
                    </tbody>))}
            </table>}
        </div> : <></>
    )
}
