import './App.css';
import { useState } from 'react'
import UserHandler from './UserHandler'
import Potlucks from './potlucks'
import Items from './items'
import logo from './gatherLogo.png'

function App() {

  const host = "http://localhost:8080";


  const[username, setUsername] = useState();
  const[loggedIn, setLoggedIn] = useState(false);
  const[chosenPotluck,setChosenPotluck] = useState("");
  const[pageDisplay,setPageDisplay] = useState("potluckList");

  function createNewUser(){
    console.log("hello");
  }

  const jsx = [<div id='header'><div id='brand'><img id='logo' src={logo}/></div><div id='saying'><h1>Gather</h1><p id='sub-saying'>Food brings us together</p></div><div id='user'><UserHandler className="userHandler" username={username} setUsername={setUsername} loggedIn={loggedIn} setLoggedIn={setLoggedIn} createUser={createNewUser} host={host} key="userHandler" /></div></div>];


  if(pageDisplay === "index") {
    jsx.push();
  }
  else if(pageDisplay === "potluckList") {
    jsx.push(<div id='display'><Potlucks username={username} loggedIn={loggedIn} setChosenPotluck={setChosenPotluck} setPageDisplay={setPageDisplay} host={host} key="potlucks"/></div>)
  }
  else if(pageDisplay === "items") {
    jsx.push(<div id='display'><Items chosenPotluck={chosenPotluck} setPageDisplay={setPageDisplay} username={username} loggedIn={loggedIn} host={host} key="items" /></div>)
  }

  return(<div className='main'><div id='center'>{jsx}</div></div>);
}

export default App;
