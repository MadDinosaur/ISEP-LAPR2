package app.domain.model;

import app.domain.model.Exceptions.InvalidDescriptionException;

public class CollectionMethod {

    private String description;

    public CollectionMethod() {

    }

    public CollectionMethod(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (!validateDescription(description)) {
            throw new InvalidDescriptionException();
        } else {
            this.description = description;
        }
    }

    public boolean validateDescription(String description) {
        if (description.equals("") || description.length() > 20) {
            return false;
        } else {
            return true;
        }
    }


}
