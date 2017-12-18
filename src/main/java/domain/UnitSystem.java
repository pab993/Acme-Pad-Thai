package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class UnitSystem extends DomainEntity{
	
	//Constructor-----------------------------
	
	//Attributes-----------------------------
	
	private String unit;
	private Double quantity; 
	//private Integer quantityInteger;
	

	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
//	public Integer getQuantityInteger() {
//		return quantityInteger;
//	}
//	public void setQuantityInteger(Integer quantity) {
//		this.quantityInteger = quantity;
//	}

	//Relationships------------------------------
	

	private Recipe recipe;
	private Ingredient ingredient;
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Ingredient getIngredient() {
		return ingredient;
	}
	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
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
