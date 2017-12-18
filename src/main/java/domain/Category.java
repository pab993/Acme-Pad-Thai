package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity{
	
	//Constructor---------------------------
	
	//Attributes-------------------------
	
	private String name;
	private String description;
	private String picture;
	private Collection<String> tags;
	
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
	
	@URL
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	@ElementCollection
	public Collection<String> getTags() {
		return tags;
	}
	public void setTags(Collection<String> tags) {
		this.tags = tags;
	}
	
	//Relationships----------------------------------
	
	private Collection<Recipe> recipes;
	private Collection<Category> categories;
	private Category oneCategory;
	
	@OneToMany(mappedBy = "category")
	public Collection<Recipe> getRecipes() {
		return recipes;
	}
	public void setRecipes(Collection<Recipe> recipes) {
		this.recipes = recipes;
	}
	
	@OneToMany(mappedBy = "oneCategory")
	public Collection<Category> getCategories() {
		return categories;
	}
	public void setCategories(Collection<Category> categories) {
		this.categories = categories;
	}
	
	@Valid
	@ManyToOne
	public Category getOneCategory() {
		return oneCategory;
	}
	public void setOneCategory(Category oneCategory) {
		this.oneCategory = oneCategory;
	}


}
