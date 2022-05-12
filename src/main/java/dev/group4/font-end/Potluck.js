import { useEffect, useState, useSyncExternalStore } from 'react';
//import '../App.css';
import History from './History';
import Operations from './Operations';
import Result from './Result';

export default function Potluck(){


    const [potluck,setPotluck] = useState([]);
    const [visibility,setVisibility] = useState([]);
    const [date,setDate]= useState([]);

    function visibleUpdate(event){
        setVisibility(event.target.checked);
    }

    function dateUpdate(event){
        console.log(event.target.value);
        setDate(event.target.value);
    }

    async function getPotlucks(){
        const request = await fetch("https://532d-67-253-63-98.ngrok.io/potlucks");
        const body = await request.json();

    setPotluck(body);
    }


    useEffect(()=>{ getPotlucks();
    },[]);

    async function createPotluck(){
        console.log(date);
        const potluck = {id:2,dateTime:new Date(date).getTime(),creatorId:"username",visibility:Boolean(visibility)}
        console.log(potluck)
        const response = await fetch("https://532d-67-253-63-98.ngrok.io/potlucks",{
            body:JSON.stringify(potluck),
            method:"POST",
            headers:{
                "Content-Type":"application/json"
            }     
        });

        if(response.status === 200){
            const body = await response.json();
            getPotlucks();
            alert(`New book registered with id of ${body.id}`)
        }else{
            alert("FAILED TO CREATE A BOOK")
        }

       
    }

    const dynamicPotluck = potluck.map(n=> 
    <tr key={n.id}>
        <td>{n.id}</td>
        <td>{new Date(n.dateTime).toDateString()}</td>
        <td>{new Date(n.dateTime).toLocaleTimeString()}</td>
        <td>{n.creatorId}</td>
        <td>{n.visibility?"x":"y"}</td>
    </tr>);

    return(<>
    <h1>Potluck Viewer</h1>

    <table>
        <thead>
            <tr>
                
                    <th>
                        ID
                    </th>
                    <th>
                        Date
                    </th>
                    <th>
                        Time
                    </th>
                    <th>
                        Creator
                    </th>
                    <th>
                        Visiblity
                    </th>
            </tr>            
            {dynamicPotluck}   
        </thead>
    </table>

    <h1>Create new Potluck</h1>
    <label htmlFor='timeDate'>Date</label>
    <input onChange={dateUpdate} name='timeDate' type={"dateTime-local"}/>
    <label htmlFor='visible'>public?</label>
    <input onClick={visibleUpdate} name='visible' type={"checkbox"}/>
    <button onClick={createPotluck}>Submit</button>
    </>)
   
    
}