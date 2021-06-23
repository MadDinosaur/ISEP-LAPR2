package app.domain.model.exceptions;

import java.util.NoSuchElementException;

public class UnregisteredOrgRolesException extends NoSuchElementException {
    public UnregisteredOrgRolesException() {
        super("There are no organization roles in the system!");
    }
    public UnregisteredOrgRolesException(String message) {
        super(message);
    }
}
