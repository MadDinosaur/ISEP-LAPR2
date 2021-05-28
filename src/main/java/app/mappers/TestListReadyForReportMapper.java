package app.mappers;

import app.domain.model.Test;
import app.mappers.dto.TestDTO;
import app.mappers.dto.TestListReadyForReportDTO;

import java.util.List;

public class TestListReadyForReportMapper {

    private TestMapper testMapper = new TestMapper();

    public TestListReadyForReportDTO toDTO(List<Test> testListReadyForReport) {
        TestListReadyForReportDTO testListReadyForReportDTO = new TestListReadyForReportDTO();
        for (int i = 0; i < testListReadyForReport.size(); i++) {
            Test testReadyForReport = testListReadyForReport.get(i);
            TestDTO testReadyForReportDTO = testMapper.toDTO(testReadyForReport);
            testListReadyForReportDTO.add(testReadyForReportDTO);
        }
        return testListReadyForReportDTO;
    }
}
