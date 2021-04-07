package br.com.iti.password.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PasswordValidatorTest {
    public PasswordValidator validator = new PasswordValidator();

    @Test
    public void testValidPassword() {
        String validPassword = "AbTp9!fok";
        Assertions.assertDoesNotThrow(() -> validator.execute(validPassword));
    }

    @Test
    public void testNullPassword() {
        Assertions.assertThrows(InvalidPasswordException.class, () -> validator.execute(null));
    }

    @Test
    public void testPasswordWithInsufficientCharacterCount() {
        String invalidPassword = "AbTp9!fo";
        Assertions.assertThrows(InsufficientCharactersPasswordException.class, () -> validator.execute(invalidPassword));
    }

    @Test
    public void testPasswordWithNoDigitCharacter() {
        String invalidPassword = "AbTpx!fol";
        Assertions.assertThrows(NoNumericDigitPasswordException.class, () -> validator.execute(invalidPassword));
    }

    @Test
    public void testPasswordNonCapitalizedCharacterOnly() {
        String invalidPassword = "abtp9!fok";
        Assertions.assertThrows(CapitalizedCharacterPasswordException.class, () -> validator.execute(invalidPassword));
    }

    @Test
    public void testPasswordCapitalizedCharacterOnly() {
        String invalidPassword = "ABTP9!FOK";
        Assertions.assertThrows(UncapitalizedCharacterPasswordException.class, () -> validator.execute(invalidPassword));
    }

    @Test
    public void testPasswordWithoutSpecialCharacter() {
        String invalidPassword = "AbTp9dfok";
        Assertions.assertThrows(PasswordLacksSpecialCharacterException.class, () -> validator.execute(invalidPassword));
    }

    @Test
    public void testPasswordWithRepeatedCharacters() {
        String invalidPassword = "AbTp9!foA";
        Assertions.assertThrows(RepeatedCharacterPasswordException.class, () -> validator.execute(invalidPassword));
    }

    @Test
    public void testPasswordWithSpacesBetween() {
        String invalidPassword = "AbTp9 fo$a";
        Assertions.assertThrows(SpacesBetweenPasswordException.class, () -> validator.execute(invalidPassword));
        String secondInvalidPassword  = " AbTp9!foa";
        Assertions.assertThrows(SpacesBetweenPasswordException.class, () -> validator.execute(secondInvalidPassword));
        String thirdInvalidPassword = "AbTp9!foa ";
        Assertions.assertThrows(SpacesBetweenPasswordException.class, () -> validator.execute(thirdInvalidPassword));
    }

    @Test
    public void testVariousInvalidScenarios() {
        String[] invalidArray = {"", "aa", "ab", "AAAbbbCc", "AbTp9!foo", "AbTp9!foA", "AbTp9 fok"};

        for (String item: invalidArray) {
            Assertions.assertThrows(InvalidPasswordException.class, () -> validator.execute(item));
        }
    }
}