import React, {useState} from 'react';
import {Button, Form} from "react-bootstrap";
import api from "../../api/axiosConfig"
import 'bootstrap/dist/css/bootstrap.css';

const AddCredentialForm = () => {
        const [rfidUid, setRfidUid] = useState('');
        const [email, setEmail] = useState('');


        const handleSubmit = async (e) => {
            e.preventDefault();
            api.post("/api/v1/credentials", {
                "rfidUid": rfidUid,
                "owner": {
                    "email": email
                }
            })
                .then((response) => alert(`Пропуск ${rfidUid} успешно зарегистрирован на пользователя ${email}!`))
                .catch((err) => {
                    console.log(err);
                });
        };

        return (
            <Form onSubmit={handleSubmit}>
                <Form.Group controlId="email">
                    <Form.Label>Введите уникальный идентификатор нового пропуска.</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="AABBCCDD"
                        value={rfidUid}
                        onChange={(e) => setRfidUid(e.target.value)}
                        required
                    />
                </Form.Group>
                <Form.Group>
                    <Form.Label>Введите адрес электронной почты владельца пропуска</Form.Label>
                    <Form.Control
                        type="email"
                        placeholder="johndoe@example.com"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </Form.Group>
                <Button className="btn btn-primary m-2" variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
        );
    }
;

export default AddCredentialForm;