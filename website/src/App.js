import './App.css';
import { useState } from 'react'
import test from './test'
import UserHandler from './UserHandler'
import Potlucks from './potlucks'

function App() {

  const host = "http://localhost:8080";


  const[username, setUsername] = useState("undefined");
  const[loggedIn, setLoggedIn] = useState(false);
  let pageDisplay = "potlucks";
  const jsx = [<UserHandler className="userHandler" username={username} setUsername={setUsername} loggedIn={loggedIn} setLoggedIn={setLoggedIn} host={host} key="userHandler" />];


  if(pageDisplay === "index") {
      jsx.push(test());
  }
  else if(pageDisplay === "potlucks") {
    jsx.push(<Potlucks username={username} loggedIn={loggedIn} host={host} key="potlucks"/>)
  }
  return(<>
    {jsx}
    </>);
}

export default App;
