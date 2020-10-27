package net.braniumacademy.ex744.exceptions;

public class InvalidPhoneNumberException extends Exception {
    private String invalidPhoneNumber;

    public InvalidPhoneNumberException() {
    }

    public InvalidPhoneNumberException(String invalidPhoneNumber) {
        this.invalidPhoneNumber = invalidPhoneNumber;
    }

    public InvalidPhoneNumberException(String message, String invalidPhoneNumber) {
        super(message);
        this.invalidPhoneNumber = invalidPhoneNumber;
    }

    public String getInvalidPhoneNumber() {
        return invalidPhoneNumber;
    }

    public void setInvalidPhoneNumber(String invalidPhoneNumber) {
        this.invalidPhoneNumber = invalidPhoneNumber;
    }
}
