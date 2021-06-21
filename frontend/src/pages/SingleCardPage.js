import { useParams } from 'react-router-dom'

export default function SingleCardPage(){

    const { name, id } = useParams();


    return(
        <div>
        <h2>{name}</h2>
        <h3>{id}</h3>
        </div>
    )

}