import React, { useEffect, useState } from 'react';
import { useLocalState } from '../util/useLocalStorage';
import ajax from '../Service/fetchService';

const AssignmentView = () => {
    const assignmentId = window.location.href.split("/assignments/")[1]
    const [assignment, setAssignment] = useState({
        branch: "",
        githubUrl: ""
    })
    const [jwt, setJwt] = useLocalState("", "jwt");
    const [branch, setBranch] = useState("")

    function updateAssignment(prop, value) {
        const newAssignment = {...assignment}
        newAssignment[prop] = value;
        setAssignment(newAssignment)
    }

    function save(){
        ajax(`/api/assignments/${assignmentId}`, "PUT", jwt, assignment).then(
            (assignmentData) => {
                setAssignment(assignmentData)
            }
        )
    }

    useEffect(() => {
        ajax(`/api/assignments/${assignmentId}`, "GET", jwt)
        .then((assignmentData) => {
            if(assignmentData.branch === null) assignmentData.branch = ""
            if(assignmentData.githubUrl === null) assignmentData.githubUrl = ""
            setAssignment(assignmentData)
        })
    }, [])

    return (
        <div>
            <h1>Assignment {assignmentId}</h1>
            {assignment ? (
            <>
                <h2>Status {assignment.status}</h2>
                <h3>Github URL: <input type='url' id='githubUrl' 
                onChange={(e) => updateAssignment("githubUrl",e.target.value)}
                value={assignment.githubUrl} /></h3>

                <h3>Branch: <input type='url' id='branch' 
                onChange={(e) => updateAssignment("branch", e.target.value)}
                value={assignment.branch} /></h3>
                
                <button onClick={() => save()}>Submit Assignment</button>
            </>) : (
                <></>
            )}
            
        </div>
    );
};

export default AssignmentView;