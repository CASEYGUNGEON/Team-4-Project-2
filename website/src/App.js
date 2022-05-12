import './App.css';
import { useState } from 'react'
import test from './test'
import UserHandler from './UserHandler'

function App() {
  const[userId, setUserId] = useState("");
  const[username, setUsername] = useState("undefined");
  let pageDisplay = "potlucks";
  let jsx = [];
  

  if(pageDisplay === "index") {
      jsx.push(test());
  }
  else if(pageDisplay === "potlucks") {
    jsx.push(<p>potluck</p>)
  }
  return(
    <>
    <UserHandler userId={userId} setUserId={setUserId} username={username} setUsername={setUsername} />
    </>);
}

export default App;
