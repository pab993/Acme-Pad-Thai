package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Qualification extends DomainEntity{
	
	//Constructor---------------------------
	
	//Attributes----------------------------
	
	//Relationships-------------------------
	
	private Recipe recipe;
	private Contest contest;
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Contest getContest() {
		return contest;
	}
	public void setContest(Contest contest) {
		this.contest = contest;
	}
		

}
