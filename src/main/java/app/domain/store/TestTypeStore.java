package app.domain.store;

import app.domain.model.Category;
import app.domain.model.CollectionMethod;
import app.domain.model.Exceptions.InvalidTestType;
import app.domain.model.Parameter;
import app.domain.model.TestType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author 1200985 Tomás Cancela
 */
public class TestTypeStore {

    /**
     * List of test types
     */
    private CollectionMethod collectionMethodTest = new CollectionMethod("test Colection");
    Category categoryTest = new Category("Hemograma", "pistola", "WBC", "toma");
    Category covidCategory = new Category("Covid", "Covid", "descrição", "Covid");
    private List<Category> categoryList = new ArrayList<Category>(Collections.singleton(categoryTest));
    Parameter IgGAN = new Parameter("IgGAN", "IgGAN", "descrição");
    List<Category> covidCategoryList = new ArrayList<>(Collections.singleton(covidCategory));
    TestType Covid = new TestType("Covid", "descrição", collectionMethodTest, covidCategoryList);
    private ArrayList<TestType> testTypeList = new ArrayList<>(Collections.singleton(Covid));


    /**
     * Empty constructor
     */
    public TestTypeStore() {
        covidCategory.saveParameter(IgGAN);
    }

    /**
     * Constructs a Test Type Store with a test type list
     * @param testTypeList List of test types
     */
    public TestTypeStore(ArrayList<TestType> testTypeList) {

        this.testTypeList = testTypeList;
    }

    /**
     *
     * @return Creates a new test type
     */
    public TestType createTestType() {
        return new TestType();
    }

    /**
     *
     * @return Creates a new collection method
     */
    public CollectionMethod createCollectionMethod() {
        return new CollectionMethod();
    }

    /**
     *
     * @param testType A Test Type
     * @return Adds a test type to the test type list
     */
    public boolean addTestType(TestType testType) {
        if(!validateTestType(testType)) {
            throw new InvalidTestType();
        } else {
            return testTypeList.add(testType);
        }
    }

    /**
     *
     * @param testType Test Type
     * @return True if the test type list doesn't contain the respective test type and false otherwise
     */
    public boolean validateTestType(TestType testType) {
        if (testTypeList.contains(testType)) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * Test Type List getter
     * @return Test Type List
     */
    public ArrayList<TestType> getTestTypeList() {
        return testTypeList;
    }

    public TestType getTestTypeByCode(String code){

        for (TestType testType : testTypeList){
            if (testType.getCode().equals(code)){
                return testType;
            }
        }
        throw new InvalidTestType("There's no test type with such code: " + code);
    }

}
