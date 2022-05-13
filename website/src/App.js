import './App.css';
import { useState } from 'react'
import test from './test'
import UserHandler from './UserHandler'
import Potlucks from './potlucks'

function App() {
  const[username, setUsername] = useState("undefined");
  let pageDisplay = "potlucks";
  let jsx = [<UserHandler username={username} setUsername={setUsername} />];


  if(pageDisplay === "index") {
      jsx.push(test());
  }
  else if(pageDisplay === "potlucks") {
    jsx.push(<Potlucks username={username} />)
  }
  return(
    <>
    {jsx}
    </>);
}

export default App;
