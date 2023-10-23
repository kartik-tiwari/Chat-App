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

public class LogoutControllerTest {

    @Mock
    private HttpSession session;

    private LogoutController logoutController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        logoutController = new LogoutController();
    }

    @Test
    public void testLogout() {
        ModelAndView modelAndView = logoutController.logout(session);

        verify(session).invalidate();
        assertEquals(Constants.Directory.LANDING+Constants.VIEWS.USER_WELCOME, modelAndView.getViewName());
    }
}
