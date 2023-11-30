import React, { useState } from 'react';
import { useEffect } from "react";
import { useLocalState } from '../util/useLocalStorage';
import { Button, Col, Container, Row, Form } from "react-bootstrap"

const Login = () => {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")

    const [jwt, setJwt] = useLocalState("", "jwt");

    function sendLoginRequest() {
        const reqBody = {
            username: username,
            password: password,
        }

        fetch("api/auth/login", {
            headers: {
                'Content-Type': 'application/json',
            },
            method: "post",
            body: JSON.stringify(reqBody),
        })
            .then((response) => {
                if (response.status === 200) {
                    return Promise.all([response.json(), response.headers])
                } else {
                    return Promise.reject("Invalid login attempt")
                }

            })
            .then(([body, headers]) => {
                setJwt(headers.get("Authorization"))
                window.location.href = "dashboard"
            }).catch((message) => {
                alert(message)
            })
    }

    return (
        <>
            <Container className='mt-5'>
                <Form>
                    <Form.Group className="mb-3" controlId="formBasicEmail">
                        <Form.Label className='fs-4'>Email address</Form.Label>
                        <Form.Control type="email" placeholder="Enter email" 
                            value={username}
                            onChange={(e) => setUsername(e.target.value)} />
                        <Form.Text className="text-muted">
                            We'll never share your email with anyone else.
                        </Form.Text>
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="formBasicPassword">
                        <Form.Label className='fs-4'>Password</Form.Label>
                        <Form.Control type="password" placeholder="Password" 
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}/>
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="formBasicCheckbox">
                        <Form.Check type="checkbox" label="Check me out" />
                    </Form.Group>

                    <Row>
                        <Col className='mt-2 d-flex flex-column gap-2'>
                            <Button variant="primary" type="submit" 
                                        onClick={() => sendLoginRequest()}>
                                Submit
                            </Button>

                            <Button variant="secondary" type="button" 
                                    onClick={() => window.location.href = "/"}>
                                Submit
                            </Button>
                        </Col>
                    </Row>
                    
                </Form>
            </Container>
        </>

    );
};

export default Login;