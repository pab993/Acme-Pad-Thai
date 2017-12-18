package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Folder;
import domain.Message;
import domain.Recipe;


import repositories.MessageRepository;

@Service
@Transactional
public class MessageService {
	
	//Managed repository ------------------
	
	@Autowired
	private MessageRepository messageRepository;
			
		
	//Supporting services -----------------
	
	@Autowired
	private ActorService actorService;
	@Autowired
	private FolderService folderService;
	//Constructors ------------------------

	// Simple CRUD methods -----------------

	public Message create() {
		actorService.check();
		Message res;
		res = new Message();
		return res;

	}
	
	public Collection<Message> findAll(){
		Collection<Message>res;
		actorService.findAll();
		res=messageRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	public Message findOne(int id){
		
		Assert.notNull(id);
		Message message = messageRepository.findOne(id);
		Assert.notNull(message);
		return message;
	}

	public Message save(Message message) {
		actorService.checkMessage(message);
		Assert.notNull(message);
		Message messageRes = messageRepository.save(message);
		return messageRes;

	}

	public void delete(Message message) {
		actorService.checkMessage(message);
		Assert.notNull(message);
		Assert.isTrue(message.getId() != 0);
		Assert.isTrue(messageRepository.exists(message.getId()));
		if(message.getFolder().getName().equalsIgnoreCase("trashbox")){
		messageRepository.delete(message);}
		else{
			for(Folder f:message.getFolder().getActor().getFolders()){
				if(f.getName().equalsIgnoreCase("trashbox")){
						message.setFolder(f);
						f.getMessages().add(message);
						save(message);
						folderService.save(f);
						break;}
					}
		}
		;

	}
	public Collection<Message> findByFolder(int folderId){
		
		Assert.notNull(folderId);
		
		Collection<Message> result = messageRepository.findByFolder(folderId);
		Assert.notNull(result, "There's not messages in this folder");
		
		return result;
	}
}
