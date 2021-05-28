package app.mappers;

import app.mappers.dto.TestDTO;
import app.domain.model.*;
import java.util.*;

public class TestMapper {

    private List<Test> list;

    private List<TestDTO> listDTO;

    public TestMapper(List<Test> list) {
    this.list = list;
    }

    public TestMapper() {
        this.list = null;
    }

    public TestDTO toDTO(Test test){
        TestDTO testDTO = null;

        return testDTO;
    }

    public List<TestDTO> toDtoList(List<Test> list){
        this.list = list;
        Iterator<Test> testIterator = list.iterator();

        return listDTO;
    }

}
