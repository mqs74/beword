package pl.mqs.beword.db.model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.io.Serializable;

import javax.persistence.*;

import pl.mqs.beword.db.util.DateHelper;
import pl.mqs.beword.db.util.StringHelper;

@Entity
public class Role implements Serializable {
	private static final long serialVersionUID = -4332855118301458723L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Integer type;
	private String name;
	
	@Column(nullable = true)
	private String description;
	
	@Column(nullable = true)
	private LocalDate validTo;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Privilege> privileges;

	public Role(Integer type, String name, String description, LocalDate validTo) {
		this.type = type;
		this.name = name;
		this.description = description;
		this.validTo = validTo;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getValidTo() {
		return validTo;
	}

	public void setValidTo(LocalDate validTo) {
		this.validTo = validTo;
	}
	 
	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	public void addPrivilege(Privilege privilege) {
		if(this.privileges == null)
			this.privileges = new ArrayList<Privilege>();
		
		privileges.add(privilege);
	}
	
	public void addPrivileges(List<Privilege> privileges) {
		if(this.privileges == null)
			this.privileges = new ArrayList<Privilege>();
		
		this.privileges.addAll(privileges);
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append(StringHelper.CLASS_OPEN_BRACKET).
			append(this.getClass()).
			append(StringHelper.NESTED_POINTER).
			append("id=").
            append(id).
            append(", type=").
            append(type).
            append(", name=").
            append(name).
            append(", description=").
            append(description).
            append(", validTo=").
        	append(DateHelper.getDateAsString(validTo)).
            append(", credentials={");
			
			builder.append("}, privileges={");
			
			if(privileges != null)
				privileges.forEach(privilege -> builder.append(privilege.toString()));
			
			return builder.append("}").append(StringHelper.CLASS_CLOSE_BRACKET).toString();
	}
}
