package net.braniumacademy.ex744.exceptions;

public class InvalidEmailException extends Exception {
    private String invalidEmail;

    public InvalidEmailException() {
    }

    public InvalidEmailException(String invalidEmail) {
        this.invalidEmail = invalidEmail;
    }

    public InvalidEmailException(String message, String invalidEmail) {
        super(message);
        this.invalidEmail = invalidEmail;
    }

    public String getInvalidEmail() {
        return invalidEmail;
    }

    public void setInvalidEmail(String invalidEmail) {
        this.invalidEmail = invalidEmail;
    }
}
