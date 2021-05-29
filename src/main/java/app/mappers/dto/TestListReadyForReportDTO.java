package app.mappers.dto;

import java.util.ArrayList;
import java.util.List;

public class TestListReadyForReportDTO {

    private List<TestDTO> testListReadyForReportDTO = new ArrayList<>();

    public void add(TestDTO testDTO) {
        this.testListReadyForReportDTO.add(testDTO);
    }

    public List<TestDTO> getTestListReadyForReportDTO() {
        return testListReadyForReportDTO;
    }
}
