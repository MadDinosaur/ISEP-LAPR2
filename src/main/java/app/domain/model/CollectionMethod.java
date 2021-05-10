package app.domain.model;

import app.domain.model.Exceptions.InvalidDescriptionException;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectionMethod that = (CollectionMethod) o;
        return Objects.equals(description, that.description);
    }

}
