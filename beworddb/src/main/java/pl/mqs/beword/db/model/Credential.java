package pl.mqs.beword.db.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

import pl.mqs.beword.db.util.DateHelper;
import pl.mqs.beword.db.util.MockupHelper;

@Entity
@Table(name = "credential")
public class Credential implements Serializable {
    private static final long serialVersionUID = -3009157739992241606L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer type;
    private String login;
    private String password;
    private LocalDate validTo;
    private Boolean isValid;

    public Integer getId() {
        return id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public String toString() {
        return new StringBuilder().
                append(this.getClass()).
                append("->[id=").
                append(id).
                append(", type=").
                append(type).
                append("], login=[").
                append(login).
                append("], password=[").
                append(MockupHelper.mockupPassword(password)).
                append("], validTo=[").
                append(DateHelper.getDateAsString(validTo)).
                append("], isValid=[").
                append(isValid).
                append("]").
                toString();
    }
}

