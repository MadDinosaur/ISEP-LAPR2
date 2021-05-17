package app.domain.model;

import app.domain.model.Exceptions.InvalidDescriptionException;

import java.util.Objects;

/**
 * @author 1200985 Tomás Cancela
 */
public class CollectionMethod {


    /**
     * Collection Method's description
     */
    private String description;

    /**
     * Empty constructor
     */
    public CollectionMethod() {

    }

    /**
     * Constructs a collection method with a description
     * @param description Collection Method's description
     */
    public CollectionMethod(String description) {
        this.description = description;
    }

    /**
     * Description getter
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Description setter
     * @param description 20 or less characters
     */
    public void setDescription(String description) {
        if (!validateDescription(description)) {
            throw new InvalidDescriptionException();
        } else {
            this.description = description;
        }
    }

    /**
     *
     * @param description Collection method's description
     * @return True if it's valid and false if it's invalid
     */
    public boolean validateDescription(String description) {
        if (description.equals("") || description.length() > 20) {
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * @param o Another object
     * @return True if a collection method has the same description as another collection method
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectionMethod that = (CollectionMethod) o;
        return Objects.equals(description, that.description);
    }

}
