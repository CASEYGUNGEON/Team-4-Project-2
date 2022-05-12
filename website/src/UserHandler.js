import './App.css';

export default function UserHandler(props) {
    const user = props.userId;
    const setUserId = props.setUserId;
    const username = props.username;
    const setUsername = props.setUsername;
    let password = '';

    function setUsernameWithLog(event) {
        setUsername(event.target.value);
        console.log(event.target.value);
    }


    if(user === "") {
        return(
            <div class="accstatus">
            <label htmlFor="username">Username </label>
            <input type="text" name="username" onChange={setUsernameWithLog} placeholder="username" /><br/><br/>
            <button class="rightbutton" onClick={() => props.setUserId("A")}>Log In</button>
            </div>
        );
    }
    else
        return(<>
            <div class="accstatus">Welcome, {username} <button onClick={() => props.setUserId("")}>Log Out</button></div>
            
        </>);
}