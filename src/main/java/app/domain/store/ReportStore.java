package app.domain.store;

import app.mappers.dto.ReportDTO;
import app.domain.model.Report;
import app.mappers.ReportMapper;

public class ReportStore {

    /**
     * Creates a report
     * @param reportDTO ReportDTO
     * @return Report
     */
    public Report createReport(ReportDTO reportDTO) {
        ReportMapper reportMapper = new ReportMapper();
        return reportMapper.toModel(reportDTO);
    }
}
