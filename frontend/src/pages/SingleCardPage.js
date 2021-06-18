import { useParams } from 'react-router-dom'

export default function SingleCardPage(){

    const { id } = useParams();


    return(
        <h2>{id}</h2>
    )

}