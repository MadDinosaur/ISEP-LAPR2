package app.domain.model;

public class CollectionMethod {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.equals(""))
        this.description = description;
    }

    @Override
    public String toString() {
        return (String.format("Collection method description: %s", description));
    }


}
