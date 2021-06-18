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
    private String descriptionCollectionMethod;

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
        this.descriptionCollectionMethod = description;
    }

    /**
     * Description getter
     * @return description
     */
    public String getDescriptionCollectionMethod() {
        return descriptionCollectionMethod;
    }

    /**
     * Description setter
     * @param descriptionCollectionMethod 20 or less characters
     */
    public void setDescriptionCollectionMethod(String descriptionCollectionMethod) {
        if (!validateDescriptionCollectionMethod(descriptionCollectionMethod)) {
            throw new InvalidDescriptionException();
        } else {
            this.descriptionCollectionMethod = descriptionCollectionMethod;
        }
    }

    /**
     *
     * @param description Collection method's description
     * @return True if it's valid and false if it's invalid
     */
    public boolean validateDescriptionCollectionMethod(String description) {
        return !description.equals("") && description.length() <= 20;
    }

    /**
     *
     * @param otherObject Another object
     * @return True if a collection method has the same description as another collection method
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null || getClass() != otherObject.getClass()) return false;
        CollectionMethod that = (CollectionMethod) otherObject;
        return Objects.equals(descriptionCollectionMethod, that.descriptionCollectionMethod);
    }

}
