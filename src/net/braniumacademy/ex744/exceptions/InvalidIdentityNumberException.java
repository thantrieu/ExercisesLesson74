package net.braniumacademy.ex744.exceptions;

public class InvalidIdentityNumberException extends Exception {
    private String invalidId;

    public InvalidIdentityNumberException() {
    }

    public InvalidIdentityNumberException(String invalidId) {
        this.invalidId = invalidId;
    }

    public InvalidIdentityNumberException(String message, String invalidId) {
        super(message);
        this.invalidId = invalidId;
    }

    public String getInvalidId() {
        return invalidId;
    }

    public void setInvalidId(String invalidId) {
        this.invalidId = invalidId;
    }
}
