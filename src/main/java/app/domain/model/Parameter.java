package app.domain.model;

import app.domain.model.Exceptions.*;
import org.apache.commons.lang3.StringUtils;

public class Parameter {

    /**
     *
     */
    private String parameterName;

    /**
     *
     */
    private String parameterCode;

    /**
     *
     */
    private String parameterDescription;

    /**
     *
     */
    private double upperRefVal;

    /**
     *
     */
    private double lowerRefVal;

    /**
     *
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
     *
     * @param parameterName
     */
    private void checkNameRules(String parameterName) {
        if (StringUtils.isBlank(parameterName))
            throw new InvalidNameException("Name cannot be blank.");
        if (parameterName.length() > 8)
            throw new InvalidNameException("Name must have less than 8 characters.");
    }

    /**
     *
     * @param parameterCode
     */
    private void checkCodeRules(String parameterCode) {
        if (StringUtils.isBlank(parameterCode))
            throw new InvalidCodeException("Code cannot be blank.");
        if (parameterCode.length() != 5)
            throw new InvalidCodeException("Code must have 5 characters.");
    }

    /**
     *
     * @param parameterDescription
     */
    private void checkDescriptionRules(String parameterDescription) {
        if (StringUtils.isBlank(parameterDescription))
            throw new InvalidDescriptionException("Description cannot be blank.");
        if (parameterDescription.length() > 20)
            throw new InvalidDescriptionException("Description must have less than 20 characters.");
    }

    /**
     *
     * @return
     */
    public String getParameterName() {
        return parameterName;
    }

    /**
     *
     * @return
     */
    public String getParameterCode() {
        return parameterCode;
    }

    /**
     *
     * @return
     */
    public String getParameterDescription() {
        return parameterDescription;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Parameter{" +
                "shortName='" + parameterName + '\'' +
                ", code='" + parameterCode + '\'' +
                ", description='" + parameterDescription + '\'' +
                '}';
    }
}
