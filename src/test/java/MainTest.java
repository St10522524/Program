import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    // ==========================================
    // PART 1: LOGIN TESTS
    // ==========================================

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

    // ==========================================
    // PART 2: MESSAGE TESTS
    // ==========================================

    @Test
    public void testCheckMessageLength_Success() {
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testCheckMessageLength_Failure() {
        String longText = "A".repeat(260);
        Message msg = new Message(0, "+27718693002", longText);
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", msg.checkMessageLength());
    }

    @Test
    public void testCheckRecipientCell_Success() {
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientCell_Failure() {
        Message msg = new Message(0, "08575975889", "Hi Keegan, did you receive the payment?");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    @Test
    public void testCreateMessageHash() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String hash = msg.createMessageHash();
        assertNotNull(hash);
        assertTrue(hash.contains("HITONIGHT"));
    }

    @Test
    public void testCheckMessageID() {
        Message msg = new Message(0, "+27718693002", "Test message");
        assertTrue(msg.checkMessageID());
        assertNotNull(msg.getMessageID());
        assertTrue(msg.getMessageID().length() <= 10);
    }

    @Test
    public void testSentMessageOptions() {
        Message msg = new Message(0, "+27718693002", "Test message");
        assertEquals("Message successfully sent.", msg.SentMessage(1));
        assertEquals("Press 0 to delete the message", msg.SentMessage(2));
        assertEquals("Message successfully stored.", msg.SentMessage(3));
    }
}