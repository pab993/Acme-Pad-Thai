package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Comment extends DomainEntity{
	
	//Constructor----------------------
	
	//Attributes------------------------
	
	private String title;
	private String text;
	private Integer numberOfStars;
	private Date momentOfCreation;
	private int recipe;
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotBlank
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@NotNull
	public Integer getNumberOfStars() {
		return numberOfStars;
	}
	public void setNumberOfStars(Integer numberOfStars) {
		this.numberOfStars = numberOfStars;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMomentOfCreation() {
		return momentOfCreation;
	}
	
	public void setMomentOfCreation(Date momentOfCreation) {
		this.momentOfCreation = momentOfCreation;
	}
	
	public int getRecipe(){
		return recipe;
	}
	
	public void setRecipe(int recipe){
		this.recipe = recipe;
	}
	
	//Relationships-----------------------

//	private Recipe recipe;
	private Actor actor;
	
//	@Valid
//	@ManyToOne(optional = true)
//	public Recipe getRecipe() {
//		return recipe;
//	}
//	public void setRecipe(Recipe recipe) {
//		this.recipe = recipe;
//	}
	
	@ManyToOne(optional = false)
	public Actor getActor() {
		return actor;
	}
	public void setActor(Actor actor) {
		this.actor = actor;
	}
	
}
