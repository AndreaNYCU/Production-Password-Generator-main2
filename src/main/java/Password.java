
public class Password {
    String Value;
    int Length;

    public Password(String s) {
        Value = s;
        Length = s.length();
    }


    /**
     * Calculates the entropy of the password.
     * 
     * This method computes the entropy of the password based on its length and the
     * character types used. It considers uppercase letters, lowercase letters,
     * digits, and special symbols. The entropy is calculated using the formula:
     * entropy = passwordLength * log2(poolSize)
     * 
     * The pool size is determined by the types of characters present in the password:
     * - Uppercase letters contribute 26 to the pool size
     * - Lowercase letters contribute 26 to the pool size
     * - Digits contribute 10 to the pool size
     * - Special symbols contribute 32 to the pool size
     * 
     * @return The calculated entropy of the password as a double value.
     *         Higher entropy indicates a stronger password.
     * @since 1.0.0
     */
    public double calculateEntropy() {
        double entropy = 0;
        int passwordLength = Value.length();
        int poolSize = 0;

        if (Value.matches(".*[A-Z].*")) poolSize += 26;
        if (Value.matches(".*[a-z].*")) poolSize += 26;
        if (Value.matches(".*\\d.*")) poolSize += 10;
        if (Value.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) poolSize += 32;

        entropy = passwordLength * (Math.log(poolSize) / Math.log(2));
        return entropy;
    }




    /**
 * Determines the type of a character in a password.
 *
 * @param C The character to be evaluated.
 * @return An integer representing the type of the character:
 *         1 - Uppercase letter
 *         2 - Lowercase letter
 *         3 - Digit
 *         4 - Symbol
 */

public int CharType(char C) {
    int val;

    // Char is Uppercase Letter
    if ((int) C >= 65 && (int) C <= 90)
        val = 1;

    // Char is Lowercase Letter
    else if ((int) C >= 97 && (int) C <= 122) {
        val = 2;
    }

     // Char is Digit
    else if ((int) C >= 48 && (int) C <= 57) {
        val = 3;
}

    // Char is Symbol
    else {
        val = 4;
    }

    return val;
}

    /**
     * Calculates the strength of the password based on various criteria.
     * 
     * This method evaluates the password strength by checking for the presence of:
     * - Uppercase letters
     * - Lowercase letters
     * - Numbers
     * - Symbols
     * It also considers the length of the password.
     * 
     * @return An integer score representing the password strength:
     *         - 1 point for each: uppercase, lowercase, number, and symbol used
     *         - 1 point if length is 8 or more
     *         - 1 additional point if length is 16 or more
     *         The maximum possible score is 6.
     */
    public int PasswordStrength() {
        String s = this.Value;
        boolean UsedUpper = false;
        boolean UsedLower = false;
        boolean UsedNum = false;
        boolean UsedSym = false;
        int type;
        int Score = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            type = CharType(c);

            if (type == 1) UsedUpper = true;
            if (type == 2) UsedLower = true;
            if (type == 3) UsedNum = true;
            if (type == 4) UsedSym = true;
        }

        if (UsedUpper) Score += 1;
        if (UsedLower) Score += 1;
        if (UsedNum) Score += 1;
        if (UsedSym) Score += 1;

        if (s.length() >= 8) Score += 1;
        if (s.length() >= 16) Score += 1;

        return Score;
    }


    /**
     * Calculates and returns a detailed score for the password, including visualization,
     * entropy information, and a descriptive message about the password strength.
     *
     * This method combines the results of password strength calculation, entropy calculation,
     * and strength visualization to provide a comprehensive assessment of the password.
     *
     * @return A String containing the password strength visualization, entropy information,
     *         and a descriptive message about the password strength. The message varies
     *         based on the calculated entropy value, providing feedback on whether the
     *         password is considered weak, moderate, strong, or very strong.
     */
    public String calculateScore() {
        int Score = this.PasswordStrength();
        double entropy = this.calculateEntropy();
        String visualization = this.visualizePasswordStrength();

        String entropyInfo = String.format("\nPassword Entropy: %.2f bits", entropy);

        if (entropy >= 80) {
            return visualization + entropyInfo + "\nThis is a very strong password with high entropy. Great job!";
        } else if (entropy >= 60) {
            return visualization + entropyInfo + "\nThis is a strong password with good entropy. You're on the right track!";
        } else if (entropy >= 40) {
            return visualization + entropyInfo + "\nThis password has moderate entropy. Consider making it stronger.";
        } else {
            return visualization + entropyInfo + "\nThis password has low entropy. It's recommended to choose a stronger password.";
        }
    }




/**
 * Visualizes the strength of the password based on its entropy.
 * 
 * This method creates a visual representation of the password strength
 * using a bar of filled and unfilled characters, along with a textual
 * description of the strength level.
 * 
 * @example
 * ```java
 * Password password = new Password("MyStr0ngP@ssw0rd");
 * String visualization = password.visualizePasswordStrength();
 * System.out.println(visualization);
 * // Output: [████░░] Strong
 * ```
 * 
 * @returns {String} A string containing a visual bar representation and
 *                   a textual description of the password strength.
 * @version 1.0.0
 * @since 1.0.0
 */
public String visualizePasswordStrength() {
    double entropy = this.calculateEntropy();
    StringBuilder visualization = new StringBuilder("[");

    int filledBars = (int) Math.min(entropy / 20, 6); // Scale entropy to 0-6 range
    for (int i = 0; i < 6; i++) {
        if (i < filledBars) {
            visualization.append("█");
        } else {
            visualization.append("░");
        }
    }

    visualization.append("] ");

    if (entropy >= 80) {
        visualization.append("Very Strong");
    } else if (entropy >= 60) {
        visualization.append("Strong");
    } else if (entropy >= 40) {
        visualization.append("Medium");
    } else {
        visualization.append("Weak");
    }

    return visualization.toString();
}



    @Override
    public String toString() {
        return Value;
    }
}
