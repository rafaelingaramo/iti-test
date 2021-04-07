package br.com.iti.password.usecase;

public class InvalidPasswordException extends Exception {
    public InvalidPasswordException() {
    }

    public InvalidPasswordException(String s) {
        super(s);
    }

    public InvalidPasswordException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InvalidPasswordException(Throwable throwable) {
        super(throwable);
    }

    public InvalidPasswordException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
