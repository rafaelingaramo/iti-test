package br.com.iti.password.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * https://github.com/itidigital/backend-challenge
 *
 * Considere uma senha sendo válida quando a mesma possuir as seguintes definições:
 *
 * Nove ou mais caracteres
 * Ao menos 1 dígito
 * Ao menos 1 letra minúscula
 * Ao menos 1 letra maiúscula
 * Ao menos 1 caractere especial
 * Considere como especial os seguintes caracteres: !@#$%^&*()-+
 * Não possuir caracteres repetidos dentro do conjunto
 *
 * Exemplo:
 *
 * IsValid("") // false
 * IsValid("aa") // false
 * IsValid("ab") // false
 * IsValid("AAAbbbCc") // false
 * IsValid("AbTp9!foo") // false
 * IsValid("AbTp9!foA") // false
 * IsValid("AbTp9 fok") // false
 * IsValid("AbTp9!fok") // true
 * Nota: Espaços em branco não devem ser considerados como caracteres válidos.
 */
@Component
@Slf4j
public class PasswordValidator {
    private static final Pattern SPECIAL_CHARACTERS_REGEXP = Pattern.compile("[!@#$%^&*()\\-+]+");
    private static final Pattern CAPITALIZED_CHARACTERS_REGEXP = Pattern.compile("[A-Z]+");
    private static final Pattern UNCAPITALIZED_CHARACTERS_REGEXP = Pattern.compile("[a-z]+");
    private static final Pattern DIGIT_REGEXP = Pattern.compile("\\d+");
    private static final String SPACE_BETWEEN_REGEXP = ".*\\s+.*";
    private static final String SPACE_BEGIN_REGEXP = "^\\s+.*";
    private static final String SPACE_END_REGEXP = ".*\\s+$";

    public void execute(String toBeValidated) throws InvalidPasswordException {
        log.info("validating password to be changed");

        mustBeNotNull(toBeValidated)
            .mustHaveMinimumLength(toBeValidated)
            .mustHaveNoRepeatedCharacters(toBeValidated)
            .mustHaveSingleSpecialCharacter(toBeValidated)
            .mustHaveSingleCapitalizedCharacter(toBeValidated)
            .mustHaveSingleUncapitalizedCharacter(toBeValidated)
            .mustHaveSingleDigitCharacter(toBeValidated)
            .mustNotHaveSpaceBetween(toBeValidated);
    }

    private PasswordValidator mustHaveMinimumLength(String toBeValidated) throws InvalidPasswordException {
        if (toBeValidated.length() < 9) {
            throw new InsufficientCharactersPasswordException("All passwords must have 9 or more characters");
        }

        return this;
    }

    private PasswordValidator mustBeNotNull(String toBeValidated) throws InvalidPasswordException {
        if (toBeValidated == null) {
            throw new InvalidPasswordException("null password");
        }
        return this;
    }

    private PasswordValidator mustNotHaveSpaceBetween(String toBeValidated) throws InvalidPasswordException {
        if (toBeValidated.matches(SPACE_BETWEEN_REGEXP) ||
                toBeValidated.matches(SPACE_BEGIN_REGEXP) ||
                toBeValidated.matches(SPACE_END_REGEXP)) {
            throw new SpacesBetweenPasswordException("Password must not have spaces between");
        }

        return this;
    }

    private PasswordValidator mustHaveSingleDigitCharacter(String toBeValidated) throws InvalidPasswordException {
        Matcher matcher = DIGIT_REGEXP.matcher(toBeValidated);

        if (!matcher.find()) {
            throw new NoNumericDigitPasswordException("Password must have at least one number character");
        }

        return this;
    }

    private PasswordValidator mustHaveSingleUncapitalizedCharacter(String toBeValidated) throws InvalidPasswordException {
        Matcher matcher = UNCAPITALIZED_CHARACTERS_REGEXP.matcher(toBeValidated);

        if (!matcher.find()) {
            throw new UncapitalizedCharacterPasswordException("Password must have at least one uncapitalized character");
        }

        return this;
    }

    private PasswordValidator mustHaveSingleCapitalizedCharacter(String toBeValidated) throws InvalidPasswordException {
        Matcher matcher = CAPITALIZED_CHARACTERS_REGEXP.matcher(toBeValidated);

        if (!matcher.find()) {
            throw new CapitalizedCharacterPasswordException("Password must have at least one capitalized character");
        }

        return this;
    }

    private PasswordValidator mustHaveSingleSpecialCharacter(String toBeValidated) throws InvalidPasswordException {
        Matcher matcher = SPECIAL_CHARACTERS_REGEXP.matcher(toBeValidated);

        if (!matcher.find()) {
            throw new PasswordLacksSpecialCharacterException("Password must have at least one special character");
        }

        return this;
    }

    private PasswordValidator mustHaveNoRepeatedCharacters(String toBeValidated) throws InvalidPasswordException {
        String[] split = toBeValidated.split("");

        if (Arrays.stream(split)
                .filter(Objects::nonNull)
                .anyMatch(i -> Arrays.stream(split).filter(j -> j.equals(i)).count() > 1)) {
            throw new RepeatedCharacterPasswordException("Password with duplicated characters detected");
        }

        return this;
    }
}
