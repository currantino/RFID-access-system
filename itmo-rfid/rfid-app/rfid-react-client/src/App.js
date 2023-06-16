import "./App.css";
import React from "react";
import {Route, Routes} from 'react-router-dom';
import Layout from "./components/Layout";
import Home from "./components/home/Home"
import Visitors from "./components/visitors/Visitors";
import AddVisitorForm from "./components/visitors/AddVisitorForm";
import Credentials from "./components/credentials/Credentials";
import AddCredentialForm from "./components/credentials/AddCredentialForm";

function App() {

    return <div className="App">
        <Routes>
            <Route path="/" element={<Layout/>}>
                <Route path="/" element={<Home/>}/>
                <Route path="/visitors" element={<Visitors/>}/>
                <Route path="/visitors/add" element={<AddVisitorForm/>}/>
                <Route path="/credentials" element={<Credentials/>}/>
                <Route path="/credentials/add" element={<AddCredentialForm/>}/>
            </Route>
        </Routes>
    </div>;
}

export default App;
