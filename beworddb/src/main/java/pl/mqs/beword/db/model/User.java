package pl.mqs.beword.db.model;

import pl.mqs.beword.db.util.ModelConsts;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.*;

/**
 * Created by mzwolinski on 8/4/17.
 */

@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = -3009157732242241606L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Integer type;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public User() {
        type = null;
        firstName = null;
        lastName = null;
        birthDate = null;
    }

    public User(Integer type, String firstName, String lastName, LocalDate birthDate) {
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public long getId() {
        return id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return new StringBuilder().
                append(this.getClass()).
                append("[id=").
                append(id).
                append(", type=").
                append(type).
                append(", firstName=").
                append(firstName).
                append(", lastName=").
                append(lastName).
                append(", birthDate=").
                append(birthDate.format(DateTimeFormatter.ofPattern(ModelConsts.DATE_FORMAT))).
                append("]").toString();
    }
}

