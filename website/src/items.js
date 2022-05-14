import './App.css'
import {useEffect, useState} from 'react'

export default function Items(props) {
    const chosenPotluck = props.chosenPotluck;
    const setPageDisplay= props.setPageDisplay;
    const host = props.host;
    const username = props.username;
    const loggedIn = props.loggedIn;
    const [itemList, setItemList] = useState([]);
    const [guestName, setGuestName] = useState("");
    const jsx = [];

    useEffect(() => {getItems();}, []);

    async function getItems() {
        const req = await fetch(host + "/potlucks/" + chosenPotluck + "/items");
        const body = await req.json();
        setItemList(body);
    }


    async function fulfill(item) {
        if(loggedIn)
            item.supplier = username;
        else if(guestName != "") {
            item.supplier = guestName;
        } else {
            return null;
        }

        const req = await fetch(host + "/potlucks/" + chosenPotluck + "/items",
            { 
                method: "PATCH",
                headers: { "Content-type": "application/json" },
                body: JSON.stringify(
                    {
                        itemId: item.id,
                        description: item.description,
                        potluckId: chosenPotluck,
                        status : "fulfilled",
                        supplier: item.supplier
                }),
            });
        const body = await req.json();
    }

    const listElement = itemList.map((n) => (
        <tr key={n.id}>
            <td>{n.description}</td>
            <td>{n.status.toString()}</td>
            <td>{n.supplier!=null?n.supplier:"N/A"}</td>
            <td><button onClick={() => fulfill(n)}>Fulfill</button></td>
        </tr>
    ))

    return(
        <>
            <table>
                <thead>
                    <tr>
                        <th>Description</th><th>Status</th><th>Supplier</th><th></th>
                    </tr>
                    {listElement}
                </thead>
            </table><br/>
            <input name="name" type="text" placeholder="input name to fulfill" onChange={(e) => setGuestName(e.target.value)} />
        </>);
}