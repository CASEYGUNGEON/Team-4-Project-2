import './App.css';
import {useEffect, useState } from 'react';

export default function UserHandler(props) {
    const username = props.username;
    const setUsername = props.setUsername;
    const password = props.password;
    const setPassword = props.setPassword;
    const loggedIn = props.loggedIn;
    const setLoggedIn = props.setLoggedIn;
    const createNewUser = props.createNewUser;
    const host = props.host;
    const setPage = props.setPageDisplay;
    const page = props.chosenPotluck;

   


    async function sendLogin(){
        console.log("sending login");
        const a =sessionStorage.getItem("auth");
        const response = await fetch(host+"/users",{
            method:"GET", 
            headers:{
                "Authorization":btoa(`${username}:${password}`)
            }
        });        
        setLoggedIn(true);

        const name = btoa(`${username}:${password}`);
        console.log(name);
        const auth = await response.json();
        sessionStorage.setItem("auth", JSON.stringify(auth));
        window.location.reload(false);
    }

    function logout(){
        sessionStorage.removeItem("auth");
        setLoggedIn(false);
        window.location.reload(false);
    }



    if(!sessionStorage.getItem("auth")) {
        return(
            <div className="right">
            <form>
            <label htmlFor="username">Username </label>
            <input  type="text" name="username" onChange={(e) => setUsername(e.target.value)} required placeholder="username" /><br />
            <label htmlFor="password">Password </label>
            <input  type="password" name="password" onChange={(e) => setPassword(e.target.value)} required placeholder="password" /><br/>
            <button onClick={() => createNewUser()}>Create User</button>
            <button className="rightbutton" onClick={(e) =>{e.preventDefault(); sendLogin()}}>Log In</button>
            </form>
            </div>
        );
    }
    else{
        console.log("username:"+ username +" logout");
        return(<>
            <div>Welcome, {username} <button onClick={() => logout()}>Log Out</button></div>  
        </>);}
}