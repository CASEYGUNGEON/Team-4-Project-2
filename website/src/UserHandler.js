export default function UserHandler(props) {
    const user = props.userId;
    const setUserId = props.setUserId;
    const username = props.username;
    const setUsername = props.setUsername;
    let password = '';



    if(props.user === "")
        return(
            <>
            <form>
                <input type="text" value="Username" />
            </form>
            <button onClick={() => props.setUserId("A")}>log in</button>
            </>
        );
    else
        return <button onClick={() => props.setUserId("")}>log out</button>
}