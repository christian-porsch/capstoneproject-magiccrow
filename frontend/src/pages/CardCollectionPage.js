import useCardsInPile from "../hooks/useCardsInPile";
import 'bootstrap/dist/css/bootstrap.min.css';
import {Table} from "react-bootstrap";

export default function CardCollectionPage() {

    const {cardsInPile} = useCardsInPile();

    return (
        <div>
            <h2>welcome to your collection</h2>
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
                        {cardInPile.name}
                    </td>
                    <td>
                        {cardInPile.set_name}
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

