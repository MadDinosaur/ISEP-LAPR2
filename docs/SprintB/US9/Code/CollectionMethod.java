public class CollectionMethod {

    private String description;


    public CollectionMethod (String description) {
        this.description = description;
    }

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
