package app.controller;

import app.domain.model.*;
import app.domain.store.TestParamList;
import app.domain.store.TestTypeStore;
import app.mappers.ParamMapper;
import app.mappers.TestCategoryMapper;
import app.mappers.TestTypeMapper;
import app.mappers.dto.ParamDTO;
import app.mappers.dto.TestCategoryDto;
import app.mappers.dto.TestTypeDto;

import java.util.ArrayList;
import java.util.List;

public class RegisterTestController {

    private ClinicalAnalysisLaboratory clinicalAnalysisLaboratory;
    private final Company company;
    private Client client;
    private TestTypeStore testTypeStore;
    private TestType testTypeChosen;
    private Category categoryOfTestType;
    private List<Category> categoriesOfTestType = new ArrayList<>();
    private List<Parameter> parametersOfCategory = new ArrayList<>();
    private Parameter parameterOfCategory;
    private TestType toSaveTestType;
    private Category toSaveCategory;
    private Parameter toSaveParameter;
    private List<Category> listOfChosenCategories = new ArrayList<>();
    private List<Parameter> listOfChosenParameters = new ArrayList<>();
    private TestParamList testParamList;


    public RegisterTestController() {
        this.company = App.getInstance().getCompany();
        this.testTypeStore = App.getInstance().getCompany().getTestTypeStore();
    }

    public void setClientByTIN(long tiNumber) {
        client = company.getClientStore().getClientByTINumber(tiNumber);
    }

    public Client getClient() {
        return client;
    }

    public TestType getToSaveTestType() {
        return toSaveTestType;
    }

    public List<TestTypeDto> testTypeList() {
        this.testTypeStore = company.getTestTypeStore();
        List<TestType> testTypes = testTypeStore.getTestTypeList();
        TestTypeMapper testTypeMapper = new TestTypeMapper();
        return testTypeMapper.toDto(testTypes);
    }

    public void setTestTypeByCode(String code) {
        testTypeChosen = testTypeStore.getTestTypeByCode(code);
    }

    public List<TestCategoryDto> getListOfTestCategories() {
        categoriesOfTestType = testTypeChosen.getCategories();
        TestCategoryMapper testCategoryMapper = new TestCategoryMapper();
        return testCategoryMapper.toDto(categoriesOfTestType);
    }

    public void setCategoryByName(String nameOfCategory) {
        categoryOfTestType = testTypeChosen.getCategoryFromTestTypeByName(nameOfCategory);
        listOfChosenCategories.add(categoryOfTestType);
    }

    public List<ParamDTO> getListOfTestParameters() {
        parametersOfCategory = categoryOfTestType.getParameterList();
        ParamMapper paramMapper = new ParamMapper();
        return paramMapper.toDto(parametersOfCategory);
    }

    public void setParameterByName(String nameOfParameter) {
        parameterOfCategory = categoryOfTestType.getParameterByName(nameOfParameter);
    }

    public void saveParametersAndCategories() {
        toSaveCategory = new Category(categoryOfTestType.getCategoryName(), categoryOfTestType.getCategoryCode(), categoryOfTestType.getCategoryDescription());
        boolean existsInListOfChosenCategories = false;
        for (Category category : listOfChosenCategories) {
            if (toSaveCategory.getCategoryName().equals(category.getCategoryName())) {
                toSaveCategory = category;
                existsInListOfChosenCategories = true;
                break;
            }
        }
        if (!existsInListOfChosenCategories) {
            listOfChosenCategories.add(toSaveCategory);
        }

        toSaveParameter = new Parameter(parameterOfCategory.getParameterName(), parameterOfCategory.getParameterCode(), parameterOfCategory.getParameterDescription());
        boolean existsInListOfChosenParameters = false;
        for (Parameter parameter : toSaveCategory.getParameterList()) {
            if (toSaveParameter.getParameterName().equals(parameter.getParameterName())) {
                toSaveParameter = parameter;
                existsInListOfChosenParameters = true;
                break;
            }
        }
        if (!existsInListOfChosenParameters) {
            toSaveCategory.saveParameter(toSaveParameter);
        }
    }

    public String[] createTest() {
        toSaveTestType = new TestType(testTypeChosen.getCode(), testTypeChosen.getDescription(), testTypeChosen.getCollectionMethod(), listOfChosenCategories);
        return company.createTestToClient(client, toSaveTestType);
    }

    public void setTestParameterByParameterCode(String parameterCode, String value) {
        for (Category category : listOfChosenCategories) {
            Parameter parameter = category.getParameterByCode(parameterCode);
            this.testParamList.addTestParameterResult(parameter, new TestParameterResult(value, "mg", null));
        }
    }

    public void setClientByNhsID(Long nhsID) {
        this.client = company.getClientStore().getClientByNhsID(nhsID);
    }

    public Test createTestFromCSV(String testCode, String nhsCode, String dateTimeRegister, String dateTimeResults, String dateTimeReport, String dateTimeValidation) {
        return new Test(this.clinicalAnalysisLaboratory, this.client, testCode, nhsCode, this.testTypeChosen, this.listOfChosenCategories, this.testParamList, dateTimeRegister, dateTimeResults, dateTimeReport, dateTimeValidation);
    }

    public void saveTest(Test test) {
        company.getTestStore().addTest(test);
    }

    public void setLabById(String laboratoryID) {
        this.company.getLabById(laboratoryID);
    }

}
