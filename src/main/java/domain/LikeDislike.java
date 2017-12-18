package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class LikeDislike extends DomainEntity{
	
	//Constructor----------------------
	
	//Attributes-----------------------
	
	private Boolean likeOrDislike;

	@NotNull
	public Boolean getLikeOrDislike() {
		return likeOrDislike;
	}

	public void setLikeOrDislike(Boolean likeOrDislike) {
		this.likeOrDislike = likeOrDislike;
	}
	
	//Relationships--------------------
	

	private Customer customer;
	private Recipe recipe;
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
}
