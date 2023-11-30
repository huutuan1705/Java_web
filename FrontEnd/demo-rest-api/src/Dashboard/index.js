import React, { useEffect, useState } from 'react';
import { useLocalState } from '../util/useLocalStorage';
import { Link } from 'react-router-dom';
import ajax from '../Service/fetchService';

const Dashboard = () => {
    const [jwt, setJwt] = useLocalState("", "jwt");
    const [assignment, setAssignment] = useState(null)

    useEffect(() => {
        ajax("api/assignments", "GET", jwt)
        .then((assignmentData) => {
            setAssignment(assignmentData)
        })
    }, [])

    function createAssignment() {
        ajax("api/assignments", "POST", jwt)
        .then((assignment) => {
            window.location.href = `/assignments/${assignment.id}`
        })
    }

    return (
        <div style={{margin: "2em"}}>
            {assignment ? assignment.map((assignment) => 
            (<div key={assignment.id}>
                <Link to={`/assignments/${assignment.id}`}>
                Assignment ID: {assignment.id}
                </Link>
            </div>
            )) : <></> }
            <button onClick={() => createAssignment()} >Submit new Assignment</button>
        </div>
    );
};

export default Dashboard;