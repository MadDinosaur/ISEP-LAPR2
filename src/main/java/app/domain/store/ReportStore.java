package app.domain.store;

import app.domain.model.Report;
import app.mappers.ReportMapper;
import app.mappers.dto.ReportDTO;

import java.io.Serializable;

public class ReportStore implements Serializable {

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
