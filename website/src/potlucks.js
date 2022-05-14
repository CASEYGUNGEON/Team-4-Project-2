import './App.css';
import { useEffect, useState, useSyncExternalStore } from 'react';

export default function Potlucks(props) {
    const username = props.username;
    let date = 0;
    let visibility = 0;
    let potlucklist = {};

    function setDate(event) {
        date = event.target.value;
    }

    function setVis(event) {
        visibility = event.target.checked;
        console.log(visibility);
    }
    
    async function getPotlucks() {
        const request = await fetch("http://localhost:8080/potlucks");
        const body = await request.json();
    }

    async function createPotluck() {
        const potluck = {dateTime: new Date(date).getTime(),creatorId:{username}, visibility:Boolean(visibility)};
    }

    return(<>
        <label>
            Start time: &nbsp;
            <input type={"dateTime-local"} /><br />
            <input onClick={(e) => setVis(e)} type="checkbox" />
            Public
        </label>
    </>);
}