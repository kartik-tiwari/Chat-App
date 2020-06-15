package spring.chatapp.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import spring.chatapp.user.component.Authentication;
import spring.chatapp.user.data.User;


@Controller
public class ProfileController {
	@Autowired
	Authentication authenticate;
	
	@RequestMapping("/profile/{userName}")
	public ModelAndView profile(@PathVariable String userName) {
		
		User user=authenticate.getUserbyUserName(userName);
		
		if(user!=null) {
			return new ModelAndView("conversation/profileView", "targetUser", user);
		}
		else {
			return new ModelAndView("conversation/profileView");
		}
		
	}
}
