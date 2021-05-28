package app.mappers.dto;

public class ReportDTO {

    private String textDiagnosis;
    private String textReport;

    public ReportDTO (String textDiagnosis, String textReport) {
        this.textDiagnosis = textDiagnosis;
        this.textReport = textReport;
    }

    public String getTextDiagnosis() {
        return textDiagnosis;
    }

    public String getTextReport() {
        return textReport;
    }
}
