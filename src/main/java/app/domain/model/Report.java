package app.domain.model;

import app.domain.model.Exceptions.InvalidTextReportException;


/**
 * @author 1200985 Tomás Cancela
 */

public class Report {

    /**
     * Report's diagnosis text
     */
    private String textDiagnosis;

    /**
     * Report's text
     */
    private String textReport;

    /**
     * Constructs a report with the diagnosis' text and the report's text
     *
     * @param textDiagnosis Report's diagnosis text
     * @param textReport    Report's text
     */
    public Report(String textDiagnosis, String textReport) {
        this.textDiagnosis = textDiagnosis;
        validateReport(textReport);
        this.textReport = textReport;
    }

    /**
     * Validates the report by checking if it has a maximum of 400 words
     * @param textReport Report's text
     * @return boolean
     */
    private boolean validateReport(String textReport) {
        String[] listaDePalavras = textReport.split(" ");
        if (listaDePalavras.length > 400) {
            throw new InvalidTextReportException();
        } else {
            return true;
        }
    }

}
