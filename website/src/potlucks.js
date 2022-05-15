import './App.css';
import { useEffect, useState, useSyncExternalStore } from 'react';


//TODO: Add another stateful potlucklist variable and display owned potlucks in a separate table,
//      use jsx array to only show creation form if logged in

export default function Potlucks(props) {
    const host = props.host;
    const username = props.username;
    const loggedIn = props.loggedIn;
    const setChosenPotluck = props.setChosenPotluck;
    const setPageDisplay = props.setPageDisplay;
    const [date,setDate] = useState(0);
    const [visibility,setVisibility] = useState(false);
    const [potluckList, setPotluckList] = useState([]);
    const [publicPotlucks, setPublicPotlucks]=useState([]);

    function goToPotluck(id) {
        setChosenPotluck(id);
        setPageDisplay("items");
    }

    const ListElement = potluckList.map((n) => (
        <tr key={n.id}>
            <td><button onClick={() => goToPotluck(n.id)}>View</button></td>
            <td>{new Date(n.dateTime).toDateString()}</td>
            <td>{new Date(n.dateTime).toLocaleTimeString()}</td>
    </tr>));

const ListElement2 = publicPotlucks.map((n) => (
    
    <tr key={n.id}>        
        <td><button onClick={() => goToPotluck(n.id)}>View</button></td>
        <td>{new Date(n.dateTime).toDateString()}</td>
        <td>{new Date(n.dateTime).toLocaleTimeString()}</td>
        <td>{n.creatorId}</td>
</tr>));
    
    async function getPotlucks() {
        let body = '';
        if(loggedIn) {
            console.log(`${host}/users/${username}/potlucks`)
            const req = await fetch(host+"/users/"+username+"/potlucks");
            body = await req.json();
        }
        setPotluckList([...body]);
    }
    async function getPublicPotlucks(){
        let req = '';
        req = await fetch(`${host}/potlucks/`);
        const body = await req.json();
        setPublicPotlucks([...body]);
    }

    async function createPotluck() {
        const potluck = {dateTime: new Date(date).getTime(),creatorId:{username}, visibility:Boolean(visibility)};

        const response = await fetch(`${host}/potlucks`,{
            body:JSON.stringify(potluck),
            method:"POST",
            headers:{
              //  "Authorization":`${session.authorization}`,
                "Content-Type":"application/json"
            }     
        });
        const body = await response.json();
        if(response.status === 200){
            //const body = await response.json();
            
            alert(`New potluck registered at ${body.dateTime}`)
            getPotlucks();
            getPublicPotlucks();
        }else{
            alert("FAILED TO CREATE A Potluck");
        }
    }

    useEffect(() => { getPotlucks(); 
    getPublicPotlucks();
}, []);

    return(<>
        <label htmlFor='private'>Your Putlucks</label>
        <table id='private'>
            <thead>
                <tr>
                    <th></th><th>Date</th><th>Time</th>
                </tr>
                {ListElement}
            </thead>
        </table>
        <label htmlFor='public'>Public Potlucks</label>
        <table id='public'>
            <thead>
                <tr>
                    <th></th><th>Date</th><th>Time</th><th>Creator</th>
                </tr>
                {ListElement2}
            </thead>
        </table>
        <button onClick={() => getPotlucks()}>refresh list</button>
        
        <form>
            <fieldset id='createPotluck'>
        <legend htmlFor='createPotluck'>Create New Potluck</legend>
            Start time: &nbsp;
            <input required onChange={(e) => setDate(e.target.value)}type={"dateTime-local"} />{' '}
            Public
            <input onClick={(e) => setVisibility(e.target.checked)} type="checkbox" />
        
        {' '}<button onClick={() => createPotluck()}>Create</button></fieldset></form>
        
        
    </>);
}