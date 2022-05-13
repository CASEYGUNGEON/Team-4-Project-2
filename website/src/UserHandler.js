import './App.css';

export default function UserHandler(props) {
    const username = props.username;
    const setUsername = props.setUsername;
    let password = '';
    let loggedin = false;

    function setUsernameWithLog(event) {
        setUsername(event.target.value);
        console.log(event.target.value);
    }


    if(!loggedin) {
        return(
            <div class="accstatus">
            <label htmlFor="username">Username </label>
            <input type="text" name="username" onChange={setUsernameWithLog} placeholder="username" /><br/><br/>
            <button class="rightbutton" onClick={() => loggedin = true}>Log In</button>
            </div>
        );
    }
    else
        return(<>
            <div class="accstatus">Welcome, {username} <button onClick={() => loggedin = false}>Log Out</button></div>
            
        </>);
}