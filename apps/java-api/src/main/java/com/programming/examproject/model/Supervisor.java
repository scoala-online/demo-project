package com.programming.examproject.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "supervisor")
public class Supervisor
{
    //region Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supervisor_id")
    private long id;

    @Column(name = "first_name", nullable = true, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;
    //endregion

    public Supervisor() {
    }

    //region Getters
    public long getId()
    {
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
    //endregion

    //region Equals & HashCode
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supervisor that = (Supervisor) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                lastName.equals(that.lastName);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, firstName, lastName);
    }
    //endregion
}
