import './App.css';
import { useEffect, useState, useSyncExternalStore } from 'react';

export default function Potlucks(props) {
    const username = props.username;
    let date = 0;
    let visibility = true;
    
    async function getPotlucks() {
        const request = await fetch("http://localhost/potlucks");
        const body = await request.json();
    }

    async function createPotluck() {
        const potluck = {dateTime: new Date(date).getTime(),creatorId:{username}, visibility:Boolean(visibility)};
    }
}