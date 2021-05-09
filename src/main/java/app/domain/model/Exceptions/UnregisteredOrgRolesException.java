package app.domain.model.Exceptions;

import java.util.NoSuchElementException;

public class UnregisteredOrgRolesException extends NoSuchElementException {
    public UnregisteredOrgRolesException() {
        super("There are no organization roles in the system!");
    }
    public UnregisteredOrgRolesException(String message) {
        super(message);
    }
}
