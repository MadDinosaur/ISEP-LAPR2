package app.domain.model;


import app.domain.store.TestParamStore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
    private Client client;
    private List<Category> listOfCategories;
    private String testCode;
    private String nhsCode;
    private Date dateOfCreation;
    private List<Parameter> listOfParameters = new ArrayList<>();
    private TestParamStore testParamStore;
    private int stateOfTest;

    private enum StateOfTest{
        REGISTERED,
        SAMPLES_COLLECTED,
        SAMPLES_ANALYZED,
        DIAGNOSIS_MADE,
        VALIDATED
    }

    public Test(Client client, List<Category> listOfCategories,String testCode, String nhsCode){
        setClient(client);
        setListOfCategories(listOfCategories);
        setTestCode(testCode);
        setNhsCode(nhsCode);
        getParametersFromCategoriesToStore();
    }

    public void setClient(Client client) {
        this.client = client;
    }
    public void setListOfCategories(List<Category> listOfCategories) {
        this.listOfCategories = listOfCategories;
    }
    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }
    public void setNhsCode(String nhsCode) {
        this.nhsCode = nhsCode;
    }

    public TestParamStore getTestParamStore() {
        return testParamStore;
    }

    private void getParametersFromCategoriesToStore(){
        for(int i = 0; i<this.listOfCategories.size();i++){
            Category category = listOfCategories.get(i);
            List<Parameter> listOfParametersOfCategory = category.getParameterList();
            for (int j = 0; j<listOfParametersOfCategory.size(); j++) {
                Parameter parameter = listOfParametersOfCategory.get(i);
                listOfParameters.add(parameter);
                //a testParamStore não tem metodo de add para a store ainda
            }
        }
    }

}

