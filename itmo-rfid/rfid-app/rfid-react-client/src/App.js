import "./App.css";
import api from "./api/axiosConfig";
import React, {useEffect, useState} from "react";
import {Route, Routes} from 'react-router-dom';
import Layout from "./components/Layout";
import Home from "./components/home/Home"
import Visitors from "./components/Visitors";

function App() {
    const [visitors, setVisitors] = useState();

    useEffect(() => {
            const getVisitors = async () => {
                try {
                    const response = await api.get("/api/v1/visitors");
                    setVisitors(response.data);
                } catch (err) {
                    console.log(err);
                }
            };
            getVisitors();
        },
        []
    );

    return <div className="App">
        <Routes>
            <Route path="/" element={<Layout/>}>
                <Route path="/" element={<Home/>}/>
                <Route path="/visitors" element={<Visitors/>}/>
            </Route>
        </Routes>
    </div>;
}

export default App;
