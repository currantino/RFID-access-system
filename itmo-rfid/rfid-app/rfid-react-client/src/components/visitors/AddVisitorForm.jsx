import React, {useState} from 'react';
import {Button, Form} from "react-bootstrap";
import api from "../../api/axiosConfig"
import 'bootstrap/dist/css/bootstrap.css';

const AddVisitorForm = () => {
    const [email, setEmail] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        api.post("/api/v1/visitors", {email})
            .then((response) => alert(`Added user ${email} successfully!`))
            .catch((err) => {
                console.log(err);
            });
    };

    return (
        <Form onSubmit={handleSubmit}>
            <Form.Group controlId="email">
                <Form.Label>Введите адрес электронной почты нового пользователя</Form.Label>
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
};

export default AddVisitorForm;