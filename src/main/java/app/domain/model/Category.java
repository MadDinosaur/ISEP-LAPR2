package app.domain.model;

import java.util.ArrayList;

/**
 *
 * @author Diogo Gaspar <1200966@isep.ipp.pt>
 */
public class Category {

    /**
     * NHS identification number for the category
     */
    private int nhsID;

    /**
     * Category's code
     */
    private String code;

    /**
     * Category's description
     */
    private String description;

    /**
     * List of parameters belonging to the class
     */
    private ArrayList<Parameter> parameterList;

    /**
     * Constructor of the class Category
     */
    public Category() {
        this.parameterList = null;
    }

    /**
     * Returns the category's NHS identification number
     * @return NHS Id
     */
    public int getNhsID() {
        return nhsID;
    }

    /**
     * Sets the NHS identification number
     * @param nhsID
     */
    public void setNhsID(int nhsID) {
        this.nhsID = nhsID;
    }

    /**
     * Returns code associated to the category
     * @return code of the category
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code associated to the category
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Returns the description of the category
     * @return category's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the category
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Return the list of parameters that fall under the category
     * @return
     */
    public ArrayList<Parameter> getParameterList() {
        return parameterList;
    }

    /**
     * Sets the list of parameters that fall under the category
     * @param parameterList
     */

    public void setParameterList(ArrayList<Parameter> parameterList) {
        this.parameterList = parameterList;
    }

    public void createNewParameter(String shortName, String code, String description){
        Parameter pc = new Parameter(shortName, code, description);
    }

    public boolean validateParameter(Parameter pc) {
        if (pc == null)
            return false;
        return ! this.parameterList.contains(pc);
    }


    public boolean saveParameter(Parameter pc) {
        if (!validateParameter(pc))
            return false;
        return this.parameterList.add(pc);
    }
}
