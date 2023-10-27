package chatapp.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;
import chatapp.component.UserAuthenticationImpl;
import chatapp.exceptions.InvalidInputException;
import chatapp.exceptions.NonRetryableException;
import chatapp.exceptions.RetryableException;
import chatapp.model.User;
import chatapp.utils.Constants;
public class DeleteUserTest {
  @Mock private UserAuthenticationImpl authentication;
  @Mock private HttpServletRequest request;
  @Mock private HttpSession session;
  private DeleteUser deleteUser;
  @BeforeEach public void setup() {
    MockitoAnnotations.initMocks(this);
    deleteUser = new DeleteUser();
  }
  @Test public void testHomepage() {
    String result = deleteUser.homepage();
    assertEquals(Constants.Directory.LANDING + Constants.VIEWS.USER_WELCOME, result);
  }
  @Test public void testLoginView() {
    ModelAndView modelAndView = deleteUser.loginView("test message");
    assertEquals(Constants.Directory.LANDING + Constants.VIEWS.USER_LOGIN, modelAndView.getViewName());
    assertEquals("test message", modelAndView.getModel().get(Constants.Attribute.MESSAGE));
  }
  @Test public void testLoginUserSuccessful() throws Exception {
    User user = new User();
    user.setUserName("testUser");
    when(authentication.authenticate("testUser", "testPassword")).thenReturn(user);
    when(request.getSession(true)).thenReturn(session);
    ModelAndView modelAndView = deleteUser.loginUser(request, "testUser", "testPassword");
    verify(session).setAttribute(Constants.Attribute.CURRENT_USER, user);
    assertEquals(Constants.Directory.CONVERSATION + Constants.VIEWS.USER_HOME, modelAndView.getViewName());
  }
  @Test public void testLoginUserNonRetryableException() throws Exception {
    when(authentication.authenticate("testUser", "testPassword")).thenThrow(new NonRetryableException("test exception"));
    ModelAndView modelAndView = deleteUser.loginUser(request, "testUser", "testPassword");
    assertEquals(Constants.Directory.LANDING + Constants.VIEWS.USER_LOGIN, modelAndView.getViewName());
    assertEquals(Constants.ErrorsMessage.NON_RETRYABLE_EXCEPTION_MESSAGE, modelAndView.getModel().get(Constants.Attribute.MESSAGE));
  }
  @Test public void testLoginUserRetryableException() throws Exception {
    when(authentication.authenticate("testUser", "testPassword")).thenThrow(new RetryableException("test exception"));
    ModelAndView modelAndView = deleteUser.loginUser(request, "testUser", "testPassword");
    assertEquals(Constants.Directory.LANDING + Constants.VIEWS.USER_LOGIN, modelAndView.getViewName());
    assertEquals(Constants.ErrorsMessage.RETRYABLE_EXCEPTION_MESSAGE, modelAndView.getModel().get(Constants.Attribute.MESSAGE));
  }
  @Test public void testLoginUserInvalidInputException() throws Exception {
    when(authentication.authenticate("testUser", "testPassword")).thenThrow(new InvalidInputException("test exception"));
    ModelAndView modelAndView = deleteUser.loginUser(request, "testUser", "testPassword");
    assertEquals(Constants.Directory.LANDING + Constants.VIEWS.USER_LOGIN, modelAndView.getViewName());
    assertEquals("test exception", modelAndView.getModel().get(Constants.Attribute.MESSAGE));
  }
  @Test public void testLoginUserGenericException() throws Exception {
    when(authentication.authenticate("testUser", "testPassword")).thenThrow(new Exception("test exception"));
    ModelAndView modelAndView = deleteUser.loginUser(request, "testUser", "testPassword");
    assertEquals(Constants.Directory.LANDING + Constants.VIEWS.USER_LOGIN, modelAndView.getViewName());
    assertEquals("test exception", modelAndView.getModel().get(Constants.Attribute.MESSAGE));
  }
}
