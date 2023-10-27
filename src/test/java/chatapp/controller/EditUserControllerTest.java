package chatapp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

import chatapp.utils.Constants;

@Test
public void testEditView() {
HttpSession session = mock(HttpSession.class);
ModelAndView expectedModelAndView = new ModelAndView(Constants.Directory.CONVERSATION + Constants.VIEWS.USER_EDIT, Constants.Attribute.USER,
session.getAttribute(Constants.Attribute.CURRENT_USER)).addObject(Constants.Attribute.MESSAGE, "test message")
.addObject(Constants.Attribute.GENDERS, Gender.values());
EditUserController controller = new EditUserController();
ModelAndView actualModelAndView = controller.editView("test message", session);
assertEquals(expectedModelAndView.getViewName(), actualModelAndView.getViewName());
assertEquals(expectedModelAndView.getModel().get(Constants.Attribute.USER), actualModelAndView.getModel().get(Constants.Attribute.USER));
assertEquals(expectedModelAndView.getModel().get(Constants.Attribute.MESSAGE), actualModelAndView.getModel().get(Constants.Attribute.MESSAGE));
assertEquals(expectedModelAndView.getModel().get(Constants.Attribute.GENDERS), actualModelAndView.getModel().get(Constants.Attribute.GENDERS));
}

@Test
public void testEditUserSuccessfulRegistration() throws Exception {
HttpSession session = mock(HttpSession.class);
User user = new User();
user.setUsername("testuser");
user.setPassword("testpassword");
user.setGender(Gender.MALE);
user.setDob(new Date());
UserRegistrationImpl registration = mock(UserRegistrationImpl.class);
when(registration.register(user, UserType.REGISTERED)).thenReturn(user);
EditUserController controller = new EditUserController();
controller.registration = registration;
ModelAndView expectedModelAndView = new ModelAndView(Constants.Directory.CONVERSATION + Constants.VIEWS.USER_HOME,
Constants.Attribute.MESSAGE, Constants.SuccessMessage.SUCCESFUL_UPDATION);
expectedModelAndView.addObject(Constants.Attribute.USER, user);
ModelAndView actualModelAndView = controller.editUser(user, session);
assertEquals(expectedModelAndView.getViewName(), actualModelAndView.getViewName());
assertEquals(expectedModelAndView.getModel().get(Constants.Attribute.MESSAGE), actualModelAndView.getModel().get(Constants.Attribute.MESSAGE));
assertEquals(expectedModelAndView.getModel().get(Constants.Attribute.USER), actualModelAndView.getModel().get(Constants.Attribute.USER));
}

@Test
public void testEditUserRetryableException() throws Exception {
HttpSession session = mock(HttpSession.class);
User user = new User();
user.setUsername("testuser");
user.setPassword("testpassword");
user.setGender(Gender.MALE);
user.setDob(new Date());
UserRegistrationImpl registration = mock(UserRegistrationImpl.class);
doThrow(RetryableException.class).when(registration).register(user, UserType.REGISTERED);
EditUserController controller = new EditUserController();
controller.registration = registration;
ModelAndView expectedModelAndView = new ModelAndView(Constants.Directory.CONVERSATION + Constants.VIEWS.USER_EDIT,
Constants.Attribute.USER, session.getAttribute(Constants.Attribute.CURRENT_USER)).addObject(Constants.Attribute.MESSAGE,
Constants.ErrorsMessage.RETRYABLE_EXCEPTION_MESSAGE).addObject(Constants.Attribute.GENDERS, Gender.values());
ModelAndView actualModelAndView = controller.editUser(user, session);
assertEquals(expectedModelAndView.getViewName(), actualModelAndView.getViewName());
assertEquals(expectedModelAndView.getModel().get(Constants.Attribute.MESSAGE), actualModelAndView.getModel().get(Constants.Attribute.MESSAGE));
}

@Test
public void testEditUserNonRetryableException() throws Exception {
HttpSession session = mock(HttpSession.class);
User user = new User();
user.setUsername("testuser");
user.setPassword("testpassword");
user.setGender(Gender.MALE);
user.setDob(new Date());
UserRegistrationImpl registration = mock(UserRegistrationImpl.class);
doThrow(NonRetryableException.class).when(registration).register(user, UserType.REGISTERED);
EditUserController controller = new EditUserController();
controller.registration = registration;
ModelAndView expectedModelAndView = new ModelAndView(Constants.Directory.CONVERSATION + Constants.VIEWS.USER_EDIT,
Constants.Attribute.USER, session.getAttribute(Constants.Attribute.CURRENT_USER)).addObject(Constants.Attribute.MESSAGE,
Constants.ErrorsMessage.NON_RETRYABLE_EXCEPTION_MESSAGE).addObject(Constants.Attribute.GENDERS, Gender.values());
ModelAndView actualModelAndView = controller.editUser(user, session);
assertEquals(expectedModelAndView.getViewName(), actualModelAndView.getViewName());
assertEquals(expectedModelAndView.getModel().get(Constants.Attribute.MESSAGE), actualModelAndView.getModel().get(Constants.Attribute.MESSAGE));
}
