package chatapp.user.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import chatapp.user.component.Authentication;
import chatapp.utils.Result;


@Controller
public class LoginController {
	
	@Autowired
	Authentication authentication;
	
	@RequestMapping(value ="/loginView",method = RequestMethod.GET)
	public String loginView() {
		return "landing/loginView";
	}
	
	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public ModelAndView loginUser(HttpServletRequest request,  String userName, String password) {
		
		Result loginResult = authentication.authenticate(userName, password);
		
		if(loginResult.isSuccess()) {
			
			HttpSession session=request.getSession(true);
			session.setAttribute("currentUser", loginResult.getUser());
			return new ModelAndView("conversation/homeView");
		
		}
		else {
			
			return new ModelAndView("landing/loginView","message",loginResult.getMessage());
		}
	}
}
