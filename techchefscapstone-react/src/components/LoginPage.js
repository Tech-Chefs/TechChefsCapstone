import React, { useState } from 'react';
import { Link, useHistory } from 'react-router-dom';
import { useContext } from 'react';
import AuthContext from '../AuthContext';

export default function LoginPage() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [errors, setErrors] = useState([]);
    const auth = useContext(AuthContext);

    const history = useHistory();

    const handleSubmit = (event) => {
        event.preventDefault();

        const authAttempt = {
            username,
            password
        };

        const init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(authAttempt)
        };

        fetch('http://localhost:8080/api/authenticate', init)
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                } else if (response.status === 403) {
                    return null;
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .then(data => {
                if (data) {
                    auth.login(data.jwt_token);
                    history.push('/');
                } else {
                    // we have error messages
                    setErrors(['login failed ):']);
                }
            })
            .catch(console.log);
    };

    const handleUsernameChange = (event) => {
        setUsername(event.target.value);
    };
    return (
        <>
            <body className="loginBody">
                <form className="security" onSubmit={handleSubmit}>
                    <input className="form-control-lg" type="username" placeholder="Username" onChange={handleUsernameChange} value={username} ></input>

                    <br></br>
                    <br></br>

                    
                    <input className="form-control-lg" type="password" placeholder="Password" onChange={(event) => setPassword(event.target.value)} value={password}></input>

                    <br></br>
                    <br></br>

                    <div className="loginButton">
                        <button className="btn btn-primary " type="submit">
                            <i class="bi bi-box-arrow-in-right"> Submit </i>
                        </button>
                    </div>
                </form>
            </body>
        </>
    )
}