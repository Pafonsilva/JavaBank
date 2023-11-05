import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.controller.LoginController;
import org.academiadecodigo.javabank.services.AuthService;
import org.academiadecodigo.javabank.view.LoginView;
import org.academiadecodigo.javabank.view.View;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class LoginControllerTest {

    private LoginController loginController;

    private AuthService authService;

    private View loginView;

    private Controller nextController;

    @Before
    public void setup() {

        loginController = new LoginController();
        authService = Mockito.mock(AuthService.class);
        loginView = Mockito.mock(LoginView.class);
        nextController = Mockito.mock(Controller.class);

        loginController.setNextController(nextController);
        loginController.setAuthService(authService);
        loginController.setView(loginView);
    }

    @Test
    public void initTest() {

        loginController.init();
        Mockito.verify(loginView).show();
    }

    @Test
    public void onLoginTest() {

        int fakeId = 1234;

        Mockito.when(authService.authenticate(fakeId)).thenReturn(true);

        loginController.onLogin(fakeId);

        Mockito.verify(authService).authenticate(fakeId);

        Mockito.verify(nextController).init();

        Mockito.verify(loginView, Mockito.never()).show();

    }
}
