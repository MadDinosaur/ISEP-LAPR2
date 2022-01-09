package app.mappers;

import app.domain.model.Test;
import app.mappers.dto.TestDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestMapper {

    /**
     * List of tests
     */
    private List<Test> list;

    /**
     * Dto of the list of tests
     */
    private List<TestDTO> listDTO = new ArrayList<>();

    /**
     * Constructor of the TestMapper class with one parameter
     * @param list
     */
    public TestMapper(List<Test> list) {
        this.list = list;
    }

    public TestMapper(){}

    /**
     * Method that turns a test into a test dto
     * @param test
     * @return dto of a test
     */
    public TestDTO toDTO(Test test){
        TestDTO testDTO = new TestDTO(test.getClient(), test.getListOfCategories(), test.getTestCode(), test.getTestType().getCode(), test.getNhsCode(), test.getDateTimeRegister(), test.getDateTimeCollection(), test.getListOfParameters(), test.getTestParamList(), test.getSampleList(), test.getReport(), test.getDateTimeReport(), test.getDateTimeResults(), test.getDateTimeValidation());
        return testDTO;
    }

    /**
     * Method that turns a list of tests into a dto list of tests
     * @return dto list of tests
     */
    public List<TestDTO> toDtoList(){
        Iterator<Test> testIterator = list.iterator();
        while(testIterator.hasNext()){
            listDTO.add(toDTO(testIterator.next()));
        }
        return listDTO;
    }


    public List<TestDTO> toDtoListValidation() {
        return null;
    }
}
