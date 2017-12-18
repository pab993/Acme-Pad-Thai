package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Nutritionist extends Customer{

	//Constructor-------------------
	
	//Attributes--------------------
	
	//Relationships-----------------
	
	private Curriculum curriculum;
	private Collection<Bartol> bartols;

	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}
	
	@OneToMany(mappedBy = "nutritionist")
	public Collection<Bartol> getBartols() {
		return bartols;
	}
	public void setBartols(Collection<Bartol> bartols) {
		this.bartols = bartols;
	}
}
