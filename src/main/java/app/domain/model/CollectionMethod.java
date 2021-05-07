package app.domain.model;

public class CollectionMethod {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return (String.format("Collection method description: %s", description));
    }


}
