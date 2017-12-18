package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Actor{
	
	//Constructor-----------------------
	
	//Attributes------------------------
	
	//Relationships---------------------
	
	private Collection<Follower> followers;
	private Collection<Follower> followeds;
	private Collection<LikeDislike> likes;
	
	@OneToMany(mappedBy="follower")
	public Collection<Follower> getFollowers() {
		return followers;
	}
	public void setFollowers(Collection<Follower> followers) {
		this.followers = followers;
	}
	
	@OneToMany(mappedBy="followed")
	public Collection<Follower> getFolloweds() {
		return followeds;
	}
	public void setFolloweds(Collection<Follower> followeds) {
		this.followeds = followeds;
	}
	
	@OneToMany(mappedBy = "customer")
	public Collection<LikeDislike> getLikes() {
		return likes;
	}
	public void setLikes(Collection<LikeDislike> likes) {
		this.likes = likes;
	}
	
}
