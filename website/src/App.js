import './App.css';
import { useState } from 'react'
import test from './test'
import UserHandler from './UserHandler'

function App() {
  const[userId, setUserId] = useState("");
  const[username, setUsername] = useState("");
  let pageDisplay = "potlucks";
  let jsx = [];
  

  if(pageDisplay == "index") {
      jsx.push(test());
  }
  else if(pageDisplay == "potlucks") {
    jsx.push(<p>potlucks</p>)
  }
  return(
    <>
    <UserHandler userId={userId} setUserId={setUserId} username={username} setUsername={setUsername} />
    </>);
}

export default App;
