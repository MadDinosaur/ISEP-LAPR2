package app.domain.model;

public class CollectionMethod {

    private String description;

    public CollectionMethod() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.equals("")) {
            throw new IllegalArgumentException("The description introduced isn't valid.");
        } else {
            this.description = description;
        }
    }

    @Override
    public String toString() {
        return (String.format("Collection method description: %s", description));
    }


}
