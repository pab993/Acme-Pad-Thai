package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Curriculum extends DomainEntity{
	
	//Constructor-------------------------------
	
	//Attributes--------------------------------
	private String photo;
	private String educationSection;
	private String experienceSection;
	private String hobbiesSection;
	
	@NotBlank
	@URL
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@NotBlank
	public String getEducationSection() {
		return educationSection;
	}
	public void setEducationSection(String educationSection) {
		this.educationSection = educationSection;
	}
	@NotBlank
	public String getExperienceSection() {
		return experienceSection;
	}
	public void setExperienceSection(String experienceSection) {
		this.experienceSection = experienceSection;
	}
	@NotBlank
	public String getHobbiesSection() {
		return hobbiesSection;
	}
	public void setHobbiesSection(String hobbiesSection) {
		this.hobbiesSection = hobbiesSection;
	}
	
	//Relationships------------------------------

	//private Nutritionist nutritionist;
	private Collection<Endorser> endorsers;
	
	/*@Valid
	@NotNull
	@OneToOne(optional = false)
	public Nutritionist getNutritionist() {
		return nutritionist;
	}
	public void setNutritionist(Nutritionist nutritionist) {
		this.nutritionist = nutritionist;
	}
	*/
	@OneToMany(mappedBy = "curriculum")
	public Collection<Endorser> getEndorsers() {
		return endorsers;
	}
	public void setEndorsers(Collection<Endorser> endorsers) {
		this.endorsers = endorsers;
	}
	
}
