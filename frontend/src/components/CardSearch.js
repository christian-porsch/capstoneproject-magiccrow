import {useState} from "react";
import {Button, Form, FormControl, InputGroup} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';


export default function CardSearch({searchForCard}){

    const [cardName, setCardName] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();
        searchForCard(cardName);
    }

    return(

            <Form onSubmit = {handleSubmit} className='form-control-sm'>
                <InputGroup className='rounded'>
                <FormControl
                    type ='text'
                    placeholder ='search for a card'
                    value ={cardName}
                    onChange = {event => setCardName(event.target.value)}
                />
                <InputGroup.Append>
                    <Button variant='primary' disabled={cardName.length === 0}>
                        search
                    </Button>
                </InputGroup.Append>
            </InputGroup>

            </Form>

    )
}
