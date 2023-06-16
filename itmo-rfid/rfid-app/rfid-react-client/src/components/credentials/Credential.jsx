import React from 'react';
import {Button} from "react-bootstrap";

const Credential = ({credential, onDelete, onBlock, onUnblock}) => {
    return (
        <div>
            <span>{credential.rfidUid}</span>
            {credential.isBlocked ?
                <Button className="btn btn-secondary btn-sm m-2"
                        onClick={() => onUnblock(credential.id)}>
                    Разблокировать
                </Button> :
                <Button className="btn btn-secondary btn-sm m-2"
                        onClick={() => onBlock(credential.id)}>
                    Заблокировать
                </Button>
            }
            <Button
                onClick={() => {
                    onDelete(credential.id)
                }}
                className="btn btn-danger btn-sm m-2"
            >
                Удалить
            </Button>
        </div>
    );
};

export default Credential;
