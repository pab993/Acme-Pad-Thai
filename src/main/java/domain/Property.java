package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Property extends DomainEntity{
	
	//Constructor--------------------------
	
	//Attributes---------------------------
	
	private String name;
	
	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//Relationships---------------------------

	private Collection<Possesion> possesions;
	
	
	@OneToMany(mappedBy = "property")
	public Collection<Possesion> getPossesions() {
		return possesions;
	}

	public void setPossesions(Collection<Possesion> possesions) {
		this.possesions = possesions;
	}

}
