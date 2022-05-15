import {useState} from 'react';
import '../App.css';

export default function user(){

    const [username, setUsername] = useState([]);
    const [password, setPassword] = useState([]);

    function usernameUpdate(event){
        setUsername(event.target.value);
    }

    function passwordUpdate(event){
        setPassword(event.target.value);
    }

    async function sendLogin(){
        const a =sessionStorage.getItem("auth");
        const response = await fetch("https://52c8-67-253-63-98.ngrok.io/users",{
            method:"GET", 
            headers:{
                "Authorization":btoa(`${username}:${password}`)
            }
        });        

        const name = btoa(`${username}:${password}`);
        console.log(name);
        const auth = await response.json();
        sessionStorage.setItem("auth", JSON.stringify(auth));
    }

    return(<>

        <label htmlFor="username"> Username</label>
        <input onChange={usernameUpdate} name="username" type="text" placeholder="username"/>

        <label htmlFor="password">Password</label>
        <input onChange={passwordUpdate}  pattern="(?=.*\d)(?=.*[A-Z]).{6,}" type="password" id="password" name="password" required placeholder="password"></input>
        <div class="requirements">You must have the following:
        <ul><li>An Uppercase Letter</li>
            <li>A Number</li>
            <li>A Special Character</li></ul></div>

        <button onClick={sendLogin}>Login</button>
    </>)
    
}