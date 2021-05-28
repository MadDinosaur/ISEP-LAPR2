package app.mappers;

import app.mappers.dto.ReportDTO;
import app.domain.model.Report;

public class ReportMapper {

    private String textDiagnosis;
    private String textReport;

    public Report toModel(ReportDTO reportDTO) {
        this.textDiagnosis = reportDTO.getTextDiagnosis();
        this.textReport = reportDTO.getTextReport();
        return new Report(textReport, textDiagnosis);
    }
}
