import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    @Test
    public void testCheckUserName_Success() {
        Login user = new Login();
        user.username = "kyl_1";
        assertTrue(user.checkUserName());
    }

    @Test
    public void testCheckUserName_Failure() {
        Login user = new Login();
        user.username = "kyleusernameextended";
        assertFalse(user.checkUserName());
    }

    @Test
    public void testCheckPasswordComplexity_Success() {
        Login user = new Login();
        user.password = "Abcdef1@";
        assertTrue(user.checkPasswordComplexity());
    }

    @Test
    public void testCheckPasswordComplexity_Failure() {
        Login user = new Login();
        user.password = "password";
        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testCheckCellPhoneNumber_Success() {
        Login user = new Login();
        user.cellPhoneNumber = "+27718693002";
        assertTrue(user.checkCellPhoneNumber());
    }

    @Test
    public void testCheckCellPhoneNumber_Failure() {
        Login user = new Login();
        user.cellPhoneNumber = "08575975889";
        assertFalse(user.checkCellPhoneNumber());
    }

    @Test
    public void testLoginUser_Success() {
        Login user = new Login();
        user.username = "kyl_1";
        user.password = "Abcdef1@";
        assertTrue(user.loginUser("kyl_1", "Abcdef1@"));
    }

    @Test
    public void testLoginUser_Failure() {
        Login user = new Login();
        user.username = "kyl_1";
        user.password = "Abcdef1@";
        assertFalse(user.loginUser("wrong_user", "wrong_pass"));
    }
}