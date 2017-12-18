package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Recipe extends DomainEntity{
	
	//Constructor-----------------------------
	
	//Attributes------------------------------
	
	private String ticker;
	private String title;
	private String summary;
	private Date momentAuthored;
	private Date momentLastUpdate;
	private Collection<String> pictures;
	private String hints;
	
	@NotBlank
	@Pattern(regexp = "^[0-9]{6}[-][a-zA-Z]{4}$")
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@NotBlank
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMomentAuthored() {
		return momentAuthored;
	}
	public void setMomentAuthored(Date momentAuthored) {
		this.momentAuthored = momentAuthored;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMomentLastUpdate() {
		return momentLastUpdate;
	}
	public void setMomentLastUpdate(Date momentLastUpdate) {
		this.momentLastUpdate = momentLastUpdate;
	}
	
	@URL.List(value = {})
	@ElementCollection
	public Collection<String> getPictures() {
		return pictures;
	}
	public void setPictures(Collection<String> pictures) {
		this.pictures = pictures;
	}
	public String getHints() {
		return hints;
	}
	public void setHints(String hints) {
		this.hints = hints;
	}

	//Relationships--------------------------------

	private User user;
	private Category category;
	private Collection<Step> steps;
	private Collection<Comment> comments;
	private Collection<UnitSystem> unitSystems;
	private Collection<LikeDislike> likes;
	private Collection<Qualification> qualifications;
	private Collection<Bartol> bartols;
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<Step> getSteps() {
		return steps;
	}
	public void setSteps(Collection<Step> steps) {
		this.steps = steps;
	}
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	public Collection<Comment> getComments() {
		return comments;
	}
	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}
	
	@OneToMany(mappedBy = "recipe")
	public Collection<UnitSystem> getUnitSystems() {
		return unitSystems;
	}
	
	public void setUnitSystems(Collection<UnitSystem> unitSystems) {
		this.unitSystems = unitSystems;
	}
	
	@OneToMany(mappedBy = "recipe")
	public Collection<LikeDislike> getLikes() {
		return likes;
	}
	
	public void setLikes(Collection<LikeDislike> likes) {
		this.likes = likes;
	}
	
	@OneToMany(mappedBy = "recipe")
	public Collection<Qualification> getQualifications() {
		return qualifications;
	}
	
	public void setQualifications(Collection<Qualification> qualifications) {
		this.qualifications = qualifications;
	}
	
	@OneToMany(mappedBy = "recipe")
	public Collection<Bartol> getBartols() {
		return bartols;
	}
	public void setBartols(Collection<Bartol> bartols) {
		this.bartols = bartols;
	}
	
	
	
}
