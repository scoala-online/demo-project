import React from "react";
import httpService from "../services/httpService";
import {Row, Col, Container, Button} from "react-bootstrap";
import {Link} from "react-router-dom";

export default class StudentCreate extends React.Component {
    constructor(props)
    {
        super(props);

        this.createStudent = this.createStudent.bind(this);
        this.getSupervisorList = this.getSupervisorList.bind(this);

        this.state = {
            firstName: "",
            lastName: "",
            email: "",
            supervisorId: 0,
            supervisors: [],
        };
    }

    getSupervisorList() {
        httpService
            .get("/supervisor")
            .then((response) =>
            {
                console.log("getSupervisorList Response :");
                console.log(response.data);
                this.setState({supervisors: response.data});
            }).catch((e) =>
            {
                console.log(e);
            });
    }

    createStudent(student) {
        let data = {
            firstName: student.firstName,
            lastName: student.lastName,
            email: student.email,
        };

        if (student.supervisorId !== 0) {
            console.log(student.supervisorId);
            data = {
                ...data,
                supervisor: {
                    id: student.supervisorId,
                }
            };
        } else {
            data = {
                ...data,
                supervisor: null,
            }
        }

        console.log(data);

        httpService
            .post("/student", data)
            .then((response) =>
            {
                console.log("createStudent Response :");
                console.log(response.data);
            })
            .catch((e) =>
            {
                console.log(e);

            });
    }

    componentDidMount()
    {
        this.getSupervisorList();
    }

    render() {
        const student = this.state;
        const supervisorsAmount = student.supervisors.length;

        return (
            <Container>
                <h2>Create Student</h2><br/>
                <Container>
                    <Row>
                        <Col>First Name:</Col>
                        <Col>
                            <input type="text" placeholder={student.firstName} required
                                   onChange={(e) =>
                                       this.setState({firstName: e.target.value})}/>
                        </Col>
                    </Row>
                    <Row>
                        <Col>Last Name:</Col>
                        <Col>
                            <input type="text" placeholder={student.lastName} required
                                   onChange={(e) =>
                                       this.setState({lastName: e.target.value})}/>
                        </Col>
                    </Row>
                    <Row>
                        <Col>Email:</Col>
                        <Col>
                            <input type="text" placeholder={student.email} required
                                   onChange={(e) =>
                                       this.setState({email: e.target.value})}/>
                        </Col>
                    </Row>
                    <Row>
                        <Col>Supervisor ID:</Col>
                        <Col>
                            <input type="number" placeholder={student.supervisorId} max={supervisorsAmount} required
                                   onChange={(e) =>
                                   {
                                       let supervisorsInput = e.target.value;
                                       if (supervisorsInput > supervisorsAmount || supervisorsInput <= 0 ) {
                                           supervisorsInput = 0;
                                       }
                                       this.setState({supervisorId: supervisorsInput})}
                                   }/>
                        </Col>
                    </Row><br/>
                    <Link to={"/students"}>
                        <Button variant="outline-primary">
                            Back to Students
                        </Button>
                    </Link>
                    <Button variant={"success"} style={{marginLeft: "5vw"}}
                            onClick={() => this.createStudent(student)}>
                        Create
                    </Button>
                </Container>
            </Container>
        );
    }
}