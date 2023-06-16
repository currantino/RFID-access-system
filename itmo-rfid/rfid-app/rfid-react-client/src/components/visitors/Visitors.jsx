import React, {useEffect, useState} from 'react';
import Visitor from "./Visitor";
import api from "../../api/axiosConfig";

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
        const handleDelete = (id) => {
            const deleted = api.delete(`/api/v1/visitors/${id}`);
            if (deleted) {
                setVisitors((visitors) => visitors.filter((v) => v.id !== id));
            }
        };
        const handleBlock = (id) => {
            console.log(`Blocking user with id ${id}`)
            const response = api.post(`/api/v1/visitors/${id}/block`);
            console.log(response);
            if (isOk(response)) {
                const visitors = [...visitors];
                const index = visitors.findIndex((v) => v.id === id);
                visitors[index].isBlocked = true;
                setVisitors({visitors});
            }
        };
        const handleUnblock = (id) => {
            console.log(`Unblocking user with id ${id}`)
            const response = api.post(`/api/v1/visitors/${id}/unblock`);
            console.log(response);
            if (isOk(response)) {
                const visitors = [...visitors];
                const index = visitors.findIndex((v) => v.id === id);
                visitors[index].isBlocked = false;
                setVisitors({visitors});
            }
        }
        return (
            <div>
                {visitors ? (
                    <ul>
                        {visitors.map((visitor) => (
                                <li key={visitor.id}>
                                    <Visitor
                                        visitor={visitor}
                                        onDelete={handleDelete}
                                        onBlock={handleBlock}
                                        onUnblock={handleUnblock}>

                                    </Visitor>
                                </li>
                            )
                        )}
                    </ul>
                ) : (
                    <p>Loading data...</p>
                )}
            </div>
        );
    }
;

export default Visitors;