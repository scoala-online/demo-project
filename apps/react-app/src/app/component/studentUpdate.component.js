import React from "react";
import httpService from "../services/httpService";
import {Row, Col, Container, Button} from "react-bootstrap";
import {Link} from "react-router-dom";

export default class StudentUpdate extends React.Component {
    constructor(props)
    {
        super(props);

        this.updateStudent = this.updateStudent.bind(this);
        this.getSupervisorList = this.getSupervisorList.bind(this);
        this.getStudent = this.getStudent.bind(this);

        this.state = {
            id: 0,
            firstName: "",
            lastName: "",
            email: "",
            supervisorId: 0,
            supervisors: [],
        };
    }

    updateStudent(student) {
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

        httpService
            .put("/student/" + student.id, data)
            .then((response) =>
            {
                console.log("updateStudent Response :");
                console.log(response.data);
            })
            .catch((e) =>
            {
                console.log(e);
            });
    }

    getStudent(id) {
        httpService
            .get("/student/" + id)
            .then((response) =>
            {
                console.log("getStudent Response :");
                console.log(response.data);

                let supervisorId;
                if (!response.data.supervisor){
                    supervisorId = 0;
                } else {
                    supervisorId = response.data.supervisor.id;
                }

                this.setState({
                    id: response.data.id,
                    firstName: response.data.firstName,
                    lastName: response.data.lastName,
                    email: response.data.email,
                    supervisorId: supervisorId,
                });
            })
            .catch((e) =>
            {
                console.log(e);
            });
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

    componentDidMount()
    {
        this.getSupervisorList();
        let id = this.props.location.state.id;
        this.getStudent(id);
    }

    render() {
        const student = this.state;
        const supervisorsAmount = student.supervisors.length;

        return (
            <Container>
                <h2>Update Student</h2><br/>
                <Container>
                    <Row>
                        <Col>First Name:</Col>
                        <Col>
                            <input type="text" placeholder={student.firstName}
                                   onChange={(e) =>
                                       this.setState({firstName: e.target.value})}/>
                        </Col>
                    </Row>
                    <Row>
                        <Col>Last Name:</Col>
                        <Col>
                            <input type="text" placeholder={student.lastName}
                                   onChange={(e) =>
                                       this.setState({lastName: e.target.value})}/>
                        </Col>
                    </Row>
                    <Row>
                        <Col>Email:</Col>
                        <Col>
                            <input type="text" placeholder={student.email}
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
                    <Button variant={"warning"} style={{marginLeft: "5vw"}}
                            onClick={() => this.updateStudent(student)}>
                        Update
                    </Button>
                </Container>
            </Container>
        );
    }
}