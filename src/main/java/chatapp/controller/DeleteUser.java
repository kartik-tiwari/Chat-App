package chatapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import chatapp.component.UserAuthenticationImpl;
import chatapp.exceptions.InvalidInputException;
import chatapp.exceptions.NonRetryableException;
import chatapp.exceptions.RetryableException;
import chatapp.model.User;
import chatapp.utils.Constants;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class DeleteUser {

	@Autowired
	UserAuthenticationImpl authentication;

	// Class constants
	private final String URL_LOGIN_VIEW = "/loginView";
	private final String URL_LOGIN_USER = "/loginUser";
	private final String URL_LANDING_HOME = "/home";

	/*
	 * Opens Welcome view
	 */
	@RequestMapping(URL_LANDING_HOME)
	public String homepage() {
		return Constants.Directory.LANDING + Constants.VIEWS.USER_WELCOME;
	}

	/*
	 * Opens login user view with given message displayed
	 * 
	 * @param message String
	 * 
	 * @param session HttpSession
	 */
	@RequestMapping(value = URL_LOGIN_VIEW, method = RequestMethod.GET)
	public ModelAndView loginView(String message) {
		return new ModelAndView(Constants.Directory.LANDING + Constants.VIEWS.USER_LOGIN,
				Constants.Attribute.MESSAGE, message);
	}

	@RequestMapping(value = URL_LOGIN_USER, method = RequestMethod.POST)
	public ModelAndView loginUser(HttpServletRequest request, final String userName, final String password) {

		try {
			User user = authentication.authenticate(userName, password);
			HttpSession session = request.getSession(true);
			session.setAttribute(Constants.Attribute.CURRENT_USER, user);
			return new ModelAndView(Constants.Directory.CONVERSATION + Constants.VIEWS.USER_HOME);

		} catch (NonRetryableException exception) {
			exception.printStackTrace();
			log.error(exception.getMessage());
			return loginView(Constants.ErrorsMessage.NON_RETRYABLE_EXCEPTION_MESSAGE);
		} catch (RetryableException exception) {
			exception.printStackTrace();
			log.error(exception.getMessage());
			return loginView(Constants.ErrorsMessage.RETRYABLE_EXCEPTION_MESSAGE);

		} catch (InvalidInputException exception) {
			exception.printStackTrace();
			log.error(exception.getMessage());
			return loginView(exception.getMessage());
		} catch (Exception exception) {
			exception.printStackTrace();
			log.error(exception.getMessage());
			return loginView(exception.getMessage());
		}

	}
}
