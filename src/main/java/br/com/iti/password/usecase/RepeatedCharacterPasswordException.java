package br.com.iti.password.usecase;

public class RepeatedCharacterPasswordException extends InvalidPasswordException {
    public RepeatedCharacterPasswordException() {
    }

    public RepeatedCharacterPasswordException(String s) {
        super(s);
    }

    public RepeatedCharacterPasswordException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public RepeatedCharacterPasswordException(Throwable throwable) {
        super(throwable);
    }

    public RepeatedCharacterPasswordException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
