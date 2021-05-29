package app.controller;

import app.domain.model.*;
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

    private final Company company;
    private Client client;
    private TestTypeStore testTypeStore;
    private TestType testTypeChosen;
    private Category categoryOfTestType;
    private List<Category> categoriesOfTestType = new ArrayList<>();
    private List<Parameter> parametersOfCategory= new ArrayList<>();
    private Parameter parameterOfCategory;
    private Category toSaveCategory;
    private Parameter toSaveParameter;
    private List<Category> listOfChosenCategories = new ArrayList<>();
    private List<Parameter> listOfChosenParameters = new ArrayList<>();
    private Test testToRegister;


    public RegisterTestController(){
        this.company = App.getInstance().getCompany();
        this.testTypeStore = App.getInstance().getCompany().getTestTypeStore();
    }

    public void setClientByCardNumber(long cardNumber){
        client = company.getClientStore().getClientByCardNumber(cardNumber);
    }

    public List<TestTypeDto> testTypeList(){
        this.testTypeStore = company.getTestTypeStore();
        List<TestType> testTypes = testTypeStore.getTestTypeList();
        TestTypeMapper testTypeMapper = new TestTypeMapper();
        return testTypeMapper.toDto(testTypes);
    }

    public void setTestTypeByCode(String code){
         testTypeChosen = testTypeStore.getTestTypeByCode(code);
    }

    public List<TestCategoryDto> getListOfTestCategories(){
        categoriesOfTestType = testTypeChosen.getCategories();
        TestCategoryMapper testCategoryMapper = new TestCategoryMapper();
        return testCategoryMapper.toDto(categoriesOfTestType);
    }

    public void setCategoryByName(String nameOfCategory){
        categoryOfTestType = testTypeChosen.getCategoryFromTestTypeByName(nameOfCategory);
    }

    public List<ParamDTO> getListOfTestParameters(){
        parametersOfCategory = categoryOfTestType.getParameterList();
        ParamMapper paramMapper = new ParamMapper();
        return paramMapper.toDTO(parametersOfCategory);
    }
    public void setParameterByName(String nameOfParameter){
        parameterOfCategory = categoryOfTestType.getParameterByName(nameOfParameter);
    }

    public void saveParametersAndCategories(){
        toSaveCategory = new Category(categoryOfTestType.getCategoryName(),categoryOfTestType.getCategoryCode(),categoryOfTestType.getCategoryDescription());
        boolean existsInListOfChosenCategories = false;
        for (Category category:listOfChosenCategories){
            if (toSaveCategory.getCategoryName().equals(category.getCategoryName())) {
                toSaveCategory = category;
                existsInListOfChosenCategories = true;
                break;
            }
        }
        if (!existsInListOfChosenCategories){
            listOfChosenCategories.add(toSaveCategory);
        }

        toSaveParameter = new Parameter(parameterOfCategory.getParameterName(),parameterOfCategory.getParameterCode(),parameterOfCategory.getParameterDescription());
        boolean existsInListOfChosenParameters = false;
        for (Parameter parameter : toSaveCategory.getParameterList()){
            if (toSaveParameter.getParameterName().equals(parameter.getParameterName())) {
                toSaveParameter = parameter;
                existsInListOfChosenParameters = true;
                break;
            }
        }
        if (!existsInListOfChosenParameters){
            toSaveCategory.saveParameter(toSaveParameter);
        }
    }

    public void createTest(){
        company.createTestToClient(client,listOfChosenCategories);

    }


}
