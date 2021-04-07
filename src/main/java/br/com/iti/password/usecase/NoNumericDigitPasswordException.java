package br.com.iti.password.usecase;

public class NoNumericDigitPasswordException extends InvalidPasswordException {
    public NoNumericDigitPasswordException() {
    }

    public NoNumericDigitPasswordException(String s) {
        super(s);
    }

    public NoNumericDigitPasswordException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public NoNumericDigitPasswordException(Throwable throwable) {
        super(throwable);
    }

    public NoNumericDigitPasswordException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
