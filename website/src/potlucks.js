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
    const [visibility,setVisibility] = useState(true);
    const [potluckList, setPotluckList] = useState([]);

    function goToPotluck(id) {
        setChosenPotluck(id);
        setPageDisplay("items");
    }

    const ListElement = potluckList.map((n) => (
        <tr key={n.id}>
            <td><button onClick={() => goToPotluck(n.id)}>View</button></td>
            <td>{new Date(n.dateTime).toDateString()}</td>
            <td>{new Date(n.dateTime).toLocaleTimeString()}</td>
            <td>{n.creatorId}</td>
            <td>{n.visibility?"public":"private"}</td>
        </tr>));
    
    async function getPotlucks() {
        let body = '';
        let req = '';
        if(loggedIn) {
            req = await fetch(host + "/users/" + username + "/potlucks/");
            body = await req.json();
        }
        req = await fetch(host + "/potlucks");
        const body2 = await req.json();
        setPotluckList([...body, ...body2]);
    }

    async function createPotluck() {
        const potluck = {dateTime: new Date(date).getTime(),creatorId:{username}, visibility:Boolean(visibility)};
    }

    useEffect(() => { getPotlucks(); }, []);

    return(<>
        <table>
            <thead>
                <tr>
                    <th></th><th>Date</th><th>Time</th><th>Creator</th><th>Public</th>
                </tr>
                {ListElement}
            </thead>
        </table>
        <h3>Create New Potluck</h3>
        <label>
            Start time: &nbsp;
            <input onChange={(e) => setDate(e.target.value)}type={"dateTime-local"} />{' '}
            Public
            <input onClick={(e) => setVisibility(e.target.checked)} type="checkbox" />
        </label>
        {' '}<button onClick={() => createPotluck()}>Create</button>
        <br /><button onClick={() => getPotlucks()}>refresh list</button>
    </>);
}