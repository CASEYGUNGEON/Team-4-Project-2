import './App.css';

export default function UserHandler(props) {
    const username = props.username;
    const setUsername = props.setUsername;
    let password = '';
    let loggedin = false;

    function setPassword(event) {
        password = event.target.value;
    }


    if(!loggedin) {
        return(
            <div>
            <label htmlFor="username">Username </label>
            <input type="text" name="username" onChange={setUsername} placeholder="username" /><br />
            <label htmlFor="password">Password </label>
            <input type="text" name="password" onChange={(e) => setPassword(e)} placeholder="password" /><br/>
            <button className="rightbutton" onClick={() => loggedin = true}>Log In</button>
            </div>
        );
    }
    else
        return(<>
            <div>Welcome, {username} <button onClick={() => loggedin = false}>Log Out</button></div>
            
        </>);
}