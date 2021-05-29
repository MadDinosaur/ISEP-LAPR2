package app.mappers;

import app.domain.store.SampleList;
import app.domain.store.TestParamList;
import app.mappers.dto.TestDTO;
import app.domain.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
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
        TestDTO testDTO = new TestDTO(test.getClient(), test.getListOfCategories(), test.getTestCode(), test.getNhsCode(), test.getDateOfCreation(), test.getListOfParameters(), test.getTestParamList(), test.getSampleList(), test.getReport(), test.getDateReport(), test.getTimeReport(), test.getDateResults(), test.getTimeResults(), test.getDateValidation(), test.getTimeValidation());
        return testDTO;
    }

    /**
     * Method that turns a list of tests into a dto list of tests
     * @return dto list of tests
     */
    public List<TestDTO> toDtoList(){
        Iterator<Test> testIterator = list.iterator();
        while(testIterator.hasNext()){
            listDTO.add(toDTO((Test) testIterator));
        }
        return listDTO;
    }

}
