import React, {useEffect, useState} from 'react';
import api from "../../api/axiosConfig";
import {Link} from "react-router-dom";
import Stack from "@mui/material/Stack";
import {Divider} from "@mui/material";
import Credential from "./Credential";

function isOk(response) {
    return response.status >= 200 && response.status < 400;
}

const Credentials = () => {
    const [credentials, setCredentials] = useState();

    useEffect(() => {
            const getCredentials = async () => {
                try {
                    const response = await api.get("/api/v1/credentials");
                    setCredentials(response.data);
                } catch (err) {
                    console.log(err);
                }
            };
            getCredentials();
        },
        []
    );
    const handleDelete = async (id) => {
        const deleted = await api.delete(`/api/v1/credentials/${id}`);
        if (deleted) {
            setCredentials((credentials) => credentials.filter((v) => v.id !== id));
        }
    };
    const handleBlock = async (id) => {
        console.log(`Blocking credential with id ${id}`)
        const response = await api.post(`/api/v1/credentials/${id}/block`);
        if (isOk(response)) {
            const updatedCredentials = credentials.map((v) => {
                if (v.id === id) {
                    v.isBlocked = true;
                }
                return v;
            });
            setCredentials(updatedCredentials);
        }
    };
    const handleUnblock = async (id) => {
        console.log(`Unblocking credential with id ${id}`)
        const response = await api.post(`/api/v1/credentials/${id}/unblock`);
        if (isOk(response)) {
            const updatedCredentials = credentials.map((v) => {
                if (v.id === id) {
                    v.isBlocked = false;
                }
                return v;
            });
            setCredentials(updatedCredentials);
        }
    }
    return (
        <div>
            <Link to={"/credentials/add"} className={"btn m-2 badge-primary"}>Добавить пропуск</Link>
            <main className="container">
                {credentials ? (
                    <Stack
                        divider={<Divider orientation="horizontal" flexItem/>}
                    >
                        {credentials.map((credential) => (
                                <Credential
                                    credential={credential}
                                    onDelete={handleDelete}
                                    onBlock={handleBlock}
                                    onUnblock={handleUnblock}
                                >
                                </Credential>
                            )
                        )}
                    </Stack>
                ) : (
                    <p>Loading data...</p>
                )}
            </main>
        </div>
    );
};

export default Credentials;
