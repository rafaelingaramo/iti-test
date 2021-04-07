package br.com.iti.password.usecase;

public class UncapitalizedCharacterPasswordException extends InvalidPasswordException {
    public UncapitalizedCharacterPasswordException() {
    }

    public UncapitalizedCharacterPasswordException(String s) {
        super(s);
    }

    public UncapitalizedCharacterPasswordException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public UncapitalizedCharacterPasswordException(Throwable throwable) {
        super(throwable);
    }

    public UncapitalizedCharacterPasswordException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
