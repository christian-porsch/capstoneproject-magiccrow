export default function CardImage({card}){
return (
    <img key={card.id} src={card.image_uris?.normal} alt={card.name}/>
)
}