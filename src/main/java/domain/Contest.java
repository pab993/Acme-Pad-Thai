package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Contest extends DomainEntity{
	
	//Constructor------------------------
	
	//Attributes-------------------------
	
	private String title;
	private Date openingTime;
	private Date closingTime;
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getOpeningTime() {
		return openingTime;
	}
	public void setOpeningTime(Date openingTime) {
		this.openingTime = openingTime;
	}
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(Date closingTime) {
		this.closingTime = closingTime;
	}
	
	//Relationship--------------------------
	
	private Collection<Qualification> qualifications;
	private Collection<ContestRecipe> contestRecipes;
	@NotNull
	@OneToMany(mappedBy = "contest")
	public Collection<Qualification> getQualifications() {
		return qualifications;
	}
	public void setQualifications(Collection<Qualification> qualifications) {
		this.qualifications = qualifications;
	}
	@NotNull
	@OneToMany(mappedBy = "contest")
	public Collection<ContestRecipe> getContestRecipes() {
		return contestRecipes;
	}
	public void setContestRecipes(Collection<ContestRecipe> contestRecipes) {
		this.contestRecipes = contestRecipes;
	}


}