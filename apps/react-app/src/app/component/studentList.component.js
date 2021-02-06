import React from "react";
import httpService from "../services/httpService";
import {Row, Col, Container, Button} from "react-bootstrap";
import {Link} from "react-router-dom";

export default class StudentList extends React.Component
{
    constructor(props)
    {
        super(props);

        this.getStudentList = this.getStudentList.bind(this);
        this.deleteStudent = this.deleteStudent.bind(this);

        this.state = {
            students: [],
        };
    }

    getStudentList() {
        httpService
            .get("/student")
            .then((response) =>
            {
                console.log("getStudentList Response :");
                console.log(response.data);

                this.setState({
                    students: response.data
                });
            })
            .catch((e) =>
            {
                console.log(e);
            });
    }

    deleteStudent(id, e) {
        httpService
            .delete("/student/" + id)
            .then((response) =>
            {
                console.log("deleteStudent Response :");
                console.log(response);
                this.getStudentList();
            })
            .catch((e) =>
            {
                console.log(e);
            });
    }

    componentDidMount()
    {
        this.getStudentList();
    }

    render()
    {
        console.log(this.state);
        const studentList = this.state.students.map((student, index) =>
        {
            let supervisor = student.supervisor;
            if (!supervisor) {
                supervisor = {
                    id: 0,
                    firstName: "",
                    lastName: "",
                }
            }
            return (
                <Container>
                    <br/>
                    <Row key={index}>
                        <Col>{student.id}</Col>
                        <Col>{student.firstName} {student.lastName}</Col>
                        <Col>{student.email}</Col>
                        <Col>{supervisor.id} {supervisor.firstName} {supervisor.lastName}</Col>
                        <Col>
                            <Link to={{
                                pathname: '/update',
                                state: {
                                    id: student.id,
                                }
                            }}>
                                <Button variant="info">Update</Button>
                            </Link>
                            {" "}
                            <Button variant="danger" onClick={(e) =>
                                this.deleteStudent(student.id, e)}>Delete</Button>
                        </Col>
                    </Row>
                </Container>
            );
        });

        return (
            <Container>
                <h3 style={{textAlign:"center"}}>Employee List</h3>
                <Link to={"/create"}>
                    <Button variant="outline-info">
                        Add new Student
                    </Button>
                </Link>
                <Container>
                    <Row>
                        <Col>ID</Col>
                        <Col>Name</Col>
                        <Col>Email</Col>
                        <Col>Supervisor</Col>
                        <Col>Actions</Col>
                    </Row>
                    {studentList}
                </Container>
            </Container>
        );
    }
}