import useCardsInUserPile from "../hooks/useCardsInUserPile";
import 'bootstrap/dist/css/bootstrap.min.css';
import {Table} from "react-bootstrap";


export default function CardCollectionPage() {

    const {cardsInPile} = useCardsInUserPile();

    return (
        <div>
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
                    <tbody>
                    <tr>
                        <td>
                            <a href={'/myCollection/' + cardInPile.id}
                               className='badge badge-light'>{cardInPile.name}</a>
                        </td>
                        <td>
                            <a href={'/myCollection/' + cardInPile.id}
                               className='badge badge-light'>{cardInPile.set_name}</a>
                        </td>
                        <td className='text-center'>
                            {cardInPile.amount}
                        </td>
                    </tr>
                    </tbody>))}
            </Table>}
        </div>
    )
}

