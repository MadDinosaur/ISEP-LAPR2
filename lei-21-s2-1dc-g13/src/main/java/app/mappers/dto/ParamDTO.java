package app.mappers.dto;

public class ParamDTO {
    private String code;
    private String name;
    private String description;


    public ParamDTO(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Parameter %s - %s - %s", getCode(), getName(), getDescription());
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
