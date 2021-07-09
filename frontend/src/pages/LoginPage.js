import {useContext, useState} from "react";
import AuthContext from "../context/AuthContext";
import 'bootstrap/dist/css/bootstrap.min.css';
import {Key, PersonSquare} from "react-bootstrap-icons";


const initialState = {
    username: '',
    password: '',
}

export default function LoginPage() {

    const [credentials, setCredentials] = useState(initialState);
    const {login} = useContext(AuthContext);

    const handleChange = event => {
        setCredentials({...credentials, [event.target.name]: event.target.value})

    }

    const handleSubmit = event => {
        event.preventDefault();
        login(credentials);
    }

    return (
        <div className='container d-flex justify-content-center h-50'>
            <form onSubmit={handleSubmit} className='align-self-center'>
                <div className='col-auto input-group'>
                    <div className='input-group-text'>
                        <PersonSquare/>
                    </div>
                    <input
                        className='form-control'
                        type={'text'}
                        placeholder='username'
                        name='username'
                        onChange={handleChange}
                        value={credentials.username}/>
                </div>
                <div className='col-auto input-group'>
                    <div className='input-group-text'><Key/></div>
                    <input
                        className='form-control'
                        type={'password'}
                        placeholder='password'
                        name='password'
                        onChange={handleChange}
                        value={credentials.password}/>
                </div>
                <div className='d-flex justify-content-center p-2'>
                    <button className='btn btn-primary'>Login</button>
                </div>
            </form>
        </div>
    )
}