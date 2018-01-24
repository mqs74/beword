package pl.mqs.beword.db.model;

import java.time.LocalDate;
import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

import pl.mqs.beword.db.util.DateHelper;
import pl.mqs.beword.db.util.StringHelper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "privilege")
public class Privilege implements Serializable{
	private static final long serialVersionUID = 2714756071132570831L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Integer type;
	private String name;
	
	@Column(nullable = true)
	private String description;
	private LocalDate validTo;
	
	public Privilege(Integer type, String name, String description, LocalDate validTo) {
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
	
	public String toString() {
		return new StringBuilder().append(StringHelper.CLASS_OPEN_BRACKET).
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
		append(StringHelper.CLASS_CLOSE_BRACKET).toString();
	}
}
