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
            <>
            <label htmlFor="username">username</label>
            <input type="text" name="username" onChange={setUsernameWithLog} placeholder="username" />
            <br/>
            <button onClick={() => props.setUserId("A")}>log in</button>
            </>
        );
    }
    else
        return <button onClick={() => props.setUserId("")}>log out</button>
}