package chatapp.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import chatapp.user.component.Authentication;
import chatapp.user.data.User;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class ProfileController {
	@Autowired
	Authentication authenticate;
	
	@RequestMapping("/profile/{userName}")
	public ModelAndView profile(@PathVariable String userName) {
		
		User user;
		try {
			user = authenticate.getUserbyUserName(userName);
			if(user!=null) {
				return new ModelAndView("conversation/profileView", "targetUser", user);
			}
			else {
				return new ModelAndView("conversation/profileView");
			}
		} 
		catch (Exception exception) {
			log.error(exception.getMessage());
			return new ModelAndView("conversation/profileView", "targetUser", null);
		}
		
		
		
	}
}
