package chatapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

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
public class RegisterController {

	@Autowired
	UserRegistrationImpl registration;

	// Class constatnts
	private final String URL_REGISTER_VIEW = "/registerView";
	private final String URL_REGISTER_USER = "/register";

	@RequestMapping(value = URL_REGISTER_VIEW, method = RequestMethod.GET)
	public ModelAndView registerView(String message) {
		return new ModelAndView(Constants.Directory.LANDING+Constants.VIEWS.USER_REGISTER, Constants.Attribute.USER, new User())
				.addObject(Constants.Attribute.MESSAGE, message)
				.addObject(Constants.Attribute.GENDERS, Gender.values());
	}

	@RequestMapping(value = URL_REGISTER_USER, method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute(Constants.Attribute.USER) User user) {

		try {
			registration.register(user, UserType.UNREGISTERED);
			return new ModelAndView(Constants.Directory.LANDING+Constants.VIEWS.USER_LOGIN, Constants.Attribute.MESSAGE,
					Constants.SuccessMessage.SUCCESFUL_REGISTRATION);
		} catch (RetryableException exception) {
			log.error(exception.getMessage());
			return registerView(Constants.ErrorsMessage.RETRYABLE_EXCEPTION_MESSAGE);

		} catch (NonRetryableException exception) {
			log.error(exception.getMessage());
			return registerView(Constants.ErrorsMessage.NON_RETRYABLE_EXCEPTION_MESSAGE);

		} catch (InvalidInputException exception) {
			log.error(exception.getMessage());
			return registerView(exception.getMessage());

		} catch (Exception exception) {
			log.error(exception.getMessage());
			return registerView(exception.getMessage());
		}
	}

	// Binding HTML date to Java Date type
	@InitBinder
	private void dateBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);

	}
}
