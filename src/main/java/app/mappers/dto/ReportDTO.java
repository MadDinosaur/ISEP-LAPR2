package app.mappers.dto;

public class ReportDTO {

    /**
     * Report's diagnosis text
     */
    private String textDiagnosis;

    /**
     * Report's text
     */
    private String textReport;

    /**
     * Creates a report with a report's text and a diagnosis' text
     * @param textDiagnosis Diagnosis' text
     * @param textReport Report's text
     */
    public ReportDTO (String textDiagnosis, String textReport) {
        this.textDiagnosis = textDiagnosis;
        this.textReport = textReport;
    }

    /**
     * Getter for the diagnosis' text
     * @return Diagnosis Text
     */
    public String getTextDiagnosis() {
        return textDiagnosis;
    }

    /**
     * Getter for the report's text
     * @return Report Text
     */
    public String getTextReport() {
        return textReport;
    }
}
