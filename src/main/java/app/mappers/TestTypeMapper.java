package app.mappers;

import app.domain.model.TestType;
import app.mappers.dto.TestTypeDto;

import java.util.ArrayList;
import java.util.List;

public class TestTypeMapper {

    public TestTypeDto toDto(TestType testType){
        return new TestTypeDto(testType.getCode());
    }
    public List<TestTypeDto> toDto(List<TestType> testTypes){
        List<TestTypeDto> testTypeDtos = new ArrayList<>();

        for (TestType testType : testTypes){
            testTypeDtos.add(this.toDto(testType));
        }
        return testTypeDtos;
    }

}
