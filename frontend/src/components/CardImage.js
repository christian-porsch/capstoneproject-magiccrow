export default function CardImage({singleCard}){

return (
    <img key={singleCard.id} src={singleCard.image_uris?.normal} alt={singleCard.name}/>
)
}