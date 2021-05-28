package app.mappers;

import app.mappers.dto.TestDTO;
import app.domain.model.*;
import java.util.*;

public class TestMapper {

    private List<Test> list;

    public TestMapper(List<Test> list) {
    this.list = list;
    }

    public TestMapper() {
        this.list = null;
    }

    public TestDTO toDTO(Test test){
        TestDTO testDTO;
        testDTO.setClient() = test.get;
    }

    public List<TestDTO> toDtoList(List<Test> list){
        this.list = list;
        Iterator<Test> testIterator = list.iterator();

    }

    public List<TestDTO> toDtoList(){
        
    }
}
