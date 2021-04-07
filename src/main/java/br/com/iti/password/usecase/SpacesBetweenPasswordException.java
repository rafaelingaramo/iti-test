package br.com.iti.password.usecase;

public class SpacesBetweenPasswordException extends InvalidPasswordException {
    public SpacesBetweenPasswordException() {
    }

    public SpacesBetweenPasswordException(String s) {
        super(s);
    }

    public SpacesBetweenPasswordException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public SpacesBetweenPasswordException(Throwable throwable) {
        super(throwable);
    }

    public SpacesBetweenPasswordException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
