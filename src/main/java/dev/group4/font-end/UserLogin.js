import {useState} from 'react';
//import '../App.css';

export default function UserLogin(){

    const [username, setUsername] = useState([]);
    const [password, setPassword] = useState([]);

    function usernameUpdate(event){
        setUsername(event.target.value);
    }

    function passwordUpdate(event){
        setPassword(event.target.value);
    }

    async function sendLogin(){
        const response = await fetch("https://52c8-67-253-63-98.ngrok.io/users",{
            method:"GET", 
            headers:{
                "Authorization":btoa(`${username}:${password}`)
            }
        });

        const auth = await response.json();
        sessionStorage.setItem("auth", JSON.stringify(auth));
    }

    return(<>

        <label htmlFor="username"> Username</label>
        <input onChange={usernameUpdate} name="username" type="text" placeholder="username"/>

        <label htmlFor="password">Password</label>
        <input onChange={passwordUpdate} name="password" type="password"/>

        <button onClick={sendLogin}>Login</button>
    </>)
    
}