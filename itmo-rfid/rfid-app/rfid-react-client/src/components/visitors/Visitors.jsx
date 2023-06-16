import React, {useEffect, useState} from 'react';
import Visitor from "./Visitor";
import api from "../../api/axiosConfig";
import {Link} from "react-router-dom";
import Stack from '@mui/material/Stack';
import {Divider} from "@mui/material";


function isOk(response) {
    return response.status >= 200 && response.status < 400;
}

const Visitors = () => {
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
        const handleDelete = async (id) => {
            const deleted = await api.delete(`/api/v1/visitors/${id}`);
            if (deleted) {
                setVisitors((visitors) => visitors.filter((v) => v.id !== id));
            }
        };
        const handleBlock = async (id) => {
            console.log(`Blocking user with id ${id}`)
            const response = await api.post(`/api/v1/visitors/${id}/block`);
            if (isOk(response)) {
                const updatedVisitors = visitors.map((v) => {
                    if (v.id === id) {
                        v.isBlocked = true;
                    }
                    return v;
                });
                setVisitors(updatedVisitors);
            }
        };
        const handleUnblock = async (id) => {
            console.log(`Unblocking user with id ${id}`)
            const response = await api.post(`/api/v1/visitors/${id}/unblock`);
            if (isOk(response)) {
                const updatedVisitors = visitors.map((v) => {
                    if (v.id === id) {
                        v.isBlocked = false;
                    }
                    return v;
                });
                setVisitors(updatedVisitors);
            }
        }
        return (
            <div>
                <Link to={"/visitors/add"} className={"btn m-2 badge-primary"}>Добавить пользователя</Link>
                <main className="container">
                    {visitors ? (
                        <Stack
                            divider={<Divider orientation="horizontal" flexItem/>}
                        >
                            {visitors.map((visitor) => (
                                    <Visitor
                                        visitor={visitor}
                                        isBlocked={visitor.isBlocked}
                                        onDelete={handleDelete}
                                        onBlock={handleBlock}
                                        onUnblock={handleUnblock}
                                    >
                                    </Visitor>
                                )
                            )}
                        </Stack>
                    ) : (
                        <p>Loading data...</p>
                    )}
                </main>
            </div>
        );
    }
;

export default Visitors;