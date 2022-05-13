import './App.css';
import { useState } from 'react'
import test from './test'
import UserHandler from './UserHandler'

function App() {
  const[userId, setUserId] = useState("");
  const[username, setUsername] = useState("undefined");
  let pageDisplay = "potlucks";
  let jsx = [<UserHandler userId={userId} setUserId={setUserId} username={username} setUsername={setUsername} />];


  if(pageDisplay === "index") {
      jsx.push(test());
  }
  else if(pageDisplay === "potlucks") {
    jsx.push(<p><br/><br/>potluck</p>)
  }
  return(
    <>
    {jsx}
    </>);
}

export default App;
