package pl.mqs.beword.db.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import pl.mqs.beword.db.util.DateHelper;
import pl.mqs.beword.db.util.StringHelper;

@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = -3009157732242241606L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    
    @OneToMany
    private List<Role> roles;
    
    @OneToMany
    private List<Credential> credentials;
    
    @OneToMany
    private List<Address> addresses;

    public User() {
        roles = null;
        firstName = null;
        lastName = null;
        birthDate = null;
    }

    public User(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        
        this.roles = null;
        this.credentials = null;
    }

    public Long getId() {
        return id;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
    public void addRole(Role role) {
    	if(this.roles == null)
    		this.roles = new ArrayList<Role>();
    	
    	this.roles.add(role);
    }
    
    public void addRoles(List<Role> roles) {
    	if(this.roles == null)
    		this.roles = new ArrayList<Role>();
    	
    	this.roles.addAll(roles);
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

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
	public void addAddress(Address address) {
		if(addresses == null)
			addresses = new ArrayList<Address>();
		
		addresses.add(address);
	}
	
	public void addAddresses(List<Address> addresses) {
		if(this.addresses == null)
			this.addresses = new ArrayList<Address>();
		
		this.addresses.addAll(addresses);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(StringHelper.CLASS_OPEN_BRACKET).
			append(this.getClass()).
        	append(StringHelper.NESTED_POINTER).
        	append("id=").
        	append(id).
        	append(", firstName=").
        	append(firstName).
        	append(", lastName=").
        	append(lastName).
        	append(", birthDate=").
        	append(DateHelper.getDateAsString(birthDate)).
        	append(", credentials={");
		
		if(credentials != null)
			credentials.forEach(credential -> builder.append(credential.toString()));
		
		builder.append("}, roles={");
		
		if(roles != null)
			roles.forEach(role -> builder.append(role.toString()));
		
		builder.append("}, addresses={");
		
		if(addresses != null)
			addresses.forEach(address -> builder.append(address.toString()));
		
        return builder.append("}").append(StringHelper.CLASS_CLOSE_BRACKET).toString();
	}
}
