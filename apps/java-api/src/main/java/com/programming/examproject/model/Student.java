package com.programming.examproject.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student")
public class Student
{
    //region Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private long id;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(name = "email", nullable = false, length = 200)
    private String email;

    @ManyToOne
    private Supervisor supervisor;
    //endregion

    public Student() {
    }

    //region Getters
    public long getId() {
        return id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public Supervisor getSupervisor()
    {
        return supervisor;
    }
    //endregion

    //region Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setSupervisor(Supervisor supervisor)
    {
        this.supervisor = supervisor;
    }
    //endregion

    //region Equals & HashCode
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                supervisor == student.supervisor &&
                firstName.equals(student.firstName) &&
                lastName.equals(student.lastName) &&
                email.equals(student.email);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, firstName, lastName, email, supervisor);
    }
    //endregion
}
