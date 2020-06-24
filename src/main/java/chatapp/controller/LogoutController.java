package chatapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import chatapp.utils.Constants;

@Controller
public class LogoutController {

	// Class constants
	private final String URL_LOGOUT_USR = "/logout";

	@RequestMapping(value = URL_LOGOUT_USR)
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		return new ModelAndView(Constants.Directory.LANDING+Constants.VIEWS.USER_WELCOME);

	}
}
