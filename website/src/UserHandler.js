import './App.css';
import {useState } from 'react';

export default function UserHandler(props) {
    const username = props.username;
    const setUsername = props.setUsername;
    const loggedIn = props.loggedIn;
    const setLoggedIn = props.setLoggedIn;
    let password = '';


    if(!loggedIn) {
        return(
            <div className="right">
            <label htmlFor="username">Username </label>
            <input type="text" name="username" onChange={(e) => setUsername(e.target.value)} placeholder="username" /><br />
            <label htmlFor="password">Password </label>
            <input type="text" name="password" onChange={(e) => password = e.target.value} placeholder="password" /><br/>
            <button className="rightbutton" onClick={() => setLoggedIn(true)}>Log In</button>
            </div>
        );
    }
    else
        return(<>
            <div className="right">Welcome, {username} <button onClick={() => setLoggedIn(false)}>Log Out</button></div>
            
        </>);
}