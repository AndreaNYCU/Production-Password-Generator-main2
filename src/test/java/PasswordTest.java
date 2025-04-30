import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class PasswordTest {

    @Test
    void testPasswordStrengthWithOnlyLowercase() {
        Password password = new Password("abcdefgh");
        assertEquals(1, password.PasswordStrength(), "Password with only lowercase letters should have a strength of 1");
    }

    @Test
    void testPasswordStrengthWithAllCriteria() {
        Password password = new Password("Ab1!Cdef2#Ghij3$Klmn");
        assertEquals(6, password.PasswordStrength(), "Password with uppercase, lowercase, numbers, symbols, and length >= 16 should have a strength of 6");
    }
}