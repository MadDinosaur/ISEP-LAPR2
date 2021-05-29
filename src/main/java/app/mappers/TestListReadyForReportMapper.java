package app.mappers;

import app.domain.model.Test;
import app.mappers.dto.TestDTO;
import app.mappers.dto.TestListReadyForReportDTO;

import java.util.List;

/**
 * @author Tomás Cancela <1200985@isep.ipp.pt>
 */
public class TestListReadyForReportMapper {

    /**
     * Creation of a Test Mapper object
     */
    private TestMapper testMapper;

    /**
     * Transforms the list of tests ready for report into a Data Transfer Object
     * @param testListReadyForReport List of tests ready for report
     * @return TestListReadyForReportDTO
     */
    public TestListReadyForReportDTO toDTO(List<Test> testListReadyForReport) {
        testMapper = new TestMapper(testListReadyForReport);
        TestListReadyForReportDTO testListReadyForReportDTO = new TestListReadyForReportDTO();
        for (int i = 0; i < testListReadyForReport.size(); i++) {
            Test testReadyForReport = testListReadyForReport.get(i);
            TestDTO testReadyForReportDTO = testMapper.toDTO(testReadyForReport);
            testListReadyForReportDTO.add(testReadyForReportDTO);
        }
        return testListReadyForReportDTO;
    }
}
