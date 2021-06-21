import { useParams } from 'react-router-dom'

export default function SingleCardPage(){

    const { name, id } = useParams();

    return(
        <section>
        <p>{name}</p>
        <p>{id}</p>
        </section>
    )

}