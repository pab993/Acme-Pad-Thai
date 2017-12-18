package controllers;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.hibernate.jpa.criteria.expression.function.CurrentDateFunction;
import org.joda.time.LocalTime;
import org.omg.PortableInterceptor.Current;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.LoginService;
import security.UserAccount;
import services.ActorService;
import services.AdministratorService;
import services.ConfigurationSystemService;
import services.FolderService;
import services.MessageService;
import services.NutritionistService;
import services.UserService;

import domain.Actor;
import domain.ConfigurationSystem;
import domain.Message;
import domain.Folder;

@Controller
@RequestMapping("/message")
public class MessageController extends AbstractController {

	// Services ---------------------------------------

	@Autowired
	private MessageService messageService;

	@Autowired
	private FolderService folderService;
	
	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private NutritionistService nutritionistService;
	@Autowired
	private UserService userService;
	@Autowired
	private ActorService actorService;
	@Autowired
	private ConfigurationSystemService configurationSystemService;
	
	// Constructors -----------------------------------------------------

	public MessageController() {
		super();
	}

	// Listing ------------------------------------------------------------

	@RequestMapping(value="list", method=RequestMethod.GET)
	public ModelAndView list(@RequestParam int folderId){
		
		ModelAndView result;
		Folder folder;
		Collection<Message> messages;
		
		folder = folderService.findOne(folderId);
		messages = folder.getMessages();
		result = new ModelAndView("message/list");
		result.addObject("message",messages);
		result.addObject("requestURI", "message/list.do");
		
		return result;
	}

	// Create methods---------------------------

	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Message message;

