package pl.mqs.beword.db.model;

import java.time.LocalDate;
import java.io.Serializable;

import javax.persistence.*;

import pl.mqs.beword.db.util.DateHelper;
import pl.mqs.beword.db.util.MockupHelper;
import pl.mqs.beword.db.util.StringHelper;

@Entity
@Table(name = "credential")
public class Credential implements Serializable {
    private static final long serialVersionUID = -3009157739992241606L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer type;
    private String login;
    private String password;
    private LocalDate validTo;
    private Boolean isValid;

    public Credential() {}
    
    public Credential(Integer type, String login) {
    	this.type = type;
    	this.login = login;
    }
    
    public Long getId() {
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

    @Override
    public String toString() {
    	return new StringBuilder().
    			append(StringHelper.CLASS_OPEN_BRACKET).
    			append(this.getClass()).
                append(StringHelper.NESTED_POINTER).
                append("id=").
                append(id).
                append(", type=").
                append(type).
                append(", login=").
                append(login).
                append(", password=").
                append(MockupHelper.mockupPassword(password)).
                append(", validTo=").
                append(DateHelper.getDateAsString(validTo)).
                append(", isValid=").
                append(isValid).
                append(StringHelper.CLASS_CLOSE_BRACKET).toString();
    }
}
