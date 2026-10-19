import java.util.Random;
import java.util.Scanner;


class Login {
    String username;
    String password;
    String cellPhoneNumber;
    String firstName;
    String lastName;

    // Checks if username contains '_' and is <= 5 characters
    public boolean checkUserName() {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    // Checks password complexity requirements
    public boolean checkPasswordComplexity() {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) hasCapital = true;
            if (Character.isDigit(ch)) hasNumber = true;
            if (!Character.isLetterOrDigit(ch)) hasSpecial = true;
        }

        return hasCapital && hasNumber && hasSpecial;
    }

    // Checks if cellphone number starts with international code (+27) and has correct length
    public boolean checkCellPhoneNumber() {
        return cellPhoneNumber != null && cellPhoneNumber.startsWith("+27") && cellPhoneNumber.length() == 12;
    }

    // Handles registration status messages
    public String registerUser() {
        String userMsg = checkUserName() 
            ? "Username successfully captured." 
            : "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";

        String passMsg = checkPasswordComplexity() 
            ? "Password successfully captured." 
            : "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";

        String cellMsg = checkCellPhoneNumber() 
            ? "Cell phone number successfully added." 
            : "Cell phone number incorrectly formatted or does not contain international code.";

        if (checkUserName() && checkPasswordComplexity() && checkCellPhoneNumber()) {
            return userMsg + "\n" + passMsg + "\n" + cellMsg;
        } else {
            return (!checkUserName() ? userMsg : (!checkPasswordComplexity() ? passMsg : cellMsg));
        }
    }

    // Verifies user login credentials
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return this.username != null && this.password != null 
            && this.username.equals(enteredUsername) 
            && this.password.equals(enteredPassword);
    }

    // Returns formatted login welcome message
    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Welcome " + firstName + " " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}

// ==========================================
// 2. MESSAGE CLASS (Part 2)
// ==========================================
final class Message {
    private final String messageID;
    private final int numMessagesSent;
    private final String recipient;
    private final String messageText;
    private final String messageHash;

    // Constructor
    public Message(int messageNumber, String recipient, String messageText) {
        this.numMessagesSent = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // Helper method to generate a random 10-digit Message ID
    private String generateMessageID() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    // Ensures Message ID is not more than 10 characters
    public boolean checkMessageID() {
        return this.messageID != null && this.messageID.length() <= 10;
    }

    // Checks recipient cell number format
    public String checkRecipientCell() {
        if (recipient != null && recipient.startsWith("+") && recipient.length() <= 12) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Validates message length (max 250 characters)
    public String checkMessageLength() {
        if (messageText != null && messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = messageText.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }

    // Generates the Message Hash format: First2DigitsID:MessageNum:FIRSTANDLASTWORD
    public String createMessageHash() {
        if (messageID == null || messageID.length() < 2 || messageText == null || messageText.trim().isEmpty()) {
            return "";
        }

        String firstTwoID = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        String combinedWords = (firstWord + lastWord).toUpperCase();
        return firstTwoID + ":" + numMessagesSent + ":" + combinedWords;
    }

    // Handles option selection (1: Send, 2: Disregard, 3: Store)
    public String SentMessage(int choice) {
        return switch (choice) {
            case 1 -> "Message successfully sent.";
            case 2 -> "Press 0 to delete the message";
            case 3 -> "Message successfully stored.";
            default -> "Invalid option selected.";
        };
    }

    // Formats and returns full details of the message
    public String printMessages() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + messageText;
    }

    // Placeholder method for storing message in JSON
    public void storeMessage() {
        // Research how to store messages in JSON
    }

    // Getters
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
}

// ==========================================
// 3. MAIN APPLICATION CLASS
// ==========================================
public class Main {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Login user = new Login();
            
            // --- PART 1: User Registration ---
            System.out.println("==================================");
            System.out.println("       USER REGISTRATION");
            System.out.println("==================================");
            System.out.print("Enter first name: ");
            user.firstName = sc.nextLine();
            
            System.out.print("Enter last name: ");
            user.lastName = sc.nextLine();
            
            System.out.print("Enter username: ");
            user.username = sc.nextLine();
            
            System.out.print("Enter password: ");
            user.password = sc.nextLine();
            
            System.out.print("Enter cell phone number (+27...): ");
            user.cellPhoneNumber = sc.nextLine();
            
            System.out.println("\n" + user.registerUser());
            
            // --- PART 1: User Login ---
            System.out.println("\n==================================");
            System.out.println("           USER LOGIN");
            System.out.println("==================================");
            System.out.print("Enter username: ");
            String loginUser = sc.nextLine();
            
            System.out.print("Enter password: ");
            String loginPass = sc.nextLine();
            
            boolean isLoggedIn = user.loginUser(loginUser, loginPass);
            System.out.println("\n" + user.returnLoginStatus(isLoggedIn));
            
            // --- PART 2: QuickChat Application ---
            if (isLoggedIn) {
                System.out.println("\n==================================");
                System.out.println("     Welcome to QuickChat.");
                System.out.println("==================================");
                
                boolean exit = false;
                int totalSentCount = 0;
                
                while (!exit) {
                    System.out.println("\nPlease choose an option:");
                    System.out.println("1) Send Messages");
                    System.out.println("2) Show recently sent messages");
                    System.out.println("3) Quit");
                    System.out.print("Option: ");
                    
                    int menuOption = sc.nextInt();
                    sc.nextLine(); // consume newline
                    
                    switch (menuOption) {
                        case 1 -> {
                            System.out.print("\nHow many messages would you like to enter? ");
                            int numMessages = sc.nextInt();
                            sc.nextLine(); // consume newline
                            
                            for (int i = 0; i < numMessages; i++) {
                                System.out.println("\n--- Message " + (i + 1) + " of " + numMessages + " ---");
                                
                                System.out.print("Enter Recipient Number: ");
                                String recipient = sc.nextLine();
                                
                                System.out.print("Enter Message: ");
                                String msgText = sc.nextLine();
                                
                                // Instantiate Message object
                                Message msg = new Message(i, recipient, msgText);
                                
                                // Validate length
                                String lengthStatus = msg.checkMessageLength();
                                if (!lengthStatus.equals("Message ready to send.")) {
                                    System.out.println(lengthStatus);
                                    i--; // Re-prompt for this slot if invalid length
                                    continue;
                                }
                                
                                System.out.println("\nChoose an action:");
                                System.out.println("1) Send Message");
                                System.out.println("2) Disregard Message");
                                System.out.println("3) Store Message to send later");
                                System.out.print("Selection: ");
                                int sendOption = sc.nextInt();
                                sc.nextLine(); // consume newline
                                
                                String resultMsg = msg.SentMessage(sendOption);
                                System.out.println("\n" + resultMsg);
                                
                                if (sendOption == 1) {
                                    totalSentCount++;
                                    System.out.println("\n--- Message Details ---");
                                    System.out.println(msg.printMessages());
                                }
                            }
                            
                            System.out.println("\nTotal messages sent in this session: " + totalSentCount);
                        }
                            
                        case 2 -> System.out.println("Coming Soon.");
                            
                        case 3 -> {
                            System.out.println("Exiting QuickChat. Goodbye!");
                            exit = true;
                        }
                            
                        default -> System.out.println("Invalid option. Please try again.");
                    }
                }
            } else {
                System.out.println("Access denied. You must be logged in to access QuickChat.");
            }
        }
    }
}