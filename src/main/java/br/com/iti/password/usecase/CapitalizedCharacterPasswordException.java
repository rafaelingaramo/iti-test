package br.com.iti.password.usecase;

public class CapitalizedCharacterPasswordException extends InvalidPasswordException {
    public CapitalizedCharacterPasswordException() {
    }

    public CapitalizedCharacterPasswordException(String s) {
        super(s);
    }

    public CapitalizedCharacterPasswordException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public CapitalizedCharacterPasswordException(Throwable throwable) {
        super(throwable);
    }

    public CapitalizedCharacterPasswordException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
