import useCardsInUserPile from "../hooks/useCardsInUserPile";
import 'bootstrap/dist/css/bootstrap.min.css';
import {Table} from "react-bootstrap";
import {useContext} from "react";
import AuthContext from "../context/AuthContext";
import {Link} from "react-router-dom";



export default function CardCollectionPage() {

    const {cardsInPile} = useCardsInUserPile();

    const {token} = useContext(AuthContext);

    return (
        token ? <div>
            <h2 className='text-center text-uppercase text-monospace'>your collection</h2>
            {cardsInPile && <Table striped bordered hover>
                <thead className='text-center'>
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
                    <tr>
                        <td>
                            <Link to={'/myCollection/' + cardInPile.id}
                               className='badge badge-light'>{cardInPile.name}</Link>
                        </td>
                        <td>
                            <Link to={'/myCollection/' + cardInPile.id}
                               className='badge badge-light'>{cardInPile.set_name}</Link>
                        </td>
                        <td className='text-center'>
                            {cardInPile.amount}
                        </td>
                    </tr>
                    </tbody>))}
            </Table>}
        </div> : <></>
    )
}

