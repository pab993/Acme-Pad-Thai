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
public class Message extends DomainEntity{
	
	//Constructor--------------------------
	
	//Attributes---------------------------
	
	private Date momentSent;
	private String subject;
	private String body;
	private String priority;
	

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMomentSent() {
		return momentSent;
	}
	public void setMomentSent(Date momentSent) {
		this.momentSent = momentSent;
	}
	@NotBlank
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@NotBlank
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@NotBlank
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	//Relationships---------------------
	
	private Folder folder;
	private Actor senderActor;
	private Actor recipientActor;
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Folder getFolder() {
		return folder;
	}
	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Actor getSenderActor() {
		return senderActor;
	}
	public void setSenderActor(Actor senderActor) {
		this.senderActor = senderActor;
	}
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Actor getRecipientActor() {
		return recipientActor;
	}
	public void setRecipientActor(Actor recipientActor) {
		this.recipientActor = recipientActor;
	}


}
