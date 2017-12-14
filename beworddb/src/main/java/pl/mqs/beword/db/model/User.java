package pl.mqs.beword.db.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import pl.mqs.beword.db.util.DateHelper;

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
    
    @OneToMany
    private List<Credential> credentials;

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

    public List<Credential> getCredentials() {
		return credentials;
	}

	public void setCredentials(List<Credential> credentials) {
		this.credentials = credentials;
	}

	@Override
    public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(this.getClass()).
        append("->[id=").
        append(id).
        append(", type=").
        append(type).
        append(", firstName=").
        append(firstName).
        append(", lastName=").
        append(lastName).
        append(", birthDate=").
        append(DateHelper.getDateAsString(birthDate)).
        append("], credentials={");
		
		if(credentials != null)
			credentials.forEach(credential -> builder.append("->").append(credential.toString()));
		
        return builder.append("}").toString();
    }
}

