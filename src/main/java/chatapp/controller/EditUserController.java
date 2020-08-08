package chatapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import chatapp.component.UserRegistrationImpl;
import chatapp.enumerations.Gender;
import chatapp.enumerations.UserType;
import chatapp.exceptions.InvalidInputException;
import chatapp.exceptions.NonRetryableException;
import chatapp.exceptions.RetryableException;
import chatapp.model.User;
import chatapp.utils.Constants;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class EditUserController {
	@Autowired
	UserRegistrationImpl registration;

	// Class constatnts
	private final String URL_EDIT_VIEW = "/editView";
	private final String URL_EDIT_USER = "/editUser";

	/*
	 * Opens Edit user view with given message displayed
	 * 
	 * @param message String
	 * 
	 * @param session HttpSession
	 */
	@RequestMapping(value = URL_EDIT_VIEW)
	public ModelAndView editView(String message, HttpSession session) {
		return new ModelAndView(Constants.Directory.CONVERSATION + Constants.VIEWS.USER_EDIT, Constants.Attribute.USER,
				session.getAttribute(Constants.Attribute.CURRENT_USER)).addObject(Constants.Attribute.MESSAGE, message)
						.addObject(Constants.Attribute.GENDERS, Gender.values());
	}

	@RequestMapping(value = URL_EDIT_USER, method = RequestMethod.POST)
	public ModelAndView editUser(@ModelAttribute(Constants.Attribute.USER) User user, HttpSession session) {
		try {
			registration.register(user, UserType.REGISTERED);
			session.setAttribute(Constants.Attribute.CURRENT_USER, user);
			return new ModelAndView(Constants.Directory.CONVERSATION + Constants.VIEWS.USER_HOME,
					Constants.Attribute.MESSAGE, Constants.SuccessMessage.SUCCESFUL_UPDATION);
		} catch (RetryableException exception) {
			log.error(exception.getMessage());
			return editView(Constants.ErrorsMessage.RETRYABLE_EXCEPTION_MESSAGE, session);

		} catch (NonRetryableException exception) {
			log.error(exception.getMessage());
			return editView(Constants.ErrorsMessage.NON_RETRYABLE_EXCEPTION_MESSAGE, session);

		} catch (InvalidInputException exception) {
			log.error(exception.getMessage());
			return editView(exception.getMessage(), session);

		} catch (Exception exception) {
			log.error(exception.getMessage());
			return editView(exception.getMessage(), session);
		}
	}

	@InitBinder
	private void dateBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);

	}
}
