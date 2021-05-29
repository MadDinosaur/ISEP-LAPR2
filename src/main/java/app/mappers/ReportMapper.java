package app.mappers;

import app.mappers.dto.ReportDTO;
import app.domain.model.Report;

public class ReportMapper {

    /**
     * Report's diagnosis text
     */
    private String textDiagnosis;

    /**
     * Report's text
     */
    private String textReport;

    /**
     * Transforms the Data Transfer Object into an object
     * @param reportDTO ReportDTO
     * @return Report
     */
    public Report toModel(ReportDTO reportDTO) {
        this.textDiagnosis = reportDTO.getTextDiagnosis();
        this.textReport = reportDTO.getTextReport();
        return new Report(textReport, textDiagnosis);
    }
}
