package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;


@Entity
@Access(AccessType.PROPERTY)
public class Follower extends DomainEntity{
	
	//Constructor-----------------------
	
	//Attributes------------------------
		
	//Relationships---------------------
	
	private Customer follower;
	private Customer followed;
	
	@Valid
	@ManyToOne
	public Customer getFollower() {
		return follower;
	}
	public void setFollower(Customer follower) {
		this.follower = follower;
	}
	
	@Valid
	@ManyToOne
	public Customer getFollowed() {
		return followed;
	}
	public void setFollowed(Customer followed) {
		this.followed = followed;
	}
		

}
