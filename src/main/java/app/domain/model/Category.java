package app.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Category {

    /**
     * Parameter category's name
     */
    private String categoryName;

    /**
     * Parameter category's code
     */
    private String categoryCode;

    /**
     * Parameter category's description
     */
    private String categoryDescription;

    /**
     * Parameter category's NHS id
     */
    private String categoryNhsId;

    /**
     * Parameter cartegory's list of parameters
     */
    private List<Parameter> parameterList;

    /**
     * Category class constructor with three parameters (Category's name, code and description)
     * @param categoryName
     * @param categoryCode
     * @param categoryDescription
     */
    public Category(String categoryName, String categoryCode, String categoryDescription) {
        checkCategoryNameRules(categoryName);
        checkCategoryCodeRules(categoryCode);
        checkCategoryDescriptionRules(categoryDescription);
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
        this.categoryDescription = categoryDescription;
        this.categoryNhsId = null;
        this.parameterList = null;
    }

    /**
     * Category class constructor with four parameters (Category's name, code, description and NHS id)
     * @param categoryName
     * @param categoryCode
     * @param categoryDescription
     * @param categoryNhsId
     */
    public Category(String categoryName, String categoryCode, String categoryDescription, String categoryNhsId) {
        checkCategoryNameRules(categoryName);
        checkCategoryCodeRules(categoryCode);
        checkCategoryDescriptionRules(categoryDescription);
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
        this.categoryDescription = categoryDescription;
        this.categoryNhsId = categoryNhsId;
        this.parameterList = null;
    }

    /**
     * Getter for parameter category's description
     * @return parameter category's description
     */
    public String getCategoryDescription() {
        return categoryDescription;
    }

    /**
     * Method that checks parameter category's name's compliance with the acceptance criteria
     * @param categoryName
     */
    private void checkCategoryNameRules(String categoryName) {
        if (StringUtils.isBlank(categoryName))
            throw new IllegalArgumentException("Name cannot be blank.");
        if (categoryName.length() > 10)
            throw new IllegalArgumentException("Name must have less than 10 characters.");
    }

    /**
     * Method that checks parameter category's code's compliance with the acceptance criteria
     * @param categoryCode
     */
    private void checkCategoryCodeRules(String categoryCode) {
        if (StringUtils.isBlank(categoryCode))
            throw new IllegalArgumentException("Code cannot be blank.");
        if ( (categoryCode.length() < 4) || (categoryCode.length() > 8))
            throw new IllegalArgumentException("Code must have 5 characters.");
    }

    /**
     *
     * @param categoryDescription
     */
    private void checkCategoryDescriptionRules(String categoryDescription){
        if (StringUtils.isBlank(categoryDescription))
            throw new IllegalArgumentException("Description cannot be blank.");
        if (categoryDescription.length() > 40)
            throw new IllegalArgumentException("Description must have less than 40 chars.");
    }

    /**
     * Method that creates a new parameter for the parameter category
     * @param shortName
     * @param code
     * @param description
     * @return new parameter
     */
    public Parameter createNewParameter(String shortName, String code, String description){
        return new Parameter(shortName, code, description);
    }

    /**
     * Method that saves the parameter after it's validated
     * @param parameter
     * @return true or false
     */
    public boolean saveParameter(Parameter parameter){
        int cont = 0;
        if(parameterList != null) {
            Iterator<Parameter> iterator = parameterList.iterator();
            while (iterator.hasNext()) {
                Parameter newParameter = iterator.next();
                if (newParameter.getParameterName().equalsIgnoreCase(parameter.getParameterName()) || newParameter.getParameterCode().equalsIgnoreCase(parameter.getParameterCode()) || newParameter.getParameterDescription().equalsIgnoreCase(parameter.getParameterDescription())) {
                    cont++;
                }
            }
            if (cont < 1) {
                parameterList.add(parameter);
                return true;
            } else return false;
        }else return false;
    }

    /**
     * Getter for the parameter category's name
     * @return parameter category's name
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Getter for the parameter category's list of parameters
     * @return parameter list
     */
    public List<Parameter> getParameterList(){
        return parameterList;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public String getCategoryNhsId() {
        return categoryNhsId;
    }

    /**
     * Method that turn the parameter category's name, code and description into a single string
     * @return parameter category's name, code, description
     */
    @Override
    public String toString() {
        return "Category{" +
                "name: "+ categoryName + "; " +
                "code: " + categoryCode + "; " +
                "description: " + categoryDescription + "; " +
                "nhs id: " + categoryNhsId +
                '}';
    }

    /**
     *
     * @param o Another object
     * @return True if a category is equal to another category and false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(categoryName, category.categoryName) && Objects.equals(categoryCode, category.categoryCode) && Objects.equals(categoryDescription, category.categoryDescription) && Objects.equals(categoryNhsId, category.categoryNhsId) && Objects.equals(parameterList, category.parameterList);
    }

    public Parameter getParameterByName(String nameOfParameter){
        for (Parameter parameter : parameterList){
            if(parameter.getParameterName().equals(nameOfParameter)){
                return parameter;
            }
        }
        return null;
    }

}