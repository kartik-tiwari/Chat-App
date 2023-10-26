package chatapp.controller;

import chatapp.component.UserRegistrationImpl;
import chatapp.utils.Constants;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Log4j
@Controller
public class DeleteController {

	@Autowired
	private UserRegistrationImpl registration;

	// class constants
	private static final String URL_DELETE_USER = "/delete";


	/*
	 * Opens Welcome view
	 */
	@RequestMapping(value = URL_DELETE_USER, method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute(Constants.Attribute.USER) String userName) {
		try {
			registration.delete(userName);
			return new ModelAndView(Constants.Directory.LANDING+Constants.VIEWS.USER_WELCOME);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
