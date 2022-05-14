import './App.css';
import { useState } from 'react'
import test from './test'
import UserHandler from './UserHandler'
import Potlucks from './potlucks'

function App() {
  const[username, setUsername] = useState("undefined");
  let pageDisplay = "potlucks";
  const jsx = [<UserHandler className="userHandler" username={username} setUsername={setUsername} key="userHandler" />];


  if(pageDisplay === "index") {
      jsx.push(test());
  }
  else if(pageDisplay === "potlucks") {
    jsx.push(<Potlucks username={username} key="potlucks"/>)
  }
  return(<>
    {jsx}
    </>);
}

export default App;
