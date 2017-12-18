package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class ContestRecipe extends DomainEntity{
	
	//Constructor--------------------------
	
	//Attributes---------------------------
	
	private String ticker;
	private String title;
	private String summary;
	private Date momentAuthored;
	private Date momentLastUpdate;
	private Collection<String> pictures;
	private String hints;
	private Integer likes;
	private Integer dislikes;
	private Integer rest;
	private Boolean winner;
	
	@NotBlank
	//@Pattern(regexp = "+[1-999]{1,3}([(][0-9]{3}[)])?+[a-zA-Z0-9_ -]")
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String Ticker) {
		this.ticker = Ticker;
	}
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String Title) {
		this.title = Title;
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
	@ElementCollection
	public Collection<String> getPictures() {
		return pictures;
	}
	public void setPictures(Collection<String> pictures) {
		this.pictures = pictures;
	}
	
	@NotBlank
	public String getHints() {
		return hints;
	}
	public void setHints(String hints) {
		this.hints = hints;
	}
	public Integer getLikes() {
		return likes;
	}
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	public Integer getDislikes() {
		return dislikes;
	}
	public void setDislikes(Integer dislikes) {
		this.dislikes = dislikes;
	}
	
	public Boolean getWinner() {
		return winner;
	}
	public void setWinner(Boolean winner) {
		this.winner = winner;
	}
	
	public Integer getRest(){
		return rest;
	}
	
	public void setRest(Integer rest){
		this.rest = rest;
	}

	
	//Relationships----------------------

	private Contest contest;
	
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
