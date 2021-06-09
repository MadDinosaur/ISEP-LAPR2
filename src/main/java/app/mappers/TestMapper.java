package app.mappers;

import app.mappers.dto.TestDTO;
import app.domain.model.*;

import java.util.*;

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

    /**
     * Method that turns a test into a test dto
     * @param test
     * @return dto of a test
     */
    public TestDTO toDTO(Test test){
        TestDTO testDTO = new TestDTO(test.getClient(), test.getListOfCategories(), test.getTestCode(), test.getNhsCode(), test.getDateTimeRegister(), test.getListOfParameters(), test.getTestParamList(), test.getSampleList(), test.getReport(), test.getDateTimeReport(), test.getDateTimeResults(), test.getDateTimeValidation());
        return testDTO;
    }

    /**
     * Method that turns a test into a test dto for validation
     * @param nhsCode
     * @param dateTimeRegister
     * @param dateTimeReport
     * @param dateTimeResults
     * @return dto of a test with the dates
     */
    public TestDTO toDTOValidation(String nhsCode, String dateTimeRegister, String dateTimeReport, String dateTimeResults){
        TestDTO testDTO = new TestDTO(nhsCode, dateTimeRegister, dateTimeReport, dateTimeResults);
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

    public List<TestDTO> toDtoListValidation(){
        try {
            Iterator<Test> testIterator = list.iterator();
            while(testIterator.hasNext()){
                this.listDTO.add(toDTOValidation(testIterator.next().getNhsCode(), testIterator.next().getDateTimeRegister(), testIterator.next().getDateTimeReport(), testIterator.next().getDateTimeResults()));
            }
        }catch (NoSuchElementException e){
            System.out.println("No such element");
        }
        return listDTO;
    }

}
