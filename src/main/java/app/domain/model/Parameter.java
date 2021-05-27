package app.domain.model;

import app.domain.model.Exceptions.*;
import org.apache.commons.lang3.StringUtils;

public class Parameter {

    /**
     * Parameter's name
     */
    private String parameterName;

    /**
     * Parameter's code
     */
    private String parameterCode;

    /**
     * Parameter's description
     */
    private String parameterDescription;

    /**
     * Parameter's upper reference value
     */
    private double upperRefVal;

    /**
     * Parameter's lower reference value
     */
    private double lowerRefVal;

    /**
     * Parameter class constructor with three parameters (Parameter's name, code and description)
     * @param parameterName
     * @param parameterCode
     * @param parameterDescription
     */
    public Parameter(String parameterName, String parameterCode, String parameterDescription){
        checkNameRules(parameterName);
        checkCodeRules(parameterCode);
        checkDescriptionRules(parameterDescription);
        this.parameterName = parameterName;
        this.parameterCode = parameterCode;
        this.parameterDescription = parameterDescription;
    }

    /**
     * Method that checks the parameter's name's compliance with the acceptance criteria
     * @param parameterName
     */
    private void checkNameRules(String parameterName) {
        if (StringUtils.isBlank(parameterName))
            throw new InvalidNameException("Name cannot be blank.");
        if (parameterName.length() > 8)
            throw new InvalidNameException("Name must have less than 8 characters.");
    }

    /**
     * Method that checks the parameter's code's compliance with the acceptance criteria
     * @param parameterCode
     */
    private void checkCodeRules(String parameterCode) {
        if (StringUtils.isBlank(parameterCode))
            throw new InvalidCodeException("Code cannot be blank.");
        if (parameterCode.length() != 5)
            throw new InvalidCodeException("Code must have 5 characters.");
    }

    /**
     * Method that checks the parameter's description's compliance with the acceptance criteria
     * @param parameterDescription
     */
    private void checkDescriptionRules(String parameterDescription) {
        if (StringUtils.isBlank(parameterDescription))
            throw new InvalidDescriptionException("Description cannot be blank.");
        if (parameterDescription.length() > 20)
            throw new InvalidDescriptionException("Description must have less than 20 characters.");
    }

    /**
     * Getter for the parameter's name
     * @return parameter's name
     */
    public String getParameterName() {
        return parameterName;
    }

    /**
     * Getter for the parameter's code
     * @return parameter's code
     */
    public String getParameterCode() {
        return parameterCode;
    }

    /**
     * Getter for the parameter's description
     * @return parameter's description
     */
    public String getParameterDescription() {
        return parameterDescription;
    }

    /**
     * Method to turn the parameter's name, code and description into a single string
     * @return string with parameter's name, code, description
     */
    @Override
    public String toString() {
        return "Parameter{" +
                "parameterName='" + parameterName + '\'' +
                ", parameterCode='" + parameterCode + '\'' +
                ", parameterDescription='" + parameterDescription + '\'' +
                '}';
    }
}
