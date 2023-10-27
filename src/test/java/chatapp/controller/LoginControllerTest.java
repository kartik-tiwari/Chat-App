package chatapp.controller;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import chatapp.component.UserAuthenticationImpl;
import chatapp.model.User;
import chatapp.utils.Constants;

public class LoginControllerTest {

    @Mock
    private UserAuthenticationImpl authentication;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @InjectMocks
    private LoginController controller;

    private final String USERNAME = "testuser";
    private final String PASSWORD = "testpassword";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(request.getSession(true)).thenReturn(session);
    }

    @Test
    public void testLoginUser() throws Exception {
        User user = new User();
        user.setUserName(USERNAME);
        user.setPassword(PASSWORD);

        when(authentication.authenticate(USERNAME, PASSWORD)).thenReturn(user);

        ModelAndView mav = controller.loginUser(request, USERNAME, PASSWORD);

        verify(authentication, times(1)).authenticate(USERNAME, PASSWORD);
        verify(session, times(1)).setAttribute(Constants.Attribute.CURRENT_USER, user);

        assert(mav.getViewName().equals(Constants.Directory.CONVERSATION + Constants.VIEWS.USER_HOME));
    }
}