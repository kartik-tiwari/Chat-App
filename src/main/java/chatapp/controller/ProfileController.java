package chatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import chatapp.component.UserMangement;
import chatapp.exceptions.DependencyFailureException;
import chatapp.exceptions.InternalException;
import chatapp.exceptions.InvalidInputException;
import chatapp.model.User;
import chatapp.utils.Constants;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class ProfileController {
	@Autowired
	UserMangement userManagement;

	/*
	 * Opens Profile View of given userName
	 * 
	 * @param userName String
	 */
	@RequestMapping("/profile/{userName}")
	public ModelAndView profile(@PathVariable final String userName) {

		try {
			User user = userManagement.getUserByUserName(userName);
			return new ModelAndView(Constants.Directory.CONVERSATION + Constants.VIEWS.USER_PROFILE,
					Constants.Attribute.TARGET_USER, user);
		} catch (InternalException exception) {
			log.error(exception.getMessage());
			return new ModelAndView(Constants.Directory.CONVERSATION + Constants.VIEWS.USER_HOME)
					.addObject(Constants.Attribute.MESSAGE, Constants.ErrorsMessage.NON_RETRYABLE_EXCEPTION_MESSAGE);

		} catch (DependencyFailureException exception) {
			log.error(exception.getMessage());
			return new ModelAndView(Constants.Directory.CONVERSATION + Constants.VIEWS.USER_HOME)
					.addObject(Constants.Attribute.MESSAGE, Constants.ErrorsMessage.RETRYABLE_EXCEPTION_MESSAGE);
		} catch (InvalidInputException exception) {
			log.error(exception.getMessage());
			return new ModelAndView(Constants.Directory.CONVERSATION + Constants.VIEWS.USER_HOME)
					.addObject(Constants.Attribute.MESSAGE, exception.getMessage());
		} catch (Exception exception) {
			log.error(exception.getMessage());
			return new ModelAndView(Constants.Directory.CONVERSATION + Constants.VIEWS.USER_HOME);
		}
	}

	@RequestMapping(value = "userHome")
	public ModelAndView userHome() {
		return new ModelAndView(Constants.Directory.CONVERSATION + Constants.VIEWS.USER_HOME);
	}
}
