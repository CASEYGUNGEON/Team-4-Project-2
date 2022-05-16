import './App.css';
import { useState, useEffect } from 'react'
import UserCreator from './user'
import UserHandler from './UserHandler'
import Potlucks from './potlucks'
import Items from './items'
import logo from './gatherLogo.png'

function App() {

  const host = "ec2-54-176-244-61.us-west-1.compute.amazonaws.com:8080";


  const[username, setUsername] = useState();
  const[loggedIn, setLoggedIn] = useState(false);
  const[chosenPotluck,setChosenPotluck] = useState("");
  const[chosenPotluckCreator,setChosenPotluckCreator] = useState("");
  const[pageDisplay,setPageDisplay] = useState("potluckList");
  const [password, setPassword] = useState([]);

  useEffect(() => { setUsername(sessionStorage.getItem("username")); }, []);


  const jsx = [<div id='header'><div id='brand'><img id='logo' src={logo}/></div><div id='saying'><h1>Gather</h1><p id='sub-saying'>Food brings us together</p></div><div id='user'><UserHandler className="userHandler" chosenPotluck = {chosenPotluck} setPageDisplay={setPageDisplay} password={password} setPassword = {setPassword} username={username} setUsername={setUsername} loggedIn={loggedIn} setLoggedIn={setLoggedIn} host={host} key="userHandler" /></div></div>];


  if(pageDisplay === "index") {
    jsx.push();
  }
  else if(pageDisplay === "potluckList") {
    jsx.push(<div id='display'><Potlucks username={username} setChosenPotluck={setChosenPotluck} setChosenPotluckCreator={setChosenPotluckCreator} setPageDisplay={setPageDisplay} host={host} key="potlucks"/></div>)
  }
  else if(pageDisplay === "items") {
    jsx.push(<div id='display'><Items chosenPotluck={chosenPotluck} chosenPotluckCreator={chosenPotluckCreator} setPageDisplay={setPageDisplay} username={username} host={host} key="items" /></div>)
  }
  else if(pageDisplay === "user"){
    jsx.push(<div id='display'><UserCreator chosenPotluck={chosenPotluck} setPageDisplay={setPageDisplay} username={username} setUsername = {setUsername} password = {password} setPassword = {setPassword}   host={host} key="user"  /></div>)
  }

  return(<div className='main'><div id='center'>{jsx}</div></div>);
}

export default App;
