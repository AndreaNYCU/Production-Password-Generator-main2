import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {

    private Generator generator;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        
        // Initialize generator with a scanner that will simulate user input
        generator = new Generator(new Scanner(System.in));
}

@Test
void shouldGeneratePasswordWithSpecifiedLengthAndCharacterTypes() {
    // Arrange
    Generator testGenerator = new Generator(true, true, true, false); // Upper, Lower, Numbers, no Symbols
    int passwordLength = 12;
    
    // Act
    Password generatedPassword = testGenerator.GeneratePassword(passwordLength);
    String passwordValue = generatedPassword.toString();
    
    // Assert
    assertEquals(passwordLength, passwordValue.length(), "Generated password should have the specified length");
    
    // Check that the password contains expected character types
    assertTrue(passwordValue.matches(".*[A-Z].*"), "Password should contain uppercase letters");
    assertTrue(passwordValue.matches(".*[a-z].*"), "Password should contain lowercase letters");
    assertTrue(passwordValue.matches(".*\\d.*"), "Password should contain numbers");
    assertFalse(passwordValue.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':"\\\\|,.<>\\/?].*"), 
            "Password should not contain symbols when symbols are not included");
}

@Test
void shouldDisplayMainMenuAfterInitialization() {
    // Reset output stream to ensure clean capture
    outputStream.reset();
    
    // Call the printMenu method which should be called during initialization
    generator.printMenu();
    
    // Get the console output
    String output = outputStream.toString();
    
    // Check that the menu was displayed properly
    assertTrue(output.contains("Password Generator and Checker Menu"), 
            "Menu title should be displayed");
    assertTrue(output.contains("1. Generate a new password"), 
            "Option 1 should be displayed");
    assertTrue(output.contains("2. Check password strength"), 
            "Option 2 should be displayed");
    assertTrue(output.contains("3. Display password security information"), 
            "Option 3 should be displayed");
    assertTrue(output.contains("4. Quit"), 
            "Option 4 should be displayed");
    
    // Restore original System.out
    System.setOut(originalOut);
}

@Test
void shouldThrowExceptionWhenGeneratingPasswordWithInvalidLength() {
    // Arrange
    Generator testGenerator = new Generator(true, true, true, true); // All character types included
    
    // Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        // Use reflection to access the private method
        java.lang.reflect.Method method = Generator.class.getDeclaredMethod("GeneratePassword", int.class);
        method.setAccessible(true);
        method.invoke(testGenerator, 2); // Try to generate password with length 2
    });
    
    // Verify the exception message
    assertTrue(exception.getCause().getMessage().contains("Password length must be at least 3"), 
            "Exception should mention that password length must be at least 3");
    }
}