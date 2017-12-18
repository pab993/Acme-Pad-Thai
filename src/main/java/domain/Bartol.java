package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Bartol extends DomainEntity{

	//Attributes---------------------------
	private String label;
	private String title;
	private String description;
	private Double calories;
	private Date momentDisplay;
	
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9_]{5}/[0-9]{5}$")
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	@NotBlank
	@Length(min=0,max=20)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotBlank
	@Length(min=0, max=100)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Range(min=0, max=10000)
	public Double getCalories() {
		return calories;
	}
	public void setCalories(Double calories) {
		this.calories = calories;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMomentDisplay() {
		return momentDisplay;
	}
	public void setMomentDisplay(Date momentDisplay) {
		this.momentDisplay = momentDisplay;
	}
	
	
	
	//Relationships---------------------


	private Recipe recipe;
	private Nutritionist nutritionist;
	
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
	public Nutritionist getNutritionist() {
		return nutritionist;
	}
	public void setNutritionist(Nutritionist nutritionist) {
		this.nutritionist = nutritionist;
	}

	
}
