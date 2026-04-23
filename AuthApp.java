import java.util.HashMap;
import java.util.Scanner;
import java.security.MessageDigest;

public class AuthApp {

    static HashMap<String, String> users = new HashMap<>();

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1 - Register");
        System.out.println("2 - Login");
        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        String hashed = hashPassword(password);

        if (choice == 1) {
            users.put(username, hashed);
            System.out.println("Registered!");
        } else if (choice == 2) {
            if (users.containsKey(username) && users.get(username).equals(hashed)) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid credentials!");
            }
        }
    }
}
