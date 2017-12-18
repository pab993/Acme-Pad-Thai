package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class ConfigurationSystem extends DomainEntity{
	
	//Construct----------------------------
	
	//Attributes---------------------------

	private Double fee;
	private Collection<String> spamWords;
	
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	@ElementCollection
	public Collection<String> getSpamWords() {
		return spamWords;
	}
	public void setSpamWords(Collection<String> spamWords) {
		this.spamWords = spamWords;
	}
	
	//Relationships---------------------------
}
