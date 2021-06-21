import { useParams } from 'react-router-dom'

export default function SingleCardPage(){

    const { id } = useParams();

    return(
        <section>
        <p>{id}</p>
        </section>
    )

}