package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Sponsor extends Actor{
	
	//Constructor---------------------------
	
	//Attributes----------------------------

	private String company;
	
	@NotBlank
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	//Relationships--------------------------

	private Collection<CreditCard> creditCards;
	private Collection<Bill> bills;
	private Collection<Campaign> campaigns;
	
	@OneToMany(mappedBy = "sponsor")
	public Collection<CreditCard> getCreditCards() {
		return creditCards;
	}
	public void setCreditCards(Collection<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}
	
	@OneToMany(mappedBy = "sponsor")
	public Collection<Bill> getBills() {
		return bills;
	}
	public void setBills(Collection<Bill> bills) {
		this.bills = bills;
	}
	
	@OneToMany(mappedBy = "sponsor")
	public Collection<Campaign> getCampaigns() {
		return campaigns;
	}
	public void setCampaigns(Collection<Campaign> campaigns) {
		this.campaigns = campaigns;
	}
	
	
	
}