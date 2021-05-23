import React from 'react';
import './App.css';
import {DirectoryList} from "./picture/list/DirectoryList";
import PrimeReact from "primereact/api";

function App() {
    PrimeReact.ripple = true

    return (
        <div>
            <DirectoryList/>
        </div>
    );
}

export default App;
