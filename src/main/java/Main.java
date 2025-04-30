import java.util.Scanner;

public class Main {

    public static final Scanner keyboard = new Scanner(System.in);

    /**
     * The main entry point of The Password Thunderdome application.
     * This method initializes the generator, displays a colorful banner,
     * runs the main program loop, and closes the input scanner.
     *
     * @param args Command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        Generator generator = new Generator(keyboard);
        displayBanner();
        generator.mainLoop();
        keyboard.close();
    }

    /**
     * Displays a colorful banner for The Password Thunderdome application.
     * 
     * This method prints an ASCII art banner to the console, introducing
     * the application name and version.
     * 
     * @since 3.0.0
     */
    private static void displayBanner() {
        String banner =
            "\n\033[1;35m" + // Bright Magenta
            "┌───────────────────────────────────────┐\n" +
            "│                                       │\n" +
            "│       The Password Thunderdome        │\n" +
            "│               v3.0.0                  │\n" +
            "│                                       │\n" +
            "│     Generate Secure Passwords &       │\n" +
            "│       Check Password Strength         │\n" +
            "│                                       │\n" +
            "└───────────────────────────────────────┘\n" +
            "\033[0m"; // Reset color
        System.out.println(banner);
    }

    /*
     * Major Changes:
     * - 2023-07-10: Updated application name from "The Password Factory" to "The Password Thunderdome"
     * - 2023-07-10: Updated version number from v2.0 to v3.0.0
     * - 2023-07-10: Added JavaDoc comments for the displayBanner method
     */
}