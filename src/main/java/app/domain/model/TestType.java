package app.domain.model;

import app.domain.adapter.ExternalModule;
import app.domain.model.Exceptions.InvalidCategoryException;
import app.domain.model.Exceptions.InvalidCodeException;
import app.domain.model.Exceptions.InvalidDescriptionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import app.domain.model.Exceptions.UnassignedExternalModuleException;
import org.apache.commons.lang3.StringUtils;


/**
 * @author 1200985 Tomás Cancela
 */
public class TestType {


    /**
     * Test Type's code
     */
    private String code;

    /**
     * Test type's description
     */
    private String description;

    /**
     * The collection method associated to the test type
     */
    private CollectionMethod collectionMethod;

    /**
     * The list of categories associated to the test type
     */
    private List<Category> categories = new ArrayList<>();


    /**
     * Constructs a test type with only a code
     *
     * @param code Test Type's code
     */
    public TestType(String code) {
        this.code = code;
    }


    /**
     * Constructs a test type with all of its parameters (code, description, collection method, categories)
     *
     * @param code             Test Type's code
     * @param description      Test Type's description
     * @param collectionMethod Test Type's Collection Method
     * @param categories       Test Type's categories
     */
    public TestType(String code, String description, CollectionMethod collectionMethod, List<Category> categories) {
        this.code = code;
        this.description = description;
        this.collectionMethod = collectionMethod;
        this.categories = categories;
    }

    /**
     * Empty constructor
     */
    public TestType() {

    }

    /**
     * Code setter
     *
     * @param code Five Alphanumeric Characters
     */
    public void setCode(String code) {
        if (!validateCode(code)) {
            throw new InvalidCodeException();
        } else {
            this.code = code;
        }
    }

    /**
     * Collection Method setter
     *
     * @param collectionMethod 20 or less characters
     */
    public void setCollectionMethod(CollectionMethod collectionMethod) {
        this.collectionMethod = collectionMethod;
    }


    /**
     * Category setter
     *
     * @param category Category from the categories list
     */
    public void setCategory(Category category) {
        if (!validateCategories(category)) {
            throw new InvalidCategoryException();
        } else {
            this.categories.add(category);
        }
    }

    /**
     * Code getter
     *
     * @return Code
     */
    public String getCode() {
        return code;
    }

    /**
     * Collection Method getter
     *
     * @return collectionMethod
     */
    public CollectionMethod getCollectionMethod() {
        return collectionMethod;
    }

    /**
     * Category getter
     *
     * @return categories
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * @param o Another object
     * @return True if a Test Type has the same code, description, collection method and categories as another Test Type
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestType testType = (TestType) o;
        return Objects.equals(code, testType.code) && Objects.equals(description, testType.description) && Objects.equals(collectionMethod, testType.collectionMethod) && Objects.equals(categories, testType.categories);
    }

    /**
     * Checks if the code is valid (if it's in accordance with the acceptance criteria given)
     *
     * @param code Test Type's code
     * @return True if it's valid and false if it's invalid
     */
    public boolean validateCode(String code) {
        return !(code.equals("") || !StringUtils.isAlphanumeric(code) || code.length() != 5);
    }

    /**
     * Description getter
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }


    /**
     * Description setter
     *
     * @param description 15 or less characters
     */
    public void setDescription(String description) {
        if (!validateDescription(description)) {
            throw new InvalidDescriptionException();
        } else {
            this.description = description;
        }
    }

    /**
     * Checks if the description is invalid (If it is empty or if it is bigger than 15 characters)
     *
     * @param description Test Type's description
     * @return True if it's valid and false if it's invalid
     */
    public boolean validateDescription(String description) {
        if (description.equals("") || description.length() > 15) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * Checks if the categories are invalid (If the category exists in the test type's category list already)
     *
     * @param category Test Type's category
     * @return True if it's valid and false if it's invalid
     */
    public boolean validateCategories(Category category) {
        if (this.categories.contains(category)) {
            return false;
        } else {
            return true;
        }
    }

    public ExternalModule getExternalModule() {
        Class<?> oClass = null;

        try {
            switch (code) {
                case "Covid":
                    oClass = Class.forName("app.domain.adapter.ExternalModuleAdapter1");
                    break;
                case "Blood":
                    oClass = Class.forName("app.domain.adapter.ExternalModuleAdapter2");
                    break;
            }
            return (ExternalModule) oClass.newInstance();

        } catch (ClassNotFoundException ex) {
            throw new UnassignedExternalModuleException();
        } catch (Exception ex) {
            throw new UnassignedExternalModuleException("Cannot access external validation module!");
        }
    }

    public List<Parameter> getParameters() {
        List<Parameter> parameterList = new ArrayList<>();
        for(Category cat : categories) {
            parameterList.addAll(cat.getParameterList());
        }
        return parameterList;
    }

    public Category getCategoryFromTestTypeByName(String nameOfCategory){
        for (Category category : categories){
            if(category.getCategoryName().equals(nameOfCategory)){
                return category;
            }
        }
        return null;
    }
}
