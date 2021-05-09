package app.domain.model;

import app.domain.model.Exceptions.*;
import org.apache.commons.lang3.StringUtils;

public class Parameter {

    private String shortName;

    private String code;

    private String description;

    private double upperRefVal;

    private double lowerRefVal;

    public Parameter(String shortName, String code, String description){
        checkNameRules(shortName);
        checkCodeRules(code);
        checkDescriptionRules(description);
        this.shortName = shortName;
        this.code = code;
        this.description = description;
    }

    private void checkNameRules(String shortName) {
        if (StringUtils.isBlank(shortName))
            throw new InvalidNameException("Name cannot be blank.");
        if (shortName.length() > 8)
            throw new InvalidNameException("Name must have less than 8 characters.");
    }

    private void checkCodeRules(String code) {
        if (StringUtils.isBlank(code))
            throw new InvalidCodeException("Code cannot be blank.");
        if (code.length() != 5)
            throw new InvalidCodeException("Code must have 5 characters.");
    }

    private void checkDescriptionRules(String description) {
        if (StringUtils.isBlank(description))
            throw new InvalidDescriptionException("Description cannot be blank.");
        if (description.length() > 20)
            throw new InvalidDescriptionException("Description must have less than 20 characters.");
    }

    public String getShortName() {
        return shortName;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "shortName='" + shortName + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
