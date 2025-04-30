import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

@Test
void longerPasswordShouldHaveHigherScore() {
    Password shortPassword = new Password("Abc123!");
    Password longPassword = new Password("Abc123!Def456@Ghi789#");

    int shortScore = shortPassword.PasswordStrength();
    int longScore = longPassword.PasswordStrength();

    assertTrue(longScore > shortScore, "Longer password should have a higher strength score");
}

@Test
void passwordWithAllCriteriaShouldHaveMaxScore() {
    Password password = new Password("Abc123!@#$");

    assertEquals(6, password.PasswordStrength(), "Password with all criteria should have max score of 6");
}

@Test
void passwordWithOnlyLowercaseShouldHaveMinScore() {
    Password password = new Password("abcdefgh");

    assertEquals(1, password.PasswordStrength(), "Password with only lowercase should have min score of 1");
}

@Test
void calculateEntropyTest() {
    Password weakPassword = new Password("abc");
    Password strongPassword = new Password("Abc123!@#");

    double weakEntropy = weakPassword.calculateEntropy();
    double strongEntropy = strongPassword.calculateEntropy();

    assertTrue(strongEntropy > weakEntropy, "Stronger password should have higher entropy");
}

@Test
void visualizePasswordStrengthTest() {
    Password weakPassword = new Password("abc");
    Password strongPassword = new Password("Abc123!@#");

    String weakVisualization = weakPassword.visualizePasswordStrength();
    String strongVisualization = strongPassword.visualizePasswordStrength();

    assertTrue(weakVisualization.contains("Weak"), "Weak password should be visualized as weak");
    assertTrue(strongVisualization.contains("Strong") || strongVisualization.contains("Very Strong"), 
               "Strong password should be visualized as strong or very strong");
}
}
