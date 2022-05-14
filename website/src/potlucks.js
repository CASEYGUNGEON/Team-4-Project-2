import './App.css';
import { useEffect, useState, useSyncExternalStore } from 'react';

export default function Potlucks(props) {
    const host = props.host;
    const username = props.username;
    let date = 0;
    let visibility = 0;
    let potluckList = [];

    function setDate(event) {
        date = event.target.value;
    }

    function setVis(event) {
        visibility = event.target.checked;
    }
    
    async function getPotlucks() {
        const request = await fetch(host + "/potlucks");
        const body = await request.json();
        potluckList = body;
    }

    async function createPotluck() {
        const potluck = {dateTime: new Date(date).getTime(),creatorId:{username}, visibility:Boolean(visibility)};
    }

    useEffect(() => { getPotlucks(); });

    return(<>
        {potluckList}
        <label>
            Start time: &nbsp;
            <input type={"dateTime-local"} /><br />
            <input onClick={(e) => setVis(e)} type="checkbox" />
            Public
        </label>
    </>);
}