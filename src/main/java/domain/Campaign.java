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

@Entity
@Access(AccessType.PROPERTY)
public class Campaign extends DomainEntity{
	
	//Constructor----------------------------
	
	//Attributes-----------------------------
	
	private Date startDate;
	private Date endDate;
	private int bannersNumber;
	private int maximumBanner;
	private Boolean starCampaign;
	
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@NotNull
	public int getBannersNumber() {
		return bannersNumber;
	}
	public void setBannersNumber(int bannersNumber) {
		this.bannersNumber = bannersNumber;
	}
	@NotNull
	public int getMaximumBanner() {
		return maximumBanner;
	}
	public void setMaximumBanner(int maximumBanner) {
		this.maximumBanner = maximumBanner;
	}
	
	public Boolean getStarCampaign() {
		return starCampaign;
	}
	public void setStarCampaign(Boolean starCampaign) {
		this.starCampaign = starCampaign;
	}
	
	//Relationships----------------------------
	
	private Sponsor sponsor;
	
	@ManyToOne(optional = false)
	public Sponsor getSponsor() {
		return sponsor;
	}
	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}


}
