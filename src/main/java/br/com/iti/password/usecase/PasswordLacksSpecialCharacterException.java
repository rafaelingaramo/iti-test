package br.com.iti.password.usecase;

public class PasswordLacksSpecialCharacterException extends InvalidPasswordException {

    public PasswordLacksSpecialCharacterException() {
    }

    public PasswordLacksSpecialCharacterException(String s) {
        super(s);
    }

    public PasswordLacksSpecialCharacterException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public PasswordLacksSpecialCharacterException(Throwable throwable) {
        super(throwable);
    }

    public PasswordLacksSpecialCharacterException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
