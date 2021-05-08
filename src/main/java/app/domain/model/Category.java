package app.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Category {

    private String name;
    private String code;
    private String description;
    private String nhsId;
    private List<Parameter> parameterList;

    public Category(String name, String code, String description) {
        checkNameRules(name);
        checkCodeRules(code);
        checkDescriptionRules(description);
        this.name = name;
        this.code = code;
        this.description = description;
        this.nhsId = null;
        this.parameterList = null;
    }

    public Category(String name, String code, String description, String nhsId) {
        checkNameRules(name);
        checkCodeRules(code);
        checkDescriptionRules(description);
        this.name = name;
        this.code = code;
        this.description = description;
        this.nhsId = nhsId;
        this.parameterList = null;
    }

    public String getDescription() {
        return description;
    }

    private void checkNameRules(String code) {
        if (StringUtils.isBlank(code))
            throw new IllegalArgumentException("Name cannot be blank.");
        if (code.length() > 10)
            throw new IllegalArgumentException("Name must have less than 10 characters.");
    }

    private void checkCodeRules(String code) {
        if (StringUtils.isBlank(code))
            throw new IllegalArgumentException("Code cannot be blank.");
        if ( (code.length() < 4) || (code.length() > 8))
            throw new IllegalArgumentException("Code must have 5 characters.");
    }

    private void checkDescriptionRules(String description){
        if (StringUtils.isBlank(description))
            throw new IllegalArgumentException("Description cannot be blank.");
        if (description.length() > 40)
            throw new IllegalArgumentException("Description must have less than 40 chars.");
    }

    public Parameter createNewParameter(String shortName, String code, String description){
        return new Parameter(shortName, code, description);
    }

    public boolean saveParameter(Parameter par){
        if (!validateParameter(par))
            return false;
        return this.parameterList.add(par);
    }

    public boolean validateParameter(Parameter par){
        if (par == null)
            return false;
        return ! this.parameterList.contains(par);
    }

    public List<Parameter> getParameterList(){
        return parameterList;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", nhsId='" + nhsId + '\'' +
                '}';
    }
}