import React from 'react';
import "bootstrap/dist/css/bootstrap.css";
import {Button} from "react-bootstrap";

const Visitor = ({visitor, onDelete, onBlock, onUnblock}) => {
        return (
            <div>
                <span>{visitor.email}</span>
                {visitor.isBlocked ?
                    <Button className="btn btn-secondary btn-sm m-2"
                            onClick={() => onUnblock(visitor.id)}>
                        Разблокировать
                    </Button> :
                    <Button className="btn btn-secondary btn-sm m-2"
                            onClick={() => onBlock(visitor.id)}>
                        Заблокировать
                    </Button>
                }
                <Button
                    onClick={() => {
                        onDelete(visitor.id)
                    }}
                    className="btn btn-danger btn-sm m-2"
                >
                    Удалить
                </Button>
            </div>
        );
    }
;

export default Visitor;
