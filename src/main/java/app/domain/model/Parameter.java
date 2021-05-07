package app.domain.model;


import app.domain.model.Exceptions.*;

/**
 *
 * @author Diogo Gaspar <1200966@isep.ipp.pt>
 */
public class Parameter {
                                                     
    /**                                              
     * Parameter's name                              
     */                                              
    private String shortName;                        
                                                     
    /**                                                                      
     * Parameter's code                                                      
     */                                                                      
    private String code;                                                     
                                                                             
    /**
     * Parameter's description
     */
    private String description;

    /**
     * Parameter's upper reference value
     */
    private double upperReferenceValue;

    /**
     * Parameter's lower reference value
     */
    private double lowerReferenceValue;

    /**
     * Contructor of the class
     */
    public Parameter(String shortName, String code, String description) {

    }

    /**
     * Returns the name of the parameter
     * @return parameter name
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Sets the name of the parameter
     * @param shortName
     */
    public void setShortName(String shortName) {
        if(shortName.length() <= 8){
            this.shortName = shortName;
        }else {
            throw new InvalidParNameException("The parameter's name is invalid! The name can only have a maximum of 8 characters.");
        }
    }

    /**
     * Returns the code of the parameter
     * @return parameter code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code of the parameter
     * @param code
     */
    public void setCode(String code) {
        if(code.length() == 5){
            this.code = code;
        }else {
            throw new InvalidParCodeException("The parameter's code is invalid! It should have 5 alphanumerical characters.");
        }
    }

    /**
     * Returns the description of the parameter
     * @return parameter description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the parameter
     * @param description
     */
    public void setDescription(String description) {
        if(description.length() <= 20){
            this.description = description;
        }else {
            throw new InvalidParDescriptionException("The parameter's description is invalid! It can only have a maximum of 20 characters.");
        }
    }

    /**
     * Returns the upper reference value of the parameter
     * @return parameter upper reference value
     */
    public double getUpperReferenceValue() {
        return upperReferenceValue;
    }

    /**
     * Sets the upper reference value of the parameter
     * @param upperReferenceValue
     */
    public void setUpperReferenceValue(double upperReferenceValue) {
        this.upperReferenceValue = upperReferenceValue;
    }

    /**
     * returns the lower reference value of the parameter
     * @return parameter lower reference value
     */
    public double getLowerReferenceValue() {
        return lowerReferenceValue;
    }

    /**
     * Sets the lower reference value of the parameter
     * @param lowerReferenceValue
     */
    public void setLowerReferenceValue(double lowerReferenceValue) {
        this.lowerReferenceValue = lowerReferenceValue;
    }


    /**
     * Returns the information of the parameter in a string
     * @return string
     */
    @Override
    public String toString() {
        return "Parameter{" +
                "shortName='" + shortName + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}
