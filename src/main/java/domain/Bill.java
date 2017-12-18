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

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Bill extends DomainEntity{
	
	//Constructor-----------------------------------
	
	//Attributes-----------------------------------

	private Date momentCreate;
	private Date momentPay;
	private String description;
	private Double cost;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMomentCreate() {
		return momentCreate;
	}
	public void setMomentCreate(Date momentCreate) {
		this.momentCreate = momentCreate;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMomentPay() {
		return momentPay;
	}
	public void setMomentPay(Date momentPay) {
		this.momentPay = momentPay;
	}
	
	@NotBlank
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public Double getCost(){
		return cost;
	}
	
	public void setCost(Double cost){
		this.cost = cost;
	}
	
	//Relationships----------------------------
	
	private Sponsor sponsor;
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Sponsor getSponsor() {
		return sponsor;
	}
	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	
}