		message = messageService.create();
		Actor actor = actorService.findByActorAccountId(LoginService.getPrincipal().getId());
		long l = 10;
		Date d = new Date(System.currentTimeMillis()- l);
		message.setMomentSent(d);
		message.setSenderActor(actor);
		for(Folder f: actor.getFolders()){
			if(f.getName().equalsIgnoreCase("outbox")){
				message.setFolder(f);
				break;
			}}
		result = createSendModelAndView(message);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int messageId) {
		ModelAndView result;
		Message message;

		message = messageService.findOne(messageId);
		Assert.notNull(message);
		result = createEditModelAndView(message);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Message message, BindingResult binding) {
		ModelAndView result=null;
		Authority ad = new Authority();
		Authority us = new Authority();
		Authority nut = new Authority();
		UserAccount uA=LoginService.getPrincipal();
		ad.setAuthority("ADMIN");
		us.setAuthority("USER");
		nut.setAuthority("NUTRITIONIST");
		if (binding.hasErrors()) {
			result = createEditModelAndView(message);
		} else {
			try {
				messageService.save(message);
				if (uA.getAuthorities().contains(ad)){
					result = new ModelAndView("redirect:/folder/administrator/list.do");}
					else if (uA.getAuthorities().contains(nut)){
						result = new ModelAndView("redirect:/folder/nutritionist/list.do");}
					else if (uA.getAuthorities().contains(us)){
						result = new ModelAndView("redirect:/folder/user/list.do");};
			} catch (Throwable oops) {
				result = createEditModelAndView(message,
						"message.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Message message, BindingResult binding) {
		ModelAndView result=null;
		Authority ad = new Authority();
		Authority us = new Authority();
		Authority nut = new Authority();
		UserAccount uA=LoginService.getPrincipal();
		ad.setAuthority("ADMIN");
		us.setAuthority("USER");
		nut.setAuthority("NUTRITIONIST");
		try {
			messageService.delete(message);
			if (uA.getAuthorities().contains(ad)){
				result = new ModelAndView("redirect:/folder/administrator/list.do");}
				else if (uA.getAuthorities().contains(nut)){
					result = new ModelAndView("redirect:/folder/nutritionist/list.do");}
				else if (uA.getAuthorities().contains(us)){
					result = new ModelAndView("redirect:/folder/user/list.do");};
		} catch (Throwable oops) {
			result = createEditModelAndView(message, "message.commit.error");

		}
		return result;
	}
	@RequestMapping(value = "/send", method = RequestMethod.POST, params = "send")
	public ModelAndView send(@Valid Message message, BindingResult binding) {
		ModelAndView result=null;
		Authority ad = new Authority();
		Authority us = new Authority();
		Authority nut = new Authority();
		ad.setAuthority("ADMIN");
		us.setAuthority("USER");
		nut.setAuthority("NUTRITIONIST");
		UserAccount uA=LoginService.getPrincipal();
		Date d= new Date();
		Actor s = actorService.findByActorAccountId(uA.getId());
		Folder fs = new Folder();
		Folder fr= new Folder();
		Actor r = message.getRecipientActor();
		Message ms= messageService.create();
		ms.setBody(message.getBody().substring(1));
		ms.setSubject(message.getSubject().substring(1));
		ms.setRecipientActor(message.getRecipientActor());
		ms.setPriority(message.getPriority());
		ms.setSenderActor(s);
		ms.setMomentSent(d);
		Message mr= messageService.create();
		mr.setBody(message.getBody().substring(1));
		mr.setSubject(message.getSubject().substring(1));
		mr.setRecipientActor(message.getRecipientActor());
		mr.setPriority(message.getPriority());
		mr.setSenderActor(s);
		mr.setMomentSent(d);
		for(Folder f:s.getFolders()){
			if(f.getName().equalsIgnoreCase("outbox")){
				fs=f;
				break;
			}}
		Collection<ConfigurationSystem> configurationSystemc;
		ConfigurationSystem cS=null;

		configurationSystemc = configurationSystemService.findAll();
		for(ConfigurationSystem c:configurationSystemc){
			cS=c;
		}
		Boolean spam=false;
		for(String st: cS.getSpamWords()){
			if(mr.getSubject().contains(st)||mr.getBody().contains(st)){
				spam=true;
			}
		}
		if(spam==true){
			for(Folder f2:r.getFolders()){
				if(f2.getName().equalsIgnoreCase("spambox")){
						fr=f2;
						break;}
					}
		}else{
		for(Folder f2:r.getFolders()){
			if(f2.getName().equalsIgnoreCase("inbox")){
					fr=f2;
					break;}
				}
		}
		ms.setFolder(fs);
		fs.getMessages().add(ms);
		mr.setFolder(fr);
		fr.getMessages().add(mr);
		messageService.save(ms);
		messageService.save(mr);
		folderService.saveSend(fs);
		folderService.saveSend(fr);
		if (uA.getAuthorities().contains(ad)){
			result = new ModelAndView("redirect:/folder/administrator/list.do");}
			else if (uA.getAuthorities().contains(nut)){
				result = new ModelAndView("redirect:/folder/nutritionist/list.do");}
			else if (uA.getAuthorities().contains(us)){
				result = new ModelAndView("redirect:/folder/user/list.do");}
	
		if (binding.hasErrors()) {
			result = createSendModelAndView(message);
		} else {
			try {
				
			} catch (Throwable oops) {
				result = createEditModelAndView(message,
						"message.commit.error");
			}
		}
		return result;
	}
	// Display messages in a contest
	
	@RequestMapping(value="/display", method=RequestMethod.GET)
	public ModelAndView displayMessages(@RequestParam int folderId){
		
		ModelAndView resul;
		Collection<Message> messages;
		
		messages = messageService.findByFolder(folderId);
		resul = new ModelAndView("message/display");
		resul.addObject("messages", messages);
		resul.addObject("requestURI", "message/display.do");
		
		return resul;
	}
	// Ancillary methods------------------------------

		protected ModelAndView createEditModelAndView(Message message) {
			ModelAndView result;

			result = createEditModelAndView(message, null);
			return result;
		}
			
		protected ModelAndView createEditModelAndView(Message message1,
					String message) {
				ModelAndView result;
				Collection<Folder> res;
				Actor a=new Actor();
				Authority ad = new Authority();
				Authority us = new Authority();
				Authority nut = new Authority();
				ad.setAuthority("ADMIN");
				us.setAuthority("USER");
				nut.setAuthority("NUTRITIONIST");
				UserAccount uA=LoginService.getPrincipal();
				if (uA.getAuthorities().contains(ad)){
				a = administratorService.findOneByuserAccountId(uA.getId());}
				else if (uA.getAuthorities().contains(nut)){
				a=nutritionistService.findOneByUserAccountId(uA.getId());}
				else if (uA.getAuthorities().contains(us)){
					a=userService.findOneByuserAccountId(uA.getId());}
				
				res=a.getFolders();
				result = new ModelAndView("message/edit");
				result.addObject("ActorMessage", message1);
				result.addObject("message", message);
				result.addObject("folders", res);

				return result;
			}
		protected ModelAndView createSendModelAndView(Message message) {
			ModelAndView result;

			result = createSendModelAndView(message, null);
			return result;
		}
		protected ModelAndView createSendModelAndView(Message message1,
				String message) {
			ModelAndView result;
			Collection<Actor> res=actorService.findAll();
			
			result = new ModelAndView("message/send");
			result.addObject("ActorMessage", message1);
			result.addObject("message", message);
			result.addObject("recipientActors", res);

			return result;
		}
		
			
		}


