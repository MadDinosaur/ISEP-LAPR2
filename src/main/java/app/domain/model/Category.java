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
