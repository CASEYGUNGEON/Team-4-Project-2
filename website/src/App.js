import './App.css';
import { useState } from 'react'
import UserHandler from './UserHandler'
import Potlucks from './potlucks'
import Items from './items'

function App() {

  const host = "http://localhost:8080";


  const[username, setUsername] = useState("undefined");
  const[loggedIn, setLoggedIn] = useState(false);
  const[chosenPotluck,setChosenPotluck] = useState("");
  const[pageDisplay,setPageDisplay] = useState("potluckList");
  const jsx = [<UserHandler className="userHandler" username={username} setUsername={setUsername} loggedIn={loggedIn} setLoggedIn={setLoggedIn} host={host} key="userHandler" />];


  if(pageDisplay === "index") {
      jsx.push();
  }
  else if(pageDisplay === "potluckList") {
    jsx.push(<Potlucks username={username} loggedIn={loggedIn} setChosenPotluck={setChosenPotluck} setPageDisplay={setPageDisplay} host={host} key="potlucks"/>)
  }
  else if(pageDisplay === "items") {
    jsx.push(<Items chosenPotluck={chosenPotluck} setPageDisplay={setPageDisplay} username={username} loggedIn={loggedIn} host={host} key="items" />)
  }

  return(<>{jsx}</>);
}

export default App;
