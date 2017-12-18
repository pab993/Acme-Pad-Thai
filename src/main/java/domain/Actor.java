package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public class Actor extends DomainEntity{
	
	//Constructors---------------------
	
	//Attributes-----------------------
	
	private String name;
	private String surname;
	private String phoneNumber;
	private String postalAddress;
	
	@NotBlank
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@NotBlank
	@Pattern(regexp = "^[+][1-999]{1,3}([(][0-9]{3}[)])?+[a-zA-Z0-9_ -]{4,}$")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@NotBlank
	public String getPostalAddress() {
		return postalAddress;
	}
	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}
	
	//Relationships-----------------------
	
	private UserAccount userAccount;
	private Collection<SocialIdentity> socialIdentities;
	private Collection<Folder> folders;
	private Collection<Message> senders;
	private Collection<Message> recipients;
	private Collection<Comment> commentsActor;
	
	@Valid
	@NotNull
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public UserAccount getUserAccount(){
		return userAccount;
	}
	
	public void setUserAccount(UserAccount userAccount){
		this.userAccount = userAccount;
	}
	
	@OneToMany(mappedBy = "actor")
	public Collection<SocialIdentity> getSocialIdentities() {
		return socialIdentities;
	}

	public void setSocialIdentities(Collection<SocialIdentity> socialIdentities) {
		this.socialIdentities = socialIdentities;
	}
	
	@NotNull
	@OneToMany(mappedBy = "actor")
	public Collection<Folder> getFolders() {
		return folders;
	}

	public void setFolders(Collection<Folder> folders) {
		this.folders = folders;
	}
	
	@OneToMany(mappedBy = "senderActor")
	public Collection<Message> getSenders() {
		return senders;
	}

	public void setSenders(Collection<Message> senders) {
		this.senders = senders;
	}
	
	@OneToMany(mappedBy = "recipientActor")
	public Collection<Message> getRecipients() {
		return recipients;
	}
	
	public void setRecipients(Collection<Message> recipients) {
		this.recipients = recipients;
	}

	@OneToMany(mappedBy = "actor")
	public Collection<Comment> getCommentsActor() {
		return commentsActor;
	}

	public void setCommentsActor(Collection<Comment> commentsActor) {
		this.commentsActor = commentsActor;
	}

}
