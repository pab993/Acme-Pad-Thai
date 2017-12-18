package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Step extends DomainEntity{
	
	//Constructor--------------------------
	
	//Attributes--------------------------
	
	private String description;
	private String picture;
	private String hints;
	private int number;
	
	@NotBlank
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@URL
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getHints() {
		return hints;
	}
	public void setHints(String hints) {
		this.hints = hints;
	}
	@NotNull
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	//Relationships------------------------------
	
	private Recipe recipe;
	
	@Valid
	@NotNull
	@ManyToOne
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

}
