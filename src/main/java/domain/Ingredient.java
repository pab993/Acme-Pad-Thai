package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Ingredient extends DomainEntity{
	
	//Construct----------------------------
	
	//Attributes-----------------------------
	
	private String name;
	private String description;
	private String picture;
	
	@NotBlank
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@NotBlank
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	//Relationships---------------------------

	private Collection<UnitSystem> unitSystems;
	private Collection<Possesion> possesions;

	@OneToMany(mappedBy = "ingredient")
	public Collection<UnitSystem> getUnitSystems() {
		return unitSystems;
	}
	public void setUnitSystems(Collection<UnitSystem> unitSystems) {
		this.unitSystems = unitSystems;
	}
	
	@OneToMany(mappedBy = "ingredient")
	public Collection<Possesion> getPossesions() {
		return possesions;
	}
	public void setPossesions(Collection<Possesion> possesions) {
		this.possesions = possesions;
	}
}
