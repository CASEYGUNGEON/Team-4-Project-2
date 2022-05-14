import './App.css';

export default function UserHandler(props) {
    const username = props.username;
    const setUsername = props.setUsername;
    let password = '';
    let loggedin = false;


    if(!loggedin) {
        return(
            <div className="right">
            <label htmlFor="username">Username </label>
            <input type="text" name="username" onChange={(e) => setUsername(e.target.value)} placeholder="username" /><br />
            <label htmlFor="password">Password </label>
            <input type="text" name="password" onChange={(e) => password = e.target.value} placeholder="password" /><br/>
            <button className="rightbutton" onClick={() => loggedin = true}>Log In</button>
            </div>
        );
    }
    else
        return(<>
            <div>Welcome, {username} <button onClick={() => loggedin = false}>Log Out</button></div>
            
        </>);
}