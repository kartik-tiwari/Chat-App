package spring.chatapp.user.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.chatapp.user.component.Registration;
import spring.chatapp.user.component.Result;
import spring.chatapp.user.data.Gender;
import spring.chatapp.user.data.User;

@Controller
public class RegisterController {

	@Autowired
	Registration registration;
	
	@RequestMapping(value = "/registerView", method = RequestMethod.GET)
	public ModelAndView registerView() {
		
		return new ModelAndView("landing/registerView","command",new User())
				.addObject("Genders", Gender.values());
	}
	
	
	@RequestMapping(value = "/register",method=RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("user")User user) {
		
		Result registerResult=registration.register(user);
		if(registerResult.isSuccess()) {
			
			return new ModelAndView("landing/loginView","message",registerResult.getMessage());
					
		}
		else {
			
			return new ModelAndView("landing/registerView","message",registerResult.getMessage())
					.addObject("command", new User())
					.addObject("Genders", Gender.values());
		}
		
				
	}   
    
	
	//Binding HTML date to Java Date type
	@InitBinder
	private void dateBinder(WebDataBinder binder) {
		
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
	    binder.registerCustomEditor(Date.class, editor);
	    
	}
}
