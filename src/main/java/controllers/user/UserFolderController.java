package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.UserService;

import controllers.AbstractController;
import domain.Folder;
import domain.User;

@Controller
@RequestMapping("/user/folders")
public class UserFolderController extends AbstractController{
	
	//Services-----------------------------
	
	@Autowired
	private UserService userService;
		
	//Constructor--------------------------
		
	//Listing------------------------------
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		
		ModelAndView result;
		User user;
		Collection<Folder> folders;
		
		user = userService.findOneByuserAccountId(LoginService.getPrincipal().getId());;
		folders= user.getFolders();
		result = new ModelAndView("folder/list");
		result.addObject("folders",folders);
		result.addObject("requestURI", "folder/list.do");
		
		return result;
	}

}
