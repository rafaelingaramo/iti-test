package br.com.iti.password.usecase;

public class InsufficientCharactersPasswordException extends InvalidPasswordException {
    public InsufficientCharactersPasswordException() {
    }

    public InsufficientCharactersPasswordException(String s) {
        super(s);
    }

    public InsufficientCharactersPasswordException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InsufficientCharactersPasswordException(Throwable throwable) {
        super(throwable);
    }

    public InsufficientCharactersPasswordException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
