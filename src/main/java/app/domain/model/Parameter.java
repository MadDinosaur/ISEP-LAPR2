package app.domain.model;


import app.domain.model.Exceptions.*;
import org.apache.commons.lang3.StringUtils;

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
        checkNameRules(shortName);
        checkCodeRules(code);
        checkDescriptionRules(description);
        this.shortName = shortName;
        this.code = code;
        this.description = description;
    }

    private void checkNameRules(String shortName){
        if (StringUtils.isBlank(shortName))
            throw new IllegalArgumentException("Name cannot be blank.");
        if ( (shortName.length() > 8))
            throw new IllegalArgumentException("Name must have less than 8 chars.");
    }

    private void checkCodeRules(String code){
        if (StringUtils.isBlank(code))
            throw new IllegalArgumentException("Code cannot be blank.");
        if ( (code.length() != 5))
            throw new IllegalArgumentException("Code must have 5 characters.");
    }

    private void checkDescriptionRules(String description){
        if (StringUtils.isBlank(description))
            throw new IllegalArgumentException("Description cannot be blank.");
        if ( (description.length() > 20))
            throw new IllegalArgumentException("Descriptiom must have less than 20 chars.");
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
